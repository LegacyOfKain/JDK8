package MapToList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sample1 {

	public static void main(String[] args) {
		
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        System.out.println("\n1. Export Map Key to List...");

        List<Integer> result = map.keySet().stream()
                .collect(Collectors.toList());

        result.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");

        List<String> result2 = map.values().stream()
                .collect(Collectors.toList());

        result2.forEach(System.out::println);

        System.out.println("\n3. Export Map Value to List..., say no to banana");
        List<String> result3 = map.values().stream()
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        result3.forEach(System.out::println);
	}

}
