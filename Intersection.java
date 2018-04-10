import java.util.*;
import java.io.*;

public class Intersection {

	// Code that runs when program is started
	public static void main(String[] args) throws Exception {

        // Makes sure user specified text file to be used
		if (args.length != 2) 
        {
        	System.out.println("ERROR: Please specify TWO TEXT files to be used.");
        	System.out.println("E.g. type in: java Intersection \"D1.txt\" \"D2.txt\"");
            System.exit(0);
        }
		readLines(args[0]);
        readLines(args[1]);

	}

	public static void readLines(String filename)
    {   
        System.out.println("DFA: "+filename);
        File file = new File(filename);
        int lineNum = 0;
        String allStates = "";
        try
        {
	        Scanner scan = new Scanner(file);
	        while(scan.hasNextLine()){

	        	String line = scan.nextLine();

	        	System.out.println(line);

	        }            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}