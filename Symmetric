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

        //System.out.println(firstFile+"\n"+secondFile);
        try{
            int numStates = Integer.valueOf(firstFile.get(1)) * Integer.valueOf(secondFile.get(1));
            // Number of states:
            intersectionDFA.add(String.valueOf(numStates));
            // State names
                // -----------
            String[] file1States = firstFile.get(2).split("\\s+");
            String[] file2States = secondFile.get(2).split("\\s+");

            String allStates = "";

            for(String i: file1States){
                for(String j: file2States){
                    allStates = allStates + i + ""+j +" ";
                }
            }
            intersectionDFA.add(allStates);

            // size of alphabet = 2
                // ----------
            intersectionDFA.add("2");
            // alphabet = a b 
                // -----------
            intersectionDFA.add("a b");
            // Transitions
                // -----------
            int linesFirstF = Integer.valueOf(firstFile.get(0));
            int linesSecondF = Integer.valueOf(secondFile.get(0));

            for (int transitionsF1 = 5; transitionsF1 < (linesFirstF-2); transitionsF1++ ){
                String[] oneTransition = firstFile.get(transitionsF1).split("\\s+");

                for (int transitionsF2 = 5; transitionsF2 < (linesSecondF-2); transitionsF2++ ){
                    String[] twoTransition = secondFile.get(transitionsF2).split("\\s+");
                    intersectionDFA.add(oneTransition[0]+twoTransition[0] + " " + oneTransition[1]+twoTransition[1]);
                }
            }
            // Start state
                // -----------
            intersectionDFA.add(firstFile.get(linesFirstF-2)+secondFile.get(linesSecondF-2));

            // Number of final states
                // -----------
            int numFinalStates = Integer.valueOf(firstFile.get(linesFirstF-1)) * Integer.valueOf(secondFile.get(linesSecondF-1));
            intersectionDFA.add(String.valueOf(numFinalStates));

            // Final states
                // -----------
            String[] oneFinal= firstFile.get(linesFirstF).split("\\s+");
            String[] twoFinal = secondFile.get(linesSecondF).split("\\s+");
            String allFinalStates = "";
            for (String finalState1: oneFinal){
                 for (String finalState2: twoFinal){
                    allFinalStates = allFinalStates + finalState1+finalState2+" ";
                    
                }
            }

            intersectionDFA.add(allFinalStates);
            printArray(filename1, filename2, intersectionDFA);
            

        }catch(Exception e){
            System.out.println("Check the file provided is correct");

        }
        
    }

    private static void printArray (String filename1, String filename2, ArrayList<String> intersect){
        System.out.println("DFA that recognises L("+filename1+") \u2229 L("+filename2+"):");
        for(String arrayLine: intersect){
            System.out.println(arrayLine);
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