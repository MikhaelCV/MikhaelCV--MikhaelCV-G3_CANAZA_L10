
public class ArrayList<T> implements TDAList<T>{
    protected T[] list;
    protected int count;

	public ArrayList(int n){
        this.count = 0;
        this.list = (T[]) new Object[n];
    }
	
	// --- Nuevo método: añade un elemento al final ---
    public void add(T x) {
        if (this.nodeFull())
            throw new RuntimeException("List is full");
        this.list[this.count++] = x;
    }

    /** Devuelve el elemento en la posición dada (0 ≤ index < capacidad). */
    public T get(int index) {
        if (index < 0 || index >= list.length) 
            throw new IndexOutOfBoundsException("Índice: " + index);
        return list[index];
    }

    /** Asigna el elemento en la posición dada (0 ≤ index < capacidad). */
    public void set(int index, T x) {
        if (index < 0 || index >= list.length) 
            throw new IndexOutOfBoundsException("Índice: " + index);
        list[index] = x;
    }

    public boolean nodeFull() {
        return (this.count == this.list.length);
    }

    public boolean nodeEmpty() {
        return (this.count == 0);
    }

    public int length() {
        return this.count;
    }

    public void destroyList() {
        this.count = 0;
    }

    public int search(T x) {
        for (int i = 0; i < this.length(); i++){
            if (this.list[i].equals(x)){
                return i;
            }
        }
        return -1;
    }

    public int search(String x) { return 0; }

    public void insertLast(T x) {
        if (this.nodeFull())
            System.out.println("List is Full");
        else
            this.list[this.count++] = x;
    }

    public void insertFirst(T x) {
        if(this.nodeFull())
            System.out.println("List is Full");
        else{
            for (int i = this.length()-1; i >= 0; i--){
                this.list[i+1] = this.list[i];
            }
            this.list[0] = x;
            this.count++;
        }
    }

    public void remove(T x) {
        int pos = this.search(x);
        if ( pos != 1 ){
            for (int i = pos; i < this.length(); i++)
                this.list[i] = this.list[i+1];
            this.count--;
        } else {
            System.out.println("Item not found");
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.length(); i++)
            str.append("[").append(i).append("]").append("\t").append(this.list[i]).append("\n");
        return str.toString();
    }
}
