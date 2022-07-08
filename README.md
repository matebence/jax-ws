## JAXB/JAX-WS

A JAX-WS application has two main roles
- Consumer 
- Producer

What is a WSDL
- WSDL is an XML notation for describing a web service

What is an XSD?
- Grammer or Blueprint
	-Element
	- Attriutes
	- Namespaces
	- Order
	- Number of Occurences
	- Restrictions
- If the XML follows the XSD then it means its a valid file

When to use XSD?	
- Its a contract between two applications
- To make sure we get the data what we expect

In IntelliJ right click in the xsd file and click on 'Generate XML document from XSD schema'
The meaning of elementFormDefault
- None of the element will be prefixed via tns
	- elementFormDefault="unqualified"
- Every element will be prefixed via tns
	- elementFormDefault="qualified"

> #### Complex type

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientComplex"
        xmlns:tns="http://bencemate.com/PatientComplex" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientComplex"/>

    <!--complex type like an object in Java-->
    <complexType name="PatientComplex">
        <sequence>
            <element name="id" type="int"/>
            <element name="age" type="int"/>
            <element name="dob" type="date"/>
            <element name="name" type="string"/>
            <element name="email" type="string"/>
            <element name="gender" type="string"/>
            <element name="phone" type="string"/>
        </sequence>
    </complexType>
</schema>
```

> #### Simple type

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientSimple"
        xmlns:tns="http://bencemate.com/PatientSimple" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientSimple"/>

    <complexType name="PatientSimple">
        <sequence>
            <element name="id" type="tns:ID"/>
            <element name="name" type="tns:String15Chars"/>
            <element name="age" type="int"/>
            <element name="dob" type="date"/>
            <element name="email" type="string"/>
            <element name="gender" type="tns:Gender"/>
            <element name="phone" type="string"/>
        </sequence>
    </complexType>

    <!--simple int with regex pattern-->
    <simpleType name="ID">
        <restriction base="int">
            <pattern value="[0-9]*"/>
        </restriction>
    </simpleType>

    <!--simple string with max length-->
    <simpleType name="String15Chars">
        <restriction base="string">
            <maxLength value="15"/>
        </restriction>
    </simpleType>

    <!--simple string with enum-->
    <simpleType name="Gender">
        <restriction base="string">
            <enumeration value="M"/>
            <enumeration value="F"/>
        </restriction>
    </simpleType>
</schema>
```

> #### Sequence

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientSequence"
        xmlns:tns="http://bencemate.com/PatientSequence" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientSequence"/>

    <complexType name="PatientSequence">
        <!--the sequence sets the order-->
        <sequence>
            <element name="first" type="int"/>
            <element name="second" type="int"/>
            <element name="third" type="int"/>
        </sequence>
    </complexType>
</schema>
```

> #### Choice

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema
        xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientChoice"
        xmlns:tns="http://bencemate.com/PatientChoice" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientChoice"/>

    <complexType name="PatientChoice">
        <sequence>
            <element name="id" type="tns:ID"/>
            <element name="name" type="tns:String15Chars"/>
            <element name="age" type="int"/>
            <element name="dob" type="date"/>
            <element name="email" type="string"/>
            <element name="gender" type="tns:Gender"/>
            <element name="phone" type="string"/>
            <element name="payment" type="tns:PaymentType"/>
        </sequence>
    </complexType>

    <!--we have to choose at least one-->
    <complexType name="PaymentType">
        <choice>
            <element name="cash" type="int"/>
            <element name="insurance" type="tns:Insurance"/>
        </choice>
    </complexType>

    <!--we have to choose all-->
    <complexType name="Insurance">
        <all>
            <element name="provider" type="string"/>
            <element name="limit" type="int"/>
        </all>
    </complexType>

    <simpleType name="ID">
        <restriction base="int">
            <pattern value="[0-9]*"/>
        </restriction>
    </simpleType>

    <simpleType name="String15Chars">
        <restriction base="string">
            <maxLength value="15"/>
        </restriction>
    </simpleType>

    <simpleType name="Gender">
        <restriction base="string">
            <enumeration value="M"/>
            <enumeration value="F"/>
        </restriction>
    </simpleType>
</schema>
```

