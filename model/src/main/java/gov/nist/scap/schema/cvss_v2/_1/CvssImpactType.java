//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.15 at 11:01:26 PM EDT 
//


package gov.nist.scap.schema.cvss_v2._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cvssImpactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cvssImpactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://scap.nist.gov/schema/cvss-v2/1.0}cvssType">
 *       &lt;sequence>
 *         &lt;element name="base_metrics" type="{http://scap.nist.gov/schema/cvss-v2/1.0}baseMetricsType"/>
 *         &lt;element name="environmental_metrics" type="{http://scap.nist.gov/schema/cvss-v2/1.0}environmentalMetricsType" minOccurs="0"/>
 *         &lt;element name="temporal_metrics" type="{http://scap.nist.gov/schema/cvss-v2/1.0}temporalMetricsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cvssImpactType")
public class CvssImpactType
    extends CvssType
{


}
