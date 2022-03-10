package TowerOfHanoi;
public class Disk{
		private int size;
		public Disk(int size) {
			this.size = size;
		}
		public int getSize() {
			return size;
		}
		public String toString() {
			return size+"";
		}
		public boolean equals(Disk d) {
			return d.getSize() == size;
		}
}