//Create a list of all the distinct books from the list of Developer Objects

package flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Sample2WithUserDefinedObjects {

	public static void main(String args[]) {

        Developer o1 = new Developer();
        o1.setName("mkyong");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        
        
        System.out.println("Distinct List of all books");
        // Start Solution
        list.stream()
        	.filter(x->x!=null)
        	.map(x->x.getBook().toArray())
        	.flatMap(x->Stream.of(x))
        	.filter(x->x!=null)
        	.distinct()
        	.sorted()
        	.forEach(System.out::println);
        
        
        	
	}
}
class Developer {

	private Integer id;
	private String name;
	private Set<String> book;

	//getters, setters, toString

	public void addBook(String book) {
		if (this.book == null) {
			this.book = new HashSet<>();
		}
		this.book.add(book);
	}

	public Set<String> getBook() {
		return book;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
