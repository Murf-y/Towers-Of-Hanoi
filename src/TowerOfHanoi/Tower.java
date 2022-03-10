package TowerOfHanoi;
import java.util.Stack;

public class Tower {
	private Stack<Disk> current_tower_disks;
	private int location;
	
	public Tower(int location) {
		this.location = location;
		current_tower_disks = new Stack<Disk>();
	}

	public Stack<Disk> getDisks() {
		return current_tower_disks;
	}

	public void setDisks(Stack<Disk> current_tower_disks) {
		this.current_tower_disks = current_tower_disks;
	}

	public int getLocation() {
		return location;
	}
	
	
}
