package com.company;

import java.util.Random;

public class Stock extends Prices {

    private String name = "";
    private int currentTimeIteration = 0;

    public Stock() {
        super();
        generateName();
    }

    public String getName() {
        return name;
    }

    //genenerates a random three character string
    private void generateName(){
        Random r = new Random();
        for (int i = 0; i < 3; i++)
            name = name + (char)(r.nextInt(26) + 'A');
    }

    //used for future developments
    public double getPriceAtItteration(int i){
        return super.getPriceAtItteration(i);
    }

    //used only for testing purposes
    public void deletePricesForTesting(){
        super.deletePricesForTesting();
    }

    public int getNumberOfPrices(){
        return super.getNumberOfPrices();
    }


}
