import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism. The methods MergeSort and mergeSorter were largely copied/inspired from https://www.baeldung.com/java-merge-sort
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Thomas Stanley
 * Student Number: 41037136  
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 */

/**
 * Contains methods for various techniques of array sorting.
 * @author Thomas Stanley
 *
 */
public class SortingAlgorithms {
	//declaring a new array for each method, to start a new sort process each time the method is called
	int[] bubbleArray;
	int[] selectArray;
	int[] insertArray;
	int[] mergeArray;
	int[] quickArray;
	
	
	/**
	 * Initialises the arrays to be used in this class' methods for sorting.
	 * @param array Array to be passed for sorting.
	 */
	public void sortingInitialise(int[] array) {
		bubbleArray=array.clone();
		selectArray=array.clone();
		insertArray=array.clone();
		mergeArray=array.clone();
		quickArray=array.clone();
	}
	
	
	/**
	 * Sorts an array using the bubble sort method.
	 * @param bubbleArray Array to be sorted
	 * @return returns sorted array
	 */
	public int[] bubbleSort(int[] bubbleArray) {
		//uses more efficient version of method with a boolean to differentiate between changes made.
		boolean changesMade = true;
		//loops through array
		for(int i=0; i<bubbleArray.length && changesMade; i++) {
			changesMade= false;
			for(int j = 0; j<bubbleArray.length-i-1;j++) {
				if(bubbleArray[j]>bubbleArray[j+1]) {
					int temp = bubbleArray[j];
					bubbleArray[j]=bubbleArray[j+1];
					bubbleArray[j+1]=temp;
					changesMade=true;
				}
			}
		}
		//returns sorted array
		return bubbleArray;
	}
	
	/**
	 * Sorts an array using the insertion sorting method.
	 * @param insertArray Array to be sorted
	 * @return returns sorted array
	 */
	public int[] insertionSort(int[] insertArray) {
		//loops through array
		for(int i = 1; i < insertArray.length; ++i) {
			int key = insertArray[i];
			int j = i-1;
			//inserts elements where elements is lower than next element and higher than the previous
			while(j >= 0 && insertArray[j] > key) {
				insertArray[j+1] = insertArray[j];
				j-=1;
			}
			insertArray[j+1]=key;
		}
		//returns sorted array
		return insertArray;
	}
	
	/**
	 * Sorts an array using selection sorting method
	 * @param selectArray Array to be sorted
	 * @return Returns sorted array
	 */
	public int[] selectionSort(int[] selectArray) {
		//loops through array
		for(int current = 0; current<selectArray.length; current++) {
			int smallest = current;
			for(int j = current+1;j<selectArray.length;j++) {
				if(selectArray[j]<selectArray[smallest]) {
					smallest=j;
				}
			}
			int temp = selectArray[current];
			selectArray[current]=selectArray[smallest];
			selectArray[smallest]=temp;
		}
		//returns sorted array
		return selectArray;
	}
	
	
	/**
	 * Sorts an array using the merge sort technique
	 * Code was largely copied/inspired from https://www.baeldung.com/java-merge-sort
	 * @param mergeArray Array to be sorted
	 * @param length Length of the array to be sorted
	 * @return Returns sorted array
	 */
	public int[] mergeSort(int[] mergeArray, int length) {
		//if the length is below two, array is returned
		if (length < 2) {
	        return mergeArray;
	    }
		//identifying middle
		int mid = length / 2;
	    int[] l = new int[mid];
	    int[] r = new int[length - mid];
	    
	    //loops through one half of array
	    for (int i = 0; i < mid; i++) {
	        l[i] = mergeArray[i];
	    }
	    //loop through other half of array
	    for (int i = mid; i < length; i++) {
	        r[i - mid] = mergeArray[i];
	    }
	    //sorts each half independently
	    mergeSort(l, mid);
	    mergeSort(r, length - mid);
	    //returns sorted array
	    return mergeSorter(mergeArray, l, r, mid, length - mid);
		
		
	}
	
