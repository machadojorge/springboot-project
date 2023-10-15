package com.miguel.spring.project.springbootproject.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.spring.project.springbootproject.entities.pk.OrderItemPK;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Mapping
@Entity
@Table(name = "bt_order_item")
public class OrderItem  implements Serializable{

    private static final long serialVersionUID = 1L;
    
    //how we work with a complex Id, we do not use the @id, 
    // in this case, we use other annotation
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK(); //whenever the primary key is from a complex class
    // we need instance the object some like that "Object = new Class()"" 

    private Integer quantity;
    private Double price;

    public OrderItem(){

    };

    public OrderItem(Order order, Products product, Integer quantity, Double price)
    {
        id.setOrder(order);
        id.setProducts(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getSubTotal(){
        return this.getPrice() * this.getQuantity();
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
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // we need generate the get and set to the orderId, 

    // in this method is necessary put ther the "@JsonIgnore" because this method calls the 
    // the getOrder, and the Order call the get product and a error appends, a infinit loop
    @JsonIgnore
    public Order getOrder()
    {
        return id.getOrder();
    }

    public void setOrder(Order order)
    {
        this.setOrder(order);
    }
    
    public Products getProduct()
    {
        return this.id.getProducts();
    }

    public void setProducts(Products products)
    {
        this.id.setProducts(products);
    }

}
