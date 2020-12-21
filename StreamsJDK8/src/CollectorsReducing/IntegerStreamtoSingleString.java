package CollectorsReducing;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerStreamtoSingleString {

	public static void main(String[] args) {
        
		// with identity and mapping  
		//<T,U> Collector<T,?,U> reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)
		
		// Output 5102050 in string
		//without parallel
		Stream<Integer> s = Stream.of(5, 10, 20, 50);
        String str = s.collect(Collectors.reducing(
                            "Go ",
                            x -> Integer.toString(x),
                            (s1, s2) -> s1 + s2));
        System.out.println(str);
        
        //with parallel
        s = Stream.of(5, 10, 20, 50).parallel();
        str = s.collect(Collectors.reducing(
                            "Go ",
                            x -> Integer.toString(x),
                            (s1, s2) -> s1 + s2));
        System.out.println(str);
        
        //null checking
        s = Stream.of(5, 10, 20, 50, null).parallel();
        str = s.filter(x->x!=null).collect(Collectors.reducing(
                            "Go ",
                            x -> Integer.toString(x),
                            (s1, s2) -> s1 + s2));
        System.out.println(str);
	}

}
