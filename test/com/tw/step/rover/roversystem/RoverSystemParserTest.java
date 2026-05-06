package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.errors.InvalidInputParsingException;
import com.tw.step.rover.errors.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Navigator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverSystemParserTest {
    @Test
    void shouldParseAndExecuteRoverSystem() {
        RoverSystemScanner scanner = RoverSystemScanner.from("5 5\nR1 1 2 N\nR1: RFF");
        RoverSystemParser parser = new RoverSystemParser(scanner, Navigator.create(), new Plateau(new Coordinate(0,0),scanner.scanCoordinate()), new CommandCreator());

        RoverSystem roverSystem = null;
        try {
            roverSystem = parser.parse();
            roverSystem.execute();
            assertEquals("3 2 E Alive", roverSystem.getState().get("R1"));
        } catch (InvalidInputParsingException e) {
            throw new RuntimeException(e);
        }

    }
}
