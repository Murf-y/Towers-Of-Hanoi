/* ****************************************************
 *
 * Author: Charbel Fayad
 * Id: 202102394
 * 
 * Date: Feb 27, 2022
 * LastModified: Mar 8, 2022
 * FileName: Problem2.java
 *****************************************************
 */
package Problems;
import Managers.InputManager;


public class Problem2 {
	private InputManager input_manager;
	// We used "Integer" and not "int" because we want to initialize the array with null
	private Integer[] numbers;
	
	public Problem2() {
		input_manager = new InputManager();
		numbers = new Integer[10];
	}
	
	
	// Arrays Helpers
	public void addNumToNumbers(Integer[] nums , int num) {
		// If array is not empty
		// add an element
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == null) {
				nums[i] = num;
				return;
			}
		}
		
		// array was full
		// copy it to a new bigger arrray
		// add the element 
		
		int old_size = nums.length;
		Integer[] new_numbers = new Integer[old_size + 10];
		for(int i = 0; i< old_size ; i ++) {
			new_numbers[i] = nums[i];
		}
		
		new_numbers[old_size] = num;
		nums = new_numbers;	
	}
	
	
	public int getArraySize(Integer[] nums) {
		// gets the array size
		// Array size != Array length
		// size is the actual count of not null elements in the array
		int c = 0;
		for(int i =0; i< nums.length; i++) {
			if(nums[i] != null) {
				c++;
			}
		}
		return c;
	}
	
	public int getArraySize(Integer[] nums, int start, int endInclusive) {
		// gets the array size
		// Array size != Array length
		// size is the actual count of not null elements in the array
		int c = 0;
		for(int i =start; i<= endInclusive; i++) {
			if(nums[i] != null) {
				c++;
			}
		}
		return c;
	} 
	
	public void displayArray(Integer[] nums) {
		System.out.print("Your numbers array is : ");
		for(int i = 0; i< nums.length; i++) {
			if(nums[i] != null) {
				System.out.print(nums[i] + " ");
			}
		}
		System.out.println();
	}
	
	
	
	
	
	// Helper function
	public void printEvenOddFromArray(boolean is_even) {
		printEvenOddFromArray(is_even, 0);
	}
	public void printEvenOddFromArray(boolean is_even, int current_index) {
		if(current_index < getArraySize(numbers)) {
			System.out.print(is_even ? (numbers[current_index] % 2 == 0? numbers[current_index] + " ":" "):((numbers[current_index] % 2 != 0? numbers[current_index] + " ":" ")));
			printEvenOddFromArray(is_even, ++current_index);
		}
	}
	
	
	
	// Helper function
	public void printMaxFromArray() {
		System.out.println("Array Max is: " + getMaxFromArray(0, 1));
	}
	public int getMaxFromArray(int current_max_index, int start) {
		// 1 4 2 6 5     0   1
		//  4> 1 ? =>    1   2
		//  2> 4 ? =>    1   3
		//  6> 4 ? =>    3   4
		//  5> 6 ? =>    3   5
		//  5<5 ? => return numbers[3] 
		if(start < getArraySize(numbers)) {
			if(numbers[start] > numbers[current_max_index]) {
				current_max_index = start;
				
			}
			return getMaxFromArray(current_max_index, ++start);
		}
		return numbers[current_max_index];
	}
	
	
	
	// Helper functions
	
	public int sumArray(Integer[] nums, int start,int end) {
		// This function logic can be directly included inside the "canSplitArrayIntoSums" 
		// but redundant code is bad!
		// the main role of a function is to remove redundant code
		// plus i made it recursive in case iam not allowed to have anything iterative :)
		if(end >= getArraySize(nums) || start < 0 || start > end) {
			return 0;
		}
		else if(start == end) {
			return nums[end];
		}
		return nums[start] + sumArray(nums,++start, end);
	}
	public boolean canSplitArrayIntoSums(Integer[] nums) {
		return canSplitArrayIntoSums(nums, 0);
	}
	public boolean canSplitArrayIntoSums(Integer[] nums, int carry_in) {
		
		// if the arrays length is 1 or less return false
		if(getArraySize(nums) <= 1) {
			return false;
		}
		
		// if there is two elements in the array 
		else if(getArraySize(nums) == 2) {
			// return sum(first + carryIn) = last or sum(last + carryIn) = first
			return nums[0] + carry_in == nums[1] || nums[1] + carry_in == nums[0];
		}
		else {
			// get nums size so we dont have to calculate it multiple times
			int nums_size = getArraySize(nums);
			
			// if the sum of the array is equal to the carry in return true
			// this is usefull because , after the first recusrion
			// we check if the sum of rest elements is equal to carryIn
			if(sumArray(nums,0, nums_size - 1) == carry_in) {
				return true;
			}
			
			// For each element, check if the rest elements 
			for(int index = 0 ; index < nums_size; index++) {
				int current = nums[index];
				
				// Clone the nums array into a new array , excluding the current element
				Integer[] nums_excluding_current = new Integer[nums_size - 1];
				for(int j = 0; j < nums_size; j++) {
					if(j != index) {
						addNumToNumbers(nums_excluding_current, nums[j]);
					}
				}
				
				// Check if the rest element sums up to current+carryIn
				if(canSplitArrayIntoSums(nums_excluding_current, current + carry_in)) {
					return true;
				}
			}
			return false;
		}
	}

	
	
	// Helper function
	public boolean nonAdjSumExists(Integer[] nums, int target) {
		return nonAdjSumExists(numbers, target, 0, getArraySize(numbers) - 1);
	}
	public boolean nonAdjSumExists(Integer[] nums, int target, int start, int end) {
		
		// Start is larger than end
		// Base case
		if(start > end) {
			return false;
		}
		// Start equal to end 
		// One element in array to check
		// Base case
		else if(start == end) {
			return nums[start] == target;
		}
		// Two elements in the the array to check
		// Base case
		else if(end - start == 1) {
			return nums[end] == target || nums[start] == target;
		}
		// More than two elements in the array to check
		// Recursion step
		else {
			
			for(int i = start; i <= end; i++) {
				// This step is to check if the element we are at is actually equal to target sum
				if(nums[i] == target) {
					return true;
				}
				// new target is the target minus our current element
				// because we know that our current element exists thus we can exclude it from the target
				// and find the new_target sum, in the left non adjacent part and in the right non adjacent part
				
				int new_target = target - nums[i];
				boolean left_non_adj_sum_exists = nonAdjSumExists(nums, new_target, start , i - 2);
				boolean right_non_adj_sum_exists = nonAdjSumExists(nums, new_target, i + 2, end);
				
				if(left_non_adj_sum_exists || right_non_adj_sum_exists) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	
	// Main application starter code
	// !!   Not	Related to the actual assignement !!
	public void handleChoice(int choice) {
		displayArray(numbers);
		switch(choice) {
		
			// Even or odd
			case 1:{
				int is_even_odd_int = input_manager.getValidIntInRange("Choose:\n1. Even\r\n2. Odd\r\n Enter your choice: ", 1, 2);
				boolean is_even = is_even_odd_int == 1? true : false;
				printEvenOddFromArray(is_even);
				// go to a new line
				System.out.println("\n-------------\n");
				break;
			}
			// Find max
			case 2:{
				printMaxFromArray();
				break;
			}
			// Adds up
			case 3:{
				boolean res = canSplitArrayIntoSums(numbers, 0);
				System.out.println("We " + (res? "CAN ":"CANNOT ") + "split the numbers array into sums");
				break;
			}
			// Sums
			case 4:{
				int i = input_manager.getValidPositiveInt("Enter a positive integer: ");
				boolean res = nonAdjSumExists(numbers, i);
				System.out.println("We " + (res? "CAN ":"CANNOT ") + "choose non adjecent numbers from the array  such that the sum of these numbers is equal to "+ i);
				break;
			}
			case 5:{
				numbers = new Integer[10];
				getNumbersFromUser();
				break;
			}
		}
	}
	public void getNumbersFromUser() {
		int choice = input_manager.getValidInt("Enter a number: ");
		do{
			addNumToNumbers(numbers,choice);
			choice = input_manager.getValidInt("Enter another number or 0 to stop: ");
		}while(choice!= 0);	
	}
	public void displayMenu() {
		String menu = 
					"1.	Even or Odd\r\n"
				+ 	"2.	Find Max\r\n"
				+ 	"3.	Adds up\r\n"
				+ 	"4.	Sum\r\n"
				+ 	"5.	ReInput Numbers\r\n"
				+ 	"6.	Exit\r\n"
				+ 	"- - - - - - - - - - - - - \r\n";
		System.out.println(menu);
	}
	public void closeApp() {
		System.out.println("Exiting the Application, Beep Boop . . .");
		System.exit(0);
	}
	public void solve() {
		System.out.println("Problem 2 is running . . .");
		getNumbersFromUser();
		displayMenu();
		int choice = input_manager.getValidInt("Enter your choice: ");
		
		while(choice != 6) {
			if(choice >=1 || choice <= 5) {
				handleChoice(choice);
			}
			else {
				System.out.println("Invalid choice");
			}
			displayMenu();
			choice = input_manager.getValidInt("Enter your choice: ");
		}
		closeApp();
		
		
	}
	public static void main(String[] args) {
		Problem2 p = new Problem2();
		p.solve();
	}

}
