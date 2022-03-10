package TowerOfHanoi;

public class Bit {
	private int value;
	
	public Bit(int i) {
		if(i != 0 && i != 1) {
			throw new IllegalArgumentException("Bit can only have value 1 or 0");
		}
		value = i;
	}
	
	public int getBit() {
		return value;
	}
	
	public void setBit(int i) {
		if(i != 0 || i != 1) {
			throw new IllegalArgumentException("Bit can only have value 1 or 0");
		}
		value = i;
	}
	
	public void flipBit() {
		if(value == 0) {
			value = 1;
		}else {
			value = 0;
		}
	}
	public String toString() {
		return value+"";
	}
}
