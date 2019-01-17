
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
 *         &lt;element name="TranslatorReferStringResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
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
    "translatorReferStringResult"
})
@XmlRootElement(name = "TranslatorReferStringResponse")
public class TranslatorReferStringResponse {

    @XmlElement(name = "TranslatorReferStringResult")
    protected ArrayOfString translatorReferStringResult;

    /**
     * Gets the value of the translatorReferStringResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTranslatorReferStringResult() {
        return translatorReferStringResult;
    }

    /**
     * Sets the value of the translatorReferStringResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTranslatorReferStringResult(ArrayOfString value) {
        this.translatorReferStringResult = value;
    }

}
