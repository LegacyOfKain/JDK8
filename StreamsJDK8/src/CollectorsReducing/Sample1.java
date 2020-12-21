package CollectorsReducing;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample1 {
	public static void main(String args[])
	{
		//Grouping by first character and summing up the word length
		Map<String, Integer> output = 
				Stream.of("this", "word", "is", "the", "best")
			    .collect(
			    		Collectors.groupingBy
			    		(
			    				x-> x.substring(0, 1),
			    				Collectors.reducing(0, x-> x.length(), (x, y)-> x + y)
			            )
			    );
		

		//Using Streams from Java 8
		output.entrySet().stream().forEach(e -> {
			System.out.println("Key = "+e.getKey()+ " Value = "+e.getValue());
		});
	}
}
