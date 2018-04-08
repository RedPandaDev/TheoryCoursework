import java.util.*;
import java.io.*;

public class Complementation {

	// Code that runs when program is started
	public static void main(String [] args) throws Exception {

		if (args.length != 1) 
        {
        	System.out.println("ERROR: Please specify TEXT file to be used.");
        	System.out.println("E.g. type in: java Complementation \"D1.txt\" ");
        	System.exit(-1);
        }
		readLines(args[0]);

	}


	public static void readLines(String filename)
    {
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