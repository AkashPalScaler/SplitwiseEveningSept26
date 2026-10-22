package com.scaler.splitwiseeveningsept2026.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private List<Command> commands;
    public CommandRegistry() {
        commands = new ArrayList<>();
    }
    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }
    public void matchAndExecute(String input) {
        for(Command command : commands){
            if(command.matchCommand(input)){
                command.execute(input);
            }
        }
    }
}
