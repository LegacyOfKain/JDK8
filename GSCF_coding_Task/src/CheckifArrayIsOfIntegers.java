import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

public class CheckifArrayIsOfIntegers {

	public static void main(String[] args) {
		String arrayOfNumbers[] = {"33","55","77"};
		//int a = Integer.parseInt("dd");
		System.out.println(NumberUtils.isParsable("77d"));
		
		Stream.of(arrayOfNumbers).forEach(System.out::println);
		boolean allNumbers = Stream.of(arrayOfNumbers).allMatch(e->NumberUtils.isParsable(e));
		System.out.println(allNumbers);
		
		System.out.println("-----------------------");
		
		String[] stringArray = "114 214 219 9".split(" ");
		Arrays.stream(stringArray)
				.map(x-> Integer.parseInt(x))
				.sorted()
				  .collect(Collectors.toList())
				  .forEach(System.out::println);
				  ;
		
		

	}

}
