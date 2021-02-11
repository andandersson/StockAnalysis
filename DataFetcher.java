package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;  



public class DataFetcher {
	
	/*This class is fetching the data from the csv file, converting it to
	 * String, and storing it in a string array. it is also transforming the data
	 * to different outputs
	 * 
	 * 
	 */


	
	
	private String [][] stockArray; //for storing the data in a nested array
	private StringBuffer dataStore;
	private Scanner sc;
	
		
	public String[][] createStockArray(StringBuffer str) {
		//taking the StringBuffer as an argument, creating a nested array
		StringBuffer data = str;
		int rowCount = 1;
		int columnCount = 1;
		
		for(int i = 0; i<data.length(); i++) {
			//here we are taking the stringbuffer as an input, and counting the rows.
			//the rows will determine the length of the array.
		
			String buffer = Character.toString(data.charAt(i));
			
			if(buffer.equals(",")|| buffer.equals("$")) {
				//in order for later calculation we must remove all non numeric values 
				data.deleteCharAt(i);
			}
			if(buffer.equals(" ")&& rowCount == 1) {
				//counting the columns, only need to to that once
				columnCount++;
			}
			
			if(buffer.equals("\n")) {
				//counting the rows
				data.deleteCharAt(i);
				data.replace(i, i, " ");
				rowCount ++;
			}
			
		}
		rowCount --; //in order to eliminate the last row, which is unneccessary
		int count =0;
		stockArray = new String[rowCount][columnCount];
		StringBuffer buff = new StringBuffer();
		int row = 0;
		int col = 0;
		while (count<data.length()) {
			String temp = Character.toString(data.charAt(count)); //string where we store a copy of cuurrent index value
		
			if(!temp.equals(" ")) {
				//if the value is no equal to a white space, 
//				append to stringbuffer
				
				buff.append(temp);

			}
			else {
				stockArray[row][col] = buff.toString();
				col++;
				buff.delete(0, buff.length());
			
				
			}
			if(col== columnCount) {
				col = 0;
				row++;
				
			}
			count ++;
			//going to loop until we are coming to first " "
		
		}

		for(int i = 0; i< rowCount; i++) {
			for(int j = 0; j< columnCount; j++) {
			

			}
		}
	
				return stockArray;
		
		
	}
	public StringBuffer fetchData() {
		dataStore = new StringBuffer();
		
		//this method is fetching the data from the csv file
	
		try {
			sc = new Scanner(new File("Resources/HistoricalQuotes.csv"));
			sc.useDelimiter(",");   //sets the delimiter pattern  
			while (sc.hasNext())  //returns a boolean value  
			{  
  //find and returns the next complete token from this scanner  
			dataStore.append(sc.next()); //we are appending the data to a stringBuffer

			}   
			sc.close();  //closes the scanner  

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		
		return(dataStore);
	} 

		
	}
