package cvrfknow.model;

import gem.domain.Attribute;
import gem.domain.Entity;
import gem.domain.Relationship;
import org.icasi.cvrf.schema.common.LocalizedString;
import org.icasi.cvrf.schema.vuln.Vulnerability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static cvrfknow.model.Constants.*;

public class EntityGraphAssembler {

    public static List<Entity> assemble(Vulnerability v) {
        List<Entity> entities = new ArrayList<Entity>();
        Entity root = assembleVulnerabilityEntity(v);

        if (v.getCWE() != null) {
            safeAttributes(CWE, v.getCWE(), U, root);
        }

        if (v.getAcknowledgments() != null) {
            entities.addAll(assembleAcknowledgments(v.getAcknowledgments().getAcknowledgment(), root));
        }

        if (v.getCVSSScoreSets() != null) {
            entities.addAll(assembleCVSSScoreSets(v.getCVSSScoreSets().getScoreSet(), root));
        }

        if (v.getInvolvements() != null) {
            entities.addAll(assembleInvolvements(v.getInvolvements().getInvolvement(), root));
        }

        if (v.getNotes() != null) {
            entities.addAll(assembleNotes(v.getNotes().getNote(), root));
        }

        if (v.getProductStatuses() != null) {
            entities.addAll(assembleProductStatuses(v.getProductStatuses().getStatus(), root));
        }

        if (v.getThreats() != null) {
            entities.addAll(assembleThreats(v.getThreats().getThreat(), root));
        }

        if (v.getRemediations() != null) {
            entities.addAll(assembleRemediations(v.getRemediations().getRemediation(), root));
        }

        if (v.getReferences() != null) {
            entities.addAll(assembleReferences(v.getReferences().getReference(), root));
        }

        entities.add(root);
        return entities;
    }

    public static Entity assembleVulnerabilityEntity(Vulnerability v) {
        Entity entity = new Entity(v.getCVE(), getClassType(v));
        safeAttribute(TITLE, v.getTitle(), Constants.U, entity);
        safeAttribute(DISCOVERY_DATE, v.getDiscoveryDate(), U, entity);
        safeAttribute(RELEASE_DATE, v.getReleaseDate(), U, entity);
        safeAttribute(ORDINAL, v.getOrdinal(), U, entity);
        return entity;
    }

