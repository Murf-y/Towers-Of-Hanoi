/* ****************************************************
 *
 * Author: Charbel Fayad
 * Id: 202102394
 * 
 * Date: Mar 8, 2022
 * LastModified: Mar 8, 2022
 * FileName: TowerOfDeath.java
 * 
 * I used a stack to represent a tower , even though we did not take it in class, this part of the assignement is not graded
 * thus i can use what ever datastructure i wanted even if we did not take it :)
 *****************************************************
 */
package TowerOfHanoi;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Stack;
import Managers.InputManager;



public class TowerOfDeath {

	private InputManager input_manager;

	// Stacks to represent each tower;
	private Tower tower_start;
	private Tower tower_mid;
	private Tower tower_end;

	private Bit[] oldBitsState;
	private Bit[] newBitsState;

	private int movesCounter;

	public TowerOfDeath() {
		input_manager = new InputManager();
		tower_start = new Tower(1);
		tower_mid = new Tower(2);
		tower_end = new Tower(3);
	}

	// Moves a disk from the fromTower to the toTower
	public void moveDisk(Tower from, Tower to) {
		Disk to_be_moved = from.getDisks().pop();
		to.getDisks().push(to_be_moved);
		System.out.println("Moved disk from tower " + from.getLocation() + " to tower " + to.getLocation());
	}

	public void moveDisk(int start, int end) {
		System.out.println("Moving disk from Tower " + start + " to Tower " + end);
	}

	public void solveTowerOfHanoiRecursively(int num_of_disks, int start, int mid, int end) {
		if (num_of_disks <= 0)
			return;
		solveTowerOfHanoiRecursively(num_of_disks - 1, start, end, mid);
		moveDisk(start, end);
		solveTowerOfHanoiRecursively(num_of_disks - 1, mid, start, end);
	}

	public Tower findTowerWithDiskNumber(int disk_size) {
		Disk target_disk = new Disk(disk_size);
		Disk current_iterator_disk;

		Enumeration<Disk> t_start_disks_array = tower_start.getDisks().elements();
		while (t_start_disks_array.hasMoreElements()) {
			current_iterator_disk = t_start_disks_array.nextElement();
			if (current_iterator_disk.equals(target_disk)) {
				return tower_start;
			}
		}
		Enumeration<Disk> t_mid_disks_array = tower_mid.getDisks().elements();
		while (t_mid_disks_array.hasMoreElements()) {
			current_iterator_disk = t_mid_disks_array.nextElement();
			if (current_iterator_disk.equals(target_disk)) {
				return tower_mid;
			}
		}
		Enumeration<Disk> t_end_disks_array = tower_end.getDisks().elements();
		while (t_end_disks_array.hasMoreElements()) {
			current_iterator_disk = t_end_disks_array.nextElement();
			if (current_iterator_disk.equals(target_disk)) {
				return tower_end;
			}
		}
		return null;
	}

	

