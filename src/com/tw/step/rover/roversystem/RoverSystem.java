package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.ArrayList;
import java.util.Arrays;

public class RoverSystem {
    private final ArrayList<Rover> rovers = new ArrayList<>();
    private final ArrayList<RoverCommands> allRoversCommands = new ArrayList<>();

    public void addRover(Rover rover) {
        this.rovers.add(rover);
    }

    public void addCommands(RoverCommands roverCommands) {
        this.allRoversCommands.add(roverCommands);
    }

    public void execute() {
        for (int index = 0; index < this.rovers.size(); index++) {
            Rover rover = this.rovers.get(index);
            RoverCommands roverCommands = this.allRoversCommands.get(index);
            roverCommands.execute(rover);
        }
    }

        @Override
    public String toString() {
        return Arrays.toString(rovers.stream().map(Rover::toString).toArray());
    }
}
