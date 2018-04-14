import java.util.*;
import java.io.*;
import java.nio.file.*;

public class nonemptyness {

	// Code that runs when program is started
	public static void main(String[] args) throws Exception {

        // Makes sure user specified text file to be used
		if (args.length != 1) 
        {
        	System.out.println("ERROR: Please specify TEXT file to be used.");
        	System.out.println("E.g. type in: java nonemptyness \"D1.txt\"");
            System.exit(0);
        }

        ArrayList<String> firstFile = new ArrayList<String>();
        firstFile = readLines(args[0]);
        String outcome = makeIntersection(firstFile);

        printOutcome(args[0], outcome);

	}

    private static String makeIntersection(ArrayList<String> theDFA){ 
        String outcome = "language empty";
        //System.out.println(firstFile+"\n"+secondFile);
        try{
          

        }catch(Exception e){
            System.out.println("Check the file provided is correct");

        }
        return outcome;
        
    }

    private static void printOutcome (String filename1, String outcome){
        System.out.println("L("+filename1+"):");
        System.out.println(outcome);

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