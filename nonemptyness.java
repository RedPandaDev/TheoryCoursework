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
        String outcome = empty(firstFile);

        printOutcome(args[0], outcome);

	}

    private static String empty(ArrayList<String> theDFA){ 
        String outcome = "language empty";
        //System.out.println(firstFile+"\n"+secondFile);
        try{
            HashMap<String, String[]> stateTransitions = new HashMap<String, String[]>();

            int linesNum = Integer.valueOf(theDFA.get(0));
            String[] states = theDFA.get(2).split("\\s+");

            for (int i = 0; i < Integer.valueOf(theDFA.get(1)); i++ ) {
                stateTransitions.put(states[i],theDFA.get(i+5).split("\\s+"));         
            }
            //System.out.println(theDFA.get(linesNum-2));
            HashMap<String, String[]> queue = new HashMap<String, String[]>();
            queue.put(theDFA.get(linesNum-2),theDFA.get(linesNum-2).split(""));

            //String[] queue = [theDFA.get(linesNum-2), [theDFA.get(linesNum-2)]];
            while (true){
                String vertex = queue

            }




// def bfs_paths(stateTransitions, start, accept):
//     queue = [(start, [start])]
//     while queue:
//         (vertex, path) = queue.pop(0)

//         for next in stateTransitions[vertex] - set(path):
//             if next == accept:
//                 print(path + [next])
//                 return
//             else:
//                 queue.append((next, path + [next]))

// print(bfs_paths(graph, 'Ax', 'Az')) # ['A', 'C', 'F']
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