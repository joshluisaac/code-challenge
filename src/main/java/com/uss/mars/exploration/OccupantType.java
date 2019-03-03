package com.uss.mars.exploration;

public enum OccupantType {
    MARS_EXPLORER("explorer", "E", 1), BLOCKER("block", "B", 2);

    private String name;
    private String code;
    private int id;

    OccupantType(String name, String code, int id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

}
