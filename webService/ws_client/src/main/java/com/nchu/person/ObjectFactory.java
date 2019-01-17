
package com.nchu.person;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rl.person package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddPerson_QNAME = new QName("http://server.nchu.com/", "addPerson");
    private final static QName _GetPersonAllResponse_QNAME = new QName("http://server.nchu.com/", "getPersonAllResponse");
    private final static QName _AddPersonResponse_QNAME = new QName("http://server.nchu.com/", "addPersonResponse");
    private final static QName _GetPersonAll_QNAME = new QName("http://server.nchu.com/", "getPersonAll");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rl.person
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPerson }
     * 
     */
    public AddPerson createAddPerson() {
        return new AddPerson();
    }

    /**
     * Create an instance of {@link GetPersonAllResponse }
     * 
     */
    public GetPersonAllResponse createGetPersonAllResponse() {
        return new GetPersonAllResponse();
    }

    /**
     * Create an instance of {@link GetPersonAll }
     * 
     */
    public GetPersonAll createGetPersonAll() {
        return new GetPersonAll();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.nchu.com/", name = "addPerson")
    public JAXBElement<AddPerson> createAddPerson(AddPerson value) {
        return new JAXBElement<AddPerson>(_AddPerson_QNAME, AddPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.nchu.com/", name = "getPersonAllResponse")
    public JAXBElement<GetPersonAllResponse> createGetPersonAllResponse(GetPersonAllResponse value) {
        return new JAXBElement<GetPersonAllResponse>(_GetPersonAllResponse_QNAME, GetPersonAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.nchu.com/", name = "addPersonResponse")
    public JAXBElement<AddPersonResponse> createAddPersonResponse(AddPersonResponse value) {
        return new JAXBElement<AddPersonResponse>(_AddPersonResponse_QNAME, AddPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.nchu.com/", name = "getPersonAll")
    public JAXBElement<GetPersonAll> createGetPersonAll(GetPersonAll value) {
        return new JAXBElement<GetPersonAll>(_GetPersonAll_QNAME, GetPersonAll.class, null, value);
    }

}
