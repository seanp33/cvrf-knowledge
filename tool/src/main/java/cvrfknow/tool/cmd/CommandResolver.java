package cvrfknow.tool.cmd;

public interface CommandResolver<I> {

    public Command resolve(I in);
}
