
package com.ync365.seed.commons.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetAllInfo2Result" type="{http://tempuri.org/}RegistryInfo2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAllInfo2Result"
})
@XmlRootElement(name = "GetAllInfo2Response")
public class GetAllInfo2Response {

    @XmlElement(name = "GetAllInfo2Result")
    protected RegistryInfo2 getAllInfo2Result;

    /**
     * 获取getAllInfo2Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegistryInfo2 }
     *     
     */
    public RegistryInfo2 getGetAllInfo2Result() {
        return getAllInfo2Result;
    }

    /**
     * 设置getAllInfo2Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegistryInfo2 }
     *     
     */
    public void setGetAllInfo2Result(RegistryInfo2 value) {
        this.getAllInfo2Result = value;
    }

}
