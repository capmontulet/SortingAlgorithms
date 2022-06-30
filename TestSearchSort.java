/**
 * Class that holds main, has a method to print a menu, so that main is cleaner to look at.
 * @author Thomas Stanley
 *
 */
public class TestSearchSort {
	
	 static void printMenu() {
		System.out.println("Select an option in the menu");
		System.out.println("1: Initialize and populate an array of 1000 random integers.");
		System.out.println("2: Perform a recursive binary search.");
		System.out.println("3: Perform iterative binary search");
		System.out.println("4: Sort the array");
		System.out.println("5: Quit");
	}

	 /**
	  * Main displays menu and calls methods from Binarysearch class to conduct two different types of search algorithm, recursive and non-recursive.
	  * @param args contains arguments of the class
	  */
	public static void main(String[] args) {
		//new Binarysearch object created
		Binarysearch search = new Binarysearch();
		SortingAlgorithms algo = new SortingAlgorithms();
		boolean loop = true;
		
		//while loop displays menu until exit is selected.
		while(loop==true) {
			printMenu();
			//try catch ensures correct input entry
			try {
				
			int option = search.input.nextInt();
			
			//start of menu choices
			switch(option) {
			case 1:
				System.out.println("Array of randomly generated integers: ");
				search.generateRandomInts();
				search.remainingElements(search.array,0,search.array.length-1);
				algo.sortingInitialise(search.unSorted);
				break;
			case 2:
				//timer method is called to time the search
				search.recursiveTimer();
				break;
			case 3:
				//timer method is called to time the search
				search.nonRecursiveTimer();
				break;
			case 4:
				//method takes option for sorting technique and also times each one
				algo.sortingTimer(search, search.input);
				//resets each array to the unsorted version to re-do the sorting if wanted
				algo.sortingInitialise(search.unSorted);
				break;
			case 5:
				System.out.println("Exiting...");
				loop=false;
				break;
			default: 
				//if user enters an integer not assigned to an option, message is displayed
				System.out.println("Invalid Input...try again");
			}
			}catch(Exception e) {
				//if there are any input mismatch exceptions, message is displayed
				System.out.println("Invalid Input...try again");
				search.input.nextLine();
			}
		}
		
	}

}
