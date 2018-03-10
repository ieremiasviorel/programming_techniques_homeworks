import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsOne {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		int result = numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * 2)
				.sum();
		System.out.println("Result: " + result);
		
		result = numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(StreamsOne::compute)
				.sum();
		System.out.println("Result: " + result);
		
		
		result = numbers.parallelStream()
				.filter(e -> e % 2 == 0)
				.mapToInt(StreamsOne::compute)
				.sum();
		System.out.println("Result: " + result);
		
		result = numbers.stream()
			.filter(e -> e % 2 == 0)
			.map(e -> e * 2)
			.reduce(0, (carry, e) -> carry + e);
		System.out.println("Result: " + result);
		
		List<Integer> numbersDuplicates = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
		
		numbersDuplicates.stream().sorted().distinct().forEach(System.out::print);
		
		System.out.println();
		
//		numbersDuplicates.stream()
//				.filter(e -> e % 2 == 0)
//				.map(e -> e * 2)
//				.forEach(e -> doubleOfEven.add(e));
		
		List<Integer> evenDoubled = numbersDuplicates.stream()
		.filter(e -> e % 2 == 0)
		.map(e -> e * 2)
		.collect(Collectors.toList());
		
		System.out.println(evenDoubled);
		
		Set<Integer> uniques = numbersDuplicates.stream()
				.filter(e -> e % 2 == 0)
				.map(e -> e * 2)
				.collect(Collectors.toSet());
		
		System.out.println(uniques);
		
		List<Integer> nr = Arrays.asList(1, 2, 3, 5, 4, 2, 9 ,54, 23, 11, 6 ,7 ,8, 32 ,78, 99, 11, 11, 9 ,10, 15, 36, 85 ,63, 52);
		
		for(int e : nr) {
			if(e > 3 && e % 2 == 0) {
				result = e * 2;
				break;
			}
		}
		
		System.out.println("Result: " + result);
		
		int x = nr.stream()
			.filter(e -> e > 3)
			.filter(e -> e % 2 == 0)
			.map(e -> e * 2)
			.findFirst()
			.get();
		
		System.out.println("Result: " + x);
		
		x = nr.stream()
				.filter(StreamsOne::isGT3)
				.filter(StreamsOne::isEven)
				.map(StreamsOne::doubleIt)
				.findFirst()
				.get();
			
			System.out.println("Result: " + x);
	}
	
	
	
	public static int compute(int number) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2 * number;
	}
	
	public static boolean isGT3(int number) {
		return number > 3;
	}
	
	public static boolean isEven(int number) {
		return number % 2 == 0;
	}
	
	public static int doubleIt(int number) {
		return number * 2;
	}
}
