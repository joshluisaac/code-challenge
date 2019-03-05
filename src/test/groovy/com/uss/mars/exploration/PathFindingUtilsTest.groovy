package com.uss.mars.exploration

import com.uss.mars.exploration.utils.PathFindingUtils
import spock.lang.Specification

class PathFindingUtilsTest extends Specification {

    def setup(){}


    def 'The manhattan distance between two points must be 8'(){
        given: "A set of start and end coordinate points"
        Coordinate p1 = new Coordinate(0,1)
        Coordinate p2 = new Coordinate(5,4)


        when: "i calculate the manhattan distance"
        int result = PathFindingUtils.manhattanDistance(p1,p2)

        then: "i expect"
        println result
        result == 8
    }


}
