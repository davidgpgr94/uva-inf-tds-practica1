package uva.tds.pr1.equipo09;
/**
 * Representación de un contacto generico(Group o Person).
 * @author ginquin
 * @author davidgo
 *
 */
public class Contact {
	private String id;

	/**
	 * Constructor de una contacto generico.
	 * @param id Identificador unico del contacto.
	 * @throws IllegalArgumentException Identificador no válido.
	 */
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
	/**
	 * Sobreescribe el metodo equals para comparar contactos por identificación.
	 */
	@Override
	public boolean equals(Object otro) {
		return getId().equalsIgnoreCase(((Contact)otro).getId());
	}
}
