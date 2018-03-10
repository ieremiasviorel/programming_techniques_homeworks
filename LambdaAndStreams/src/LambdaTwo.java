import java.util.Arrays;
import java.util.List;

public class LambdaTwo {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		for(int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		for(int i : numbers) {
			System.out.println(i);
		}
		
		/*numbers.forEach(new Consumer<Integer>() {
			public void accept(Integer value) {
				System.out.println(value);
			}
		});*/
		
		numbers.forEach(value -> System.out.println(value));
		
		numbers.forEach(System.out::println);
		
		numbers.forEach(LambdaTwo::doStuff);
	}
	
	public static void doStuff(int i) {
		System.out.println(i);
	}
}
