package uva.tds.pr1.equipo09;

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
	private FileReader input;
	private InputSource source;
	private SAXParserFactory factory;
	private SAXParser parser;
	
	public  EContactSystemImpl() {
		XmlLoaded=false;
	}
	
	@Override
	public void loadFrom(Path pathToXML) {
		// TODO Auto-generated method stub
		
		try {
			input = new FileReader(pathToXML.toString());
			source=  new InputSource(input);
			factory= SAXParserFactory.newInstance();
			factory.setValidating(true);
			 parser= factory.newSAXParser();
			parser.parse(source,(DefaultHandler) null);
			XmlLoaded=true;
			
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
		return false;
	}

	@Override
	public void createNewPerson(String name, String nickname, String surName, String[] emails,
			Map<String, EnumKindOfPhone> phones) {
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
