package util;


import java.util.ArrayList;

public class ConsoleTable {
    /*
     * Modify these to suit your use
     */
    private final int TABLEPADDING = 4;
    private final char SEPERATOR_CHAR = '-';
    private List<String> headers;
    private List<List<String>> table = new List<>();
    private List<Integer> maxLength;
    private int rows, cols;
    private String title= "";

    public ConsoleTable(List<String> headers, List<List<String>> content){
        this.headers = headers;
        this.maxLength = new List<Integer>();

        //Set headers length to maxLength at first
        for (int i = 0; i < headers.getSize(); i++) {
            maxLength.add(headers.get(i).length());
        }

        this.table = content;

    }

    /*
     * Fills maxLenth with the length of the longest word
     * in each column
     *
     * This will only be used if the user dont send any data
     * in first init
     */
    private void calcMaxLengthAll() {
        for (int i = 0; i < table.getSize(); i++) {
            List<String> temp = table.get(i);
            for (int j = 0; j < temp.getSize(); j++) {
                //If the table content was longer then current maxLength - update it
                if (temp.get(j).length() > maxLength.get(j)) {
                    maxLength.set(j, temp.get(j).length());
                }
            }
        }
    }

    /*
     * Same as calcMaxLength but instead its only for the column given as inparam
     */
    private void calcMaxLengthCol(int col) {
        for (int i = 0; i < table.getSize(); i++) {
            if (table.get(i).get(col).length() > maxLength.get(col)) {
                maxLength.set(col, table.get(i).get(col).length());
            }
        }
    }

    /*
     * Prints the content in table to console
     */
    public void printTable() {
        calcMaxLengthAll();
        //Take out the
        StringBuilder sb = new StringBuilder();
        StringBuilder sbRowSep = new StringBuilder();
        String padder = "";
        int rowLength = 0;
        String rowSeperator = "";

        //Create padding string containing just containing spaces
        for (int i = 0; i < TABLEPADDING; i++) {
            padder += " ";
        }

        //Create the rowSeperator
        for (int i = 0; i < maxLength.getSize(); i++) {
            sbRowSep.append("|");
            for (int j = 0; j < maxLength.get(i) + (TABLEPADDING * 2); j++) {
                sbRowSep.append(SEPERATOR_CHAR);
            }
        }

        sbRowSep.append("|");
        rowSeperator = sbRowSep.toString();
        String title = formatTitle(rowSeperator.length());

        sb.append(title);
        sb.append("\n");

        //HEADERS
        String hs = formatHeaders(padder);
        sb.append(hs);

        sb.append("\n");
        sb.append(rowSeperator);
        sb.append("\n");

        String tableBody = getBody(padder,rowSeperator);
        sb.append(tableBody);

        System.out.println(sb.toString());
    }

    private String getBody(String padder, String rowSeperator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.getSize(); i++) {
            List<String> tempRow = table.get(i);
            //New row
            sb.append("|");
            for (int j = 0; j < tempRow.getSize(); j++) {
                sb.append(padder);
                sb.append(tempRow.get(j));
                //Fill up with empty spaces
                for (int k = 0; k < (maxLength.get(j) - tempRow.get(j).length()); k++) {
                    sb.append(" ");
                }
                sb.append(padder);
                sb.append("|");
            }
            sb.append("\n");
            sb.append(rowSeperator);
            sb.append("\n");
        }
        return sb.toString();
    }

    private String formatHeaders(String padder){
        StringBuilder headSb = new StringBuilder();
        headSb.append("|");
        for (int i = 0; i < headers.getSize(); i++) {
            headSb.append(padder);
            headSb.append(headers.get(i));
            //Fill up with empty spaces
            for (int k = 0; k < (maxLength.get(i) - headers.get(i).length()); k++) {
                headSb.append(" ");
            }
            headSb.append(padder);
            headSb.append("|");
        }

        return headSb.toString();
    }

    private String formatTitle(int len){
        StringBuilder titleSb = new StringBuilder();
        titleSb.append("|");

        for(int i = 0; i < len-2; i ++){
            titleSb.append("-");
        }
        titleSb.append("|");
        String titleSeparator = titleSb.toString();
        titleSb.append("\n");
        titleSb.append("|");
        for (int k = 0; k < (len - title.length() - 1) / 2; k++) {
            titleSb.append(" ");
        }
        titleSb.append(title);
        for (int k = 0; k < (len - title.length() -1) / 2; k++) {
            titleSb.append(" ");
        }
        titleSb.append("|");
        titleSb.append("\n");
        titleSb.append(titleSeparator);

        return titleSb.toString();
    }
    private String getPadding() {
        String padder = "";
        //Create padding string containing just containing spaces
        for (int i = 0; i < TABLEPADDING; i++) {
            padder += " ";
        }
        return padder;
    }

    private String getRowSeparator() {
        StringBuilder sbRowSep = new StringBuilder();
        String rowSeperator = "";
        //Create the rowSeperator
        for (int i = 0; i < maxLength.getSize(); i++) {
            sbRowSep.append("|");
            for (int j = 0; j < maxLength.get(i) + (TABLEPADDING * 2); j++) {
                sbRowSep.append(SEPERATOR_CHAR);
            }
        }
        sbRowSep.append("|");
        rowSeperator = sbRowSep.toString();
        return rowSeperator;
    }
}
