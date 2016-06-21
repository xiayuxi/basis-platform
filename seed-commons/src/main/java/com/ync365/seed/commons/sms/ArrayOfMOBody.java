
package com.ync365.seed.commons.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfMOBody complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMOBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MOBody" type="{http://tempuri.org/}MOBody" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMOBody", propOrder = {
    "moBody"
})
public class ArrayOfMOBody {

    @XmlElement(name = "MOBody", nillable = true)
    protected List<MOBody> moBody;

    /**
     * Gets the value of the moBody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moBody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMOBody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MOBody }
     * 
     * 
     */
    public List<MOBody> getMOBody() {
        if (moBody == null) {
            moBody = new ArrayList<MOBody>();
        }
        return this.moBody;
    }

}
