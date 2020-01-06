package com.company;

import java.util.ArrayList;

public class Exchange {

    ArrayList<Stock> stocks = new ArrayList<>();
    ArrayList<OrderManager> orderManagers = new ArrayList<>();

    public Exchange() {
        initialiseExchange();
        //testExhangeInitialisation();
    }

    //Initialises teh exchange here
    //Creates 50 stocks and 50 Order managers
    private void initialiseExchange(){
        for (int i = 0; i < 50; i++) {
            stocks.add(new Stock());
            orderManagers.add(new OrderManager(stocks.get(i)));
        }
    }

    //Following code is to test the exchanges have been setup properly and the variables and clasees are working as they should
    private void testExhangeInitialisation(){
        for (int i = 0; i < 30; i++) {
            System.out.println("Stock: " + stocks.get(i).getName() + " AveragePrice: " + stocks.get(i).geAvgPrice() + " Size: " + stocks.get(i).getNumberOfPrices());
            stocks.get(i).deletePricesForTesting();
            System.out.println("StockOrderManager: " + orderManagers.get(i).getStock().getName() + " AveragePrice: " + orderManagers.get(i).getStock().geAvgPrice() + " Size: " + orderManagers.get(i).getStock().getNumberOfPrices());
        }
    }

    public void getClosestTimes(){
        for (OrderManager om : orderManagers){
            om.closestOrders();
        }
    }

}
