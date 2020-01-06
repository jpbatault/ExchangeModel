package com.company;

import java.util.ArrayList;

public class Trade {

    //Static arraylist of call trades to view them later as a whole
    private static ArrayList<Trade> trades;


    private double bprice, sprice;
    private int bquantity, squantity;
    private String stock;

    //block to initialise the array at the start
    static{
        trades = new ArrayList<>();
    }

    public Trade(double bprice, double sprice, int bquantity, int squantity, String stock){
        //create and add this object to the trades array
        trades.add(this);
        this.bprice = bprice;
        this.sprice = sprice;
        this.bquantity = bquantity;
        this.squantity = squantity;
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Trade Executed:" +
                "\nStock: " + this.stock +
                "\nBUY: " + this.bprice + " SELL: " + this.sprice +
                "\nBUYQuantity: " + this.bquantity + " SELLQuantity: " + this.squantity + "\n";
    }
}
