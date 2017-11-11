package uva.tds.pr1.equipo09;

import java.util.HashSet;
import java.util.Set;

public class Group extends Contact {

	private Set<Contact> members; // usamos un conjunto para evitar que un mismo
									// contacto se encuentre repetido en el
									// grupo

	public Group(String nombre, Contact[] miembros) {
		super(nombre);
		if (miembros == null || miembros.length == 0)
			throw new IllegalArgumentException("El grupo \"" + nombre + "\" debe tener al menos un miembro.");
		members = new HashSet<Contact>();
		añadirMiembros(miembros);
	}
	
	public boolean contieneA(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		return members.contains(miembro);
	}

	/**
	 * Añade un nuevo miembro al grupo. Si el nuevo miembro ya pertenece al grupo, el método no tiene efecto.
	 * 
	 * @param miembro 
	 */
	public void añadirMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		members.add(miembro);
	}

	public void añadirMiembros(Contact[] miembros) {
		if (miembros == null || miembros.length == 0) throw new IllegalArgumentException("El argumento no puede ser ni null ni no contener algún elemento.");
		for (int i = 0; i < miembros.length; i++) {
			añadirMiembro(miembros[i]);
		}
	}

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
	 * @param miembro
	 * @return true si el miembro pertenecía al grupo, false en caso contrario
	 */
	public boolean eliminarMiembro(Contact miembro) {
		if (miembro == null) throw new IllegalArgumentException("El argumento es un null.");
		if (members.size() == 1) {
			throw new IllegalStateException("No se puede eliminar el miembro \"" + miembro.getId() + "\" del grupo \"" + this.getId() + "\" porque es su único miembro.");
		}
		return  members.remove(miembro);
	}

	
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
	
	public String imprimirGrupo() {
		String grupo =" <grupo gNombre=\""+getId()+"\">";
		for (int i = 0; i < getMiembros().length; i++) {
			 grupo+="\n   <miembro alias=\""+getMiembros()[i].getId()+"\">";
		}
		grupo+="\n </grupo>";
		return grupo;
	}

}