    public static List<Entity> assembleCVSSScoreSets(List<Vulnerability.CVSSScoreSets.ScoreSet> scoreSets, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.CVSSScoreSets.ScoreSet scoreSet : scoreSets) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(scoreSet));
            safeAttribute(BASE_SCORE, scoreSet.getBaseScore(), U, e);
            safeAttribute(ENVIRONMENTAL_SCORE, scoreSet.getEnvironmentalScore(), U, e);
            safeAttribute(TEMPORAL_SCORE, scoreSet.getTemporalScore(), U, e);
            safeAttribute(VECTOR, scoreSet.getVector(), U, e);
            safeAttributes(PRODUCT_ID, scoreSet.getProductID(), U, e);
            safeRelationship(SCORE_SET, e, U, root);

            entities.add(e);
        }

        return entities;
    }

    public static List<Entity> assembleAcknowledgments(List<Vulnerability.Acknowledgments.Acknowledgment> acks, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.Acknowledgments.Acknowledgment ack : acks) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(ack));
            safeAttribute(DESCRIPTION, ack.getDescription().getValue(), U, e);
            safeAttributes(NAME, ack.getName(), U, e);
            safeAttributes(ORGANIZATION, ack.getOrganization(), U, e);
            safeAttributes(URL, ack.getURL(), U, e);
            safeRelationship(ACKNOWLEDGMENT, e, U, root);

            entities.add(e);
        }

        return entities;
    }

    public static List<Entity> assembleInvolvements(List<Vulnerability.Involvements.Involvement> involvements, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.Involvements.Involvement inv : involvements) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(inv));
            safeAttribute(DESCRIPTION, inv.getDescription().getValue(), U, e);
            safeAttribute(PARTY, inv.getParty().value(), U, e);
            safeAttribute(STATUS, inv.getStatus().value(), U, e);
            safeRelationship(INVOLVEMENT, e, U, root);
            entities.add(e);
        }

        return entities;
    }

    public static List<Entity> assembleNotes(List<Vulnerability.Notes.Note> notes, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.Notes.Note note : notes) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(note));
            safeAttribute(AUDIENCE, note.getAudience(), U, e);
            safeAttribute(VALUE, note.getValue(), U, e);
            safeAttribute(ORDINAL, note.getOrdinal(), U, e);
            safeAttribute(TITLE, note.getTitle(), U, e);
            safeAttribute(LANG, note.getLang(), U, e);
            safeRelationship(NOTE, e, U, root);

            entities.add(e);
        }

        return entities;
    }

    public static List<Entity> assembleProductStatuses(List<Vulnerability.ProductStatuses.Status> stats, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.ProductStatuses.Status stat : stats) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(stat));
            safeAttribute(TYPE, stat.getType(), U, e);
            safeAttributes(PRODUCT_ID, stat.getProductID(), U, e);
            entities.add(e);
        }
        return entities;
    }

    public static List<Entity> assembleThreats(List<Vulnerability.Threats.Threat> threats, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.Threats.Threat threat : threats) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(threat));
            safeAttribute(DATE, threat.getDate(), U, e);
            safeAttribute(DESCRIPTION, threat.getDescription().getValue(), U, e);
            safeAttribute(TYPE, threat.getType().value(), U, e);
            safeAttributes(GROUP_ID, threat.getGroupID(), U, e);
            safeAttributes(PRODUCT_ID, threat.getProductID(), U, e);
            safeRelationship(THREAT, e, U, root);
            entities.add(e);
        }
        return entities;
    }

    public static List<Entity> assembleRemediations(List<Vulnerability.Remediations.Remediation> rems, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.Remediations.Remediation rem : rems) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(rem));
            safeAttribute(DATE, rem.getDate(), U, e);
            safeAttribute(DESCRIPTION, rem.getDescription().getValue(), U, e);
            safeAttribute(TYPE, rem.getType(), U, e);
            safeAttribute(URL, rem.getURL(), U, e);
            safeAttributes(GROUP_ID, rem.getGroupID(), U, e);
            safeAttribute(PRODUCT_ID, rem.getProductID(), U, e);
            safeAttributes(ENTITLEMENTS, rem.getEntitlement(), U, e);
            safeRelationship(REMEDIATION, e, U, root);

            entities.add(e);
        }
        return entities;
    }

    public static List<Entity> assembleReferences(List<Vulnerability.References.Reference> refs, Entity root) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Vulnerability.References.Reference ref : refs) {
            Entity e = new Entity(UUID.randomUUID().toString(), getClassType(ref));
            safeAttribute(DESCRIPTION, ref.getDescription(), U, e);
            safeAttribute(URL, ref.getURL(), U, e);
            safeAttribute(TYPE, ref.getType(), U, e);
            safeRelationship(REMEDIATION, e, U, root);
            entities.add(e);
        }
        return entities;
    }

    public static String getClassType(Object o) {
        return o.getClass().getCanonicalName();
    }

    public static void safeAttributes(String name, List values, String vis, Entity e) {
        for (Object value : values) {
            safeAttribute(name, value, vis, e);
        }
    }

    public static void safeAttribute(String name, Object value, String vis, Entity e) {
        if (value != null) {
            if (value instanceof LocalizedString) {
                e.addAttribute(new Attribute(name, ((LocalizedString) value).getValue(), vis));
            } else if (value.getClass().isEnum()) {
                e.addAttribute(new Attribute(name, ((Enum) value).name(), vis));
            } else {
                e.addAttribute(new Attribute(name, value, vis));
            }
        }
    }

    public static void safeRelationship(String name, Entity target, String vis, Entity e) {
        if (target != null) {
            e.addRelationship(new Relationship(name, target.getId(), target.getType(), vis));
        }
    }

}

