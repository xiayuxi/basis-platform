
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
 *         &lt;element name="RECSMSEx_UTF8Result" type="{http://tempuri.org/}ArrayOfMOBody" minOccurs="0"/>
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
    "recsmsExUTF8Result"
})
@XmlRootElement(name = "RECSMSEx_UTF8Response")
public class RECSMSExUTF8Response {

    @XmlElement(name = "RECSMSEx_UTF8Result")
    protected ArrayOfMOBody recsmsExUTF8Result;

    /**
     * 获取recsmsExUTF8Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMOBody }
     *     
     */
    public ArrayOfMOBody getRECSMSExUTF8Result() {
        return recsmsExUTF8Result;
    }

    /**
     * 设置recsmsExUTF8Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMOBody }
     *     
     */
    public void setRECSMSExUTF8Result(ArrayOfMOBody value) {
        this.recsmsExUTF8Result = value;
    }

}
