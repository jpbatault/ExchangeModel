package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Prices {

    /**
     * Superclass for stocks.
     * It was designed in this manner to allow extensible uses for trades which has not yet been implemented
     */

    private ArrayList<Double> prices = new ArrayList<Double>();

    public Prices() {
        generatePrices();
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    //randomly creates 50 different stock prices per stock
    private void generatePrices(){
        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            Double temp = 20 + (25 - 20) * r.nextDouble();
            prices.add((double) (Math.round(temp*100.0)/100.0));
        }

    }

    public double geMaxPrice(){
        return Collections.max(prices);
    }

    public double geMinPrice(){
        return Collections.min(prices);

    }

    public double geAvgPrice(){
        return prices.stream().mapToDouble(val -> val).average().orElse(0.0);
    }

    public String toString(){

        String temp = "";
        for (int i = 0; i < prices.size(); i++) {
            temp = temp + prices.get(i) + " ";
        }

        return temp;
    }

    public double popPrice(){
        if (prices.size() != 0){
            double tempPrice = prices.get(0);
            prices.remove(0);
            return tempPrice;
        }

        return -1;
    }

    /**
     * Not yet in use. For future developments
     */
    public double getPriceAtItteration(int i){
        return prices.get(i);
    }

    /**
     * Only used for testing purposes
     */
    public void deletePricesForTesting(){
        for (int i = 0; i < 5; i++) {
            prices.remove(i);
        }
    }

    public int getNumberOfPrices(){
        return prices.size();
    }
}
