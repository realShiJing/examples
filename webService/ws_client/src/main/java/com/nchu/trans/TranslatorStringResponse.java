
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
 *         &lt;element name="TranslatorStringResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
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
    "translatorStringResult"
})
@XmlRootElement(name = "TranslatorStringResponse")
public class TranslatorStringResponse {

    @XmlElement(name = "TranslatorStringResult")
    protected ArrayOfString translatorStringResult;

    /**
     * Gets the value of the translatorStringResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTranslatorStringResult() {
        return translatorStringResult;
    }

    /**
     * Sets the value of the translatorStringResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTranslatorStringResult(ArrayOfString value) {
        this.translatorStringResult = value;
    }

}
