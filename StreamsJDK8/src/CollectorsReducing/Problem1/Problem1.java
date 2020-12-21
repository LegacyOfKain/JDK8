package CollectorsReducing.Problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem1 {

	public static void main(String[] args) {
		 
		List<User> list = new ArrayList<>();
		list.add(new User("sam", "java"));
		list.add(new User("sam", "js"));
		list.add(new User("apollo", "html"));
		
		// Solution using two streams
		List<User> l = list.stream()
			    .collect(Collectors.groupingBy(
			         u -> u.name,
			         Collectors.reducing((u1, u2) -> 
			             new User(u1.name, u1.languages + ", " + u2.languages))))
			    .values() // gets the map values only
			    .stream()
			    .filter(user -> user.get() != null)
			    .map(user -> user.get())
			    .collect(Collectors.toList());
		

		l.stream().forEach(e -> {
			System.out.println("User = "+e.name+"  Languages = "+e.languages);
		});
		
		// checking the map
		Map<Object, Optional<User>> output = 
				list.stream()
			    .collect(Collectors.groupingBy(
			         u -> u.name,
			         Collectors.reducing((u1, u2) -> 
			             new User(u1.name, u1.languages + ", " + u2.languages))));

		//Solution Using Collectors.Map
		List<User> outputList2 = new ArrayList<> (
				list.stream()
			    	.collect(Collectors.toMap(	u -> u.name,
			                              		u -> new User (u.name,u.languages),
			                              		(u1, u2) -> new User(u1.name, u1.languages + ", " + u2.languages)))
			    	.values()
			    );
		
		outputList2.stream().forEach(e -> {
			System.out.println("User = "+e.name+"  Languages = "+e.languages);
		});
		
		
		
		
		//Checking list parsing Stream  
		List<String> outputList1 = list.stream().map(x->x.getName()).collect(Collectors.toList()); 
		outputList1.stream().forEach(e -> {
			System.out.println("User List = "+e );
		});
		
		//Checking set parsing Stream  
		Set<String> outputSet1 = list.stream().map(x->x.getName()).collect(Collectors.toSet()); 
		outputSet1.stream().forEach(e -> {
			System.out.println("User Set = "+e );
		});
		
		
	}

}

class User {
	String name;
	String languages;
	
    User() {
		super();
	}
    
    User(String name, String languages) {
		super();
		this.name = name;
		this.languages = languages;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getLanguages() {
		return languages;
	}

	void setLanguages(String languages) {
		this.languages = languages;
	}
    
    
}
