package com.uss.mars.exploration;

public class Command {

    String name;
    int xAxis;
    int yAxis;

    public Command(){}
    public Command(String name, int xAxis, int yAxis){
        this.name = name;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public String getName() {
        return name;
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }
}
