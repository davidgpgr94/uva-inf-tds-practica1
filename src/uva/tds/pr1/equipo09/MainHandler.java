package uva.tds.pr1.equipo09;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MainHandler extends DefaultHandler {
	
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		//Accion que se realice cada vez que SAX "lea" un nuevo elemento
		System.out.println(localName + " " + qName);
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
	}
}
