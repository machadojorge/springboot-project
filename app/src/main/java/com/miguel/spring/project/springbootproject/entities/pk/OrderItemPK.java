package com.miguel.spring.project.springbootproject.entities.pk;

import java.io.Serializable;

import com.miguel.spring.project.springbootproject.entities.Order;
import com.miguel.spring.project.springbootproject.entities.Products;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// this class is necessary because we need creates a complex primarykey with two primary keys from two entities
// the Order and the Product
// this class goes creates this primary key
@Embeddable
public class OrderItemPK implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products products;

    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Products getProducts() {
        return products;
    }
    public void setProducts(Products products) {
        this.products = products;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((products == null) ? 0 : products.hashCode());
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
        OrderItemPK other = (OrderItemPK) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        return true;
    }

    // this class does not have the constructor, just the getters and setters
    
    
} 