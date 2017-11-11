package uva.tds.pr1.equipo09;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representacion de una libreta electronica, la cual contiene contactos.
 * 
 * @author ginquin
 * @author davidgo
 */
public class Libreta {

	private Map<String, Contact> contactos;
	private String xmlVersion;
	private String encoding;
	private String dtdSource;

	/**
	 * Constructor por defecto.Inicialzia una libreta sin contactos.
	 */
	public Libreta() {
		contactos = new HashMap<String, Contact>();
		xmlVersion = "1.0";
		encoding = "UTF-8";
		dtdSource = "src/uva/tds/pr1/equipo09/libreta.dtd";
	}

	/**
	 * Inidica si la libreta esta vacia o no.
	 * 
	 * @return true si hay algun miembro , false en caso contrario.
	 */
	public boolean isEmpty() {
		return contactos.isEmpty();
	}

	/**
	 * Devuelve los contactos de la libreta electronica.
	 * 
	 * @return contactos de la libreta electronica.
	 */
	public Contact[] getContactos() {
		return contactos.values().toArray(new Contact[1]);
	}

	/**
	 * Deuelve un contacto de la lista de contactos.
	 * 
	 * @param id
	 *            Identificador del contacto a elegir.
	 * @return contacto de la libreta de contactos.
	 * @throws IllegalArgumentException
	 *             El identificador es vacio o null.
	 */
	public Contact getContacto(String id) {
		if (id == null || id == "")
			throw new IllegalArgumentException("El id no puede ser ni null ni vacío.");
		
		return contactos.get(id);
	}

	/**
	 * Devuelve solo los contactos que son grupos en la libreta.
	 * 
	 * @return lista de los grupos en la libreta.
	 */
	public Group[] getGrupos() {
		if (contactos.isEmpty())
			throw new IllegalStateException("No hay ningún contacto en la libreta.");
		ArrayList<Group> grupos = new ArrayList<Group>();
		for (Contact cont : getContactos()) {
			if (cont instanceof Group) {
				grupos.add((Group) cont);
			}
		}
		if (grupos.isEmpty()) {
			return null;
		} else {
			return grupos.toArray(new Group[1]);
		}
	}

	/**
	 * Devuelve solo los contactos que son personas en la libreta.
	 * 
	 * @return lista de los personas en la libreta.
	 */
	public Person[] getPersonas() {
		if (contactos.isEmpty())
			throw new IllegalStateException("No hay ningún contacto en la libreta.");
		ArrayList<Person> personas = new ArrayList<Person>();
		for (Contact cont : getContactos()) {
			if (cont instanceof Person) {
				personas.add((Person) cont);
			}
		}
		if (personas.isEmpty()) {
			return null;
		} else {
			return personas.toArray(new Person[1]);
		}
	}

