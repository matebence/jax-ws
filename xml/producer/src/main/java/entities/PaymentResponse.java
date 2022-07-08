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
@XmlType(name = "PaymentResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "paymentResponse")
public class PaymentResponse {

    @Getter
    @Setter
    @XmlElement(name = "result")
    private boolean result;
}
