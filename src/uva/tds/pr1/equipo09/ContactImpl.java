package uva.tds.pr1.equipo09;

import uva.tds.pr1.Contact;

/**
 * Representación de un contacto generico(Group o Person).
 * @author ginquin
 * @author davidgo
 *
 */
public class ContactImpl implements Contact {
	private String id;

	/**
	 * Constructor de una contacto generico.
	 * @param id Identificador unico del contacto.
	 * @throws IllegalArgumentException Identificador no válido.
	 */
	public ContactImpl(String id) {
		if(id==null || id=="") throw new IllegalArgumentException("Id no válido (" + id==null?"null":"String sin valor" + ").");
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Contact#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Contact#cambiarId(java.lang.String)
	 */
	@Override
	public void cambiarId(String nuevo) {
		if(nuevo==null || nuevo=="") throw new IllegalArgumentException("El nuevo id no es válido (" + nuevo==null?"null":"String sin valor" + ").");
		id = nuevo;
	}
	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Contact#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otro) {
		return getId().equalsIgnoreCase(((Contact)otro).getId());
	}
}
