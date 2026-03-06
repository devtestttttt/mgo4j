package io.mangonet.mgo.bcs.types.transaction;

import io.mangonet.mgo.bcs.types.arg.call.CallArg;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class ProgrammableTransaction {
    
    private final LinkedHashMap<CallArg, Integer> inputs;
    private final List<Command> commands;

    public ProgrammableTransaction() {
        this.inputs = new LinkedHashMap<>();
        this.commands = new ArrayList<>();
    }

    public ProgrammableTransaction(LinkedHashMap<CallArg, Integer> inputs, List<Command> commands) {
        this.inputs = inputs;
        this.commands = commands;
    }

    public ProgrammableTransaction(List<CallArg> inputs, List<Command> commands) {
        this.inputs = new LinkedHashMap<>(inputs.size());
        this.addInputs(inputs);
        this.commands = commands;
    }

    public int addInput(CallArg callArg) {
        return this.inputs.computeIfAbsent(callArg, k -> inputs.size());
    }

    public void addInputs(LinkedHashMap<CallArg, Integer> callArgs) {
        callArgs.forEach((callArg, integer) -> this.inputs.computeIfAbsent(callArg, k -> inputs.size()));
    }

    public void updateInputs(LinkedHashMap<CallArg, Integer> callArgs) {
        this.inputs.clear();
        this.addInputs(callArgs);
    }

    public void addInputs(List<CallArg> callArgs) {
        callArgs.forEach(callArg -> this.inputs.computeIfAbsent(callArg, k -> inputs.size()));
    }

    public ProgrammableTransaction addCommand(Command command) {
        this.commands.add(command);
        return this;
    }

    public void addCommands(List<Command> commands) {
        this.commands.addAll(commands);
    }

    public List<CallArg> getInputList() {
        return new ArrayList<>(inputs.keySet());
    }

    public LinkedHashMap<CallArg, Integer> getInputs() {
        return inputs;
    }
    
    public List<Command> getCommands() {
        return this.commands;
    }

    public int getCommandsSize() {
        return this.commands.size();
    }

} 