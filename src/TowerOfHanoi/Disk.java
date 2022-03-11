/* ****************************************************
 *
 * Author: Charbel Fayad
 * 
 * Date: Mar 8, 2022
 * LastModified: Mar 8, 2022
 * FileName: Disk.java
 *****************************************************
 */
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