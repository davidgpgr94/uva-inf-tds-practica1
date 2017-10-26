package uva.tds.pr1.equipo09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uva.tds.pr1.Contact;
import uva.tds.pr1.EnumKindOfPhone;
import uva.tds.pr1.Group;
import uva.tds.pr1.Person;

public class EContactSystemImpl implements uva.tds.pr1.EContactSystemInterface {
	private FileReader input;
	private InputSource source;
	private DocumentBuilderFactory domParserFactory;
	private DocumentBuilder parser;
	private Document doc;
	
	private boolean loaded = false;
	private boolean modified = false;

	@Override
	public void loadFrom(Path pathToXML) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		domParserFactory = DocumentBuilderFactory.newInstance();
		domParserFactory.setValidating(true);
		parser = domParserFactory.newDocumentBuilder();
		
		input = new FileReader(pathToXML.toString());
		source = new InputSource(input);
		
		doc = parser.parse(source);
		loaded = true;
	}

	@Override
	public void updateTo(Path pathToXML) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isXMLLoaded() {
		// TODO Auto-generated method stub
		return loaded;
	}

	@Override
	public boolean isModifiedAfterLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNewPerson(String name, String nickname, String surName, String[] emails, Map<String, EnumKindOfPhone> phones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewGroup(String name, Contact[] contacts) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void removeContactFromGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContactFromSystem(Contact contact) {
		// TODO Auto-generated method stub
		
	}

}
