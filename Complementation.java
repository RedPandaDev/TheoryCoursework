import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Complementation {

	// Code that runs when program is started
	public static void main(String[] args) throws Exception {

        // Makes sure user specified text file to be used
		if (args.length != 1) 
        {
        	System.out.println("ERROR: Please specify TEXT file to be used.");
        	System.out.println("E.g. type in: java Complementation \"D1.txt\" ");
            System.exit(0);
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
        	Path path = Paths.get(filename);
			long totalLines = Files.lines(path).count() ;

	        Scanner scan = new Scanner(file);
	        while(scan.hasNextLine()){
                // line 2 is states
                // last line accept state
                lineNum++;

	        	String line = scan.nextLine();

                if (lineNum == 2){
                    allStates = line;
                }

                if (lineNum == totalLines-1){

                	line = scan.nextLine();

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
                    System.out.println(allStatesSet.size());
                    for(String n: allStatesSet){
                        System.out.print(n+" ");
                    }
                    
                }
                else{
                	System.out.println(line);
                }
	        }            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}