package com.epam.repairstudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @JsonProperty(access = READ_ONLY)
    Long requestid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "requestdescr")
    String requestDescr;
    @Column(name = "price")
    double price;
    @Column(name = "status")
    String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return requestid != null && Objects.equals(requestid, order.requestid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
