package com.uss.mars.exploration

import com.uss.mars.exploration.commandhandlers.BlockCommandHandler
import com.uss.mars.exploration.commandhandlers.CommandHandler
import spock.lang.Specification

class BlockCommandHandlerTest extends Specification {

    TableTopOccupant blocker
    ITableTop tableTop

    def setup(){
        tableTop = new TableTop()
    }

    def 'When a blocker is placed on the grid and the slot is vacant, i expect the grid to be updated'(){

        given: "A blocker with valid coordinates"
        Coordinate coordinate = new Coordinate(2,4)
        blocker = new Blocker(coordinate)
        CommandHandler blockCommandHandler = new BlockCommandHandler(blocker,tableTop)

        when: "We handle the BLOCK command"
        blockCommandHandler.execute()

        then: "We expect the exact X and Y positions of the grid to be updated with B"
        tableTop.getMatrix()[coordinate.getCoordinateX()][coordinate.getCoordinateY()] == OccupantType.BLOCKER.getCode()
    }

    def 'A block will be ignored if an explorer has occupied the unit'(){

        given: "A set of already occupied points"
        Coordinate coordinate = new Coordinate(2,4)
        tableTop.update(new MarsExplorer(coordinate))
        blocker = new Blocker(coordinate)
        CommandHandler blockCommandHandler = new BlockCommandHandler(blocker,tableTop)

        when: "we try to BLOCK command"
        blockCommandHandler.execute()

        then: "the block action should be ignored and the explorer should remain in that unit"
        tableTop.getMatrix()[coordinate.getCoordinateX()][coordinate.getCoordinateY()] == OccupantType.MARS_EXPLORER.getCode()

    }

}
