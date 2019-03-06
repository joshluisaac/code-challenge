package com.uss.mars.exploration;

public class PathUnit {

    int hVal;
    int gVal;
    int fVal;
    Coordinate coordinate;


    public PathUnit(int hVal, int gVal, int fVal) {
        this.hVal = hVal;
        this.gVal = gVal;
        this.fVal = fVal;
    }

    public PathUnit(){}


    public int gethVal() {
        return hVal;
    }

    public void sethVal(int hVal) {
        this.hVal = hVal;
    }

    public int getgVal() {
        return gVal;
    }

    public void setgVal(int gVal) {
        this.gVal = gVal;
    }

    public int getfVal() {
        return fVal;
    }

    public void setfVal(int fVal) {
        this.fVal = fVal;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "PathUnit{" +
                "hVal=" + hVal +
                ", gVal=" + gVal +
                ", fVal=" + fVal +
                ", coordinate=" + coordinate +
                '}';
    }
}
