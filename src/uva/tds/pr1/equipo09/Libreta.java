package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Libreta {

	private Map<String, Contact> contactos;
	private String xmlVersion;
	private String encoding;
	private String dtdSource;

	public Libreta() {
		contactos = new HashMap<String, Contact>();
		xmlVersion = "1.0";
		encoding = "UTF-8";
		dtdSource = "src/uva/tds/pr1/equipo09/libreta.dtd";
	}
	
	public boolean isEmpty() {
		return contactos.isEmpty();
	}
	
	public Contact[] getContactos() {
		return contactos.values().toArray(new Contact[1]);
	}
	
	public Contact getContacto(String id) {
		if (id == null || id == "") throw new IllegalArgumentException("El id no puede ser ni null ni vacío.");
		return contactos.get(id);
	}
	
	public Group[] getGrupos() {
		if (contactos.isEmpty()) throw new IllegalStateException("No hay ningún contacto en la libreta.");
		ArrayList<Group> grupos = new ArrayList<Group>();
		for (Contact cont : getContactos()) {
			if (cont instanceof Group) {
				grupos.add((Group)cont);
			}
		}
		if (grupos.isEmpty()) {
			return null;
		} else {
			return grupos.toArray(new Group[1]);
		}
	}
	
	public Person[] getPersonas() {
		if (contactos.isEmpty()) throw new IllegalStateException("No hay ningún contacto en la libreta.");
		ArrayList<Person> personas = new ArrayList<Person>();
		for (Contact cont : getContactos()) {
			if (cont instanceof Person) {
				personas.add((Person)cont);
			}
		}
		if (personas.isEmpty()) {
			return null;
		} else {
			return personas.toArray(new Person[1]);
		}
	}

	public void añadirContacto(Contact contacto) {
		if (contacto == null) throw new IllegalArgumentException("El argumento no puede ser null.");
		if (contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" ya existe en la libreta");
		}
		if (contacto instanceof Group) {
			Group grupo = (Group) contacto;
			for (Contact miembro : grupo.getMiembros()) {
				if (!contactos.containsValue(miembro)) { 
					//Si algun miembro del grupo que vamos a añadir a la libreta no pertenece previamente a ésta, lanzamos excepción
					throw new IllegalArgumentException("El miembro \"" + miembro.getId() + "\" del grupo \"" + grupo.getId() + "\" no se encuentra en la lbireta");
				}
			}
		}
		contactos.put(contacto.getId(), contacto);
	}
	
	public void añadirContactos(Contact[] contactos) {
		if (contactos == null || contactos.length == 0) throw new IllegalArgumentException("El argumento no puede ser ni null ni no contener algún elemento.");
		for (Contact contacto : contactos) {
			añadirContacto(contacto);
		}
	}
	

	public void eliminarContacto(Contact contacto) {
		if (contacto == null) throw new IllegalArgumentException("El argumento no puede ser null.");
		if (!contactos.containsValue(contacto)) {
			throw new IllegalArgumentException("El contacto \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		for (Group grupo : getGrupos()) { //Primero borramos todas las referencias del contacto en los grupos en los que estuviera presente
			if (grupo.contieneA(contacto)) {
				//el lanzamiento de excepcion por ser el unico miembro del grupo esta en Group.eliminarMiembro()
				grupo.eliminarMiembro(contacto);
			}
		}
		//Aqui borramos al contacto de la libreta en general
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
		if (miembro == null || grupo == null) throw new IllegalArgumentException("Ni el miembro ni el grupo pueden ser null.");
		
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException("El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group) ) {
			//Si existe un contacto con el id del grupo, pero dicho contacto no es instanceof Group....
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
		grupo.añadirMiembro(miembro);
		contactos.remove(grupo.getId());
		contactos.put(grupo.getId(), grupo);
	}
	
	
	//Por aqui me llego revisando (grupo, person y contacto estan revisados en principio).
	public void eliminarMiembroGrupo(Contact miembro, Group grupo) {
		if (!contactos.containsValue(miembro)) {
			throw new IllegalArgumentException("El contacto \"" + miembro.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(grupo)) {
			throw new IllegalArgumentException("El grupo \"" + grupo.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(grupo.getId()) != null) && !(contactos.get(grupo.getId()) instanceof Group) ) {
			//Si existe un contacto con el id del grupo, y dicho contacto no es un Group, entonces...
			throw new IllegalArgumentException("Existe un contacto con el id \"" + grupo.getId() + "\" en la libreta.");
		}
//		if(grupo.getMiembros().length==1){
//			throw new IllegalArgumentException("No puedes borrar el miembro, el grupo debe tener uno como mínimo. Antes se debe borrar el grupo.");
//		}
		grupo.eliminarMiembro(miembro);
		contactos.remove(grupo.getId()); //creo que no haria falta
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
