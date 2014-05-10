package cvrfknow.store.index;

import gem.domain.Entity;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class DefaultEntityIndexer implements Indexer<Entity> {

    private IndexWriter indexWriter;

    public DefaultEntityIndexer() throws IOException {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_48, analyzer);
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        conf.setRAMBufferSizeMB(256.0); // gotta set -Xmx512m or -Xmx1g
        Directory dir = FSDirectory.open(new File("/tmp/cvrf-index"));
        indexWriter = new IndexWriter(dir, conf);
    }

    @Override
    public void index(Entity entity) {
        Document eDoc = new Document();

    }
}
