package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.*;
import java.util.stream.Collectors;

public class RoverSystem {
    private final Map<String,Rover> rovers = new HashMap<>();
    private final Map<String,RoverCommands> roverCommandsByRoverId = new HashMap<>();

    public void addRover(String roverId,Rover rover) {
        this.rovers.put(roverId,rover);
    }

    public void addCommands(String roverId,RoverCommands roverCommands) {
        this.roverCommandsByRoverId.put(roverId,roverCommands);
    }

    public void execute() {
        for (String roverId : rovers.keySet()) {
            Rover rover = this.rovers.get(roverId);
            RoverCommands roverCommands = this.roverCommandsByRoverId.get(roverId);
            roverCommands.execute(rover);
        }
    }

    @Override
    public String toString() {
        return rovers.values().toString();
    }
}
