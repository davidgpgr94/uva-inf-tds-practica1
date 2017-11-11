package uva.tds.pr1.equipo09;

public class Contact {
	private String id;


	public Contact(String id) {
		if(id==null || id=="") throw new IllegalArgumentException("Id no válido \"" + id==null?"null":"String sin valor" + "\".");
		this.id = id;
	}
	
	/**
	 * Devuelve el Id del contacto
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	public void cambiarId(String nuevo) {
		if(nuevo==null || nuevo=="") throw new IllegalArgumentException("El nuevo id no es válido (" + nuevo==null?"null":"String sin valor" + ").");
		id = nuevo;
	}
	
	/**
	 * Devuelve verdadero si los id son iguales
	 */
	@Override
	public boolean equals(Object otro) {
		if (otro == null) return false;
		if (!(otro instanceof Contact)) return false;
		return getId() == ((Contact)otro).getId();
	}
}
