import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Equivalence {

	// Code that runs when program is started
	public static void main(String[] args) throws Exception {
        HashMap<String,String[]> input = new HashMap<String,String[]>();
        // Makes sure user specified text file to be used
		if (args.length != 2) 
        {
        	System.out.println("ERROR: Please specify TWO TEXT files to be used.");
        	System.out.println("E.g. type in: java Equivalence \"D1.txt\" \"D2.txt\"");
            System.exit(0);
        }
        ArrayList<String> fileOne = new ArrayList<String>();
        ArrayList<String> fileTwo = new ArrayList<String>();

        fileOne = readLines(args[0]);
        fileTwo = readLines(args[1]);

        ArrayList<String> complementOne = new ArrayList<String>();
        ArrayList<String> complementTwo = new ArrayList<String>();

        complementOne = makeComplementation(args[0]);
        complementTwo = makeComplementation(args[1]);

        ArrayList<String> intersectOne = new ArrayList<String>();
        ArrayList<String> intersectTwo = new ArrayList<String>();

        intersectOne = makeIntersection(complementOne, fileTwo);
        intersectTwo = makeIntersection(fileOne, complementTwo);

        // make union of intersectOne - intersectTwo
        ArrayList<String> finalDFA = new ArrayList<String>();
        finalDFA = makeUnion(intersectOne, intersectTwo);

        String[] states = finalDFA.get(2).split("\\s+");
        for (int i =0; i< Integer.valueOf(finalDFA.get(1)); i++) {
            input.put(states[i],finalDFA.get(i+5).split("\\s+"));
        }
        int numLines = Integer.valueOf(finalDFA.get(0));

        ArrayList<String> path = new ArrayList<String>();
        path = search(input, finalDFA.get(numLines-2), finalDFA.get(numLines).split("\\s+"));
        if (path == null) {
            System.out.println("equivalent");
            
        }else{
            String lastStep = "";
            String transitions = "";
            int start = 0;
            for (String step : path) {
                if (start != 0) {
                    if (input.get(lastStep)[0]==step) {
                        transitions = transitions + "a";
                    }
                    else if (input.get(lastStep)[1]==step) {
                        transitions = transitions + "b";
                    }
                }
                lastStep = step;
                start = 1;
            }
            System.out.println("Not equivalent");
        }
        

	}
    private static ArrayList<String> search(HashMap<String,String[]> graph, String startState, String[] endState){
        ArrayList<ArrayList<String>> queue = new ArrayList<ArrayList<String>>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<String> endStates = new ArrayList<String>(Arrays.asList(endState));

        String[] items = graph.get(startState);
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(startState);
        queue.add(temp);

        Boolean first = true;

        while (queue.size() != 0){
            path = queue.get(0);
            queue.remove(0);
            String vertex = path.get(path.size() - 1);
            if (first == false) {
                if (endStates.contains(vertex)){

                    return path;

                }
                else if (!visited.contains(vertex)) {
                    ArrayList<String> newPath = new ArrayList<String>();
                    for (String step : path) {
                            newPath.add(step);

                        }
                    newPath.add(graph.get(vertex)[0]);
                    queue.add(newPath);
                    ArrayList<String> newPath2 = new ArrayList<String>();
                    for (String step : path) {
                            newPath2.add(step);
                        }
                    newPath2.add(graph.get(vertex)[1]);
                    queue.add(newPath2);
                    visited.add(vertex);
                }

            }
            else{
                ArrayList<String> newPath = new ArrayList<String>();
                for (String step : path) {
                        newPath.add(step);

                    }
                newPath.add(graph.get(vertex)[0]);
                queue.add(newPath);
                ArrayList<String> newPath2 = new ArrayList<String>();
                for (String step : path) {
                        newPath2.add(step);
                    }
                newPath2.add(graph.get(vertex)[1]);
                queue.add(newPath2);
                
                first= false;
            }



        }

        return null;
    }
    private static ArrayList<String> makeUnion(ArrayList<String> firstFile, ArrayList<String> secondFile){
        ArrayList<String> unionDFA = new ArrayList<String>();


        //System.out.println(firstFile+"\n"+secondFile);
        try{
            unionDFA.add("0");
            int numStates = Integer.valueOf(firstFile.get(1)) * Integer.valueOf(secondFile.get(1));
            // Number of states:
            unionDFA.add(String.valueOf(numStates));
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
            unionDFA.add(allStates);

            // size of alphabet = 2
                // ----------
            unionDFA.add("2");
            // alphabet = a b 
                // -----------
            unionDFA.add("a b");
            // Transitions
                // -----------
            int linesFirstF = Integer.valueOf(firstFile.get(0));
            int linesSecondF = Integer.valueOf(secondFile.get(0));

            for (int transitionsF1 = 5; transitionsF1 < (linesFirstF-2); transitionsF1++ ){
                String[] oneTransition = firstFile.get(transitionsF1).split("\\s+");

                for (int transitionsF2 = 5; transitionsF2 < (linesSecondF-2); transitionsF2++ ){
                    String[] twoTransition = secondFile.get(transitionsF2).split("\\s+");
                    unionDFA.add(oneTransition[0]+twoTransition[0] + " " + oneTransition[1]+twoTransition[1]);
                }
            }
            // Start state
                // -----------
            unionDFA.add(firstFile.get(linesFirstF-2)+secondFile.get(linesSecondF-2));

            // Number of final states
                // -----------
            

            // Final states
                // -----------
            String[] oneFinal= firstFile.get(linesFirstF).split("\\s+");
            String[] oneAll= firstFile.get(2).split("\\s+");
            String[] twoFinal = secondFile.get(linesSecondF).split("\\s+");
            String[] twoAll = secondFile.get(2).split("\\s+");

            TreeSet<String> allFinalStates = new TreeSet<String>();

            for (String finalState1: oneFinal){
                 for (String finalState2: twoAll){
                   allFinalStates.add(finalState1+finalState2);
                    
                }
            }
            for (String finalState1: twoFinal){
                 for (String finalState2: oneAll){
                   allFinalStates.add(finalState1+finalState2);
                    
                }
            }
            unionDFA.add(String.valueOf(allFinalStates.size()));
            String unionStates = "";
            for(Object n: allFinalStates){
                if (unionStates == "") {
                    unionStates=String.valueOf(n);
                }else{
                    unionStates = unionStates+ " "+  String.valueOf(n);
                }
            }
            unionDFA.add(String.valueOf(unionStates));
            unionDFA.set(0, String.valueOf(unionDFA.size()-1));
            
            

        }catch(Exception e){
            System.out.println("Check the files provided are correct.");
            System.exit(0);

        }
        return unionDFA;
        
    }


    private static ArrayList<String> makeComplementation(String filename){
        ArrayList<String> outputArray = new ArrayList<String>();

        File file = new File(filename);
        int lineNum = 0;
        String allStates = "";
        try
        {
            Path path = Paths.get(filename);
            long totalLines = Files.lines(path).count();
            
            outputArray.add(String.valueOf(totalLines));

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
                    outputArray.add(String.valueOf(allStatesSet.size()));
                    String complementStates = "";
                    for(String n: allStatesSet){
                        if (complementStates == "") {
                            complementStates=n;
                        }else{
                            complementStates = complementStates+ " "+  n;
                        }
                    }
                    outputArray.add(complementStates);
                    
                }
                else{
                    outputArray.add(line);
                }
            }            

        }
        catch (Exception e) {
            System.out.println("Cannot find specified file.");
            System.exit(0);
        }
        return outputArray;
    }

    private static ArrayList<String> makeIntersection(ArrayList<String> firstFile, ArrayList<String> secondFile){
        ArrayList<String> intersectionDFA = new ArrayList<String>();


        //System.out.println(firstFile+"\n"+secondFile);
        try{
            intersectionDFA.add("0");
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
            intersectionDFA.set(0, String.valueOf(intersectionDFA.size()));
            intersectionDFA.add(allFinalStates);

            
            

        }catch(Exception e){
            System.out.println("Check the files provided are correct.");
            System.exit(0);

        }
        return intersectionDFA;
        
    }

    private static void printArray (String filename1, String filename2, ArrayList<String> intersect){
        System.out.println("DFA that recognises symmetric difference of L("+filename1+") and L("+filename2+"):");
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
            System.out.println("Cannot find specified file.");
            System.exit(0);
        }
        return fileInArray;
    }

}