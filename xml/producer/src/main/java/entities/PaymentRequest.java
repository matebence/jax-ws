package entities;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
//we define here the name of the root element <paymentRequest></paymentRequest>
@XmlRootElement(name = "paymentRequest")
//we define here that we want to have a custom complex type with name PaymentRequest
@XmlType(name = "PaymentRequest")
//we define here that we are going to annotate the fields not the accessors (getter, setter)
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentRequest {

    @Getter
    @Setter
    //required complex type with name creditCardInfo
    @XmlElement(name = "creditCardInfo", required = true)
    private CreditCardInfo creditCardInfo;

    @Getter
    @Setter
    //simple type
    //this could be also @XmlAttribute
    @XmlElement(name = "amount")
    private Double amount;
}
