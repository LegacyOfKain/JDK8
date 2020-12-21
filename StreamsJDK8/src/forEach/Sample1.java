package forEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> inputList = new ArrayList();
		inputList.add("Test13"); 
		inputList.add("Test23"); 

		//Using forEach from Java 8
		inputList.forEach((e)->{   
			System.out.println("Items = "+e);
		});
		//Using Streams from Java 8
		inputList.stream().forEach(e -> {
			System.out.println("Items = "+e);
		});

		Map<String, Integer> inputMap = new HashMap<>();
		inputMap.put("Test1", 1);   
		inputMap.put("Test2", 35);    


		//Using forEach from Java 8
		inputMap.forEach((K,V)->{   
			System.out.println("Key = "+K+ " Value = "+V);
		});
		//Using Streams from Java 8
		inputMap.entrySet().stream().forEach(e -> {
			System.out.println("Key = "+e.getKey()+ " Value = "+e.getValue());
		});
	}

}
