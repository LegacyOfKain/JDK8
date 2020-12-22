package GroupingBy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample1 {

	public static void main(String[] args) {

		/*********************************************************************************
			Group by a List of Strings and display the total count of it. And then sort it
		 *********************************************************************************/
		List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                //Function.identity(), Collectors.counting()
                        		//x->x,Collectors.counting()
                        		x->{return x;},Collectors.counting()
                        )
                );

        System.out.println(result);

        //Sort a map and add to finalMap
        
        final Map<String, Long> finalMap = new LinkedHashMap<>();
        
        //sort by count descending
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
        
        
        final Map<String, Long> finalMap2 = new LinkedHashMap<>();
        
        //sort by fruit name descending
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByKey())
                .forEachOrdered(e -> finalMap2.put(e.getKey(), e.getValue()));

        System.out.println(finalMap2);
        
		/*********************************************************************************
          ‘group by’ a list of user defined Objects.
		 *********************************************************************************/
        
        //3 apple, 2 banana, others 1
        List<Item> objList = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        
        //Group by name
        System.out.println("Group by name - User defined objects");
        Map <String,Long> map1 = objList.stream().collect(
                Collectors.groupingBy(Item::getName,Collectors.counting())
        );
        System.out.println(map1);
        
        //Group by + Sum qty
        System.out.println("/Group by + Sum qty - User defined objects");
        Map <String,Integer> map2 = objList.stream().collect(
                Collectors.groupingBy(x->x.getName(),Collectors.summingInt(x->x.getQty()))
        );
        System.out.println(map2);
        
        //Group by + Sum qty*price
        System.out.println("/Group by + Sum qty*price - User defined objects");
        Map <String,Double> map3 = objList.stream()
        											.filter(x->x.getPrice()!=null)
        											.collect(
                Collectors.groupingBy(x->x.getName(),Collectors.summingDouble(x->x.getQty()*x.getPrice().doubleValue()))
        );
        System.out.println(map3);
        
        //Group by Price – Collectors.groupingBy and Collectors.mapping example.
        System.out.println("Group by Price – Collectors.groupingBy - User defined objects");
        Map<BigDecimal, List<Item>> map4 = objList.stream()
				.filter(x->x.getPrice()!=null)
				.collect(
							Collectors.groupingBy(x->x.getPrice())
						);
        System.out.println(map4);
        
        System.out.println("Group by Price – Collectors.groupingBy and Collectors.mapping - User defined objects");
        Map<BigDecimal, Set<String>> map5 = objList.stream()
				.filter(x->x.getPrice()!=null)
				.collect(
							Collectors.groupingBy(x->x.getPrice(),
									Collectors.mapping( x->x.getName(), Collectors.toSet() )
							)
						);
        System.out.println(map5);
        
        System.out.println("Group by Price – Collectors.groupingBy and Collectors.reducing - User defined objects");
        Map<BigDecimal, String> map6 = objList.stream()
				.filter(x->x.getPrice()!=null)
				.collect(
							Collectors.groupingBy(x->x.getPrice(),
									
									/*
									reducing(BinaryOperator<T> op)
									reducing(T identity, BinaryOperator<T> op)
									reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)
									//
									Interface BinaryOperator<T> - both input and output will have the same type
									Type Parameters:
									T - the type of the operands and result of the operator
									*/
									//Collectors.reducing("", x->x.getName(), (x,y)->x +","+y  )  
									Collectors.reducing( "",x->x.getName(), (x,y)-> x +","+y   )  
							)
						);
        System.out.println(map6);
               

	}

}

class Item {

    private String name;
    private int qty;
    private BigDecimal price;
	Item(String name, int qty, BigDecimal price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	int getQty() {
		return qty;
	}
	void setQty(int qty) {
		this.qty = qty;
	}
	BigDecimal getPrice() {
		return price;
	}
	void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [name=" + name + ", qty=" + qty + ", price=" + price + "]\n";
	}
}
