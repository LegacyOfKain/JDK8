import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

public class CalculalateWallpaper {

	public static void main(String[] args) {

		String fileName = "input1.txt";
		List <String>dimensionList = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

        	//first read the values from the text file into a list
        	dimensionList = stream
	            	.filter(e->e!=null)
	            	//.map(e->e.split("x"))
	            	.filter(f->	{
	            		             String sides[] = f.split("x");
	            		             if( sides.length==3 && Stream.of(sides).allMatch(e->NumberUtils.isParsable(e)  ) )
	            		            		 return true;
	            		             else
	            		            	 return false;
	            				}
	            			)
 
	            	
	            	.collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // calculate number of total square feet of wallpaper the company should order for all rooms from input
        System.out.println("total square feet of wallpaper the company should order = ");
        Integer totalArea =
        					dimensionList.stream()
        						.map(CalculalateWallpaper::getArea )       						     						 
        						.reduce(0, Integer::sum);
        		
        System.out.println(totalArea);
        
        // list all rooms from input that have a cubic shape (order by total needed wallpaper descending)
        System.out.println("\nall rooms from input that have a cubic shape (ordered by total needed wallpaper descending)= ");
        
        					dimensionList.stream()
        						.filter(f->	{
				            		             String sides[] = f.split("x");
				            		             if( Integer.parseInt(sides[0]) == Integer.parseInt(sides[1])
				            		            		 && Integer.parseInt(sides[1])==Integer.parseInt(sides[2]) )
				            		            	 return true;
				            		             else
				            		            	 return false;
	            							}
        								)
        						.sorted( Comparator.comparingInt(CalculalateWallpaper::getArea ) )
        						.collect(Collectors.toList())
        						.forEach(System.out::println);
        					
        // list all rooms from input that have a cubic shape (order by total needed wallpaper descending)        					
        System.out.println("\nall rooms from input that are appearing more than once (order is irrelevant)= ");

        					dimensionList.stream()
        						.map(CalculalateWallpaper::getSortedOrder)
        						.collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ) )
        						.entrySet()
        						.stream()
        						.filter( p -> p.getValue() > 1 )
        						.map( Map.Entry::getKey )
        						.collect( Collectors.toList() )
        						.forEach(System.out::println);
        						
	}
	
	
	
	public static Integer getArea(String dimensions)
	{
		  
		List <Integer> dList =	StringToInList (dimensions); 
		
		return (3*dList.get(0)*dList.get(1)+ 
					2*(dList.get(1)*dList.get(2) + dList.get(0)*dList.get(2) ));
					 
				
	}
	
	public static String getSortedOrder(String dimensions)
	{
		  
		List <Integer> dList =	StringToInList (dimensions);
		
		return ( dList.get(0)+"x"+dList.get(1)+"x"+dList.get(2) );
					 
				
	}
	
	public static List <Integer> StringToInList(String dimensions)
	{
		return	
				Stream.of(dimensions.split("x"))
						.map(x-> Integer.parseInt(x))  				
						.sorted()       		            			 
						.collect(Collectors.toList())  ; 
	}

}
