package CollectorsReducing;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiplyandSumIntegers {

	public static void main(String[] args) {

		//Without identity
		//<T> Collector<T,?,Optional<T>> reducing(BinaryOperator<T> op)
		// orElse converts Optional<Integer> to Integer
		Stream<Integer> s = Stream.of(5, 10, 20, 50,null);
        Integer i = s.filter(x->x!=null).collect(Collectors.reducing((integer, integer2)
                            -> integer2 + integer))
                     .orElse(-1);

        System.out.println("sum = "+ i);
        
		s = Stream.of(5, 10, 20, 50);
        i = s.collect(Collectors.reducing((integer, integer2)
                            -> integer2 - integer))
                     .orElse(-1);

        System.out.println(i);
        
        s = Stream.of(5, 10, 20, 50,null);
        i = s.filter(x->x!=null).collect(Collectors.reducing((integer, integer2)
                            -> integer2 * integer))
                     .orElse(-1);

        System.out.println("product = "+ i);
		
        //With identity
        //<T> Collector<T,?,T> reducing(T identity, BinaryOperator<T> op)
		s = Stream.of(5, 10, 20, 50);
        i = s.collect(Collectors.reducing(1, (integer, integer2)
                            -> integer2 * integer));
        System.out.println("product = "+ i);

	}

}
