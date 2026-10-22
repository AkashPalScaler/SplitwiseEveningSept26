package com.scaler.splitwiseeveningsept2026.Commands;

public interface Command {
    boolean matchCommand(String input);
    void execute(String input);
}
