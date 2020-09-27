package Assignment3;

import Assignment1.TextFilter;
import datastruct.set.SET;
import datastruct.st.LinkedHashTable;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import util.In;
import util.StdIn;

import java.util.ArrayList;
import java.util.List;

public class HashDistribution {
    
    public static void main(String[] args) {
        HashTable hashTable;

        if(args.length > 0){
            hashTable = new HashTable(Integer.parseInt(args[0])); // create hashtable with fixed size
        }
        else
            hashTable = new HashTable(); // create hashtable with scaling size

        collectWords(hashTable);  // Collect all distinct words from Stdin

        long sum = sumChainCount(hashTable); // Sum all of the chain counts

        double mean = (double) sum / (double) hashTable.nrOfEntries(); // get the mean chain-length

        System.out.println(sum);
        System.out.println(hashTable.size());

        System.out.println("Mean: " + mean);

        double variance = 0;

        for (int i = 0; i < hashTable.nrOfEntries(); i++) {
            variance += Math.pow((double) hashTable.getChainLength(i) - mean, 2.0);
        }

        double std = Math.sqrt(variance / (double) hashTable.nrOfEntries());

        System.out.println("Standard Deviation: " + std);

        plot(hashTable);
    }

    private static long sumChainCount(HashTable hashTable){
        long sum = 0;
        for (int i = 0; i < hashTable.nrOfEntries(); i++) {
            sum += hashTable.getChainLength(i);
            System.out.println("Bucket " + i + ": " + hashTable.getChainLength(i));
        }
        return sum;
    }

    private static double variance(HashTable hashTable, double mean){
        double variance = 0;
        for (int i = 0; i < hashTable.nrOfEntries(); i++) {
            variance += Math.pow((double) hashTable.getChainLength(i) - mean, 2.0);
        }
        return variance;
    }

    public static void plot(HashTable hashTable){
        XYChart chart = new XYChartBuilder().width(800).
                height(600).
                theme(Styler.ChartTheme.Matlab).
                title("Matlab Theme").
                xAxisTitle("hash indices").
                yAxisTitle("chain length/collisions").
                build();
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);

        List<Integer> xAxis = new ArrayList<>(); // hashes
        List<Integer> yAxis = new ArrayList<>(); // chainLength

        for (int i = 0; i < hashTable.nrOfEntries(); i++) {
            xAxis.add(i);
            yAxis.add(hashTable.getChainLength(i));
        }

        chart.addSeries("Gaussian 1", xAxis, yAxis);
       // XYChart chart = QuickChart.getChart("Hash function spread", "Hash", "Collisions", "spread", hashes, collisions); // Create Chart
        new SwingWrapper(chart).displayChart(); // Show it
    }

    public static void collectWords(SET<String> set) {
        TextFilter textFilter = new TextFilter();
        String[] words;
        while (!StdIn.isEmpty()) {
            words = textFilter.filterText(StdIn.readLine()).split("\\s");
            for (String word : words)
                if (!set.contains(word)) {
                    set.add(word);
                }
            ;
        }
    }

    public static void collectWords(LinkedHashTable<String, Integer> set) {
        TextFilter textFilter = new TextFilter();
        String[] words;
        while (!StdIn.isEmpty()) {
            words = textFilter.filterText(StdIn.readLine()).split("\\s");
            for (String word : words)
                if (!set.contains(word)) {
                    set.put(word, 1);
                }
            ;
        }
    }


    /**
     * Implementation of {@link LinkedHashTable} Modified to better
     * meet the requirements of this assignment.
     */
    private static class HashTable extends LinkedHashTable<String, Integer> {
        private final boolean withResize;

        public HashTable(int M) {
            super(M);
            withResize = false;
        }

        public HashTable() {
            super();
            withResize = true;
        }

        public int getChainLength(int index) {
            return super.chainLengthAt(index);
        }

        public int nrOfEntries(){
            return super.getM();
        }

        @Override
        protected void resize(int chains) {
            if (withResize)
                super.resize(chains);
        }

        public int hash(String x) {
            return (x.hashCode() & 0x7fffffff) % nrOfEntries();
        }
    }
}