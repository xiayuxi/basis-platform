
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
 *         &lt;element name="RECSMS_UTF8Result" type="{http://tempuri.org/}ArrayOfMOBody" minOccurs="0"/>
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
    "recsmsutf8Result"
})
@XmlRootElement(name = "RECSMS_UTF8Response")
public class RECSMSUTF8Response {

    @XmlElement(name = "RECSMS_UTF8Result")
    protected ArrayOfMOBody recsmsutf8Result;

    /**
     * 获取recsmsutf8Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMOBody }
     *     
     */
    public ArrayOfMOBody getRECSMSUTF8Result() {
        return recsmsutf8Result;
    }

    /**
     * 设置recsmsutf8Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMOBody }
     *     
     */
    public void setRECSMSUTF8Result(ArrayOfMOBody value) {
        this.recsmsutf8Result = value;
    }

}
