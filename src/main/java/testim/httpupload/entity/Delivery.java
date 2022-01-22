package testim.httpupload.entity;

import lombok.Getter;
import testim.httpupload.domain.Address;
import testim.httpupload.enums.DeliveryStatus;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
