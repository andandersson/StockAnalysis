package Controller;

import javax.swing.JFrame;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;  

import View.*;
import Model.*;

public class DataController {
	
	private Gui w = new Gui(this);
	private DataFetcher df = new DataFetcher();
	
	private JOptionPane j;
	private JFrame f;
	private StringBuffer data = new StringBuffer();
	private int startNum;
	private int stopNum;
	private String startDate;
	private String stopDate;
	private String[][] arrayData;
	private StringBuffer closePrices;
	private int choice;
	private StringBuffer outPut;
	
	public String getStartDate() {	
		//need the userinput for the startdate
		return w.start.getText();
		
	}
	public String getStopDate() {	
		//and stopdate
		return w.stop.getText();
		
	}
 
	public StringBuffer getData() {
		//returning the data in the csv-file as a stringbuffer
		return df.fetchData();
	}	

	public int getDateNum(String date, StringBuffer dataStr, String[][] datArr) {
		
		/*
		 * 		returning the row in the nested array for where the startdate 
		and stopdate are.
		It is looping through the first element in all rows.
		it is returning the number for the input,
		if there is no input, or if the input is in wrong format, it is returning 0
		 * 
		 */
		

		
		closePrices = dataStr;
		String [][]priceArr = datArr;
	
		int num;
		for(int row = 0; row<priceArr.length; row++) {
			
			if(priceArr[row][0].equals(date)) {
				
				num = row;
				return num ;
				
		
				}
			}
		return 0;
		
	
	}
	
	
	public void sortArray(String[][] arr, int length) {	
		
		//this is a method for sorting the arrays
		String[] temporary = new String[length];  
	
	
	        for (int i = 0; i < arr.length; i++) {     
	        	for (int j = i+1; j < arr.length; j++) {
	        		float sort =Float.parseFloat(arr[i][1]);  
	        		float sortTwo =Float.parseFloat(arr[j][1]); 

            		if(sort < sortTwo) {   
            			temporary = arr[i];    
            			arr[i] = arr[j];    
            			arr[j] = temporary;    
                   } 
            	} 	
             }     
    }
	
	public String[][] bestOpPric(int start, int stop, String[][] arr){
		/*
		 * in this method, we are returning which dates had the best opening price compared to 5 days
	simple moving average (SMA 5)?
		 * 
		 */
		

		int temp = (start-stop) +1; //getting the length of new array
		String[][] bestOpArr = new String[temp][2];   //new nested array for storing the data
		float countClose = 0;							//counter for storing closing price
		float average;		//average value for closing price, day N-1 to day N-5
		
		float percentageChange;			//this is the percentage change between average and opening price for the day
		for(int i = temp; i>0; i--) {
			bestOpArr[i-1][0] = arr[i + stop-1][0];
							
			float opening = Float.parseFloat(arr[i][3]);
			for(int j = i+1; j<(i+6); j++) {	
					float close = Float.parseFloat(arr[j+stop-1][3]);
					countClose = countClose + close;
					if(j == i+5) {						
						average = countClose/5;
						countClose = 0;
						percentageChange= ((average - opening)/average)*100;
						String perChange = String.valueOf(percentageChange);
						bestOpArr[i-1][1] = perChange;				
				}				
			}	
		}
		
		sortArray(bestOpArr, 2);
		
		return bestOpArr;
	}

	
	public int closingPrices(int start, int stop, String[][] arr) {
		
		//Here we are counting the longest upwarding range of closing prices

		int counterTwo = 0;		
		int counterOne = 0;

		for (int i = start; i>= stop+1; i--) {
				
				float current =Float.parseFloat(arr[i][1]);  
				float next =Float.parseFloat(arr[i-1][1]); 				
				if(next > current ) {
					counterOne++;
//			
				}		
				else {
					if(counterOne> counterTwo) {
						counterTwo = counterOne;
					
					}
					counterOne = 0;
					
				}
			}
		
		
	
		return counterTwo;
	
	}
	
	

