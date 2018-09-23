package com.translator.intergalactic.services;

import java.util.Scanner;
import com.translator.intergalactic.entity.*;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;

/**
 * @author amit@springtutorials.com
 *
 * A dummy service implementation that returns "OK"
 */
@Service
public class Translator{
	private static String errorReturn = "I have no idea what you are talking about.\n";
//	public static String[] romanSymbol = {"I","V","X","L", "C", "D", "M"};
	private static final Set<String> romanSymbols = new HashSet<String>(Arrays.asList(
			new String[] {"I","V","X","L", "C", "D", "M"}));
	private static final Set<String> illegal = new HashSet<String>(Arrays.asList(
			new String[] {"VV","LL","DD","IIII", "XXXX", "CCCC", "MMMMM"}));
   	public String questionType="credit";
	Dictionary dictionary = new Dictionary();
    
    public String translate() {
    	String reply = "";
		System.out.println("================= World's Favorite Translator ==================");
		System.out.print("Ask me anything! : ");
		Scanner scan = new Scanner(System.in);
		String[] alienWords = scan.nextLine().split("\\?|!|\\.");
		
		for(int i=0;i<alienWords.length;i++) {
			String plainWords = "";
			alienWords[i] = alienWords[i].trim();
			String[] splitted = alienWords[i].split(" ");
			if(alienWords[i].length() > 5 && splitted.length > 2) {
				if(splitted[1].toLowerCase().equals("is")) {
					if(romanSymbols.contains(splitted[2]))
						dictionary.setLib(splitted[0].toLowerCase(), splitted[2].toUpperCase());
					else
						reply = reply + errorReturn;
					
					reply = reply + splitted[0] + " has been set as " +splitted[2]+ "\n";
				}else if(splitted[splitted.length-3].toLowerCase().equals("is") && !splitted[0].toLowerCase().equals("how")) {
					int valueLength = splitted.length-4;
					String unit = splitted[valueLength];
					
					for(int ii=0;ii<valueLength;ii++)
						plainWords = plainWords + splitted[ii] + " ";

					String translator = translator(plainWords.trim(), "notCredit");

					if(translator.equals("error")) {
						reply = reply + errorReturn;
					}else {
						Double creditValue = Double.parseDouble(splitted[splitted.length-2]);
						dictionary.setStuffLib(unit.toLowerCase(), creditValue/Double.parseDouble(translator));
//						reply = reply + "The value of "+ unit + " has been set!\n";
					}
				}else if(alienWords[i].toLowerCase().substring(0, 11).equals("how much is") && splitted.length > 3){
					plainWords = statementor(alienWords[i], "notCredit");
					String translator = translator(plainWords, "notCredit");
					
					if(translator.equals("error"))
						reply = reply + errorReturn;
					else
						reply = reply + plainWords.trim() + " is " + translator + "\n";
//			 	}else if(alienWords[i].trim().toLowerCase().substring(0,19).equals("how many credits is")){
			 	}else if(alienWords[i].trim().toLowerCase().substring(0,8).equals("how many")){
					String translator = translator(alienWords[i], "credit");
					if(translator.equals("error"))
						reply = reply + errorReturn;
					else
						reply = reply + translator;
				}else {
					reply = reply + errorReturn;
				}
			}else {
				if(alienWords[i].toLowerCase().substring(0, 4).equals("exit"))
					return "exit";
				
				reply = reply + errorReturn;
			}
		}
		
		return reply;
	}
    
    String statementor(String plainWords, String qType) {
    	String[] okWords = plainWords.split(" ");
    	if(qType.equals("notCredit")) {
			plainWords = plainWords.substring(12, plainWords.length());
		}else if(qType.equals("credit")) {
			int start = 0;
			plainWords = "";
			for(int i=0;i<okWords.length;i++) {
				if(okWords[i].equals("is"))
					start = i+1;
			}
			
			for(int i=start;i<okWords.length;i++)
				plainWords = plainWords + okWords[i] + " ";
		}
    	
    	return plainWords.trim();
    }
    
    public String translator(String alienWords, String questionType) {
    	String romanNum = "", unit = "";
        if(questionType.equals("credit"))
			alienWords = statementor(alienWords, "credit");
        
	 	String[] splitted = alienWords.split(" ");
	 	
        if(questionType.equals("credit")) {
    	 	unit = splitted[splitted.length-1];
    	 	splitted = Arrays.copyOf(splitted, splitted.length-1);
        }
        
    	for (int i = 0; i < splitted.length; i++) {
    			String value = dictionary.getLib(splitted[i]);
    			
        		if(value.equals("404"))
        			return "error";
        		else
        			romanNum = romanNum + value;
		}
        
    	
        Double decimal = 0.0;
        int lastNumber = 0;
        String lastSymbol = "";
        String noStack = "";

        for (int x = romanNum.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNum.charAt(x);
            if(lastSymbol.equals(String.valueOf(convertToDecimal)))
            	noStack = noStack + convertToDecimal;
            else
            	noStack = String.valueOf(convertToDecimal);
            
            if(illegal.contains(noStack))
            	return "error";

            switch (convertToDecimal) {
                case 'M':
                	if(!lastSymbol.equals("C") && !lastSymbol.equals("") && lastNumber<1000)
                		return "error";
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    lastSymbol = "M";
                    break;

                case 'D':
                	if(!lastSymbol.equals("C") && !lastSymbol.equals("") && lastNumber<500)
                		return "error";
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    lastSymbol = "D";
                    break;

                case 'C':
                	if(!lastSymbol.equals("X") && !lastSymbol.equals("") && lastNumber<100)
                		return "error";
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    lastSymbol = "C";
                    break;

                case 'L':
                	if(!lastSymbol.equals("X") && !lastSymbol.equals("") && lastNumber<50)
                		return "error";
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    lastSymbol = "L";
                    break;

                case 'X':
                	if(lastSymbol.equals("V") && !lastSymbol.equals("") && lastNumber<10)
                		return "error";
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    lastSymbol = "X";
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    lastSymbol = "V";
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    lastSymbol = "I";
                    break;
            }
        }
        
        if(questionType.equals("credit")) {
			Double multiplier = dictionary.getStuffLib(unit.toLowerCase());
			if(multiplier==0.0)
				return "error";
			else
				decimal = decimal*multiplier;
			
			return alienWords.trim() + " " + unit + " is " + decimal + " Credits\n";
        }
        
    	return Double.toString(decimal);
    }

    public static Double processDecimal(int decimal, int lastNumber, Double lastDecimal) {
        if (lastNumber > decimal)
            return lastDecimal - decimal;
        else
            return lastDecimal + decimal;
    }
}