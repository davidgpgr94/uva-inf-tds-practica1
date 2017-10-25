package uva.tds.pr1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface EContactSystemInterface {
	public static EcontactSystemInterface contactSystemFactory();

	void loadFrom(Path pathToXML) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException;

	void updateTo(Path pathToXML);

	boolean isXMLLoaded();

	boolean isModifiedAfterLoaded();

	void createNewPerson(String name, String nickname, String surName, String[] emails,
			Map<String, EnumKindOfPhone> phones);

	void createNewGroup(String name, Contact[] contacts);

	Contact getAnyContactById(String id);

	Person getPersonByNickname(String name);

	Group getGroupByName(String name);

	void addContactToGroup(Contact contact, Group group);

	void removeContactFromGroup(Contact contact, Group group);

	void removeContactFromSystem(Contact contact);
}
