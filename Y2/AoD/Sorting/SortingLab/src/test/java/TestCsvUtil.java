import org.junit.Before;
import org.junit.Test;
import util.CsvUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestCsvUtil {
    private List<List<String>> records;
    private static final String FILEPATH = "./src/main/resources/results/newCsvFile.csv";
    @Before
    public void setUp() throws Exception {
        records = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add("N");
        records.add(row1);
        for(int i = 0; i < 10; i++){
            row1 = new ArrayList<>();
            row1.add(String.valueOf(i));
            records.add(row1);
        }
    }

    @Test
    public void testWrite(){
        CsvUtil.write(FILEPATH,records);
    }


    @Test
    public void testAddColumn(){
        List<String> column = new ArrayList<>();
        column.add("col1");
        for(int i = 0; i < 10; i++){
            column.add(String.valueOf(50 + i));
        }
        CsvUtil.appendColumn(FILEPATH,column);

        column = new ArrayList<>();
        column.add("col2");
        for(int i = 0; i < 10; i++){
            column.add(String.valueOf(34 + i));
        }
        CsvUtil.updateColumn(FILEPATH,column,1);

        column = new ArrayList<>();
        column.add("overwritten");
        for(int i = 0; i < 10; i++){
            column.add(String.valueOf(14 + i));
        }
        CsvUtil.updateColumn(FILEPATH,column,1);
    }

    @Test
    public void testDelete(){
        CsvUtil.deleteRecord(FILEPATH);
    }

}
