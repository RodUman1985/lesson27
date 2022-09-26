package com.company;

import java.util.Random;

public class Ship {
    private String name;


    private  int weight;
    private static int shipNumber = 0;
    private  static Random rand= new Random();

    public Ship(){
       this.name="ship_"+ ++shipNumber;
       //[1000..10000]
       this.weight=rand.nextInt(90001)+1000;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
