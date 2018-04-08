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
        System.out.println("DFA encoding file: "+filename);
        File file = new File(filename);

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