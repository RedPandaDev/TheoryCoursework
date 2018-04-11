import java.util.*;
import java.io.*;
import java.nio.file.*;

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
        makeIntersection(args[0], args[1]);
        

	}

    private static void makeIntersection(String filename1, String filename2){
        ArrayList<String> firstFile = new ArrayList<String>();
        ArrayList<String> secondFile = new ArrayList<String>();
        ArrayList<String> intersectionDFA = new ArrayList<String>();

        firstFile = readLines(filename1);
        secondFile = readLines(filename2);

        System.out.println("DFA that recognises L("+filename1+") \u2229 L("+filename2+"):");
        //System.out.println(firstFile+"\n"+secondFile);
        try{
            int numStates = Integer.valueOf(firstFile.get(1)) * Integer.valueOf(secondFile.get(1));
            // Number of states:
            intersectionDFA.add(String.valueOf(numStates));
            // State names
                // -----------
            // size of alphabet = 2
                // ----------
            // alphabet = a b 
                // -----------
            // Transitions
                // -----------
            // Start state
                // -----------
            // Number of final states
                // -----------
            // Final states
                // -----------
            System.out.println(numStates);

        }catch(Exception e){
            System.out.println("Check the file provided is correct");

        }
        



    }



	private static ArrayList<String> readLines(String filename)
    {   
        ArrayList<String> fileInArray = new ArrayList<String>();

        File file = new File(filename);
        int lineNum = 0;
        String allStates = "";
        try
        {   
            Path path = Paths.get(filename);
            long totalLines = Files.lines(path).count();
            fileInArray.add(String.valueOf(totalLines));
            // Make array list of lists to save each line and number of lines. 
            
	        Scanner scan = new Scanner(file);

	        while(scan.hasNextLine()){

	        	String line = scan.nextLine();
                fileInArray.add(line);

	        }            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return fileInArray;
    }

}