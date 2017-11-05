package uva.tds.pr1.equipo09;

import java.util.HashMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EContactSystemImpl implements EContactSystemInterface {

	private boolean XmlLoaded;
	private boolean modified;
	private FileReader input;
	private InputSource source;
	private SAXParserFactory factory;
	private SAXParser parser;

	private Libreta libreta;

	public EContactSystemImpl() {
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
			MainHandler2 handler = new MainHandler2();
			parser.parse(source, handler);
			libreta = handler.getLibreta();
			XmlLoaded = true;
			
			// prueba
			/*for (Contact contacto : libreta.getContactos().values()) {
				if (contacto instanceof Person) {
					Person persona = (Person) contacto;
					System.out.println("Alias: " + persona.getId() + " nombre: " + persona.getNombre() + " apellido: "
							+ persona.getApellido());
					for (String email : persona.getEmails()) {
						System.out.println("Email: " + email);
					}
					HashMap<String, EnumKindOfPhone> telefonos = (HashMap<String, EnumKindOfPhone>) persona
							.getTelefonos();
					for (String numero : telefonos.keySet().toArray(new String[1])) {
						System.out.println("Numero: " + numero + " tipo: " + telefonos.get(numero));
					}
				} else if (contacto instanceof Group) {
					Group grupo = (Group) contacto;
					System.out.println("-------__-------Ini-Grupo--------__-----------");
					System.out.println("Nombre del grupo: " + grupo.getId());
					System.out.println("Miembros");
					for (Contact miembro : grupo.getMiembros()) {
						System.out.println("Alias: " + miembro.getId());
					}
					System.out.println("-------__-------Fin-Grupo--------__-----------");
				}
			}*/

			/*
			 * // prueba for (Contact con : libreta.getContactos().values()) {
			 * if (con instanceof Person) {
			 * 
			 * System.out.println(1 + " " + ((Person) con).getId() + " ape " +
			 * ((Person)con).getApellido()); for (int i = 0; i < ((Person)
			 * con).getEmails().length; i++) { System.out.println(((Person)
			 * con).getEmails()[i]); }
			 * 
			 * for (EnumKindOfPhone en : ((Person) con).getTelefonos().values())
			 * { System.out.println(en); }
			 * 
			 * 
			 * } else if (con instanceof Group) { System.out.println(2 + " " +
			 * ((Group) con).getId() + " numero de miembros " + ((Group)
			 * con).getMiembros().length); } }
			 */

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateTo(Path pathToXML) {
		// TODO Auto-generated method stub

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
		if (libreta.getContacto(nickname) == null) {
			Person persona = new Person(nickname, name, surName, emails, phones);
			libreta.añadirContacto(persona);
			modified = true;
		} else {
			throw new IllegalArgumentException("Ya se encuentra un contacto (grupo o persona) con el nombre \"" + nickname + "\" en la libreta");
		}
	}

	@Override
	public void createNewGroup(String name, Contact[] contacts) {
		for (Contact contacto : contacts) { //Comprobamos que los contactos que se quieren añadir al grupo, existen previamente en la libreta
			if (libreta.getContacto(contacto.getId()) == null) throw new IllegalArgumentException("El contacto con nickname \"" + contacto.getId() + "\" no se encuentra en la libreta.");
		}
		if (libreta.getContacto(name) == null) { //Si el nombre del nuevo grupo no se encuentra en la libreta, creamos el grupo y lo añadimos a la libreta
			Group grupo = new Group(name, contacts);
			libreta.añadirContacto(grupo);
			modified = true;
		} else {
			throw new IllegalArgumentException("Ya se encuentra un contacto (grupo o persona) con el nombre \"" + name + "\" en la libreta");
		}
	}

	@Override
	public Contact getAnyContactById(String id) {
		return libreta.getContacto(id);
	}

	@Override
	public Person getPersonByNickname(String name) {
		if (libreta.getContacto(name) instanceof Person) {
			return (Person)libreta.getContacto(name);
		} else {
			return null;
		}
	}

	@Override
	public Group getGroupByName(String name) {
		if (libreta.getContacto(name) instanceof Group) {
			return (Group)libreta.getContacto(name);
		} else {
			return null;
		}
	}

	@Override
	public void addContactToGroup(Contact contact, Group group) {
		libreta.añadirMiembroGrupo(contact, group);
		modified = true;
	}

	@Override
	public void removeContactFromGroup(Contact contact, Group group) {
		libreta.eliminarMiembroGrupo(contact, group);
		modified = true;
	}

	@Override
	public void removeContactFromSystem(Contact contact) {
		libreta.eliminarContacto(contact);
		modified = true;
	}

}
