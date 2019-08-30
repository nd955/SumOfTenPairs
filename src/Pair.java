
public class Pair {

	int leftEntry, rightEntry;
	
	public Pair(int left, int right){
		leftEntry = left;
		rightEntry = right;
	}
	
	public String toString() {
		return "[" + leftEntry + "," + rightEntry + "]";
	}
}
