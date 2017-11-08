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
			throw new IllegalArgumentException("El grupo debe tener al menos un miembro.");
		members = new HashSet<Contact>();
		añadirMiembros(miembros);
	}

	/**
	 * Añade un nuevo miembro al grupo. Si el nuevo miembro ya pertenece al grupo, el método no tiene efecto.
	 * 
	 * @param miembro 
	 */
	public void añadirMiembro(Contact miembro) {
		members.add(miembro);
	}

	public void añadirMiembros(Contact[] miembros) {
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
		
		return  members.remove(miembro);
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
