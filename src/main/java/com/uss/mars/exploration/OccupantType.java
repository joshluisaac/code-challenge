package com.uss.mars.exploration;

public enum OccupantType {
    MARS_EXPLORER("explorer", 1), BLOCKER("block", 2);

    private String name;
    private int id;

    OccupantType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
