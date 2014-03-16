//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.15 at 11:01:26 PM EDT 
//


package org.icasi.cvrf.schema.vuln._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemedyTypeEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RemedyTypeEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Workaround"/>
 *     &lt;enumeration value="Mitigation"/>
 *     &lt;enumeration value="Vendor Fix"/>
 *     &lt;enumeration value="None Available"/>
 *     &lt;enumeration value="Will Not Fix"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RemedyTypeEnumType")
@XmlEnum
public enum RemedyTypeEnumType {


    /**
     * Workaround contains information about a configuration or specific deployment scenario that can be used to avoid exposure to the vulnerability.
     * 
     */
    @XmlEnumValue("Workaround")
    WORKAROUND("Workaround"),

    /**
     * Mitigation contains information about a configuration or deployment scenario that helps to reduce the risk of the vulnerability but that does not resolve the vulnerability on the affected product.
     * 
     */
    @XmlEnumValue("Mitigation")
    MITIGATION("Mitigation"),

    /**
     * Vendor Fix contains information about an official fix that is issued by the original author of the affected product.
     * 
     */
    @XmlEnumValue("Vendor Fix")
    VENDOR_FIX("Vendor Fix"),

    /**
     * Currently there is no fix available.
     * 
     */
    @XmlEnumValue("None Available")
    NONE_AVAILABLE("None Available"),

    /**
     * There is no fix for the vulnerability and there never will be one.
     * 
     */
    @XmlEnumValue("Will Not Fix")
    WILL_NOT_FIX("Will Not Fix");
    private final String value;

    RemedyTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RemedyTypeEnumType fromValue(String v) {
        for (RemedyTypeEnumType c: RemedyTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}