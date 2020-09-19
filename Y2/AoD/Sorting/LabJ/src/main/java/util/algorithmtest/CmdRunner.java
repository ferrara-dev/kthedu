package util.algorithmtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CmdRunner {
    private static final String cmd = "C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\java\\util\\algorithmtest\\plot_script.py";
    private static CmdRunner instance;
    private ProcessBuilder processBuilder;
    private List<String> commands;

    private CmdRunner() {

    }

    private static CmdRunner getInstance() {
        if (instance == null) {
            synchronized (CmdRunner.class) {
                if (instance == null) {
                    instance = new CmdRunner();
                }
            }
        }
        return instance;
    }

    public static CmdRunner startProcess() {
        CmdRunner cmdRunner = getInstance();
        cmdRunner.processBuilder = new ProcessBuilder();
        cmdRunner.commands = new ArrayList<>();
        cmdRunner.commands.addAll(Arrays.asList(new String[]{"cmd.exe", "/c", "start"}));
        return cmdRunner;
    }

    public static CmdRunner runPythonScript(String s){
        CmdRunner cmdRunner = getInstance();
        cmdRunner.processBuilder = new ProcessBuilder();
        cmdRunner.commands = new ArrayList<>();
        cmdRunner.commands.addAll(Arrays.asList(new String[]{"python", s}));
        return cmdRunner;
    }

    public CmdRunner commands(String...cmds){
        if(Objects.nonNull(processBuilder)) {
            for(String command: cmds)
                this.commands.add(command);
        }
        return this;
    }

    public CmdRunner commands(List<String> cmds){
        if(Objects.nonNull(processBuilder)) {
            for(String command: cmds)
                this.commands.add(command);
        }
        return this;
    }

    public CmdRunner workingDirectory(File workingDirectory) {
        if (Objects.nonNull(processBuilder))
            processBuilder.directory(workingDirectory);
        return this;
    }

    public CmdRunner openPrompt() throws IOException {
        String[] command = {"cmd.exe", "/c", "start"};
        if(Objects.nonNull(processBuilder)) {
            processBuilder.command(command);
        }
        return this;
    }

    public int run() throws IOException {
        processBuilder.redirectErrorStream(true); //redirect STD ERR to STD OUT
        processBuilder.command(commands);
        Process process = processBuilder.start();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("std-out-line: " + line);
            }
        }
        int outputVal = 0;
        try {
            outputVal = process.waitFor();
            process.destroy();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format("outputVal: %d\n", outputVal);
        return outputVal;
    }

    private static class CmdRunnerTestClass{
        public static void main(String... args) throws IOException {
          //  Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "start"});
          //viewDirectory();
           runPython();
        }

        private static void runPython() throws IOException {
            String s = "C:\\Users\\root\\Desktop\\Programming\\pythonscripts\\plot_script.py";
            CmdRunner cmdRunner = CmdRunner.startProcess()
                    .commands("python", s);
            cmdRunner.run();

        }

        private static String getPath() {
            File f = new File("temp");
            String absolutePath = f.getAbsolutePath().replace("temp", "");
            return absolutePath;
        }
    }

} 