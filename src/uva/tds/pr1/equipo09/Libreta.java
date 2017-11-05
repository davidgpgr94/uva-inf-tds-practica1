package uva.tds.pr1.equipo09;

import java.util.HashMap;
import java.util.Map;

public class Libreta {

	private Map<String, Contact> contactos;

	public Libreta() {
		contactos = new HashMap<String, Contact>();
	}
	
	public Contact[] getContactos() {
		return contactos.values().toArray(new Contact[1]);
	}
	
	public Contact getContacto(String id) {
		return contactos.get(id);
	}
	
	public void añadirContactos(Contact[] contactos) {
		for (Contact contacto : contactos) {
			añadirContacto(contacto);
		}
	}

	public void añadirContacto(Contact contacto) {
		if (contacto instanceof Group) {
			Group grupo = (Group) contacto;
			for (Contact miembro : grupo.getMiembros()) {
				if (!contactos.containsValue(miembro)) {
					throw new IllegalArgumentException("El miembro \"" + miembro.getId() + "\" del grupo \"" + grupo.getId() + "\" no se encuentra en la lbireta");
				}
			}
		}
		if (contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" ya existe en la libreta");
		}
		contactos.put(contacto.getId(), contacto);
	}

	public void eliminarContacto(Contact contacto) {
		if (!contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		contactos.remove(contacto.getId());
	}
	
	/**
	 * Añade un nuevo miembro al grupo pasado.
	 * 
	 *  Pre: el contacto y el grupo han de existir previamente en la libreta
	 *  Post: se ha añadido el contacto al grupo
	 * @param contacto
	 * @param grupo
	 */
	public void añadirMiembroGrupo(Contact miembro, Group grupo) {
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException("El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group) ) {
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
		grupo.añadirMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}
	
	public void eliminarMiembroGrupo(Contact miembro, Group grupo) {
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException("El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group) ) {
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
		grupo.eliminarMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}
	
	
	
	
	
	
	
	
	
}