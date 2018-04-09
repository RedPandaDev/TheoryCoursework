import java.util.*;
import java.io.*;

public class Complementation {

	// Code that runs when program is started
	public static void main(String[] args) throws Exception {

        // Makes sure user specified text file to be used
		if (args.length != 1) 
        {
        	System.out.println("ERROR: Please specify TEXT file to be used.");
        	System.out.println("E.g. type in: java Complementation \"D1.txt\" ");
        }
		readLines(args[0]);

	}

	public static void readLines(String filename)
    {   
        System.out.println("DFA that recognises L(M): "+filename);
        File file = new File(filename);
        int lineNum = 0;
        String allStates = "";
        try
        {
	        Scanner scan = new Scanner(file);
	        while(scan.hasNextLine()){
                // line 2 is states
                // last line accept state
                lineNum++;

	        	String line = scan.nextLine();

                if (lineNum == 2){
                    allStates = line;
                }

                if (scan.hasNextLine()){
	        	  System.out.println(line);
                }
                else{

                    String[] allStatesArray = allStates.split("\\s+");
                    String[] acceptStateArray = line.split("\\s+");

                    HashSet<String> allStatesSet = new HashSet<String>();
                    for(String i: allStatesArray){
                        allStatesSet.add(i);
                    }
                 
                    for(String i: acceptStateArray){
                        if(allStatesSet.contains(i)){
                            allStatesSet.remove(i);
                        }
                    }
                 
                    String[] result = new String[allStatesSet.size()];
                    int i=0;
                    for(String n: allStatesSet){
                        System.out.print(n+" ");
                    }
                    
                }
	        }            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}