package StreamToList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample1 {

	public static void main(String[] args) {

		//Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);
		
		//To avoid creating streams every time in code
		Supplier<Stream<Integer>> streamSupplier  = () -> Stream.of(1, 2, 3, 4, 5);
		// streamSupplier.get() creates a new stream every time -> 
		// this is to avoid the exception that stream is already closed once parsed
		
		
		//Old way by parsing using Stream.foreach
		List<Integer> result1 = new ArrayList<>();
		// (Integer x) is written as x
		//number.forEach((Integer x)->{
		streamSupplier.get().forEach((Integer x)->{
			result1.add(x);
		});
		result1.forEach(x -> System.out.println(x));

		
		
		 System.out.println("-------------New way-----");
		//New way by using Stream.collect
        List<Integer> result2 = streamSupplier.get()
        							.filter(x ->  x != 3 )
        							.collect(Collectors.toList());

        result2.forEach(x -> System.out.println(x));
        
	}

}
