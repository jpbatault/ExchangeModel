package com.company;

import java.time.LocalTime;

public class Order{

    //defines the type of order
    enum Type{
        SELL,
        BUY
    }

    private double price;
    private int quantity;
    private LocalTime orderTime = LocalTime.now();

    Type ordertype = null;

    public Order(double price, int quantity, Type t){
        this.price = price;
        this.quantity = quantity;
        this.ordertype = t;
    }

    // this is used to find the exact transaction price
    public double execute(){
        return quantity*price;
    }

    @Override
    public String toString(){
        return this.ordertype + " Price $" + price + ", Quantity " + quantity + " Created at " + orderTime;
    }

    public double getPrice() {
        return price;
    }

    //update the quantity of an order
    //for example, if it was partially filled
    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }
}
