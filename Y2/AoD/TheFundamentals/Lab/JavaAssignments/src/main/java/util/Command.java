package util;

public enum Command {
    ADD_LAST("l+"),
    ADD_FIRST("f+"),
    ADD_AT("a+"),
    REMOVE_LAST("l-"),
    REMOVE_FIRST("f-"),
    REMOVE_AT("a-");

    public final String instr;

    Command(String instr) {
        this.instr = instr;
    }
}