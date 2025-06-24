
public class GestorEstudiantes {
	private BTree<RegistroEstudiante> btree;
	public GestorEstudiantes() {
		btree = new BTree<>(4);
	}
	
	public void insertarEstudiante(int codigo, String nombre) {
		btree.insert(new RegistroEstudiante (codigo, nombre));
	}
	
	public String buscarEstudiante(int codigo) {
        RegistroEstudiante clave = new RegistroEstudiante(codigo, "");
        RegistroEstudiante encontrado = btree.recover(clave);
        return (encontrado != null) ? encontrado.getNombre() : "No encontrado";
    }
	
	public void eliminarEstudiante(int codigo) {
        RegistroEstudiante clave = new RegistroEstudiante(codigo, "");
        btree.remove(clave);
    }

    public void reporteEstudiante() {
        System.out.println(btree.toString());
    }
}
