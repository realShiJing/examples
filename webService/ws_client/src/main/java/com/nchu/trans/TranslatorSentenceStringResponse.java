
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
 *         &lt;element name="TranslatorSentenceStringResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
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
    "translatorSentenceStringResult"
})
@XmlRootElement(name = "TranslatorSentenceStringResponse")
public class TranslatorSentenceStringResponse {

    @XmlElement(name = "TranslatorSentenceStringResult")
    protected ArrayOfString translatorSentenceStringResult;

    /**
     * Gets the value of the translatorSentenceStringResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTranslatorSentenceStringResult() {
        return translatorSentenceStringResult;
    }

    /**
     * Sets the value of the translatorSentenceStringResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTranslatorSentenceStringResult(ArrayOfString value) {
        this.translatorSentenceStringResult = value;
    }

}