	/**
	 * Añade un contacto a la libreta tanto si es grupo o persona.
	 * 
	 * @param contacto
	 *            Contacto a añadir.
	 * @throws IllegalArgumentException
	 *             Al añadir un contacto grupo, algun miembro del grupo no esta
	 *             en la libreta.
	 * @throws IllegalArgumentException
	 *             Al añadir un contacto, ya existe el contacto.
	 */
	public void añadirContacto(Contact contacto) {
		if (contacto instanceof Group) {
			Group grupo = (Group) contacto;
			for (Contact miembro : grupo.getMiembros()) {
				if (!contactos.containsValue(miembro)) {
					throw new IllegalArgumentException("El miembro \"" + miembro.getId() + "\" del grupo \""
							+ grupo.getId() + "\" no se encuentra en la libreta");
				}
			}
		}
		if (contactos.get(contacto.getId()) != null) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" ya existe en la libreta");
		}
		// if (contactos.containsValue(contacto)) {
		// throw new IllegalArgumentException("El contacto \"" +
		// contacto.getId() + "\" ya existe en la libreta");
		// }
		contactos.put(contacto.getId(), contacto);
	}

	/**
	 * Permite añadir contactos a la libreta.
	 * 
	 * @param contactos
	 *            Nuevos contactos para añadir.
	 * @throws IllegalArgumentException
	 *             Por lo menos debe haber un contacto.
	 */
	public void añadirContactos(Contact[] contactos) {
		if (contactos == null || contactos.length == 0)
			throw new IllegalArgumentException("El argumento no puede ser ni null ni no contener algún elemento.");
		for (Contact contacto : contactos) {
			añadirContacto(contacto);
		}
	}

	/**
	 * Permite eliminar un contacto de la libreta.
	 * 
	 * @param contacto
	 *            Contacto a eliminar.
	 * @throws IllegalArgumentException
	 *             el contacto no se encuentra en la libreta.
	 */
	public void eliminarContacto(Contact contacto) {
		if (contactos.get(contacto.getId()) == null) {
			throw new IllegalArgumentException(
					"El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}

		if (contacto instanceof Person) {
				for (Group g : getGrupos()) {
					if(g.contieneA(contacto)){
						g.eliminarMiembro(contacto);
					}
				}
		}

		contactos.remove(contacto.getId());
	}

	/**
	 * Añade un nuevo miembro al grupo pasado como argumento. Pre: el contacto y
	 * el grupo han de existir previamente en la libreta Post: se ha añadido el
	 * contacto al grupo
	 * 
	 * @param miembro
	 *            Contacto que queremos añadir al grupo.
	 * @param grupo
	 *            Grupo al que queremos añadir un contacto.
	 * @throws IllegalArgumentException
	 *             El contacto no se encuentra en la libreta.
	 * @throws IllegalArgumentException
	 *             El grupo no se enuentra en la libreta.
	 */
	public void añadirMiembroGrupo(Contact miembro, Group grupo) {
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException(
					"El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ((contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group)) {
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
		grupo.añadirMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}

	/**
	 * Elimina un miembro al grupo pasado como argumento.
	 * 
	 * @param miembro
	 *            Contacto que queremos eliminar del grupo.
	 * @param grupo
	 *            Grupo al que queremos quitar un contacto.
	 * @throws IllegalArgumentException
	 *             El contacto no se encuentra en la libreta.
	 * @throws IllegalArgumentException
	 *             El grupo no se encuentra en la libreta.
	 * @throws IllegalArgumentException
	 *             No se puede borrar un grupo con un solo miembro.
	 */
	public void eliminarMiembroGrupo(Contact miembro, Group grupo) {
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException(
					"El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ((contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group)) {
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
		if (grupo.getMiembros().length == 1) {
			throw new IllegalArgumentException("No puedes borrar el miembro, el grupo debe tener uno como mínimo.");
		}
		grupo.eliminarMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}

	/**
	 * Devuelve la version de xml.
	 * 
	 * @return xmlVersion version del xml que se usa.
	 */
	public String getXmlVersion() {
		return xmlVersion;
	}

	/**
	 * Establece la version del xml a utilizar.
	 * 
	 * @param xmlVersion
	 *            nueva version del xml que se quiere usar.
	 */
	public void setXmlVersion(String xmlVersion) {
		this.xmlVersion = xmlVersion;
	}

	/**
	 * Devuelve la codificacion del xml.
	 * 
	 * @return encoding codificacion que se usa.
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Establece la codificacion a utilizar en el xml.
	 * 
	 * @param encoding
	 *            nueva codificacion para el xml.
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Devuelve la ruta donde se encuentra el DTD.
	 * 
	 * @return
	 */
	public String getDtdSource() {
		return dtdSource;
	}

	/**
	 * Establece la ruta en la que se encuentra el DTD.
	 * 
	 * @param dtdSource
	 */
	public void setDtdSource(String dtdSource) {
		this.dtdSource = dtdSource;
	}

	/**
	 * Genera una cadena que representa la libreta en formato xml.
	 * 
	 * @return xml Libreta en formato xml.
	 */
	public String toXmlString() {
		String xml = "<?xml version=\"" + getXmlVersion() + "\" encoding=\"" + getEncoding() + "\"?>\n";
		xml += "<!DOCTYPE libreta SYSTEM \"" + this.dtdSource + "\">\n";
		xml += "<libreta>";
		for (Contact con : getContactos()) {
			if (con instanceof Person)
				xml += "\n" + ((Person) con).toXmlString();
			else if (con instanceof Group)
				xml += "\n" + ((Group) con).toXmlString();
		}
		xml += "\n</libreta>";
		return xml;
	}

}