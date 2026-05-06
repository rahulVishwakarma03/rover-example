package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;

import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(coordinate, heading);
    }

    private void addRover(RoverSystem roverSystem) {
        String roverId = scanner.scanId();
        Rover rover = parseRover();
        roverSystem.addRover(roverId, rover);
    }

    private void addRoverCommands(RoverSystem roverSystem) {
        String roverId = scanner.scanId();
        RoverCommands roverCommands = parseRoverCommands();
        roverSystem.addCommands(roverId, roverCommands);
    }

    public RoverSystem parse() throws InvalidInputParsingException {
        RoverSystem roverSystem = new RoverSystem();
        String currentToken = scanner.peek();

        while (currentToken != null) {
            boolean isRoverToken = currentToken.matches("^R\\d+$");
            boolean isCommandToken = currentToken.matches("^R\\d+:$");

            if (isRoverToken) addRover(roverSystem);

            if (isCommandToken) addRoverCommands(roverSystem);

            if (!(isRoverToken || isCommandToken)) {
                throw new InvalidInputParsingException("Invalid Input token");
            }

            currentToken = scanner.peek();
        }

        return roverSystem;
    }

    private RoverCommands parseRoverCommands() {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();

        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
