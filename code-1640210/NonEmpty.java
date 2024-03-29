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
        	System.out.println("ERROR: Please specify TEXT file to be used.");
        	System.out.println("E.g. type in: java NonEmpty \"D1.txt\"");
            System.exit(0);
        }
        ArrayList<String> file = new ArrayList<String>();
        file = readLines(args[0]);

        String[] states = file.get(2).split("\\s+");
        for (int i =0; i< Integer.valueOf(file.get(1)); i++) {
        	input.put(states[i],file.get(i+5).split("\\s+"));
        }
        int numLines = Integer.valueOf(file.get(0));

        ArrayList<String> path = new ArrayList<String>();
        path = search(input, file.get(numLines-2), file.get(numLines).split("\\s+"));
        if (path == null) {
        	System.out.println("Language "+args[0]+" empty.");
        	
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
			System.out.println("Language non-empty - "+transitions+" accepted");
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
