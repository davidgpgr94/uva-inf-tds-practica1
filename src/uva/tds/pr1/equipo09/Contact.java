package uva.tds.pr1.equipo09;

public class Contact {
	private String id;
	
	public Contact(String id) {
		if(id==null || id=="") throw new IllegalArgumentException("Id no válido (" + id==null?"null":"String sin valor" + ").");
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

	public boolean equals(Contact otro) {
		return id == otro.getId();
	}
}
