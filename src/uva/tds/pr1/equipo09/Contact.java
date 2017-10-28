package uva.tds.pr1.equipo09;

/**
 * Representa de un contacto ya sea persona o grupo.
 * @author davidgo
 * @author ginquin
 *
 */
public class Contact {
	private String id;
	
	public Contact(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void cambiarId(String nuevo) {
		id = nuevo;
	}
}