> #### Number of occurrence

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientOccurrence"
        xmlns:tns="http://bencemate.com/PatientOccurrence" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientOccurrence"/>

    <complexType name="PatientOccurrence">
        <sequence>
            <!--set number of occurrence to 2-->
            <element name="id" type="tns:ID" maxOccurs="2"/>
            <element name="name" type="tns:String15Chars"/>
            <!--make it optional-->
            <element name="age" type="int" minOccurs="0"/>
            <element name="dob" type="date"/>
            <!--we can use it any number of times-->
            <element name="email" type="string" maxOccurs="unbounded"/>
            <element name="gender" type="tns:Gender"/>
            <element name="phone" type="string"/>
            <element name="payment" type="tns:PaymentType"/>
        </sequence>
    </complexType>

    <complexType name="PaymentType">
        <choice>
            <element name="cash" type="int"/>
            <element name="insurance" type="tns:Insurance"/>
        </choice>
    </complexType>

    <complexType name="Insurance">
        <all>
            <element name="provider" type="string"/>
            <element name="limit" type="int"/>
        </all>
    </complexType>

    <simpleType name="ID">
        <restriction base="int">
            <pattern value="[0-9]*"/>
        </restriction>
    </simpleType>

    <simpleType name="String15Chars">
        <restriction base="string">
            <maxLength value="15"/>
        </restriction>
    </simpleType>

    <simpleType name="Gender">
        <restriction base="string">
            <enumeration value="M"/>
            <enumeration value="F"/>
        </restriction>
    </simpleType>
</schema>
```

> #### Attributes

```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://bencemate.com/PatientAttribute"
        xmlns:tns="http://bencemate.com/PatientAttribute" elementFormDefault="qualified">

    <element name="patient" type="tns:PatientAttribute"/>

    <complexType name="PatientAttribute">
        <sequence>
            <element name="name" type="tns:String15Chars"/>
            <element name="age" type="int" minOccurs="0"/>
            <element name="dob" type="date"/>
            <element name="email" type="string" maxOccurs="unbounded"/>
            <element name="gender" type="tns:Gender"/>
            <element name="phone" type="string"/>
            <element name="payment" type="tns:PaymentType"/>
        </sequence>

        <attribute name="id" type="tns:ID"/>
    </complexType>

    <complexType name="PaymentType">
        <choice>
            <element name="cash" type="int"/>
            <element name="insurance" type="tns:Insurance"/>
        </choice>
    </complexType>

    <complexType name="Insurance">
        <all>
            <element name="provider" type="string"/>
            <element name="limit" type="int"/>
        </all>
    </complexType>

    <simpleType name="ID">
        <restriction base="int">
            <pattern value="[0-9]*"/>
        </restriction>
    </simpleType>

    <simpleType name="String15Chars">
        <restriction base="string">
            <maxLength value="15"/>
        </restriction>
    </simpleType>

    <simpleType name="Gender">
        <restriction base="string">
            <enumeration value="M"/>
            <enumeration value="F"/>
        </restriction>
    </simpleType>
</schema>
```

> #### SOAP Web Services Concepts

- Its a standard from W3C
- The current version is 1.2

The main structure is:

```xml
<soap:Envelope>			
	<soap:header/>
	<soap:Body>
		<!-- our custom tags -->
		<creaditcard>
		</creaditcard>
	</soap:Body>
</soap:Envelope>			
```

Exception handling:

```xml
<soap:Envelope>			
	<soap:header/>
	<soap:Body>
		<soap:Fault>
			<ospa:code>soap:Server</soap:code>
			<soap:Reason>
				<soap:text>
					Card Expired
				</soap:text>
			</soap:Reason>
		</soap:Fault>
	</soap:Body>
</soap:Envelope>			
```

Meta data like authentication:

```xml
<soap:Envelope>			
	<soap:header>
		<wsse:Security>
			<wsse:UsernameToken>
				<wsse:Username>....
				<wsse:Password>....
			</wsse:UsernameToken>
		</wsse:Security>
	</soap:header>
