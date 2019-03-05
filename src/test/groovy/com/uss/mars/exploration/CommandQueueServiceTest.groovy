package com.uss.mars.exploration

import com.uss.mars.exploration.exceptions.CoordinateOutsideBoundsException
import com.uss.mars.exploration.services.CommandQueueService
import com.uss.mars.exploration.services.ICommandQueueService
import groovy.transform.TypeChecked
import spock.lang.Specification

@TypeChecked
class CommandQueueServiceTest extends Specification {

    //dependencies
    ICommandQueueService commandQueueService


    def setup(){
        Command cmd1 = new Command("PLACE", 0, 0)
        Command cmd2 = new Command("PLACE", 1, 2)
        Command cmd3 = new Command("BLOCK", 2, 2)
        Command cmd4 = new Command("BLOCK", 1, 4)
        def commands = new ArrayList<>(Arrays.asList(cmd1, cmd2, cmd3, cmd4))
        commandQueueService = new CommandQueueService(commands)
        commandQueueService.initialize()
    }


    def 'I expect an IllegalArgumentException if the command at first command is not at origin(0,0)'() {
        given: "a set of commands"
        Command cmd1 = new Command("PLACE", 0, 1)
        Command cmd2 = new Command("BLOCK", 0, 4)

        and: "a CommandQueueService"
        commandQueueService = new CommandQueueService(Arrays.asList(cmd1,cmd2))
        commandQueueService.initialize()

        when: "we check for origin"
        commandQueueService.isPlaceCommandAtOrigin()

        then: "we expect an IllegalArgumentException"
        thrown IllegalArgumentException

    }

    def 'I expect a CoordinateOutsideBoundsException if the coordinates are way off the grid'(){
        given: "a set of commands which are off grid"
        Command cmd1 = new Command("PLACE", 0, 1)
        Command cmd2 = new Command("BLOCK", 0, 9)

        and: "a CommandQueueService"
        commandQueueService = new CommandQueueService(Arrays.asList(cmd1,cmd2))
        commandQueueService.initialize()

        when: "we check for coordinate validity"
        commandQueueService.validateCoordinates()

        then: "throw CoordinateOutsideBoundsException"
        thrown CoordinateOutsideBoundsException
    }

    def 'I expect true if all the coordinates are positioned within the grid'(){
        given: "a set of commands which are off grid"
        Command cmd1 = new Command("PLACE", 0, 1)
        Command cmd2 = new Command("BLOCK", 0, 3)

        and: "a CommandQueueService"
        commandQueueService = new CommandQueueService(Arrays.asList(cmd1,cmd2))
        commandQueueService.initialize()

        when: "we check for coordinate validity"
        def result = commandQueueService.validateCoordinates()

        then: "i expect a true value"
        result == true
    }

    def 'command at head of command queue must be PLACE'() {
        when: "we query the head of the queue"
        def result = commandQueueService.placeCommandIsHeadEnqueued()

        then: "we expect to get true"
        result == true


    }

}
