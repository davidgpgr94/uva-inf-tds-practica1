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
import org.xml.sax.helpers.DefaultHandler;

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
		// TODO Auto-generated method stub

		try {
			input = new FileReader(pathToXML.toString());
			source = new InputSource(input);
			factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			parser = factory.newSAXParser();
			MainHandler2 handler = new MainHandler2();
			parser.parse(source, handler);
			libreta = handler.getLibreta();
			
			//prueba
			for (Contact contacto : libreta.getContactos().values()) {
				if (contacto instanceof Person) {
					Person persona = (Person) contacto;
					System.out.println("Alias: " + persona.getId() + " nombre: " + persona.getNombre() + " apellido: " + persona.getApellido());
					for (String email : persona.getEmails()) {
						System.out.println("Email: " + email);
					}
					HashMap<String, EnumKindOfPhone> telefonos = (HashMap<String, EnumKindOfPhone>)persona.getTelefonos();
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
			}
			
			/*
			// prueba 
			for (Contact con : libreta.getContactos().values()) {
				if (con instanceof Person) {

					System.out.println(1 + " " + ((Person) con).getId() + " ape " + ((Person)con).getApellido());
					for (int i = 0; i < ((Person) con).getEmails().length; i++) {
						System.out.println(((Person) con).getEmails()[i]);
					}

					for (EnumKindOfPhone en : ((Person) con).getTelefonos().values()) {
						System.out.println(en);
					}
					

				} else if (con instanceof Group) {
					System.out.println(2 + " " + ((Group) con).getId() + " numero de miembros "
							+ ((Group) con).getMiembros().length);
				}
			}
			*/

			XmlLoaded = true;

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
		// TODO Auto-generated method stub
		return XmlLoaded;
	}

	@Override
	public boolean isModifiedAfterLoaded() {
		// TODO Auto-generated method stub
		return modified;
	}

	@Override
	public void createNewPerson(String name, String nickname, String surName, String[] emails,
			Map<String, EnumKindOfPhone> phones) {
		// TODO Auto-generated method stub

		
		
		//Despues de todo
		modified = true;
	}

	@Override
	public void createNewGroup(String name, Contact[] contacts) {
		// TODO Auto-generated method stub

		//Despues de todo
		modified = true;
	}

	@Override
	public Contact getAnyContactById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByNickname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroupByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContactToGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub

		//Despues de todo
		modified = true;
	}

	@Override
	public void removeContactFromGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub

		//Despues de todo
		modified = true;
	}

	@Override
	public void removeContactFromSystem(Contact contact) {
		// TODO Auto-generated method stub

		//Despues de todo
		modified = true;
	}

}
