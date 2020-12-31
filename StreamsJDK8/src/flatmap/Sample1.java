/*
How to convert a file with multiple sentences into a stream of distinct words		
*/		
package flatmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Sample1 {

	public static void main(String args[]) {
		//Find the distinct words in a file
		//Path path = new File("flatmap\\test.txt").toPath();
		String[] strArray = new String[3];
		strArray[0] = "This is a test file";
		strArray[1] = "This test file is";
		strArray[2] = "a demo of flatMap";

		//Traditional Approach
		Set <String>setOfWords = new HashSet();

		//List<String> lines = Files.readAllLines(path);

		for(String line: strArray) {
			String[] words = line.split("\\s+");

			for(String word: words) {
				setOfWords.add(word);
			}
		}

		System.out.println("Distinct words from traditional approach is "+setOfWords);
		
		
		System.out.println("\n\nDistinct words using stream stream approach is ");
		//Stream Approach
		//Arrays.stream(strArray)
		Stream.of(strArray)
		.filter( x -> x!=null )
		.map(x->x.split("\\s+"))
		.flatMap(x -> Arrays.stream(x) )
		.filter( x -> x!=null )
		.distinct()
		.sorted()
		.forEach(System.out::println);
		 
							
	}

}
