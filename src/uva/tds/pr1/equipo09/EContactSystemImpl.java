package uva.tds.pr1.equipo09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import uva.tds.pr1.EContactSystemInterface;

public class EContactSystemImpl implements EContactSystemInterface {
	
	private FileReader input;
	private InputSource source;
	private SAXParserFactory factory = SAXParserFactory.newInstance();
	private SAXParser parser;
	
	@Override
	public void loadFrom(Path pathToXML) {
		// TODO Auto-generated method stub
		try {
			input = new FileReader(pathToXML.toString());
			source = new InputSource(input);
			factory.setValidating(true);
			parser = factory.newSAXParser();
			parser.parse(source, new MainHandler());		
		} catch (Exception e) {
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
		return false;
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
