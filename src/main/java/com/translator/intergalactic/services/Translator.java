package com.translator.intergalactic.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;
import java.util.HashMap;
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
   	public String alienWords="", oriAlienWords="", questionType="credit", romanNum="";
    
    public String translate() {
		System.out.println("================= World Favorite Translator ==================");
		System.out.print("What do you wanna ask ? : ");
		Scanner scan = new Scanner(System.in);
		oriAlienWords = scan.nextLine();
		alienWords = oriAlienWords.toLowerCase();

         if(alienWords.toLowerCase().substring(0, 11).equals("how much is")){
    	 	alienWords = alienWords.replace("how much is ", "").replace(" ?", "").replace("?", "");
    	 	questionType="notCredit";
    	 	String translator = translator(alienWords, questionType);
    	 	
    	 	if(translator.equals("error")) {
    	 		return "I have no idea what you are talking about";
    	 	}else {
    	 		return oriAlienWords.substring(12, oriAlienWords.length()).replace(" ?", "").replace("?", "") + " is " + translator;
    	 	}
    	 	
    	 	}else if(alienWords.toLowerCase().substring(0,19).equals("how many credits is")){
     	 	alienWords = alienWords.replace("how many credits is ", "").replace(" ?", "").replace("?", "");
     	 	String[] splittedWords = alienWords.split(" ");
    	 	String translator = translator(alienWords, questionType);
    	 	
    	 	if(translator.equals("error")) {
    	 		return "I have no idea what you are talking about";
    	 	}else {
    	 		return oriAlienWords.substring(20, oriAlienWords.length()).replace(" ?", "").replace("?", "") + " is " + translator + " Credits";
    	 	}
         }else {
        	 return "I have no idea what you are talking about";
         }
    }
    
    public String translator(String alienWords, String questionType) {
    	String unit = "";
	 	String[] splittedWords = alienWords.split(" ");
	 	
    	Map<String, String> spaceStandard = new HashMap<String, String>();
    	spaceStandard.put("glob", "I");
    	spaceStandard.put("prok", "V");
    	spaceStandard.put("pish", "X");
    	spaceStandard.put("tegj", "L");
    	
     	Map<String, Double> creditStandard = new HashMap<String, Double>();
     	creditStandard.put("silver", 17.0);
     	creditStandard.put("gold", 14450.0);
     	creditStandard.put("iron", 195.5);

        if(questionType.equals("credit")) {
    	 	unit = splittedWords[splittedWords.length-1];
        	splittedWords = Arrays.copyOf(splittedWords, splittedWords.length-1);
        }
    	
    	for (int i = 0; i < splittedWords.length; i++) {
        	try{
        		romanNum = romanNum + spaceStandard.get(splittedWords[i]);
        	}catch(NullPointerException e) {
        		return "error";
        	}
		}
        
        Double decimal = 0.0;
        int lastNumber = 0;
        for (int x = romanNum.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNum.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }

        if(questionType.equals("credit")) {
        	double multiplier = creditStandard.get(unit);
        	decimal = decimal*multiplier;
        }
        
    	return Double.toString(decimal);
    }
    


    public static Double processDecimal(int decimal, int lastNumber, Double lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
}