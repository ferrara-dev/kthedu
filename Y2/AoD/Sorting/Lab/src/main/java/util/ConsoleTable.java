package util;

import java.util.ArrayList;
import java.util.List;

public class ConsoleTable{

	/*
	 * Modify these to suit your use
	 */
	private final int TABLEPADDING = 4;
	private final char SEPERATOR_CHAR = '-';

	private List<String> headers;
	private List<List<String>> table;
	private ArrayList<Integer> maxLength;
	private int rows,cols;

	/*
	 * Constructor that needs two arraylist
	 * 1: The headersIs is one list containing strings with the columns headers
	 * 2: The content is an matrix of Strings build up with ArrayList containing the content
	 * 
	 * Call the printTable method to print the table
	 */
	public ConsoleTable(List<String> headersIn, List<List<String>> content){
		this.headers = headersIn;
		this.maxLength =  new ArrayList<Integer>();
		//Set headers length to maxLength at first
		for(int i = 0; i < headers.size(); i++){
			maxLength.add(headers.get(i).length());
		}
		this.table = content;
		calcMaxLengthAll();
	}

	public ConsoleTable(){
		this.headers = new ArrayList<>();
		this.table = new ArrayList<>();
		this.maxLength = new ArrayList<>();
	}

	public void setHeaders(List<String> headersIn){
		this.headers = headersIn;
		for(int i = 0; i < headers.size(); i++)
			maxLength.add(headers.get(i).length());
		if(!table.isEmpty())
			calcMaxLengthAll();
	}

	public void setContent(List<List<String>> content){
		this.table = content;
		if(!table.isEmpty() && !headers.isEmpty())
			calcMaxLengthAll();
	}

	public void addRow(List<String> row){
		table.add(row);
	}


	/*
	 * To update the matrix
	 */
	public void updateField(int row, int col, String input){
		//Update the value
		table.get(row).set(col,input);
		//Then calculate the max length of the column
		calcMaxLengthCol(col);
	}
	/*
	 * Prints the content in table to console
	*/ 
	public void printTable(){
		//Take out the
		StringBuilder sb = new StringBuilder();
		StringBuilder sbRowSep = new StringBuilder();
		String padder = "";
		int rowLength = 0;
		String rowSeperator = "";

		//Create padding string containing just containing spaces
		for(int i = 0; i < TABLEPADDING; i++){
			padder += " ";
		}

		//Create the rowSeperator
		for(int i = 0; i < maxLength.size(); i++){
			sbRowSep.append("|");
			for(int j = 0; j < maxLength.get(i)+(TABLEPADDING*2); j++){
				sbRowSep.append(SEPERATOR_CHAR);
			}
		}
		sbRowSep.append("|");
		rowSeperator = sbRowSep.toString();

		sb.append(rowSeperator);
		sb.append("\n");
		//HEADERS
		sb.append("|");
		for(int i = 0; i < headers.size(); i++){
			sb.append(padder);
			sb.append(headers.get(i));
			//Fill up with empty spaces
			for(int k = 0; k < (maxLength.get(i)-headers.get(i).length()); k++){
				sb.append(" ");
			}
			sb.append(padder);
			sb.append("|");
		}
		sb.append("\n");
		sb.append(rowSeperator);
		sb.append("\n");

		//BODY	
		for(int i = 0; i < table.size(); i++){
			List<String> tempRow = table.get(i);
			//New row
			sb.append("|");
			for(int j = 0; j < tempRow.size(); j++){
				sb.append(padder);
				sb.append(tempRow.get(j));
				//Fill up with empty spaces
				for(int k = 0; k < (maxLength.get(j)-tempRow.get(j).length()); k++){
					sb.append(" ");
				}
				sb.append(padder);
				sb.append("|");
			}
			sb.append("\n");
			sb.append(rowSeperator);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	/*
	 * Fills maxLenth with the length of the longest word 
	 * in each column
	 *
	 * This will only be used if the user dont send any data 
	 * in first init
	 */ 
	private void calcMaxLengthAll(){
		for(int i = 0; i < table.size(); i++){
			List<String> temp = table.get(i);
			for(int j = 0; j < temp.size(); j++){
				//If the table content was longer then current maxLength - update it
				if(temp.get(j).length() > maxLength.get(j)){
					maxLength.set(j, temp.get(j).length());
				}
			}
		}
	}
	/*
	 * Same as calcMaxLength but instead its only for the column given as inparam
	 */
	private void calcMaxLengthCol(int col){
		for(int i = 0; i < table.size(); i++){
			if(table.get(i).get(col).length() > maxLength.get(col)){
				maxLength.set(col, table.get(i).get(col).length());
			}
		}
	}

	public static class ConsoleTableTester{

		public ConsoleTableTester(){
			ArrayList<String> headers = new ArrayList<String>();
			headers.add("Row");
			headers.add("Surname");
			headers.add("Lastname");
			headers.add("Age");

			List<List<String>> content = new ArrayList<List<String>>();
			ArrayList<String> row1 = new ArrayList<String>();
			row1.add("1");
			row1.add("Donald");
			row1.add("Duck");
			row1.add("55");
			ArrayList<String> row2 = new ArrayList<String>();
			row2.add("2");
			row2.add("Huey");
			row2.add("Duck");
			row2.add("13");
			ArrayList<String> row3 = new ArrayList<String>();
			row3.add("3");
			row3.add("Dewey");
			row3.add("Duck");
			row3.add("13");
			ArrayList<String> row4 = new ArrayList<String>();
			row4.add("4");
			row4.add("Louie");
			row4.add("Duck");
			row4.add("13");
			content.add(row1);
			content.add(row2);
			content.add(row3);
			content.add(row4);

			ConsoleTable ct = new ConsoleTable(headers,content);
			ct.printTable();
		}

		public static void main(String[] args) {
			new ConsoleTableTester();
		}
	}
}