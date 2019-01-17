
package com.nchu.trans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetMp3Result" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getMp3Result"
})
@XmlRootElement(name = "GetMp3Response")
public class GetMp3Response {

    @XmlElement(name = "GetMp3Result")
    protected byte[] getMp3Result;

    /**
     * Gets the value of the getMp3Result property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetMp3Result() {
        return getMp3Result;
    }

    /**
     * Sets the value of the getMp3Result property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetMp3Result(byte[] value) {
        this.getMp3Result = ((byte[]) value);
    }

}
