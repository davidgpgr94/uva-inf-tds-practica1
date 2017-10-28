package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MainHandler extends DefaultHandler{
	
	private String alias,gNombre;
	private String nombre;
	private String apellido;
	private boolean siNombre;
	private boolean siEmail;
	private boolean siTelefono;
	private boolean siApellido;
	private EnumKindOfPhone tipo;
	private Map<String,EnumKindOfPhone> telefonos;
	private	List<String> emails;
	private	List<Contact> miembros;
	private Libreta libreta= new Libreta();
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		emails= new ArrayList<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	
		switch (qName) {
		case "libreta":			
			break;
		case "persona":
				alias= attributes.getValue(0);
			break;
			
		case "grupo":
				gNombre=attributes.getValue(0);		
			break;
		case "nombre":
			siNombre=true;
			break;
		case "apellidos":
			siApellido=true;
			break;
		case "email":
			siEmail=true;
			break;
		case "telef":
			tipo= EnumKindOfPhone.valueOf(attributes.getValue(0));
			siTelefono=true;
			break;
			
		case "miembro":
			break;
			
		}
		
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		switch (qName) {
		case "libreta":			
			break;
		case "persona":
				Person per=(Person) libreta.getContactos().get(alias);
				if(per==null){
					per=new Person(alias, nombre,emails);
					
				}
				emails= new ArrayList<>(); //cade vez que encuentre una persona incializa un nuevo array de emails.
				telefonos=new HashMap<>();//cade vez que encuentre una persona incializa un nuevo array de telefono.
				libreta.getContactos().put(alias, per);
				
			break;
			
		case "grupo":	
			break;
		case "nombre":
			siNombre=false;
			break;
		case "apellidos":
			siApellido=false;
			break;
		case "email":
			siEmail=false;
			break;
		case "telef":
			siTelefono=false;
			break;
			
		case "miembro":
			break;
			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		
		if(siNombre)	nombre= new String(ch, start, length);
		if(siApellido)	apellido= new String(ch, start, length);
		if(siEmail){ 	
			String email= new String(ch, start, length);
			emails.add(email);
		}
		if(siTelefono){ 
			String telefono= new String(ch, start, length);
	
		}
	}
	
	public Libreta getLibreta(){
		return libreta;
	}

}
