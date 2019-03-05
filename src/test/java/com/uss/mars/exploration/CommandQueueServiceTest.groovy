package com.uss.mars.exploration

import com.uss.mars.exploration.services.CommandQueueService
import groovy.transform.TypeChecked
import spock.lang.Specification

@TypeChecked
class CommandQueueServiceTest extends Specification {

def 'command at head of command queue must be PLACE'(){
    given: "a set of commands"
    Command cmd1 = new Command("PLACE",0,0)
    Command cmd2 = new Command("PLACE",1,2)
    Command cmd3 = new Command("BLOCK",2,2)
    Command cmd4 = new Command("BLOCK",1,4)

    and: "a list of those commands"
    def commands = new ArrayList<>(Arrays.asList(cmd1,cmd2,cmd3,cmd4))

    and: "an initialized command queue"
    def commandQueueService = new CommandQueueService(commands)
    commandQueueService.initialize();

    when: "we query the head of the queue"
    def result = commandQueueService.placeCommandIsHeadEnqueued()

    then: "we expect to get true"
    result == true


}

}