</soap:Envelope>			
```

> #### WSDL Example

```xml
<?xml version='1.0' encoding='UTF-8'?>
<wsdl:definitions 
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" 
	xmlns:tns="http://bence.mate.com/"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
	xmlns:ns1="http://schemas.xmlsoap.org/soap/http"
	name="CustomerOrdersService" 
	targetNamespace="http://bence.mate.com/">
	
	<wsdl:types>
	<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns:tns="http://bence.mate.com/" elementFormDefault="unqualified"
		targetNamespace="http://bence.mate.com/" version="1.0">

		<!-- if custom types, then define it first -->
		<xs:complexType name="order">
			<xs:sequence>
				<xs:element name="id" type="xs:integer" />
				<xs:element maxOccurs="unbounded" name="product" type="tns:product" />
			</xs:sequence>
		</xs:complexType>
		<xs:complexType name="product">
			<xs:sequence>
				<xs:element minOccurs="0" name="id" type="xs:string" />
				<xs:element minOccurs="0" name="description" type="xs:string" />
				<xs:element minOccurs="0" name="quantity" type="xs:integer" />
			</xs:sequence>
		</xs:complexType>

		<!-- create request and reponse element based on the complex types -->
		<xs:element name="getOrdersRequest" type="tns:getOrdersRequest" />
		<xs:element name="getOrdersResponse" type="tns:getOrdersResponse" />

		<xs:complexType name="getOrdersRequest">
			<xs:sequence>
				<xs:element minOccurs="0" name="customerId" type="xs:integer" />
			</xs:sequence>
		</xs:complexType>
		<xs:complexType name="getOrdersResponse">
			<xs:sequence>
				<xs:element minOccurs="0" maxOccurs="unbounded" name="order" type="tns:order" />
			</xs:sequence>
		</xs:complexType>

		<!-- create request and reponse element based on the simple types -->
		<xs:element name="createOrdersRequest" type="tns:createOrdersRequest" />
		<xs:element name="createOrdersResponse" type="tns:createOrdersResponse" />

		<xs:complexType name="createOrdersRequest">
			<xs:sequence>
				<xs:element name="customerId" type="xs:integer" />
				<xs:element name="order" type="tns:order" />
			</xs:sequence>
		</xs:complexType>
		<xs:complexType name="createOrdersResponse">
			<xs:sequence>
				<xs:element name="result" type="xs:boolean" />
			</xs:sequence>
		</xs:complexType>
		</xs:schema>
	</wsdl:types>

	<!-- defining request and reponse -->
	<wsdl:message name="getOrdersRequest">
		<wsdl:part element="tns:getOrdersRequest" name="parameters">
		</wsdl:part>
	</wsdl:message>
	<wsdl:message name="getOrdersResponse">
		<wsdl:part element="tns:getOrdersResponse" name="parameters">
		</wsdl:part>
	</wsdl:message>

	<wsdl:message name="createOrdersRequest">
		<wsdl:part element="tns:createOrdersRequest" name="parameters">
		</wsdl:part>
	</wsdl:message>
	<wsdl:message name="createOrdersResponse">
		<wsdl:part element="tns:createOrdersResponse" name="parameters">
		</wsdl:part>
	</wsdl:message>

	<!-- defining request as input and response as output -->
	<wsdl:portType name="CustomerOrdersPortType">
		<wsdl:operation name="getOrders">
			<wsdl:input message="tns:getOrdersRequest" name="getOrdersRequest">
			</wsdl:input>
			<wsdl:output message="tns:getOrdersResponse" name="getOrdersResponse">
			</wsdl:output>
		</wsdl:operation>
		<wsdl:operation name="createOrders">
			<wsdl:input message="tns:createOrdersRequest" name="createOrdersRequest">
			</wsdl:input>
			<wsdl:output message="tns:createOrdersResponse" name="createOrdersResponse">
			</wsdl:output>
		</wsdl:operation>
	</wsdl:portType>

	<!-- binding everything together -->
	<wsdl:binding name="CustomerOrdersServiceSoapBinding"
		type="tns:CustomerOrdersPortType">
		<soap:binding style="document"
			transport="http://schemas.xmlsoap.org/soap/http" />
		<wsdl:operation name="getOrders">
			<soap:operation soapAction="" style="document" />
			<wsdl:input name="getOrdersRequest">
				<soap:body use="literal" />
			</wsdl:input>
			<wsdl:output name="getOrdersResponse">
				<soap:body use="literal" />
			</wsdl:output>
		</wsdl:operation>
		<wsdl:operation name="createOrders">
			<soap:operation soapAction="" style="document" />
			<wsdl:input name="createOrdersRequest">
				<soap:body use="literal" />
			</wsdl:input>
			<wsdl:output name="createOrdersResponse">
				<soap:body use="literal" />
			</wsdl:output>
		</wsdl:operation>
	</wsdl:binding>

	<!-- exposing the service -->
	<wsdl:service name="CustomerOrdersService">
		<wsdl:port binding="tns:CustomerOrdersServiceSoapBinding"
			name="CustomerOrdersPort">
			<soap:address
				location="http://localhost:8080/wsdlfirstws/services/customerOrdersService" />
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>
```

We have 4 types of bindings and they impact the following
- The payload generation
- The possiblity of payload validation
- The list of operation in (myMethod)

```java
public void myMethod(int x, float y);
```

**RPC/encoded**

```xml
<message name="myMethodRequest">
    <part name="x" type="xsd:int"/>
    <part name="y" type="xsd:float"/>
</message>
<message name="empty"/>

<portType name="PT">
    <operation name="myMethod">
        <input message="myMethodRequest"/>
        <output message="empty"/>
    </operation>
</portType>

<binding .../>
```

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x xsi:type="xsd:int">5</x>
            <y xsi:type="xsd:float">5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```	


**RPC/literal**

```xml
<message name="myMethodRequest">
    <part name="x" type="xsd:int"/>
    <part name="y" type="xsd:float"/>
</message>
<message name="empty"/>

<portType name="PT">
    <operation name="myMethod">
        <input message="myMethodRequest"/>
        <output message="empty"/>
    </operation>
</portType>

<binding .../>
```

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x>5</x>
            <y>5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```

**Document/encoded**

No one use this


**Document/literal**

```xml
<types>
    <schema>
        <element name="myMethod">
            <complexType>
                <sequence>
                    <element name="x" type="xsd:int"/>
                    <element name="y" type="xsd:float"/>
                </sequence>
            </complexType>
        </element>
        <element name="myMethodResponse">
            <complexType/>
        </element>
    </schema>
