//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.15 at 11:01:26 PM EDT 
//


package org.icasi.cvrf.schema.prod._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BranchType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BranchType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.icasi.org/CVRF/schema/prod/1.1}FullProductName"/>
 *         &lt;element name="Branch" type="{http://www.icasi.org/CVRF/schema/prod/1.1}BranchType" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *       &lt;attribute name="Type" use="required" type="{http://www.icasi.org/CVRF/schema/prod/1.1}BranchTypeEnumType" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BranchType", propOrder = {
    "fullProductName",
    "branch"
})
public class BranchType {

    @XmlElement(name = "FullProductName")
    protected FullProductName fullProductName;
    @XmlElement(name = "Branch")
    protected List<BranchType> branch;
    @XmlAttribute(name = "Type", required = true)
    protected BranchTypeEnumType type;
    @XmlAttribute(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the fullProductName property.
     * 
     * @return
     *     possible object is
     *     {@link FullProductName }
     *     
     */
    public FullProductName getFullProductName() {
        return fullProductName;
    }

    /**
     * Sets the value of the fullProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullProductName }
     *     
     */
    public void setFullProductName(FullProductName value) {
        this.fullProductName = value;
    }

    /**
     * Gets the value of the branch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the branch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBranch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BranchType }
     * 
     * 
     */
    public List<BranchType> getBranch() {
        if (branch == null) {
            branch = new ArrayList<BranchType>();
        }
        return this.branch;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BranchTypeEnumType }
     *     
     */
    public BranchTypeEnumType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchTypeEnumType }
     *     
     */
    public void setType(BranchTypeEnumType value) {
        this.type = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
