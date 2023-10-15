package com.miguel.spring.project.springbootproject.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.spring.project.springbootproject.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //we uses the class Instant from java >= 8, this class replace the class Data, is better
   //this annotations goes format the Instant to show in json with the apropriate format
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    //this annotations can define the mask that we show the date, withe the respective timezone
    private Instant moment;

    private Integer orderStatus;

    // now, we declare the associations
    // We need informs that attribut client is a foreight key
   // this relationship is 1 for many;
   // one client can be have many Orders
   // This annotation inform the type of relation 1 - *
   //@JsonIgnore
   @ManyToOne
   // this annottation is to join this foreigh key to this table, and we give a name to this column
   @JoinColumn(name = "client_id")
    private User client;

    // in this case, the relation is On to Many, one Order can be have many products
    // for that in class OrderItem, we are the private attribute Order
    // because that, in this class we use the mappeBy = "id.order" because the id in the OrderItem class
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    // The Order has a Payment
    // we need expecify this relation
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // This is used when the relation is "one to one" bethween class. The cascade indicates that order as the same id that the payment
    private Payment payment;


    public Order() {
    }

    public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        setOrderStatus(orderStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
        {
            this.orderStatus = orderStatus.getCode();
    
        }
        else
        {
           throw new IllegalArgumentException("Invalida Order Status");
        }
    }


    public Set<OrderItem> getItem(){
        return items;
    }
    
    
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
