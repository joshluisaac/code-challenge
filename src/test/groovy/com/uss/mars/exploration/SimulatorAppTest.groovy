package com.uss.mars.exploration

import com.uss.mars.exploration.services.CommandQueueService
import spock.lang.Specification

class SimulatorAppTest extends Specification {

    //dependencies
    CommandQueueService commandQueueService

    def setup(){
        commandQueueService = new CommandQueueService(null)
    }

    def 'If zero/empty/null commands are passed to the simulator, then throw an IllegalArgumentException'(){
        given: "the command queue service"
        commandQueueService = new CommandQueueService(null)
        and: "the simulator"
        def simulator = new SimulatorApp()

        when: "i check the queue size"
        simulator.validateCommandQueueSize(commandQueueService)

        then:
        thrown IllegalArgumentException
    }

    def 'Throw an IllegalArgumentException if the first command issued is not PLACE'(){
        given: "a set of commands"
        Command cmd1 = new Command("BLOCK", 0, 1)
        Command cmd2 = new Command("PLACE", 0, 3)

        and: "a CommandQueueService"
        commandQueueService = new CommandQueueService(Arrays.asList(cmd1,cmd2))
        commandQueueService.initialize()
        def simulator = new SimulatorApp()

        when: "we check the head of the queue"
        simulator.checkHeadOfQueue(commandQueueService)

        then:
        thrown IllegalArgumentException
    }



}
