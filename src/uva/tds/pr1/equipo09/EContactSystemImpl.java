package uva.tds.pr1.equipo09;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uva.tds.pr1.Contact;
import uva.tds.pr1.EContactSystemInterface;
import uva.tds.pr1.EnumKindOfPhone;
import uva.tds.pr1.Group;
import uva.tds.pr1.Person;

/**
 * Implementación de EContactSystemInterface.
 * @author ginquin
 * @author davidgo
 *
 */
public class EContactSystemImpl implements EContactSystemInterface {

	private boolean XmlLoaded;
	private boolean modified;
	private FileReader input;
	private InputSource source;
	private SAXParserFactory factory;
	private SAXParser parser;

	private Libreta libreta;
	
	/**
	 * Crea una nueva instancia de EContactSystemImpl
	 * @return una nueva instancia de EContactSystemImpl
	 */
	public static EContactSystemInterface contactSystemFactory() {
		return new EContactSystemImpl();
	}

	/**
	 * Constructor de la clase EContactSystemImpl
	 */
	protected EContactSystemImpl() {
		XmlLoaded = false;
		modified = false;
	}

	@Override
	public void loadFrom(Path pathToXML) {
		try {
			input = new FileReader(pathToXML.toString());
			source = new InputSource(input);
			factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			parser = factory.newSAXParser();
			MainHandler handler = new MainHandler();
			parser.parse(source, handler);
			libreta = handler.getLibreta();
			XmlLoaded = true;			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("El archivo " + pathToXML.toString() + " no se ha encontrado.");
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException(e.getMessage());
		} catch (SAXException e) {
			throw new IllegalStateException(e.getMessage());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	@Override
	public void updateTo(Path pathToXML) {
		if(!isModifiedAfterLoaded()){
			throw new IllegalStateException("El documento debe ser modificado para volcar en un xml");
		}

		try(BufferedWriter bf= new BufferedWriter(new FileWriter(pathToXML.toFile()))){
			bf.write(libreta.toXmlString());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public boolean isXMLLoaded() {
		return XmlLoaded;
	}

	@Override
	public boolean isModifiedAfterLoaded() {
		return modified;
	}

	@Override
	public void createNewPerson(String name, String nickname, String surName, String[] emails, Map<String, EnumKindOfPhone> phones) {
		if(nickname==null || nickname=="") throw new IllegalArgumentException("Nickname no válido \"" + nickname==null?"null":"String sin valor" + "\".");
		if (name == null || name == "") {
			throw new IllegalArgumentException("El name ha de ser " + name == null ? "no null." : "un String con valor.");
		}
		if (surName == null) throw new IllegalArgumentException("El surName no puede ser null (si puede ser una cadena vacía).");
		if (emails.length == 0) { //Nos aseguramos de que al menos tenga un email
			throw new IllegalArgumentException("Ha de tener al menos un email."); 
		}
		libreta.añadirContacto(new PersonImpl(nickname, name, surName, emails, phones));
		modified = true;
	}

	@Override
	public void createNewGroup(String name, Contact[] contacts) {
		if(name==null || name=="") throw new IllegalArgumentException("Name no válido \"" + name==null?"null":"String sin valor" + "\".");
		if (contacts == null || contacts.length == 0)
			throw new IllegalArgumentException("El grupo \"" + name + "\" debe tener al menos un miembro.");
		Group grupo = new GroupImpl(name, contacts);
		libreta.añadirContacto((Contact)grupo);
		modified = true;
	}

	@Override
	public Contact getAnyContactById(String id) {
		if (id == null || id == "") throw new IllegalArgumentException("Id no válido.");
		return libreta.getContacto(id);
	}

	@Override
	public Person getPersonByNickname(String name) {
		if (name == null || name == "") throw new IllegalArgumentException("Name no válido.");
		if (libreta.getContacto(name) instanceof Person) {
			return (Person)libreta.getContacto(name);
		} else {
			return null;
		}
	}

	@Override
	public Group getGroupByName(String name) {
		if (name == null || name == "") throw new IllegalArgumentException("Name no válido.");
		if (libreta.getContacto(name) instanceof Group) {
			return (Group)libreta.getContacto(name);
		} else {
			return null;
		}
	}

	@Override
	public void addContactToGroup(Contact contact, Group group) {
		if (contact == null || group == null) throw new IllegalArgumentException("Ni el contact ni el group pueden ser null.");
		HashMap<String, Contact> contactos = new HashMap<String, Contact>(); //para facilitar las posteriores comprobaciones 
		for (Contact contacto : libreta.getContactos()) {
			contactos.put(contacto.getId(), contacto);
		}
		if (!contactos.containsValue(contact)) {
			throw new IllegalArgumentException("El contacto \"" + contact.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(group)) {
			throw new IllegalArgumentException("El grupo \"" + group.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(group.getId()) != null) && !(contactos.get(group.getId()) instanceof Group) ) {
			//Si existe un contacto con el id del grupo, pero dicho contacto no es instanceof Group....
			throw new IllegalArgumentException("Existe un contacto con el id \"" + group.getId() + "\" en la libreta.");
		}
		libreta.añadirMiembroGrupo(contact, group);
		modified = true;
	}

	@Override
	public void removeContactFromGroup(Contact contact, Group group) {
		if (contact == null || group == null) throw new IllegalArgumentException("Ni el contact ni el group pueden ser null.");
		HashMap<String, Contact> contactos = new HashMap<String, Contact>(); //para facilitar las posteriores comprobaciones 
		for (Contact contacto : libreta.getContactos()) {
			contactos.put(contacto.getId(), contacto);
		}
		if (!contactos.containsValue(contact)) {
			throw new IllegalArgumentException("El contacto \"" + contact.getId() + "\" no se encuentra en la libreta.");
		}
		if (!contactos.containsValue(group)) {
			throw new IllegalArgumentException("El grupo \"" + group.getId() + "\" no se encuentra en la libreta.");
		}
		if ( (contactos.get(group.getId()) != null) && !(contactos.get(group.getId()) instanceof Group) ) {
			//Si existe un contacto con el id del grupo, pero dicho contacto no es instanceof Group....
			throw new IllegalArgumentException("Existe un contacto con el id \"" + group.getId() + "\" en la libreta.");
		}
		libreta.eliminarMiembroGrupo(contact, group);
		modified = true;
	}

	@Override
	public void removeContactFromSystem(Contact contact) {
		if (contact == null) throw new IllegalArgumentException("El argumento no puede ser null.");
		HashMap<String, Contact> contactos = new HashMap<String, Contact>(); //para facilitar las posteriores comprobaciones 
		for (Contact contacto : libreta.getContactos()) {
			contactos.put(contacto.getId(), contacto);
		}
		if (!contactos.containsValue(contact)) {
			throw new IllegalArgumentException("El contacto \"" + contact.getId() + "\" no se encuentra en la libreta.");
		}
		for (Group grupo : libreta.getGrupos()) {
			if (grupo.contieneA(contact) && grupo.getMiembros().length == 1) {
				throw new IllegalStateException("No se puede eliminar el contacto \"" + contact.getId() + "\" del sistema porque es el único miembro del grupo \"" + grupo.getId() + "\".");
			}
		}
		libreta.eliminarContacto(contact);
		modified = true;
	}

}
