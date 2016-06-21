
package com.ync365.seed.commons.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RegistryInfo2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RegistryInfo2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESULT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGISTRYCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERVICECODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BALANCE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CREATEDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGISTRYSTATEID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRIORITY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FIRSTREGISTRYDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGISTRYDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AGENTID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PRODUCTNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESTMOBILE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FLAG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REPLYCONTENT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISRETURN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VERSION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GRADE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PARENT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUBSIDIARY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ROLES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BALSTATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DISCOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SLIST" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POPM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UPDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryInfo2", propOrder = {
    "result",
    "registrycode",
    "servicecode",
    "balance",
    "createdate",
    "registrystateid",
    "priority",
    "firstregistrydate",
    "registrydate",
    "agentid",
    "productname",
    "destmobile",
    "flag",
    "replycontent",
    "isreturn",
    "version",
    "grade",
    "parent",
    "subsidiary",
    "roles",
    "balstatus",
    "discount",
    "slist",
    "popm",
    "update",
    "mms"
})
public class RegistryInfo2 {

    @XmlElement(name = "RESULT")
    protected String result;
    @XmlElement(name = "REGISTRYCODE")
    protected String registrycode;
    @XmlElement(name = "SERVICECODE")
    protected String servicecode;
    @XmlElement(name = "BALANCE")
    protected String balance;
    @XmlElement(name = "CREATEDATE")
    protected String createdate;
    @XmlElement(name = "REGISTRYSTATEID")
    protected String registrystateid;
    @XmlElement(name = "PRIORITY")
    protected String priority;
    @XmlElement(name = "FIRSTREGISTRYDATE")
    protected String firstregistrydate;
    @XmlElement(name = "REGISTRYDATE")
    protected String registrydate;
    @XmlElement(name = "AGENTID")
    protected String agentid;
    @XmlElement(name = "PRODUCTNAME")
    protected String productname;
    @XmlElement(name = "DESTMOBILE")
    protected String destmobile;
    @XmlElement(name = "FLAG")
    protected String flag;
    @XmlElement(name = "REPLYCONTENT")
    protected String replycontent;
    @XmlElement(name = "ISRETURN")
    protected String isreturn;
    @XmlElement(name = "VERSION")
    protected String version;
    @XmlElement(name = "GRADE")
    protected String grade;
    @XmlElement(name = "PARENT")
    protected String parent;
    @XmlElement(name = "SUBSIDIARY")
    protected String subsidiary;
    @XmlElement(name = "ROLES")
    protected String roles;
    @XmlElement(name = "BALSTATUS")
    protected String balstatus;
    @XmlElement(name = "DISCOUNT")
    protected String discount;
    @XmlElement(name = "SLIST")
    protected String slist;
    @XmlElement(name = "POPM")
    protected String popm;
    @XmlElement(name = "UPDATE")
    protected String update;
    @XmlElement(name = "MMS")
    protected String mms;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESULT() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESULT(String value) {
        this.result = value;
    }

    /**
     * 获取registrycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGISTRYCODE() {
        return registrycode;
    }

    /**
     * 设置registrycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGISTRYCODE(String value) {
        this.registrycode = value;
    }

    /**
     * 获取servicecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICECODE() {
        return servicecode;
    }

    /**
     * 设置servicecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICECODE(String value) {
        this.servicecode = value;
    }

    /**
     * 获取balance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBALANCE() {
        return balance;
    }

    /**
     * 设置balance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBALANCE(String value) {
        this.balance = value;
    }

    /**
     * 获取createdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATEDATE() {
        return createdate;
    }

    /**
     * 设置createdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATEDATE(String value) {
        this.createdate = value;
    }

    /**
     * 获取registrystateid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGISTRYSTATEID() {
        return registrystateid;
    }

    /**
     * 设置registrystateid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGISTRYSTATEID(String value) {
        this.registrystateid = value;
    }

    /**
     * 获取priority属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRIORITY() {
        return priority;
    }

    /**
     * 设置priority属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRIORITY(String value) {
        this.priority = value;
    }

    /**
     * 获取firstregistrydate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFIRSTREGISTRYDATE() {
        return firstregistrydate;
    }

    /**
     * 设置firstregistrydate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFIRSTREGISTRYDATE(String value) {
        this.firstregistrydate = value;
    }

    /**
     * 获取registrydate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGISTRYDATE() {
        return registrydate;
    }

    /**
     * 设置registrydate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGISTRYDATE(String value) {
        this.registrydate = value;
    }

    /**
     * 获取agentid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAGENTID() {
        return agentid;
    }

    /**
     * 设置agentid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAGENTID(String value) {
        this.agentid = value;
    }

    /**
     * 获取productname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRODUCTNAME() {
        return productname;
    }

    /**
     * 设置productname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRODUCTNAME(String value) {
        this.productname = value;
    }

    /**
     * 获取destmobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESTMOBILE() {
        return destmobile;
    }

    /**
     * 设置destmobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESTMOBILE(String value) {
        this.destmobile = value;
    }

    /**
     * 获取flag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFLAG() {
        return flag;
    }

    /**
     * 设置flag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFLAG(String value) {
        this.flag = value;
    }

    /**
     * 获取replycontent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREPLYCONTENT() {
        return replycontent;
    }

    /**
     * 设置replycontent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREPLYCONTENT(String value) {
        this.replycontent = value;
    }

    /**
     * 获取isreturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISRETURN() {
        return isreturn;
    }

    /**
     * 设置isreturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISRETURN(String value) {
        this.isreturn = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVERSION() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVERSION(String value) {
        this.version = value;
    }

    /**
     * 获取grade属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRADE() {
        return grade;
    }

    /**
     * 设置grade属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRADE(String value) {
        this.grade = value;
    }

    /**
     * 获取parent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARENT() {
        return parent;
    }

    /**
     * 设置parent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARENT(String value) {
        this.parent = value;
    }

    /**
     * 获取subsidiary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBSIDIARY() {
        return subsidiary;
    }

    /**
     * 设置subsidiary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBSIDIARY(String value) {
        this.subsidiary = value;
    }

    /**
     * 获取roles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getROLES() {
        return roles;
    }

    /**
     * 设置roles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROLES(String value) {
        this.roles = value;
    }

    /**
     * 获取balstatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBALSTATUS() {
        return balstatus;
    }

    /**
     * 设置balstatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBALSTATUS(String value) {
        this.balstatus = value;
    }

    /**
     * 获取discount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCOUNT() {
        return discount;
    }

    /**
     * 设置discount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCOUNT(String value) {
        this.discount = value;
    }

    /**
     * 获取slist属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSLIST() {
        return slist;
    }

    /**
     * 设置slist属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSLIST(String value) {
        this.slist = value;
    }

    /**
     * 获取popm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOPM() {
        return popm;
    }

    /**
     * 设置popm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOPM(String value) {
        this.popm = value;
    }

    /**
     * 获取update属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPDATE() {
        return update;
    }

    /**
     * 设置update属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPDATE(String value) {
        this.update = value;
    }

    /**
     * 获取mms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMMS() {
        return mms;
    }

    /**
     * 设置mms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMMS(String value) {
        this.mms = value;
    }

}
