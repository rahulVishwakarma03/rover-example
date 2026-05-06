package com.tw.step.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.errors.InvalidInputParsingException;
import com.tw.step.rover.errors.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

public class App {
    static void main() {
        String text = """
                5 5
                R1 1 5 N
                R2 2 2 E
                R1: LFFRFLFFFR
                R2: LFF
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Coordinate topRight = scanner.scanCoordinate();
        Boundary boundary = new Plateau(topRight);
        CommandCreator commandCreator = new CommandCreator();
        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        RoverSystem system = null;
        try {
            system = roverSystemParser.parse();
            system.execute();
            System.out.println(system.getState());
        } catch (InvalidInputParsingException e) {
            System.out.println(e.getMessage());
        }
    }
}
