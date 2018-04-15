import java.util.*;
import java.io.*;
import java.nio.file.*;
 
public class NonEmpty
{ 
	
	
	public static void main(String[] args) throws Exception {
		HashMap<String,String[]> input = new HashMap<String,String[]>();
        // Makes sure user specified text file to be used
		if (args.length != 1) 
        {
        	System.out.println("ERROR: Please specify TWO TEXT files to be used.");
        	System.out.println("E.g. type in: java NonEmpty \"D1.txt\"");
            System.exit(0);
        }
        ArrayList<String> file = new ArrayList<String>();
        file = readLines(args[0]);

        String[] states = file.get(2).split("\\s+");
        for (int i =0; i< Integer.valueOf(file.get(1)); i++) {
        	input.put(states[i],file.get(i+4).split("\\s+"));
        }
        int numLines = Integer.valueOf(file.get(0));
        search(input, file.get(numLines-2), file.get(numLines).split("\\s+"));

	}

	private static void search(HashMap<String,String[]> graph, String startState, String[] endState){
		ArrayList<String> queue = new ArrayList<String>();
		String[] items = graph.get(startState);
		System.out.println(Arrays.toString(items));
		queue.add(startState);

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
