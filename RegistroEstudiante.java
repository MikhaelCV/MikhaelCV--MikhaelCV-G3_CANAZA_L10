public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
	private int codigo;
	private String nombre;

	public RegistroEstudiante(int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int compareTo(RegistroEstudiante otro) {
		return Integer.compare(this.codigo, otro.codigo);
	}

	@Override
	public String toString() {
		return codigo + " - " + nombre;
	}
	
	public boolean equals(Object o) {
		if (o instanceof RegistroEstudiante) {
			RegistroEstudiante e = (RegistroEstudiante) o;
			return this.codigo == e.codigo;
		}
		return false;
	}
}