	public String[][] tradingVolumes(int start, int stop, String[][] arr) {
	//returning the trading volumes, and most signigicant stock price changes
	 
		int temp = (start-stop) +1;
		String[][] tradeData = new String[temp][3];

		for(int i = temp; i>0; i--) {
	
			
			float high =Float.parseFloat(arr[i][4]);  //
			float low =Float.parseFloat(arr[i][5]);
			float change = Math.abs(high-low); //the absolute value of price change
			String prChange = String.valueOf(change);
			
			tradeData[i-1][0] = arr[i + stop-1][0];
			tradeData[i-1][1] = arr[i + stop-1][2];
			tradeData[i-1][2] = prChange;
	
			}
	
		

		String[] temporary = new String[3];    
      
		sortArray(tradeData, 3);
		
		for (int i = 0; i < tradeData.length; i++) {   
			//A nested array for accessing both the rows and columns
            for (int j = i+1; j < tradeData.length; j++) {
            	int volume =Integer.parseInt(tradeData[i][1]);  
            	int volumeTwo =Integer.parseInt(tradeData[j][1]); 
            	
            	if (volume == volumeTwo){  
            		float change =Float.parseFloat(tradeData[i][2]);  
                	float changeTwo =Float.parseFloat(tradeData[j][2]); 
                	
                  	
                	if(change < changeTwo) {   
                		
                       temporary = tradeData[i];    
                       tradeData[i] = tradeData[j];    
                       tradeData[j] = temporary;    
                   } 	
            	}
            	

            }
        }

      return tradeData;
		
		}
	
	public void printOnConsole() {
	
//		this method is called whenever the "fetch"-button is pressed
		
			clearConsole();

			arrayData = df.createStockArray(getData());
			startDate= getStartDate();
			stopDate = getStopDate();
			startNum = getDateNum(startDate, data, arrayData);
			stopNum = getDateNum(stopDate, data, arrayData);
			choice = w.ab.getSelectedIndex();
			
			
			if(choice == 0) {
				f = new JFrame();
				j = new JOptionPane();
				j.setVisible(true);
				j.showMessageDialog(f, "Please choose a parameter from the dropdownmenu!");
				
			}	
			
			
			else if(startNum==0 || stopNum==0) {
				JFrame frame = new JFrame();
				JOptionPane jo = new JOptionPane();
				jo.setVisible(true);
				jo.showMessageDialog(f, "Please insert dates between 01/21/2020 and 01/20/2021!" +"\n"+"Please also note that there are no data for weekeend days!");
				
			}
			else {
				if(choice == 1) {

					
					int closeNum = closingPrices(startNum, stopNum, arrayData);
					outPut = new StringBuffer( "Between "+ startDate + " and " + stopDate +"\n"+"the closingprice was increasing for maximum" + closeNum + " days in a row");

					w.townInfo.printData(outPut);
			
					
				}
				else if(choice == 2) {
					String[][] outPut = tradingVolumes(startNum, stopNum, arrayData);
					StringBuffer outPutBuff = new StringBuffer("DATE              VOLUME        PRICECHANGE"+"\n");
					for(int i = 0; i<outPut.length; i++) {
						for (int j = 0; j<outPut[i].length; j ++) {
							outPutBuff.append(outPut[i][j].toString());
							outPutBuff.append("   ");
							if(j ==outPut[i].length-1) {
								outPutBuff.append("\n");
							}
						}
					}
					w.townInfo.printData(outPutBuff);					
				}
				else {
					
					if(startNum>248) {
						JFrame fr = new JFrame();
						JOptionPane jop = new JOptionPane();
						jop.setVisible(true);
						jop.showMessageDialog(f, "Unfortunately we are not able to count the range from this starting date, "+"\n"+ " the first possible is 01/28/2020!");
						
					}
					else {
						String[][] outPut = bestOpPric(startNum, stopNum, arrayData);
						StringBuffer outPutBuff = new StringBuffer("DATE                 PRICECHANGE (%) "+"\n");
						for(int i = 0; i<outPut.length; i++) {
							for (int j = 0; j<outPut[i].length; j ++) {
								outPutBuff.append(outPut[i][j].toString());
								outPutBuff.append("      ");
								if(j ==outPut[i].length-1) {
									outPutBuff.append("\n");
								}
							}
						}
						w.townInfo.printData(outPutBuff);
						
					}
					
				}
			}	
		}

	public void clearConsole() {
//		This method is called whener the "clear-Button" is pressed
		w.townInfo.clearData();
		
	}
	
	

}
