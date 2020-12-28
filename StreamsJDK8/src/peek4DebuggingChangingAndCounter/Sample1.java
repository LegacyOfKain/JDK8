package peek4DebuggingChangingAndCounter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample1 {

	public static void main(String[] args) {

		/*
		The snippet below produces no output. 
		The reason peek() didn't work in our first example is that it's an intermediate operation 
		and we didn't apply a terminal operation to the pipeline. 
		Alternatively, we could have used forEach() with the same argument to get the desired behavior:		
		*/
		Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
		nameStream.peek(System.out::println);
		
		/*
			peek()‘s Javadoc page says: “This method exists mainly to support debugging, 
			where you want to see the elements as they flow past a certain point in a pipeline“.
		 */

		Stream.of("one", "two", "three", "four")
		  .filter(e -> e.length() > 3)
		  .peek(e -> System.out.println("Filtered value: " + e))
		  .map(String::toUpperCase)
		  .peek(e -> System.out.println("Mapped value: " + e))
		  .collect(Collectors.toList());
		
		/*
		On top of that, peek() can be useful in another scenario: when we want to alter the inner state of an element. 
		For example, let's say we want to convert all user's name to lowercase before printing them:
		Alternatively,we could have used map(), but peek() is more convenient since we don't want to replace the element.
			*/
		Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
		userStream.peek(u -> u.setName(u.getName().toLowerCase()))
		  .forEach(System.out::println);
		
		
		/*
	    Since Java 9, if JDK compiler is able computing the count directly from the stream (optimization in Java 9),
	    it didn’t traverse the stream, so there is no need to run peek() at all.
	    To force the peek() to run, just alter some elements with filter() or 
	    switch to another terminal operation like collect()
		*/
	    long count = Arrays.asList("A", "B", "C", "D").stream()
	            .filter(x->!x.isEmpty())
	            .peek(System.out::println)
	            .count();

	    System.out.println(count); // 4

	}

}

class User
{
	String name;

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
