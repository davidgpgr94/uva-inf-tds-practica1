package uva.tds.pr1.equipo09;

import java.util.HashSet;
import java.util.Set;
/**
 * Representacion de un grupo en la libreta electronica.
 * @author ginquin
 * @author davidgo
 */
public class Group extends Contact {

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
	public Group(String nombre, Contact[] miembros) {
		super(nombre);
		if (miembros == null || miembros.length == 0)
			throw new IllegalArgumentException("El grupo \"" + nombre + "\" debe tener al menos un miembro.");
		members = new HashSet<Contact>();
		añadirMiembros(miembros);
	}
	
	/**
	 * Devuelve true si el miembro esta en el grupo, false en caso contrario.
	 * @param miembro miembro que se desea saber si  esta en el grupo.
	 * @return true si esta en el grupo, false en caso contrario.
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 */
	public boolean contieneA(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		return members.contains(miembro);
	}


	/**
	 * Añade un nuevo miembro al grupo. Si el nuevo miembro ya pertenece al grupo, el método no tiene efecto.
	 * @param miembro Nuevo miembro.
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 */
	public void añadirMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		members.add(miembro);
	}
	/**
	 * Permite añadir una lista de miembros al grupo. si algun miembro ya existe no lo añade.
	 * @param miembros Lista de miembros para añadir al grupo.
	 *  @throws IllegalArgumentException La lista de miembros no puede ser null y contener al menos un miembro.
	 */
	public void añadirMiembros(Contact[] miembros) {
		if (miembros == null || miembros.length == 0) throw new IllegalArgumentException("El argumento no puede ser ni null ni no contener algún elemento.");
		for (int i = 0; i < miembros.length; i++) {
			añadirMiembro(miembros[i]);
		}
	}

	/**
	 * Devuelve los miembros del grupo.
	 * @return miembros que pertenecen al grupo.
	 */
	public Contact[] getMiembros() {
		return members.toArray(new Contact[1]);
	}

	/**
	 * Devuelve el nombre del grupo
	 */
	public String getId() {
		return super.getId();
	}

	/**
	 * Elimina la primera ocurrencia del miembro en el grupo
	 * 
	 * @param miembro Miembro a borrar del grupo.
	 * @return true si el miembro pertenecía al grupo, false en caso contrario
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 * @throws IllegalStateException El grupo solo tiene un miembro.
	 */
	public boolean eliminarMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		if (members.size() == 1) {
			throw new IllegalStateException("No se puede eliminar el miembro \"" + miembro.getId() + "\" del grupo \"" + this.getId() + "\" porque es su único miembro.");
		}

		return  members.remove(miembro);
	}
	
	/**
	 * Sobreescribe el metodo equals para que tome en cuenta todos los atributos
	 * de un grupo.
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

	
	/**
	 * Genera una cadena que representa un grupo en formato xml.
	 * 
	 * @return grupo Grupo en formato xml.
	 */
	public String toXmlString() {
		String grupo =" <grupo gNombre=\""+getId()+"\">";
		for (int i = 0; i < getMiembros().length; i++) {
			 grupo+="\n   <miembro alias=\""+getMiembros()[i].getId()+"\">";
		}
		grupo+="\n </grupo>";
		return grupo;
	}

}