	/**
	 * Sorts an array using a merge sort technique.
	 * Code was largely copied/inspired from https://www.baeldung.com/java-merge-sort
	 * @param mergeArray Array to be sorted
	 * @param l Array made from left side
	 * @param r Array made from right side
	 * @param left Left side of each respective half of the array
	 * @param right Right side of each respective half of the array
	 * @return Returns the sorted array to the mergeSort() method
	 */
	public int[] mergeSorter(int[] mergeArray, int[] l, int[] r, int left, int right) {
			
		//initialising local variables
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i] <= r[j]) {
	            mergeArray[k++] = l[i++];
	        }
	        else {
	            mergeArray[k++] = r[j++];
	        }
	    //joining both sides of array
	    }
	    while (i < left) {
	        mergeArray[k++] = l[i++];
	    }
	    while (j < right) {
	        mergeArray[k++] = r[j++];
	    }
	    return mergeArray;
		}
	
	/**
	 * @param quickArray Sorts an array using quicksort technique
	 * @param start beginning of the array
	 * @param end end of the array
	 * @return returns sorted array
	 */
	public int[] quickSort(int[] quickArray, int start, int end) {
		
		if (start >= end)
			return quickArray;
		int pivot = start, left = start+1, right=end;
		while (left < right) { // partition
			while (quickArray[left] < quickArray[pivot] && left <right) left ++;
			while (quickArray[right] > quickArray[pivot] && left < right) right --;
			int temp = quickArray [left]; // swap
			quickArray[left] = quickArray[right];
			quickArray[right] = temp;
			if (left < right) {
			left++; right --;
			}
		}
		// move pivot and call recursively
		if (quickArray[left] > quickArray[pivot]){
			int temp = quickArray[pivot];
			quickArray[pivot] = quickArray[left-1];
			quickArray[left-1] = temp;
			quickSort (quickArray, start, left-2);
			quickSort (quickArray, left, end);
			}else{
			int temp = quickArray[pivot];
			quickArray[pivot] = quickArray[left];
			quickArray[left] = temp;
			quickSort (quickArray, start, left-1);
			quickSort (quickArray, left+1, end);
			}
		return quickArray;
	}
	
	/**
	 * Contains and times each sorting method. 
	 * @param search Binarysearch object used to print remaining elements
	 * @param input Scanner object for user input
	 */
	public void sortingTimer(Binarysearch search, Scanner input) {
		System.out.println("Select a sorting algorithm to sort the data array");
		System.out.printf("\n\tB. Bubble Sort\n\tI. Insertion Sort\n\tS. Selection Sort\n\tM. Merge Sort\n\tQ. Quick Sort\n\tR. Return to Main Menu\n\n");
		//getting user input
		char sortOption = search.input.next().charAt(0);
		//option takes both upper and lower case
		if(sortOption=='b'||sortOption=='B') {
			//start timer
			long nanoStart=System.nanoTime();
			long milliStart=System.currentTimeMillis();
			bubbleSort(bubbleArray);
			//end timer
			long nanoEnd=System.nanoTime();
			long milliEnd=System.currentTimeMillis();
			long nanoDuration=(nanoEnd-nanoStart);
			long milliDuration=(milliEnd-milliStart);
			//prints the unsorted array, the sorted array,the details of sorting, and the time it took
			search.remainingElements(search.unSorted,0,search.unSorted.length-1);
			System.out.printf("Bubble Sort: Simple sorting algorithm - 0(n2) Complexity - In-place Sorting\n\nSorted: ");
			search.remainingElements(bubbleArray,0,bubbleArray.length-1);
			System.out.println("Time taken in nanoseconds: "+ nanoDuration);
			System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
			
		}else if(sortOption=='i'||sortOption=='I') {
			//start timer
			long nanoStart=System.nanoTime();
			long milliStart=System.currentTimeMillis();
			insertionSort(insertArray);
			//end timer
			long nanoEnd=System.nanoTime();
			long milliEnd=System.currentTimeMillis();
			long nanoDuration=(nanoEnd-nanoStart);
			long milliDuration=(milliEnd-milliStart);
			//prints the unsorted array, the sorted array,the details of sorting, and the time it took
			search.remainingElements(search.unSorted,0,search.unSorted.length-1);
			System.out.printf("Insertion Sort: Simple Sorting algorithm - 0(n2) Complexity - In-place sorting\n\nSorted:  ");
			search.remainingElements(insertArray,0,insertArray.length-1);
			System.out.println("Time taken in nanoseconds: "+ nanoDuration);
			System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
			
		}else if(sortOption=='s'||sortOption=='S') {
			//start timer
			long nanoStart=System.nanoTime();
			long milliStart=System.currentTimeMillis();
			selectionSort(selectArray);
			//end timer
			long nanoEnd=System.nanoTime();
			long milliEnd=System.currentTimeMillis();
			long nanoDuration=(nanoEnd-nanoStart);
			long milliDuration=(milliEnd-milliStart);
			//prints the unsorted array, the sorted array,the details of sorting, and the time it took
			search.remainingElements(search.unSorted,0,search.unSorted.length-1);
			System.out.printf("Selection Sort: Simple sorting algorithm - 0(n2) - Complexity - In-Place sorting\n\nSorted: ");
			search.remainingElements(selectArray,0, selectArray.length-1);
			System.out.println("Time taken in nanoseconds: "+ nanoDuration);
			System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
			
		}else if(sortOption=='m'||sortOption=='M') { 
			//start timer
			long nanoStart=System.nanoTime();
			long milliStart=System.currentTimeMillis();
			mergeSort(mergeArray,mergeArray.length);
			//end timer
			long nanoEnd=System.nanoTime();
			long milliEnd=System.currentTimeMillis();
			long nanoDuration=(nanoEnd-nanoStart);
			long milliDuration=(milliEnd-milliStart);
			//prints the unsorted array, the sorted array,the details of sorting, and the time it took
			search.remainingElements(search.unSorted,0,search.unSorted.length-1);
			System.out.printf("Merge Sort: Recursive Divide and Conquer - 0(n log n)  Complexity - Not In-place sorting\n\nSorted: ");
			search.remainingElements(mergeArray,0, mergeArray.length-1);
			System.out.println("Time taken in nanoseconds: "+ nanoDuration);
			System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
			
		}else if(sortOption=='q'||sortOption=='Q') {
			//start timer
			long nanoStart=System.nanoTime();
			long milliStart=System.currentTimeMillis();
			quickSort(quickArray, 0, quickArray.length-1);
			//end timer
			long nanoEnd=System.nanoTime();
			long milliEnd=System.currentTimeMillis();
			long nanoDuration=(nanoEnd-nanoStart);
			long milliDuration=(milliEnd-milliStart);
			//prints the unsorted array, the sorted array,the details of sorting, and the time it took
			search.remainingElements(search.unSorted,0,search.unSorted.length-1);
			System.out.printf("Quick Sort: Recursive Divide and Conquer - 0(n log n)  Complexity - In-place sorting\n\nSorted: ");
			search.remainingElements(quickArray,0, quickArray.length-1);
			System.out.println("Time taken in nanoseconds: "+ nanoDuration);
			System.out.println("Time taken in milliseconds: "+ milliDuration+"\n");
			
		}else if(sortOption=='r'||sortOption=='R') { 
			return;
		}else {
			System.out.println("Please Enter Valid Option");
		}
	}
	
}
