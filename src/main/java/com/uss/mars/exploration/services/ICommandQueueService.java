package com.uss.mars.exploration.services;

import com.uss.mars.exploration.Command;

import java.util.Queue;

public interface ICommandQueueService {
    void initialize();

    boolean placeCommandIsHeadEnqueued();

    boolean isPlaceCommandAtOrigin();

    boolean validateCoordinates();

    boolean validateQueueCommandSyntax();

    Queue<Command> getQueue();
}
