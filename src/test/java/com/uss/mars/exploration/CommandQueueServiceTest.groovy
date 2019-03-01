package com.uss.mars.exploration

import spock.lang.Specification

class CommandQueueServiceTest extends Specification {


def 'command at head of command queue must be PLACE'(){
    given: "an initialized command queue"
    Command cmd1 = new Command("PLACE",0,0)
    Command cmd2 = new Command("PLACE",1,2)
    Command cmd3 = new Command("BLOCK",2,2)
    Command cmd4 = new Command("BLOCK",1,4)

    def commands = new ArrayList<>(Arrays.asList(cmd1,cmd2,cmd3,cmd4))
    def commandQueue = new CommandQueue()
    commandQueue.initialize(commands)
    def commandQueueService = new CommandQueueService(commandQueue)

    when: "we query the head of the queue"
    def result = commandQueueService.placeCommandIsHeadEnqued()

    then: "we expect to get true"
    result == true


}

}
