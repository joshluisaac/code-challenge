package com.uss.mars.exploration

import spock.lang.Specification

class MovementUtilsTest extends Specification {


    def 'X,Y coordinates passed into the command queue must be within the grid'(){
        given: "Given a set of coordinates/points"
        Coordinate coordinate = new Coordinate(4,4)

        when: "i check for coordinate validity. That is, if those points are within the grid"
        boolean result = MovementUtils.coordinatePathIsValid(coordinate)

        then: "i expect to get true"
        result == true

    }
}