	public void solveTowerOfHanoiIteraively(int num_of_disk, Tower start, Tower mid, Tower end) {

		System.out.println("Current towers state: ");
		displayTower(tower_start);
		displayTower(tower_mid);
		displayTower(tower_end);

		System.out.println("\n-----------------\n");
		// This is by defination that the optimal solution should take 2^n - 1
		// operations
		int number_of_operations = (int) (Math.pow(2, num_of_disk) - 1);

		do {
			
			
			// old bits state
			updateBitsFromMoveCounter(oldBitsState);
			// increment the counter
			movesCounter++;
			// new bits state after the counter has been updated
			updateBitsFromMoveCounter(newBitsState);
			
			
			

			// if the last bit changed from the last iteration
			if (oldBitsState[oldBitsState.length - 1] != newBitsState[oldBitsState.length - 1]
					&& newBitsState[oldBitsState.length - 1].getBit() == 1) {
				Tower diskOneLocation = findTowerWithDiskNumber(1);
				if (diskOneLocation == null) {
					System.out
							.println("I couldnt find disk zero,this is not supposed to happen,something went worng :/");
					break;
				}
				// move disk one to the right according to its place
				if (diskOneLocation.getLocation() == 1) {
					// move one step
					if (num_of_disk % 2 == 0) {
						moveDisk(diskOneLocation, tower_mid);
					}
					// move two steps
					else {
						moveDisk(diskOneLocation, tower_end);
					}
				} else if (diskOneLocation.getLocation() == 2) {
					// move one step
					if (num_of_disk % 2 == 0) {
						moveDisk(diskOneLocation, tower_end);
					}
					// move two steps
					else {
						moveDisk(diskOneLocation, tower_start);
					}
				} else {
					// move one step
					if (num_of_disk % 2 == 0) {
						moveDisk(diskOneLocation, tower_start);
					}
					// move two steps
					else {
						moveDisk(diskOneLocation, tower_mid);
					}
				}
			} else {
				// Loop over the bits
				// check if any new bits has been set to one while it was zero before
				// if it exists
				// get its index, add one to it, subtract the length of the array, to get the
				// disk you need to move
				for (int k = 0; k < newBitsState.length; k++) {
					// new bit with value 1
					// move the corresponding disk
					if (newBitsState[k].getBit() == 1 && newBitsState[k].getBit() != oldBitsState[k].getBit()) {
						int disk_size = (newBitsState.length - (k + 1)) + 1;
						
						Tower initial_tower = findTowerWithDiskNumber(disk_size);
						if (initial_tower == null) {
							System.out.println("I couldnt find disk " + disk_size
									+ " ,this is not supposed to happen,something went worng :/");
						}
						
						Tower target_tower = null;
						int current_loc = initial_tower.getLocation();
						switch (current_loc) {
							// starting from pos 1, check if 2 or 3 respectively are available
							case 1: {
								
								
								if (tower_mid.getDisks().size() == 0) {
									target_tower= tower_mid;
								} else if (((Disk) tower_mid.getDisks().peek()).getSize() > disk_size) {
									target_tower= tower_mid;
								} else {
									target_tower= tower_end;
								}
								break;
							}
							// starting from pos 2, check if 3 or 1 respectively are available
							case 2: {
								if (tower_end.getDisks().size() == 0) {
									target_tower = tower_end;
								} else if (((Disk) tower_end.getDisks().peek()).getSize() > disk_size) {
									target_tower = tower_end;
								} else {
									target_tower = tower_start;
								}
								break;
							}
							// starting from pos 3, check if 1 or 2 respectively are available
							case 3: {
								if (tower_start.getDisks().size() == 0) {
									target_tower= tower_start;
								} else if (((Disk) tower_start.getDisks().peek()).getSize() > disk_size) {
									target_tower= tower_start;
								} else {
									target_tower= tower_mid;
								}
								break;
							}
						}
						if (target_tower == null) {
							System.out.println("I couldnt find target tower for disk " + disk_size
									+ " ,this is not supposed to happen,something went worng :/");
						}
						moveDisk(initial_tower, target_tower);
					}
				}
			}
		} while (movesCounter < number_of_operations);

		System.out.println("\n-----------------\n");
		System.out.println("Finished Tower state: ");
		displayTower(tower_start);
		displayTower(tower_mid);
		displayTower(tower_end);
		
	}

	public Stack<Disk> createInitialDiskStack(int num_of_disks) {
		Stack<Disk> disks = new Stack<Disk>();
		for (int i = num_of_disks; i > 0; i--) {
			Disk new_disk = new Disk(i);
			disks.push(new_disk);
		}
		return disks;
	}

	public void displayTower(Tower t) {
		System.out.print("Content of Tower at location " + t.getLocation() + " : ");
		System.out.println(Arrays.toString(t.getDisks().toArray()));
	}

	public void updateBitsFromMoveCounter(Bit[] bits) {
		int current_move_counter = movesCounter;
		int size = bits.length;
		for (int j = 0; j < size; j++) {
			bits[j] = new Bit(0);
		}
		int i = size - 1;
		while (i >= 0 && current_move_counter > 0) {
			bits[i] = (current_move_counter & 0x1) == 1 ? new Bit(1) : new Bit(0);
			current_move_counter = current_move_counter >> 1;
			i--;
		}
	}

	public void displayBitsArray(Bit[] bits) {
		for (Bit b : bits) {
			System.out.print(b);
		}
		System.out.println();
	}

	public void initializeBitsArray(int disk_num) {
		movesCounter = 0;
		oldBitsState = new Bit[disk_num];
		newBitsState = new Bit[disk_num];

		updateBitsFromMoveCounter(oldBitsState);
		updateBitsFromMoveCounter(newBitsState);
	}

	public void solve() {
		int num_of_disks = input_manager.getValidPositiveInt("Enter number of disks: ");

		Stack<Disk> disks = createInitialDiskStack(num_of_disks);
		tower_start.setDisks(disks);

		initializeBitsArray(num_of_disks);
		
		// The recursove method was used to generate the pattern 
		// solveTowerOfHanoiRecursively(num_of_disks, 1, 2, 3);
		solveTowerOfHanoiIteraively(num_of_disks, tower_start, tower_mid, tower_end);

	}

	public static void main(String[] args) {
		TowerOfDeath tower_of_hanoi = new TowerOfDeath();
		tower_of_hanoi.solve();
	}

}
