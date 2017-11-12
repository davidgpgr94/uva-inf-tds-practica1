package uva.tds.pr1.equipo09;

import java.util.HashSet;
import java.util.Set;

import uva.tds.pr1.Contact;
import uva.tds.pr1.Group;
/**
 * Representacion de un grupo en la libreta electronica.
 * @author ginquin
 * @author davidgo
 */
public class GroupImpl extends ContactImpl implements Group {

	private Set<Contact> members; // usamos un conjunto para evitar que un mismo
									// contacto se encuentre repetido en el
									// grupo

	/**
	 * Contructor de un grupo.Inicializa un grupo con un nombre y una lista de miembros.
	 * @param nombre Nombre de un grupo. No puede ser nulo, ni vacio.
	 * @param miembros Miembros del grupo. Como minimo debe tener un miembro.
	 * @throws IllegalArgumentException Nombre es nulo o vacio. @see Contact
	 * @throws IllegalArgumentException Sin miembros, por lo menos debe tener un miembro. 
	 */
	public GroupImpl(String nombre, Contact[] miembros) {
		super(nombre);
		if (miembros == null || miembros.length == 0)
			throw new IllegalArgumentException("El grupo \"" + nombre + "\" debe tener al menos un miembro.");
		members = new HashSet<Contact>();
		añadirMiembros(miembros);
	}
	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#contieneA(uva.tds.pr1.Contact)
	 */
	@Override
	public boolean contieneA(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		return members.contains(miembro);
	}


	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#añadirMiembro(uva.tds.pr1.Contact)
	 */
	@Override
	public void añadirMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		members.add(miembro);
	}
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#añadirMiembros(uva.tds.pr1.Contact[])
	 */
	@Override
	public void añadirMiembros(Contact[] miembros) {
		if (miembros == null || miembros.length == 0) throw new IllegalArgumentException("El argumento no puede ser ni null ni no contener algún elemento.");
		for (int i = 0; i < miembros.length; i++) {
			añadirMiembro(miembros[i]);
		}
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#getMiembros()
	 */
	@Override
	public Contact[] getMiembros() {
		return members.toArray(new Contact[1]);
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#getId()
	 */
	@Override
	public String getId() {
		return super.getId();
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#eliminarMiembro(uva.tds.pr1.Contact)
	 */
	@Override
	public boolean eliminarMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		if (members.size() == 1) {
			throw new IllegalStateException("No se puede eliminar el miembro \"" + miembro.getId() + "\" del grupo \"" + this.getId() + "\" porque es su único miembro.");
		}

		return  members.remove(miembro);
	}
	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otro) {
		if ( !(otro instanceof Group) ) return false;
		if ( ((Group)otro).getId() == this.getId() ) {
			for (Contact contacto : ((Group)otro).getMiembros()) {
				if (!members.contains(contacto)) return false;
			}
			return true;
		} else return false;
	}

	
	/* (non-Javadoc)
	 * @see uva.tds.pr1.Group#toXmlString()
	 */
	@Override
	public String toXmlString() {
		String grupo =" <grupo gNombre=\""+getId()+"\">";
		for (int i = 0; i < getMiembros().length; i++) {
			 grupo+="\n   <miembro alias=\""+getMiembros()[i].getId()+"\">";
		}
		grupo+="\n </grupo>";
		return grupo;
	}

}
