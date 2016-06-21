
package com.ync365.seed.commons.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pszMobis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pszMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iMobiCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pszSubPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "userId",
    "password",
    "pszMobis",
    "pszMsg",
    "iMobiCount",
    "pszSubPort"
})
@XmlRootElement(name = "MongateCsSpSendSmsNew")
public class MongateCsSpSendSmsNew {

    protected String userId;
    protected String password;
    protected String pszMobis;
    protected String pszMsg;
    protected int iMobiCount;
    protected String pszSubPort;

    /**
     * 获取userId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置userId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * 获取password属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * 获取pszMobis属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPszMobis() {
        return pszMobis;
    }

    /**
     * 设置pszMobis属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPszMobis(String value) {
        this.pszMobis = value;
    }

    /**
     * 获取pszMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPszMsg() {
        return pszMsg;
    }

    /**
     * 设置pszMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPszMsg(String value) {
        this.pszMsg = value;
    }

    /**
     * 获取iMobiCount属性的值。
     * 
     */
    public int getIMobiCount() {
        return iMobiCount;
    }

    /**
     * 设置iMobiCount属性的值。
     * 
     */
    public void setIMobiCount(int value) {
        this.iMobiCount = value;
    }

    /**
     * 获取pszSubPort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPszSubPort() {
        return pszSubPort;
    }

    /**
     * 设置pszSubPort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPszSubPort(String value) {
        this.pszSubPort = value;
    }

}
