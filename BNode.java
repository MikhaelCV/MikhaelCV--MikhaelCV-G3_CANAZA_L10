
public class BNode<E extends Comparable<E>> {//ada clave E debe ser comparable (para poder ordenarlas).
	private static int nextId = 0;
    private final int idNode;//amobos indetificadores en 0aumenra cada ves que cremoa un bnode
	protected ArrayList<E> keys;
	protected ArrayList<BNode<E>> childs;
	protected int count;

	public BNode(int n) {
		this.idNode = nextId++;//para que cada nodo tenga un ID único
		this.keys = new ArrayList<E>(n);//para claves
		this.childs = new ArrayList<BNode<E>>(n);//para los hijitos
		this.count = 0;

		for (int i = 0; i < n; i++) {//corredor
			this.keys.add(null);
			this.childs.add(null);
		}
	}
	//Te permite visualizar o depurar, sabiendo qué nodo es cada uno
	public int getIdNode() {
        return this.idNode;
    }

	// lleno” o vacío
	public boolean nodeFull (int n) {
		return count == n-1; 
   }

	// vacio?
	public boolean nodeEmpty (int n) { 
		return count < (n-1)/2;
   }

	// Búsqueda dentro del nodo
	public boolean searchNode(E x, int[] pos) {
	    pos[0] = 0;
	    // 1) Avanza mientras haya claves validas y la clave sea menor que x
	    while (pos[0] < count
	           && keys.get(pos[0]) != null//compruevba que haya clave en esa posisicion
	           && keys.get(pos[0]).compareTo(x) < 0) { //Mientras la clave actual siga siendo menor que x, significa que x —si existiera— tendría que buscarse más a la derecha.
	        pos[0]++;// si se cumplen las tres condiciones anteriores, incrementa pos[0]
	    }
	    // 2) Comprueba si estamos dentro de count y la clave es exactamente x
	    if (pos[0] < count//si esta dentro de el rango
	        && keys.get(pos[0]) != null
	        && keys.get(pos[0]).compareTo(x) == 0) {//Usa el método compareTo de la interfaz Comparable. Un resultado 0 significa que la clave almacenada es igual a x
	        return true;
	    }
	    return false;
	}
//Representación en texto
	public String toString(){
		String str = "";
		for (int i = 0; i < count; i++) {
			str += keys.get(i);
			str += i < count - 1? "," : ")";
		}
		return str;
	}
	//Añadir y desplazar claves	
	public void add(E x, int n) {
        if (count < n) {
            keys.set(count, x);
            count++;
        }
    }

	//inserta x al final de las claves (si no supera n), sin mantener orden.
	public void insertKey(E x, int pos) {
        for (int i = count - 1; i >= pos; i--) {
            keys.set(i + 1, keys.get(i));
        }
        keys.set(pos, x);
        count++;
    }
	//Eliminar una clave
    public void removeKey(int pos) {
        for (int i = pos; i < count - 1; i++) {
            keys.set(i, keys.get(i + 1));
        }
        keys.set(count - 1, null);
        count--;
    }
}
