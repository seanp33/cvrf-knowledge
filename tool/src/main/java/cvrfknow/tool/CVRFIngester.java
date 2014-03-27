package cvrfknow.tool;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;

import mvm.rya.accumulo.AccumuloRyaDAO;
import mvm.rya.api.persist.RyaDAO;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.icasi.cvrf.schema.cvrf.Cvrfdoc;
import org.icasi.cvrf.schema.vuln.Vulnerability;

import cvrfknow.model.EntityGraphAssembler;
import gem.domain.Attribute;
import gem.domain.Entity;
import gem.domain.Relationship;
import gem.index.accumulo.AccumuloIndexStore;
import gem.index.rya.SlidingWindowEntityIndexer;
import gem.rya.store.service.impl.RyaEntityService;
import gem.service.EntityService;
import gem.service.IndexEntityService;
import gem.service.impl.MockEntityMutationHook;

public class CVRFIngester {

    private static final String PARAM_CVRF_FILE = "f";
    private static final String PARAM_ZOOKEEPERS = "zk";
    private static final String PARAM_INSTANCE = "inst";
    private static final String PARAM_USERNAME = "u";
    private static final String PARAM_PASSWORD = "p";
    private static final String DEFAULT_ZOOKEEPERS = "localhost";
    private static final String DEFAULT_INSTANCE = "accumulo";
    private static final String DEFAULT_USERNAME = "gem";
    private static final String DEFAULT_PASSWORD = "gem";

    protected EntityService baseEntityService;
    protected IndexEntityService indexEntityService;
    protected RyaDAO ryaDAO;
    protected AccumuloIndexStore accumuloIndexStore;
    protected SlidingWindowEntityIndexer slidingWindowEntityIndexer;

    public CVRFIngester(String[] args) {
        try {
            final CommandLine commandLine = new PosixParser().parse(getOptions(), args);
            final String file = commandLine.getOptionValue(PARAM_CVRF_FILE);
            final String zooKeepers = commandLine.hasOption(PARAM_ZOOKEEPERS) ? commandLine.getOptionValue(PARAM_ZOOKEEPERS) : DEFAULT_ZOOKEEPERS;
            final String instanceName = commandLine.hasOption(PARAM_INSTANCE) ? commandLine.getOptionValue(PARAM_INSTANCE) : DEFAULT_INSTANCE;
            final String username = commandLine.hasOption(PARAM_USERNAME) ? commandLine.getOptionValue(PARAM_USERNAME) : DEFAULT_USERNAME;
            final String password = commandLine.hasOption(PARAM_PASSWORD) ? commandLine.getOptionValue(PARAM_PASSWORD) : DEFAULT_PASSWORD;

            init(username, password, instanceName, zooKeepers);
            ingest(file);
        } catch (Exception e) {
            printUsage();
        }
    }

    protected void init(String username, String password, String instanceName, String zooKeepers) throws Exception {
        ZooKeeperInstance instance = new ZooKeeperInstance(instanceName, zooKeepers);
        Connector connector = instance.getConnector(username, password.getBytes());
        AccumuloRyaDAO dao = new AccumuloRyaDAO();
        dao.setConnector(connector);
        dao.init();
        ryaDAO = dao;

        baseEntityService = new RyaEntityService(dao, new MockEntityMutationHook());
        accumuloIndexStore = new AccumuloIndexStore(connector);
        slidingWindowEntityIndexer = new SlidingWindowEntityIndexer(accumuloIndexStore);
        indexEntityService = new IndexEntityService(baseEntityService, slidingWindowEntityIndexer);
    }

    protected void ingest(String filename) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Cvrfdoc.class);
        Cvrfdoc doc = (Cvrfdoc) context.createUnmarshaller().unmarshal(new FileInputStream(filename));
        int total = doc.getVulnerability().size();
        System.out.println("Ingesting " + total + " vulnerabilities");
        int count = 0;
        for (Vulnerability v : doc.getVulnerability()) {
            List<Entity> entities = EntityGraphAssembler.assemble(v);
            negateValues(entities);
            System.out.print("\rIngesting Vulnerability entity graph [" + (++count) + " of " + total + "] (" + entities.size() + ") entities");
            indexEntityService.saveObjects(entities.iterator(), false);
        }
    }

    private void negateValues(List<Entity> entities){
        for(Entity e : entities){
            Collection<Set<Attribute>> attributes = e.getAttributes().values();
            for(Set<Attribute> attributeSet : attributes){
                for(Attribute attr : attributeSet){
                    attr.setValue("an attribute");
                }
            }

            Collection<Set<Relationship>> relationships = e.getRelationships().values();
            for(Set<Relationship> relationshipSet : relationships){
                for(Relationship rel : relationshipSet){
                    rel.setValue("a relationship");
                }
            }
        }
    }

    public static void main(String[] args) {
        new CVRFIngester(args);
    }

    private static Options getOptions() {

        return new Options()
                .addOption(
                        OptionBuilder.withDescription("The source cvrl file to ingest, conforming to http://www.icasi.org/CVRF/schema/cvrf/1.1.").
                                hasArg().
                                isRequired().
                                withArgName("CVRL").
                                withLongOpt("cvrl-file").
                                create(PARAM_CVRF_FILE))
                .addOption(
                        OptionBuilder.withDescription("Comma separated list of zookeepers.").
                                hasArg().
                                withArgName("ZOOKEEPERS").
                                withLongOpt("zookeepers").
                                create(PARAM_ZOOKEEPERS))
                .addOption(
                        OptionBuilder.withDescription("Name of your cloudbase instance.").
                                hasArg().
                                withArgName("INSTANCE").
                                withLongOpt("cloudbase-instance").
                                create(PARAM_INSTANCE))
                .addOption(
                        OptionBuilder.withDescription("Username to use when connecting to cloudbase.").hasArg().withArgName("USER")
                                .withLongOpt("cloudbase-username").create(PARAM_USERNAME))
                .addOption(
                        OptionBuilder.withDescription("Password to use when connecting to cloudbase.").hasArg().withArgName("PASSWORD")
                                .withLongOpt("cloudbase-password").create(PARAM_PASSWORD));

    }

    private static void printUsage() {
        new HelpFormatter().printHelp("java -cp {JAR} cvrfknow.tool.CVRFIngester {OPTIONS} ", getOptions());
        System.exit(1);
    }
}
