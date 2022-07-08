package entities;

import javax.xml.bind.annotation.XmlAccessType;
import java.util.Date;

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
@XmlType(name = "CreditCardInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "creditCardInfo")
public class CreditCardInfo {

    @Getter
    @Setter
    @XmlElement(name = "cardNumber")
    private String cardNumber;

    @Getter
    @Setter
    @XmlElement(name = "expiryDate")
    private Date expiryDate;

    @Getter
    @Setter
    @XmlElement(name = "firstName")
    private String firstName;

    @Getter
    @Setter
    @XmlElement(name = "lastName")
    private String lastName;

    @Getter
    @Setter
    @XmlElement(name = "secCode")
    private String secCode;

    @Getter
    @Setter
    @XmlElement(name = "address")
    private String address;
}
