package uva.tds.pr1.equipo09;

public class Contact {
	private String id;
	
	public Contact(String id) {
		if(id==null || id=="") throw new IllegalArgumentException();
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
		id = nuevo;
	}
}
