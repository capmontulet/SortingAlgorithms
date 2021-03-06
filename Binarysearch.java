import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class contains methods that conducts two different types of binary searches, both recursive and non-recursive.
 * It also contains methods to time these operations and to create a random array of ints in order to search them.
 * @author Thomas Stanley
 *
 */
public class Binarysearch {
	//array is created
	int[] array;
	int[] unSorted;
	Scanner input = new Scanner(System.in);
	
	
	/**
	 * This method conducts a recursive search on the array with a user entered number..
	 * @param array is the array that will be filled prior to calling this method.
	 * @param input is the user entered number to search for.
	 * @return If the value is not found by the end of the loop, '-1' is returned and triggers a condition to print a 'value not found' message.
	 */
	public int nonRecursiveBinarySearch(int[] array, int input) {
		//setting array element positions
		int first=0;
		int last=array.length-1;
		
		//while the first element of the array is lower or equal to the last, loop runs.
		while(first<=last){
			//setting middle element position
			int middle=(first+last) /2;
			//if the middle of the array is the number the user entered, middle is returned and the loop ends.
			if(array[middle]==input) {
				return middle;
			}
			//if the middle is lower than the input, the left side is cut
			if(array[middle]<input) {
				first=middle+1;
			}else {
			//else the right side is cut
				last=middle-1;
			}
		}
		return -1;
	}
	
	/**
	 * This method conducts a recursive binary search on the array with a user entered value.
	 * @param array is the array that will be filled prior to calling this method.
	 * @param first This is the First element of the array. When the method is first called, this is set to 0 by default,
	 * 		  then as the method calls itself, this value gets updated to be the 'middle+1'.
	 * @param last This is the last element of the array. When the method is first called, this is set to 'array.length-1' by default,
	 * 		  then as the method calls itself, this value gets updated to be the 'middle-1'.
	 * @param input input is the user entered number to search for.
	 * @return If the value is not found by the end of the loop, '-1' is returned and triggers a condition to print a 'value not found' message.
	 */
	public int recursiveBinarySearch(int[] array,int first,int last,int input) {
		
		//starting search loop
		if(first<=last) {
			//setting middle
			int middle = (first+last) /2;
			//if the middle is the input, middle is returned and loop is stopped.
			if(array[middle] == input) {
				return middle;
			}
			//if the middle is lower than the input, the left side is cut
			if(array[middle]<input) {
				return recursiveBinarySearch(array,middle+1,last,input);
			}else {
			//otherwise, the right side is cut.
				return recursiveBinarySearch(array,first,middle-1,input);
			}
		}
		return -1;
		
	}
	
	/**
	 * This method generates an array of length 20, then using a for loop, enters a random integer into each element.
	 * @return returns the array.
	 */
	public int[] generateRandomInts(){
		//creating new array of length 20.
		array=new int[1000];
		//new secure random class to generate random integers
		SecureRandom random=new SecureRandom();
		//fills array with random values between 10 and 100
		for(int i=0;i<1000;i++){
			array[i]=random.nextInt(1000-120)+120;
		}
		unSorted=new int[1000];
		unSorted = array.clone();
		return array;
		}
	
	/**
	 * This method prints the remaining elements in the array generated by 'generateRandomInts()'.
	 * It is called this because it is intended to be called multiple times in succession.
	 * @param array Is the array to be iterated through.
	 * @param first Is the first element of the array.
	 * @param last Is the last element of the array.
	 */
	public void remainingElements(int[] array,int first,int last){
		//prints each element of the array between 'first' and 'last'.
		//The values of first and last can change depending on how many times this method has been called in another.
		for(int i=first;i<=last;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("\n");
	}
	
	/**
	 * This method times how long it takes to perform a recursive/iterative search on an array.
	 */
	public void recursiveTimer() {
		
		Arrays.sort(array);
		//user is prompted for an integer to search for
		System.out.print("Please enter an integer value to search: ");
		int num = input.nextInt();
		//both nanosecond and millisecond timers are started.
		long nanoStart=System.nanoTime();
		long milliStart=System.currentTimeMillis();
		//recursive binary search is conducted while the timers run.
		int numCheck=recursiveBinarySearch(array,0,array.length-1,num);
		//timers are stopped after search has completed, and the duration is assigned to variables.
		long nanoEnd=System.nanoTime();
		long milliEnd=System.currentTimeMillis();
		long nanoDuration=(nanoEnd-nanoStart);
		long milliDuration=(milliEnd-milliStart);
		
		remainingElements(unSorted, 0 ,unSorted.length-1);
		//messages are displayed depending on whether the integer was found or not
		if(numCheck!=-1) {
			System.out.println(num+" was found at index position "+numCheck+": recursive Binary Search");
		}else {
			System.out.println("Number " + num + " was not found");
		}
		//time of search is displayed for both units of time.
		System.out.println("Time taken in nanoseconds: "+ nanoDuration);
		System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
	}
	/**
	 * This method times how long it takes to perform a non-recursive search on an integer array
	 */
	public void nonRecursiveTimer() {
		Arrays.sort(array);
		//user in prompted for an integer to search for
		System.out.print("Please enter an integer value to search: ");
		int num = input.nextInt();
		//both nanosecond and millisecond timers are started.
		long nanoStart=System.nanoTime();
		long milliStart=System.currentTimeMillis();
		//non-recursive search is performed while timers run
		int numCheck=nonRecursiveBinarySearch(array, num);
		//timers are stopped after search has completed, and the duration is assigned to variables.
		long nanoEnd=System.nanoTime();
		long milliEnd=System.currentTimeMillis();
		long nanoDuration=(nanoEnd-nanoStart);
		long milliDuration=(milliEnd-milliStart);
		
		remainingElements(unSorted, 0 ,unSorted.length-1);
		//messages are displayed depending on whether the integer was found or not
		if(numCheck!=-1) {
			System.out.println(num+" was found at index position "+numCheck+": Iterative Binary Search");
		}else {
			System.out.println("Number " + num + " was not found");
		}
		//time of search is displayed for both units of time.
		System.out.println("Time taken in nanoseconds: "+ nanoDuration);
		System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
	}
}
