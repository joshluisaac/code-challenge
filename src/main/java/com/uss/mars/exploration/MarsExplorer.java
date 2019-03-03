package com.uss.mars.exploration;

public class MarsExplorer implements TableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public MarsExplorer(Coordinate coordinate) {
        type = OccupantType.MARS_EXPLORER;
        this.coordinate = coordinate;
    }

    @Override
    public OccupantType getOccupantType() {
        return type;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "MarsExplorer{" +
                "type=" + type +
                ", coordinate=" + coordinate +
                '}';
    }
}
