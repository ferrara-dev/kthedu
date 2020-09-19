package plotting;

import util.algorithmtest.CmdRunner;

import java.io.IOException;


public class ComparisonPlotter {
    private final String scriptPath =  "C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\scripts\\plot_script.py";

    public static void main(String...args){

    }

    public static void createGraphs(String arg1, String graphName) throws IOException {
        CmdRunner cmdRunner = CmdRunner.startProcess()
                .commands("python",
                        "C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\scripts\\plot_script.py",
                        arg1,
                        graphName);
        cmdRunner.run();
    }

    public static void plotTestResult(String dataSrc, String plotScriptPath, String plotTitle) throws IOException {
        CmdRunner cmdRunner = CmdRunner.startProcess()
                .commands("python",
                        plotScriptPath,
                        dataSrc,
                        plotTitle);
        cmdRunner.run();
    }
}
