package common;

import util.ConsoleTable;
import util.algorithmtest.CmdRunner;
import util.csv.CsvUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparisonService {
    public static void printResult(TestResult testResult){
        List<List<String>> res = testResult.getResults();
        List<String> headers = testResult.getHeaders();
        ConsoleTable consoleTable = new ConsoleTable(headers,res);
        consoleTable.setTitle(testResult.getDescription());
        consoleTable.printTable();
    }

    public static String saveResult(TestResult testResult){
        String filePath = "./src/main/resources/testresults/";
        String fileName = testResult.getDescription().replace(" ", "").replace(":","") + ".csv";
        List<List<String>> result = testResult.getResults();
        result.add(0,testResult.getHeaders());
        CsvUtil.write(filePath+fileName, result);
        return fileName;
    }


    public static String saveComparison(String fileName, List<List<String>> records){
        String filePath = "./src/main/resources/testresults/";
        fileName = fileName.replace(" ", "").replace(":","") + ".csv";
        CsvUtil.write(filePath+fileName, records);
        return fileName;
    }

    public static void printResult(String title, List<String> headers, List<List<String>> result){
        ConsoleTable consoleTable = new ConsoleTable();
        consoleTable.setTitle(title);
        consoleTable.setHeaders(headers);
        consoleTable.setContent(result);
        consoleTable.printTable();
    }

    public static TestResult runAlgorithmTest(int lowerBound, int upperBound, int trials, Sort...sorts){
        TestResult testResult = new TestResult();
        testResult.addHeader("Number of elements (N)");
        for (Sort sort : sorts)
            testResult.addHeader(sort.toString());

        for (int N = lowerBound; N < upperBound; N += N) {
            double totalTime = 0.0;
            List<String> res = new ArrayList<String>();
            res.add(String.valueOf(N));
            for (Sort sort : sorts) {
                totalTime = AlgorithmCompare.timeRandomInputTotal(sort, N, trials);
                res.add(String.valueOf((int) totalTime));
            }
            testResult.add(res);
        }
        return testResult;
    }

    public static String csvFormat(TestResult testResult){
      return CsvUtil.toCsvFormat(testResult.getResults());
    }

}
