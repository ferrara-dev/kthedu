package util;

import assignment2.BasicStack;
import assignment3.Queue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputInterpreter {
    private String delimiters;
    private Pattern pattern = Pattern.compile("(\\d+)?(f[\\+-]|l[\\+-])");


    public InputInterpreter(String delimiters) {
        this.delimiters = delimiters;
    }

    private String[] splitString(String string) {
        string = match(pattern.matcher(string)).trim();
        return string.split(" ");
    }

    public void setCommands(Command... commands) {
        StringBuilder sb = new StringBuilder();
        List<Command> cmds = new ArrayList<>(Arrays.asList(commands));
        int i = 0;
        for (Command cmd : cmds) {
            sb.append(cmd.instr);
            if (i < commands.length - 1)
                sb.append("|");
            i++;
        }
        this.delimiters = sb.toString();
        pattern = Pattern.compile(this.delimiters);
    }

    public void setDelimiters(String... delimiters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < delimiters.length; i++) {
            sb.append(delimiters[i]);
            if (i < delimiters.length - 1)
                sb.append("|");
        }
        this.delimiters = sb.toString();
        pattern = Pattern.compile(this.delimiters);
    }

    public InputInterpreter() {
        delimiters = "";
    }

    private String match(Matcher matcher) {
        return match(new StringBuilder(), matcher);
    }

    private String match(StringBuilder sb, Matcher matcher) {
        boolean match = matcher.find();
        if (match) {
            match(sb.append(matcher.group() + " "), matcher);
        }
        return sb.toString();
    }

    public Queue<String> evaluateInstructions(String input, Queue<String> queue) {
        String string = match(pattern.matcher(input)).trim();
        String[] instructions = string.split(" ");
        return evaluateInstructions(instructions, queue);
    }

    private Queue<String> evaluateInstructions(String[] instructions, Queue<String> instructionQueue) {
        instructionQueue.disablePrint();
        String pattern = "(\\d+)";
        Pattern p = Pattern.compile(pattern);
        for (String instruction : instructions) {
            String multiple = match(p.matcher(instruction)).trim();
            if (multiple.isEmpty())
                addInstruction(instructionQueue, instruction, 1);
            else if (multiple.chars().allMatch(Character::isDigit))
                addInstruction(instructionQueue, instruction.replace(multiple, ""), Integer.parseInt(multiple));
        }
        return instructionQueue;
    }

    private void addInstruction(Queue<String> instructionQueue, String instruction, int multiple) {
        if (multiple < 1) {
            return;
        }
        instructionQueue.enqueue(instruction);
        addInstruction(instructionQueue, instruction, multiple - 1);
    }


    public Queue<String> readInput(Scanner scanner) {
        Queue<String> commandQueue = new Queue<>();
        String line = scanner.nextLine();
        commandQueue = evaluateInstructions(line, commandQueue);
        return commandQueue;
    }


    public Queue<String> readInput(InputStream inputStream) {
        Queue<String> commandQueue = new Queue<>();
        commandQueue.disablePrint();
        String line;
        try {
            byte[] bytes = inputStream.readAllBytes();
            line = new String(bytes);
            String[] cmds = splitString(line);
            for (String s : cmds)
                commandQueue.enqueue(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandQueue;
    }

    public static void main(String... args) {
        String input = "2addfaddlrmladdf a2dd";
        InputInterpreter inputInterpreter = new InputInterpreter();
        inputInterpreter.setCommands(Command.ADD_LAST, Command.ADD_FIRST);
        String[] s = inputInterpreter.splitString(input);
    }

}
