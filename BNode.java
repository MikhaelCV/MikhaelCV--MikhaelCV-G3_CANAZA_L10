
public class BNode<E extends Comparable<E>> {
	private static int nextId = 0;
    private final int idNode;
	protected ArrayList<E> keys;
	protected ArrayList<BNode<E>> childs;
	protected int count;

	public BNode(int n) {
		this.idNode = nextId++;
		this.keys = new ArrayList<E>(n);
		this.childs = new ArrayList<BNode<E>>(n);
		this.count = 0;

		for (int i = 0; i < n; i++) {
			this.keys.add(null);
			this.childs.add(null);
		}
	}
	
	public int getIdNode() {
        return this.idNode;
    }

	// Check if the current node is full
	public boolean nodeFull (int n) {
		return count == n-1; 
   }

	// Check if the current node is empty
	public boolean nodeEmpty (int n) { 
		return count < (n-1)/2;
   }

	// Search for a key in the current node, if found it returns true and
	// the position where it is located, otherwise, returns false and the
	// position of the child where it should descend.
	public boolean searchNode(E x, int[] pos) {
	    pos[0] = 0;
	    // 1) Avanza mientras haya claves válidas y la clave sea menor que x
	    while (pos[0] < count
	           && keys.get(pos[0]) != null
	           && keys.get(pos[0]).compareTo(x) < 0) {
	        pos[0]++;
	    }
	    // 2) Comprueba si estamos dentro de count y la clave es exactamente x
	    if (pos[0] < count
	        && keys.get(pos[0]) != null
	        && keys.get(pos[0]).compareTo(x) == 0) {
	        return true;
	    }
	    return false;
	}

	public String toString(){
		String str = "";
		for (int i = 0; i < count; i++) {
			str += keys.get(i);
			str += i < count - 1? "," : ")";
		}
		return str;
	}
	
	public void add(E x, int n) {
        if (count < n) {
            keys.set(count, x);
            count++;
        }
    }
	
	public void insertKey(E x, int pos) {
        for (int i = count - 1; i >= pos; i--) {
            keys.set(i + 1, keys.get(i));
        }
        keys.set(pos, x);
        count++;
    }
	
    public void removeKey(int pos) {
        for (int i = pos; i < count - 1; i++) {
            keys.set(i, keys.get(i + 1));
        }
        keys.set(count - 1, null);
        count--;
    }
}