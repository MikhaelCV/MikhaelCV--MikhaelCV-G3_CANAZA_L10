
public class ArrayList<T> implements TDAList<T>{
    protected T[] list;
    protected int count;
// la cuneta inicia en 0 
	public ArrayList(int n){// reciebe la cpacidad de n
        this.count = 0;//la cuenta estara vacia
        this.list = (T[]) new Object[n]; //Crea un Object[] de longitud n y lo convierte a T[].
    }
	
	// ---  método: añade un elemento al final ---
    public void add(T x) {
        if (this.nodeFull()) //nodeFull, comprueba si count == list.length.
            throw new RuntimeException("List is full");
        this.list[this.count++] = x; //recorre la lista y se inserta el x
    }

    ////devuelve el elemento en la posición dada (0 ≤ index < capacidad).
    public T get(int index) {
        if (index < 0 || index >= list.length) //verificamos que el index este en0 y la longitud de la lista en -1
            throw new IndexOutOfBoundsException("Índice: " + index);
        return list[index];
    }

    // aasigna el elemento en la posición dada (0 ≤ index < capacidad).
    public void set(int index, T x) {
        if (index < 0 || index >= list.length) 
            throw new IndexOutOfBoundsException("Índice: " + index);
        list[index] = x;
    }
//estado de la lista
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
//buscamos
    public int search(String x) { return 0; }

    public void insertLast(T x) {
        if (this.nodeFull())
            System.out.println("List is Full");
        else
            this.list[this.count++] = x;
    }
//insertamos en extremos
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
//eliminamos elemento x
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
//imprimimiomos o represntamos en texto
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.length(); i++)
            str.append("[").append(i).append("]").append("\t").append(this.list[i]).append("\n");
        return str.toString();
    }
}
