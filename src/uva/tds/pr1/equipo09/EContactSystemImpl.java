package uva.tds.pr1.equipo09;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
			bf.write(libreta.imprimirLibreta());
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
		libreta.añadirContacto(new Person(nickname, name, surName, emails, phones));
		modified = true;
	}

	@Override
	public void createNewGroup(String name, Contact[] contacts) {
		Group grupo = new Group(name, contacts);
		libreta.añadirContacto(grupo);
		modified = true;
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
