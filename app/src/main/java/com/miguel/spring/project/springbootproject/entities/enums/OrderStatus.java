package com.miguel.spring.project.springbootproject.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    // how we enumerate the enum, we need cretaes a constructor for this enum
    // the constructor needs be PRIVATE
    private OrderStatus(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static OrderStatus valueOf(int code)
    {
        // this for goes run all values in the Order status and return the text
        for(OrderStatus value: OrderStatus.values())
        {
            if (value.getCode() == code)
            {
                return value;
            }
        }
        // if the code is not valid, lunch one exception
        throw new IllegalArgumentException("Invalid OrderStatus Code");
    }

}


