package peek4DebuggingChangingAndCounter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CounterExample {

	public static void main(String[] args) {

		AtomicInteger runCount = new AtomicInteger(0);

		 ArrayList<String> cars = new ArrayList();
		    cars.add("Volvo");
		    cars.add("BMW");
		    cars.add("Ford");
		    cars.add("Mazda");
		    
		cars.stream()
		.filter(x->x!=null)
		.peek(x->{runCount.incrementAndGet();})
		.collect(Collectors.toList());

		System.out.println("Listcount "+runCount);

	}

}
