package Casual.Problem1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Problem1 {

	public static void main(String[] args) {

		Student student1 = new Student(
				"Jayesh",
				20,
				new Address("1234"),
				Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

		Student student2 = new Student(
				"Khyati",
				20,
				new Address("1235"),
				Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

		Student student3 = new Student(
				"Jason",
				20,
				new Address("1236"),
				Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

		List<Student> students = Arrays.asList(student1, student2, student3);

		System.out.println("\nGet student with matching address \"jayesh\"");
		/*****************************************************
             Get student with exact match name "jayesh"
		 *****************************************************/
		Student jayesh = students.stream()
				.filter(studentobj -> studentobj!=null)
				.filter(studentobj -> studentobj.getName().equalsIgnoreCase("jayesh"))
				.findFirst().orElse(null);
		System.out.println(jayesh);

		System.out.println("\nGet student with matching address \"1235\"");
		/*****************************************************
            Get student with matching address "1235"
		 *****************************************************/
		Student studentWithAddress = students.stream()
				.filter(studentobj -> studentobj!=null)
				.filter(studentobj -> studentobj.getAddress().getZipcode().equalsIgnoreCase("1235"))  
				.findFirst().orElse(null);
		System.out.println(studentWithAddress);

		System.out.println("\nGet all student having mobile numbers 3333");
		/*****************************************************
            Get all student having mobile numbers 3333.
		 *****************************************************/
		List<Student> studentWith1MobileNumber = students.stream()
				.filter(studentobj -> studentobj!=null)
				.filter(studentobj -> 
				studentobj.getMobileNumbers().stream().anyMatch(o -> o.getNumber().equals("3333"))
						)
				.collect(Collectors.toList());
		;
		studentWith1MobileNumber.forEach(System.out::println);

		/*
			List<List<MobileNumber>> gfgf = students.stream()
            		.filter(studentobj -> studentobj!=null)
            		.map(studentobj -> studentobj.getMobileNumbers())
            		.collect(Collectors.toList());
            ;


            for(int i=0;i<gfgf.size();i++)
            {
            	List<MobileNumber> gf = gfgf.get(i);
            	//System.out.println(gf);
            	boolean isMobileNumberPresent = gf.stream().anyMatch(o -> o.getNumber().equals("3333"));
            	if(isMobileNumberPresent)
            	{
            		System.out.println(gf);
            	}
            }
		 */

		System.out.println("\nGet all student having mobile numbers 1233 and 1234");
		/*****************************************************
            Get all student having mobile numbers 1233 and 1234
		 *****************************************************/
		//including 1233 and 1234
		List<Student> studentWith1MobileNumbers = students.stream()
				.filter(studentobj -> studentobj!=null)
				.filter(studentobj -> 
				studentobj.getMobileNumbers().stream().anyMatch(o -> o.getNumber().equals("1233"))
						)
				.filter(studentobj -> 
				studentobj.getMobileNumbers().stream().anyMatch(o -> o.getNumber().equals("1234"))
						)
				.collect(Collectors.toList());
		;
		studentWith1MobileNumbers.forEach(System.out::println);

		//Or with allmatch
		//Having 1233 and 1234 only
		studentWith1MobileNumbers = students.stream()
				.filter(studentobj -> studentobj!=null)
				.filter(studentobj -> 
				studentobj.getMobileNumbers().stream().
							allMatch(o -> (o.getNumber().equals("1233") || o.getNumber().equals("1234")) )
						)
				.collect(Collectors.toList());
		;
		studentWith1MobileNumbers.forEach(System.out::println);
		
		
		System.out.println("\nCreate a List<Student> from the List<TempStudent>");
		/*****************************************************
            Create a List<Student> from the List<TempStudent>
		 *****************************************************/
		List<TempStudent> tempStudents = students.stream()
				.filter(studentobj -> studentobj!=null)
				.map((Student obj)->new TempStudent(obj.getName(),obj.getAge(), obj.getAddress(), obj.getMobileNumbers()))
				.collect(Collectors.toList());
		
		tempStudents.forEach(System.out::println);
		

		System.out.println("\nConvert List<Student> to List<String> of student name");
		/*****************************************************
        	Create a List<Student> from the List<TempStudent>
		 *****************************************************/
		List<String> nameStringList = students.stream()
				.filter(studentobj -> studentobj!=null)
				.map((Student obj)->obj.getName())
				//.map(Student::getName)
				.collect(Collectors.toList());
		
		nameStringList.forEach(System.out::println);
		
		
		System.out.println("\nConvert List<students> to String");
       /*****************************************************
        	Convert List<students> to String
       *****************************************************/
       String nameString = students.stream()
           .map(Student::getName)
           //.collect(Collectors.joining(","))
           .collect(Collectors.joining(",","[","]"))
           ;
       System.out.println(nameString);
        		   
		System.out.println("\nChange the case of List<String>");
		/*****************************************************
			Change the case of List<String>
		 *****************************************************/
		List<String> nameList = Arrays.asList("Jayesh", "Dany", "Khyati", "Hello", "Mango");
		List<String> nameUppercaseList = nameList.stream()
				.filter(o -> o!=null)
				.map(o->o.toUpperCase())
				//.map(Student::getName)
				.collect(Collectors.toList());
		
		nameUppercaseList.forEach(System.out::println);
		
		System.out.println("\nSort List<String>");
		/*****************************************************
			Sort List<String>
		 *****************************************************/
		List<String> nameSortedList = nameList.stream()
				.filter(o -> o!=null)
				.sorted()
				//.map(Student::getName)
				.collect(Collectors.toList());
		
		nameSortedList.forEach(System.out::println);
		
		System.out.println("\nSort List<Student> using name field");
		/*****************************************************
			Sort List<Student> using name field
		 *****************************************************/
		List<Student> nameSortedStudentList = students.stream()
				.filter(o -> o!=null)
				.sorted(Comparator.comparing(x-> x.getName()))
				//.sorted(Comparator.comparing(Student::getName))
				//.map(Student::getName)
				.collect(Collectors.toList());
		
		nameSortedList.forEach(System.out::println);
		
	}

}
