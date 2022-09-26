package com.company;

public class Dock {
   private static  int dockCounter;
   private int dockNumber;
   private boolean empty=true;

    public Dock (){
       this.dockNumber=++dockCounter;
    }
    public boolean isEmpty(){
        return this.empty;
    }
    public synchronized void unloadShip (Ship ship){
        this.empty=false;
        System.out.println(ship+"прибыл в док №"+this.dockNumber);
        try {
            Thread.sleep(ship.getWeight());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // сообщаем что док освободился
        this.notify();
        System.out.println("Док #"+this.dockNumber+"освободился");
        this.empty=true;
    }

}
