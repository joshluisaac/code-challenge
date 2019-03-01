package com.uss.mars.exploration;

public enum OccupantType {
    EXPLORER("explorer",1), BLOCK("block",2);

    private String name;
    private int id;

    OccupantType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId(){return id;}


}
