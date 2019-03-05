package com.uss.mars.exploration

import com.uss.mars.exploration.commandhandlers.CommandHandler
import com.uss.mars.exploration.commandhandlers.PlaceCommandHandler
import spock.lang.Specification

class PlaceCommandHandlerTest extends Specification {

    TableTopOccupant explorer
    ITableTop tableTop
    CommandHandler placeCommandHandler

    def setup(){
        tableTop = new TableTop()
    }

    def 'When an explorer is placed on the grid and the slot is vacant, i expect the grid to be updated'(){

        given: "An explorer with valid points"
        Coordinate coordinate = new Coordinate(2,4)
        explorer = new MarsExplorer(coordinate)
        placeCommandHandler = new PlaceCommandHandler(explorer,tableTop)

        when: "we handle the PLACE command"
        placeCommandHandler.execute()

        then: "we expect a side effect. We expect the grid to be updated with the exact X and Y positions"
        tableTop.getMatrix()[coordinate.getCoordinateX()][coordinate.getCoordinateY()] == OccupantType.MARS_EXPLORER.getCode()
    }

    def 'An explorer would reset the table top and remove all exiting blocks'(){

        given: "A set of blocked units"
        tableTop.update(new Blocker(new Coordinate(2,1)))
        tableTop.update(new Blocker(new Coordinate(2,2)))
        tableTop.update(new Blocker(new Coordinate(2,3)))
        tableTop.update(new Blocker(new Coordinate(2,4)))
        explorer = new MarsExplorer(new Coordinate(1,3))
        placeCommandHandler = new PlaceCommandHandler(explorer,tableTop)

        when: "we handle the PLACE command"
        placeCommandHandler.execute()

        then: "the table top should be refreshed removing all existing blocks"
        tableTop.getMatrix()[2][1] == "X"
        tableTop.getMatrix()[2][2] == "X"
        tableTop.getMatrix()[2][3] == "X"
        tableTop.getMatrix()[2][4] == "X"
        tableTop.getMatrix()[explorer.getCoordinate().getCoordinateX()][explorer.getCoordinate().getCoordinateY()] == "E"
    }

    def 'If the last unit occupied by the explorer is the origin(0,0) subsequent PLACE command should reset the grid even if it is blocked'(){

        given: "An origin (0,0)"
        tableTop.update(new MarsExplorer(new Coordinate(0,0)))

        and: "a set of blocked units"
        tableTop.update(new Blocker(new Coordinate(2,1)))
        tableTop.update(new Blocker(new Coordinate(2,2)))
        tableTop.update(new Blocker(new Coordinate(2,3)))
        tableTop.update(new Blocker(new Coordinate(2,4)))

        and: "a new point"
        explorer = new MarsExplorer(new Coordinate(2,3))
        placeCommandHandler = new PlaceCommandHandler(explorer,tableTop)

        when: "we handle the PLACE command"
        placeCommandHandler.execute()

        then: ""


    }


}
