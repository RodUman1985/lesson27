package com.company;

import java.util.LinkedList;

public class Port {
private Tunnel tunnel;
private  Thread tunnelThread;
private LinkedList<Dock> docks;

    public Port (int docCount, int tunnelCapacity){
     this.tunnel=new Tunnel(tunnelCapacity);
     this.docks=new LinkedList<>();
     for( int i=0;i<docCount;i++){
         this.docks.add(new Dock());
     }

    }
    private void manageDock(){
        for (Dock dock: this.docks){
            Thread t=new Thread(()->{
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        //ждем пока док освободится
                        if (dock.isEmpty()){
                           try {
                               dock.unloadShip(this.tunnel.getShip());
                           } catch (RuntimeException e){
                              // System.out.println(" док свободен но в тонеле нет кораблей");
                           }
                            continue;
                        }
                        synchronized (dock) {
                            dock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    };

    private void manageTunnel(){
        this.tunnelThread =new Thread(()-> {
           while (!Thread.currentThread().isInterrupted()){
               if(!this.tunnel.isFull()){
                   //добовляем корабль в тонель
                   Ship ship= Sea.createShip();
                   try {
                       this.tunnel.addShip(ship);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           }
        });
        this.tunnelThread.start();
    }
    public void start(){
        this.manageTunnel();
        this.manageDock();
        try {
            this.tunnelThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
