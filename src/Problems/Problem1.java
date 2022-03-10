package Problems;
import Managers.InputManager;

/* ****************************************************
 *
 * Author: Charbel Fayad
 * Id: 202102394
 * 
 * Date: Feb 27, 2022
 * LastModified: Feb 28, 2022
 * FileName: Problem1.java
 *****************************************************
 */
public class Problem1 {
	
	private InputManager input_manager;
	
	public Problem1() {
		input_manager = new InputManager();
	}
	
	public int reverseANumber(int n) {
		if(n / 10 == 0)return n;
		if(n > 0) {
			return Integer.parseInt(String.format("%d",n % 10) + String.format("%d",reverseANumber(n / 10)));
		}
		else {
			n *= -1;
			return - Integer.parseInt(String.format("%d",n % 10) + String.format("%d",reverseANumber(n / 10)));
		}
	}
	
	
	public int countDigits(int n) {
		if (n / 10 == 0) return 1;
		return 1 + countDigits(n / 10);
	}
	
	
	public String repeat(String s, int num) {
		if(num == 0) return "";
		return s + repeat(s, --num);
	}
	
	public void handleChoice(int choice) {
		switch(choice) {
		// Reverse a number
			case 1:{
				int num = input_manager.getValidInt("Enter a number to reverse it: ");
				num = reverseANumber(num);
				System.out.println(String.format("Reversed number is %d ", num));
				break;
			}
		// Count Digits
			case 2:{
				int num = input_manager.getValidInt("Enter a number to count its digits: ");
				num = countDigits(num);
				System.out.println(String.format("Digits Count is %d ", num));
				break;
			}
		// Repeat
			case 3:{
				String s = input_manager.getString("Enter a String that you want to repeat: ");
				int num = input_manager.getValidIntInRange("Enter a postive integer: ", 0 , Integer.MAX_VALUE);
				s = repeat(s, num);
				String result = String.format("Repeated String is %s ", s.equals("") ? "Empty String": s);
				System.out.println(result);
				break;
			}
		}
	}
	public void solve() {
		System.out.println("Problem 1 is running . . .");
		displayMenu();
		int choice = input_manager.getValidInt("Enter your choice: ");
		
		while(choice != 4) {
			if(choice >=1 || choice <= 3) {
				
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
	
	public void displayMenu() {
		String menu = 
					"1.	Reverse number\r\n"
				+ 	"2.	Count Digits\r\n"
				+ 	"3.	Repeat\r\n"
				+ 	"4.	Exit\r\n"
				+ 	"- - - - - - - - - - - - - \r\n";
		System.out.println(menu);
	}
	public void closeApp() {
		System.out.println("Exiting the Application, Beep Boop . . .");
		System.exit(0);
	}
	public static void main(String[] args) {
		Problem1 p = new Problem1();
		p.solve();
	}
}
