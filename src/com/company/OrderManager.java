package com.company;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class OrderManager {

    //copy of the ctock object
    private Stock stock;

    //order arrays
    //only open orders are stored here
    private ArrayList<Order> buyOrders = new ArrayList<>();
    private ArrayList<Order> sellOrders = new ArrayList<>();

    //used for future developments of this code
    private int currentTimeIteration = 0;


    //creation of a new order manager project
    public OrderManager(Stock st){
        this.stock = st;
        //System.out.println("Created new Order Manager for Stock: " + stock.getName());

        generateFirstOrders();

        sortOrders();
        matchAndProcess();
        cleanup(buyOrders);
        cleanup(sellOrders);
        printOrders();
    }

    public ArrayList<Order> getOrders(){
        ArrayList<Order> temp = new ArrayList<>();
        temp.addAll(buyOrders);
        temp.addAll(sellOrders);
        return temp;
    }



    public ArrayList<Order> getBuyOrders() {
        return buyOrders;
    }

    public Stock getStock() {
        return stock;
    }

    public ArrayList<Order> getSellOrders() {
        return sellOrders;
    }

    //generates the first orders with random pricing and random quantities
    public void generateFirstOrders(){
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            buyOrders.add(new Order(getOrderPrice(r), getOrderQuantity(r), Order.Type.BUY));
            sellOrders.add(new Order(getOrderPrice(r), getOrderQuantity(r), Order.Type.SELL));
        }
    }

    public void matchAndProcess(){
        for (int i = 0; i < 50; i++) {


            Order o = buyOrders.get(i);

            for ( int j = 0; j < 50; j++){

            Order workingSellOrder = sellOrders.get(j);

            if (o.getPrice() >= workingSellOrder.getPrice()) {
                if (o.getQuantity() == workingSellOrder.getQuantity()) {
                    Trade t = new Trade(o.getPrice(), workingSellOrder.getPrice(), o.getQuantity(), workingSellOrder.getQuantity(), this.stock.getName());
                    System.out.println(t.toString());
                    workingSellOrder.updateQuantity(0);
                    o.updateQuantity(0);
                    break;
                } else if (o.getQuantity() <= workingSellOrder.getQuantity()) {
                    Trade t = new Trade(o.getPrice(), workingSellOrder.getPrice(), o.getQuantity(), workingSellOrder.getQuantity(), this.stock.getName());
                    System.out.println(t.toString());
                    workingSellOrder.updateQuantity(workingSellOrder.getQuantity() - o.getQuantity());
                    o.updateQuantity(0);
                    break;
                } else if (o.getQuantity() >= workingSellOrder.getQuantity() && workingSellOrder.getQuantity() > 0) {
                    Trade t = new Trade(o.getPrice(), workingSellOrder.getPrice(), o.getQuantity(), workingSellOrder.getQuantity(), this.stock.getName());
                    System.out.println(t.toString());
                    o.updateQuantity(o.getQuantity() - workingSellOrder.getQuantity());
                    workingSellOrder.updateQuantity(0);
                }
            }
            }
        }
    }

    //returns a random quantity in a multiple of 10 from 10-50
    private int getOrderQuantity(Random r){
        return r.ints(1, (6)).findFirst().getAsInt() * 10;
    }

    //random prices from 20 to 25
    private double getOrderPrice(Random r){
        Double temp = 20 + (25 - 20) * r.nextDouble();
        return (Math.round(temp*100.0)/100.0);
    }


    public void printOrders(){
        for (Order o : getOrders()){
            System.out.println(o.toString());
        }

    }

    //after the processing is done, certain orders will have a quantity of 0
    //this means they are done and this method removes them from the orders list
    //the relevant trades are still present in the trades
    private void cleanup(ArrayList<Order> orderList){
        for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
            Order o = iterator.next();
            if (o.getQuantity() == 0) {
                iterator.remove();
            }
        }
    }

    //sorts the orders in ascending order for better price matching
    public void sortOrders (){
        buyOrders.sort(Comparator.comparing(Order::getPrice));
        sellOrders.sort(Comparator.comparing(Order::getPrice));
    }




    private class TimeOrderObject{

        Order order1, order2;
        long timeDiff = -1;

        public TimeOrderObject(Order order1, Order order2, long timeDiff){
            this.order1 = order1;
            this.order2 = order2;
            this.timeDiff = timeDiff;
        }

        public TimeOrderObject() {
        }

        public String toString(){
            return order1.toString() + "\n" + order2.toString() + " Time diff: " + timeDiff;
        }
    }

    public void closestOrders(){
        TimeOrderObject closest = getNearestDate(this.getOrders());
        System.out.println(closest.toString());
    }

    public TimeOrderObject getNearestDate(ArrayList<Order> orders) {
        long minDiff = -1;
        TimeOrderObject tob = new TimeOrderObject();

        for (int i = 0; i < orders.size(); i++) {
            long currentTime = orders.get(i).getOrderTime().toNanoOfDay();
            for (int j = i+1; j < orders.size(); j++) {
                long diff = Math.abs(currentTime - orders.get(j).getOrderTime().toNanoOfDay());
                if ((minDiff == -1) || (diff < minDiff)) {
                    minDiff = diff;
                    tob = new TimeOrderObject(orders.get(i), orders.get(j), minDiff);
                }
            }
        }
        return tob;
    }
}
