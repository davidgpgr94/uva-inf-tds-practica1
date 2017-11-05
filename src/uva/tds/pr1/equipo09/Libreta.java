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

	public void añadirContactos(Map<String, Contact> contactos) {
		for (Contact contacto : contactos.values()) {
			añadirContacto(contacto);
		}
	}

	public void añadirContacto(Contact contacto) {
		if (contactos.get(contacto.getId()) == null) {
			contactos.put(contacto.getId(), contacto);
		} else {
			throw new IllegalArgumentException("El id \"" + contacto.getId() + "\" del contacto ya existe en la libreta");
		}
	}

	public void eliminarContacto(Contact contacto) {
		if (!contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		contactos.remove(contacto.getId());
	}
	
	/**
	 * Añade un nuevo contacto al grupo pasado.
	 * 
	 *  Pre: el contacto y el grupo han de existir previamente en la libreta
	 *  Post: se ha añadido el contacto al grupo
	 * @param contacto
	 * @param grupo
	 */
	public void añadirMiembroGrupo(Contact contacto, Group grupo) {
		if (!contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsKey(grupo.getId()) || !(contactos.get(grupo.getId()) instanceof Group) ) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		grupo.añadirMiembro(contacto);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}
	
	public void eliminarMiembroGrupo(Contact contacto, Group grupo) {
		if (!contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsKey(grupo.getId()) || !(contactos.get(grupo.getId()) instanceof Group) ) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		grupo.eliminarMiembro(contacto);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}
	
	
	
	
	
	
	
	
	
}