</types>
<message name="myMethodRequest">
    <part name="parameters" element="myMethod"/>
</message>
<message name="empty">
    <part name="parameters" element="myMethodResponse"/>
</message>

<portType name="PT">
    <operation name="myMethod">
        <input message="myMethodRequest"/>
        <output message="empty"/>
    </operation>
</portType>

<binding .../>
```

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x>5</x>
            <y>5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```


**Document - Wrapped version**

```xml
<types>
    <schema>
        <element name="myMethod">
            <complexType>
                <sequence>
                    <element name="x" type="xsd:int"/>
                    <element name="y" type="xsd:float"/>
                </sequence>
            </complexType>
        </element>
        <element name="myMethodResponse">
            <complexType/>
        </element>
    </schema>
</types>
<message name="myMethodRequest">
    <part name="parameters" element="myMethod"/>
</message>
<message name="empty">
    <part name="parameters" element="myMethodResponse"/>
</message>

<portType name="PT">
    <operation name="myMethod">
        <input message="myMethodRequest"/>
        <output message="empty"/>
    </operation>
</portType>

<binding .../>
```

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x>5</x>
            <y>5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```

> #### SOAP Web Services Design and Implementation

We have two approaches when we design SOAP web services:
- TOP DOWN or WSDL First or Contract First
	- We first create the WSDL file
	- Then we generate the java stubs using tools like wsdl2java
	- And at the we implement the web services endpoint
- CODE first or Bottom UP		
	- Write Java Code and annotate
	- Generate the WSDL from the code using java2wsdl

> #### SOAP Faults

- <faultCode> - error code
- <faultString> - short description
- <faultActor> - if we have multiple nodes, clusters
- <detail> - detailed message (for multiple error codes)

Fault Codes:
- SOAP-ENV:VersionMismatch
    - namespace doesnt match
- SOAP-ENV:MustUnderstand
    - the attribute can be used to indicate whether a header entry is mandatory or optional for the recipient to process.
- SOAP-ENV:Client
    - request is wrong
- SOAP-ENV:Server
    - error on server side

> #### SOAP Handlers

THe flow in in which the handlers are called:

- Request
    - getHeaders
    - handleMessage
- Response
    - getHeaders
    - handleMessage

> #### SSL localhost validation

[InstallCert](https://github.com/matebence/InstallCert)

```bash
# Compile
javac InstallCert.java

# Press 1
java InstallCert localhost:8443

cp jssecacerts $JAVA_HOME\jre\lib\security
```


> #### WSDL policy

```xml
<wsp:PolicyReference URI="#SecurityServiceBindingPolicy"/>
```

```xml
<wsp:Policy wsu:Id="SecurityServiceBindingPolicy" xmlns:sp="http://schemas.xmlsoap.org/ws/2005/07/securitypolicy">
    <wsp:ExactlyOne>
        <wsp:All>
            <sp:SignedSupportingTokens>
                <wsp:Policy>
                    <sp:UsernameToken
                            sp:IncludeToken="http://schemas.xmlsoap.org/ws/2005/07/securitypolicy/IncludeToken/AlwaysToRecipient">
                        <wsp:Policy>
                            <sp:WssUsernameToken10/>
                        </wsp:Policy>
                    </sp:UsernameToken>
                </wsp:Policy>
            </sp:SignedSupportingTokens>
        </wsp:All>
    </wsp:ExactlyOne>
</wsp:Policy>
```

> #### Generating asymmetric keys

```bash
keytool -genkey -keyalg RSA -sigalg SHA1withRSA -validity 600 -alias producerAlias -keypass producerPass -storepass producerStorePass -keystore producerKeyStore.jks -dname "cn=ecneb"
keytool -genkey -keyalg RSA -sigalg SHA1withRSA -validity 600 -alias consumerAlias  -keypass consumerPass -storepass consumerStorePass -keystore consumerKeyStore.jks -dname "cn=ecneb"


keytool -export -rfc -keystore consumerKeyStore.jks -storepass consumerStorePass -alias consumerAlias -file consumer.cer
keytool -export -rfc -keystore producerKeyStore.jks -storepass producerStorePass -alias producerAlias -file producer.cer


keytool -import -trustcacerts -keystore producerKeyStore.jks -storepass producerStorePass -alias consumerAlias -file consumer.cer -noprompt
keytool -import -trustcacerts -keystore consumerKeyStore.jks -storepass consumerStorePass -alias producerAlias -file producer.cer -noprompt
```
