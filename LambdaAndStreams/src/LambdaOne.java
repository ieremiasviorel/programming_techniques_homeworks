
public class LambdaOne {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello from thread!");
			}
		});
		
		thread.start();
		
		thread = new Thread( () -> System.out.println("other hello"));
		thread.start();
	}
	
	// only 2 classes: LambdaOne and the anonymous class inside the first thread
	// Lambda expression does not create an additional class
	// invoke dynamic (not reflection, invoke a method dynamically, without creating a class)
	
}
