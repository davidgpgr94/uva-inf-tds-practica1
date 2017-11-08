package uva.tds.pr1.equipo09;

import java.util.HashMap;
import java.util.Map;

public class Libreta {

	private Map<String, Contact> contactos;
	private String xmlVersion;
	private String encoding;
	private String dtdSource;

	public Libreta() {
		contactos = new HashMap<String, Contact>();
		xmlVersion="1.0";
		encoding="UTF-8";
		dtdSource="src/uva/tds/pr1/equipo09/libreta.dtd";
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
		if(grupo.getMiembros().length==1){
			throw new IllegalArgumentException("No puedes borrar el miembro, el grupo debe tener uno como mínimo.");
		}
		grupo.eliminarMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}

	public String getXmlVersion() {
		return xmlVersion;
	}

	public void setXmlVersion(String xmlVersion) {
		this.xmlVersion = xmlVersion;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	
	

	public String imprimirLibreta(){
		String xml="<?xml version=\""+getXmlVersion()+"\" encoding=\""+getEncoding()+"\"?>\n";
		xml += "<!DOCTYPE libreta SYSTEM \""+this.dtdSource+"\">\n";
		xml += "<libreta>";
		for (Contact  con: getContactos()){
			if(con instanceof Person) xml+= "\n"+ ((Person)con).imprimirPersona();
			else if(con instanceof Group) xml+= "\n"+ ((Group)con).imprimirGrupo();
		}
		xml += "\n</libreta>";
		return xml;
	}
	
	
	
	
	
}