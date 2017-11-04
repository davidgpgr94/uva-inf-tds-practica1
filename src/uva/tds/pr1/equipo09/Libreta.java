package uva.tds.pr1.equipo09;

import java.util.HashMap;
import java.util.Map;

public class Libreta {

	private Map<String, Contact> contactos;

	public Libreta() {
		// TODO Auto-generated constructor stub
		contactos = new HashMap<String, Contact>();
	}

//	public Map<String, Contact> getContactos() {
//		return contactos;
//	}
	
	public Contact[] getContactos() {
		return contactos.values().toArray(new Contact[1]);
	}

	public void setContactos(Map<String, Contact> contactos) {
		for (Contact contacto : contactos.values()) {
			añadirContacto(contacto);
		}
		//this.contactos = contactos; //esto es lo que estaba antes
	}

	public void añadirContacto(Contact contacto) {
		if (contactos.get(contacto.getId()) == null) {
			contactos.put(contacto.getId(), contacto);
		} else {
			throw new IllegalArgumentException("El id (" + contacto.getId() + ") del contacto ya existe en la libreta");
		}
	}

	public Contact getContacto(String id) {
		return contactos.get(id);
	}

}