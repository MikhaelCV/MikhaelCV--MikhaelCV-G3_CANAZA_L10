
public interface TDAList<T> {
	boolean nodeFull();
	boolean nodeEmpty();
	int length();
	void destroyList();
	int search(T x);
	int search(String x);
	void insertLast(T x);
	void insertFirst(T x);
	void remove(T x);
	T get(int index);
	String toString();
}
