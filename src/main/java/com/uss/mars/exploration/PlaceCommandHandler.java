package com.uss.mars.exploration;

//Explorer movement class
//This class is dependent on the Explorer object and CommandQueueService
public class ExplorerCommandHandler {

    private Explorer exp;
    private CommandQueueService commandQueueService;

    public ExplorerCommandHandler(Explorer exp, CommandQueueService commandQueueService){
        this.exp = exp;
        this.commandQueueService = commandQueueService;
    }

    //check coordinate validity
    //check if coordinate isn't already occupied. slotIsAvailable
    //if true check if the head of the CommandQueue is PLACE
    //reset blocks. That is, reinitialize the grid/TableTop.
    //move to coordinate position
    void occupySlot(final Explorer exp) {
        exp.coordinateIsValid();
        //if coordinate is valid and commandQueue is empty
        if (slotIsAvailable(exp) && commandQueueService.placeCommandIsHeadEnqueued())
        {
            resetGrid();
            moveToCoordinatePosition();
        }
    }

    //Checks for slot availability
    //Takes an explorer and then checks if the slot it is asking for is available.
    //A slot is defined available if it is not currently being occupied by any blocks.
    //Please note, i used the word unit in the requirements to mean slots
    boolean slotIsAvailable(final Explorer exp){
        return false;
    }

    SlotStatus slotAvailabilityStatus(final Explorer exp){

        return SlotStatus.BLOCKED;
    }


    void resetGrid(){

    }

    void moveToCoordinatePosition(){

    }

    void execute(){

    }





}
