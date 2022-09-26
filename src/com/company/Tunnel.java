package com.company;

import java.util.LinkedList;

public class Tunnel {
   private final LinkedList<Ship>shipList;
   private int maxCapacity;

    public Tunnel(int maxCapacity){
        this.shipList= new LinkedList<>();
        this.maxCapacity=maxCapacity;
    }
    public void addShip(Ship ship) throws Exception {
        synchronized (this.shipList){
       if (this.shipList.size() >= this.maxCapacity){
           throw  new Exception("tunnel is fool");
       }
        System.out.println(ship+"прибыл в тоннель");
       this.shipList.add(ship);}

    }
    public Ship getShip() {
        synchronized (this.shipList){
        if (this.shipList.isEmpty()){
            throw  new RuntimeException("tunnel is empty");
        }
      return  this.shipList.removeFirst();}
    }

   public  boolean isFull(){
       synchronized (this.shipList) {
           return this.shipList.size() >= this.maxCapacity;
       }
   }
}
