package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Implementacon de DefaultHandler
 * @author ginquin
 * @author davidgo
 */

public class MainHandler extends DefaultHandler {
	
	private Libreta libreta;
	private EnumKindOfPhone tipo;
	
	private String alias, gNombre, idref;
	private String nombre, apellido;
	private boolean siNombre, siApellido, siEmail, siTelefono;
	
	private ArrayList<Contact> miembros;
	private ArrayList<String> emails;
	private HashMap<String, EnumKindOfPhone> telefonos;

	@Override
	public void startDocument() throws SAXException {
		libreta = new Libreta();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName) {
		case "libreta":
			//no op
			break;
		case "persona":
			alias = attributes.getValue("alias");
			apellido = "";
			nombre = "";
			emails = new ArrayList<String>();
			telefonos = new HashMap<String, EnumKindOfPhone>();
			
			break;
		case "grupo":
			gNombre = attributes.getValue("gNombre");
			miembros = new ArrayList<Contact>();
			break;
		case "nombre":
			siNombre = true;
			break;
		case "apellidos":
			siApellido = true;
			break;
		case "email":
			siEmail = true;
			break;
		case "telef":
			siTelefono = true;
			tipo = EnumKindOfPhone.valueOf(attributes.getValue("tipo"));
			break;
		case "miembro":
			idref = attributes.getValue("alias");
			if (libreta.getContacto(idref) != null) {
				miembros.add(libreta.getContacto(idref));
			}
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(qName) {
		case "libreta":
			//no op
			break;
		case "persona":
			Person persona = (Person) libreta.getContacto(alias);
			if (persona == null) {
				persona = new Person(alias, nombre, apellido, emails.toArray(new String[1]), telefonos);
				libreta.añadirContacto(persona); //solo cuando no existe previamente el contacto, porque sino podria haber duplicados (dependiendo de la implementacion de libreta.java)
			}
			break;
		case "grupo":
			Group grupo = (Group) libreta.getContacto(gNombre);
			if (grupo == null) {
				grupo = new Group(gNombre, miembros.toArray(new Contact[1]));
				libreta.añadirContacto(grupo);
			}
			break;
		case "nombre":
			siNombre = false;
			break;
		case "apellidos":
			siApellido = false;
			break;
		case "email":
			siEmail = false;
			break;
		case "telef":
			siTelefono = false;
			break;
		case "miembro":
			//no op
			break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (siNombre) {
			nombre = new String(ch, start, length);
			if (!isAlphabetic(nombre) || nombre == null || nombre == "") {
				throw new IllegalStateException("El nombre asignado a " + alias + " contiene carácteres numéricos.");
			}
		}
		if (siApellido) {
			apellido = new String(ch, start, length);
			if (!isAlphabetic(apellido)) {
				throw new IllegalStateException("El apellido asignado a " + alias + " contiene carácteres numéricos.");
			}
		}
		if (siEmail) {
			String email = new String(ch, start, length);
			if (!email.contains("@")) {
				throw new IllegalStateException("El email de la persona " + alias + " no es correcto.");
			} 
			emails.add(email);
		}
		if (siTelefono) {
			String telefono = new String(ch, start, length);
			if (length != 9) throw new IllegalStateException("El numero de telefono de " + alias + " es invalido.");
			if (!isNumericInt(telefono)) throw new IllegalStateException("El numero de telefono de " + alias + " es invalido (no es numerico)."); 
			telefonos.put(telefono, tipo);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		throw e;
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		throw e;
	}
	

	/**
	 * Devuelve la libreta seleccionada mediante el parámetro index.
	 * 
	 * @param index
	 * @return una libreta de las libretas que hubiera en el XML leido por el parser SAX.
	 */
	public Libreta getLibreta() {
		return libreta;
	}
	
	/**
	 * Comprueba si el parametro pasado es un numerico entero.
	 * 
	 * @param str cadena a comprobar
	 * @return true si el parámetro es un numerico entero positivo, false en caso contrario
	 */
	private boolean isNumericInt(String str) {
		if (str == null || str == "") {
			return false;
		}
		return str.chars().allMatch(Character :: isDigit);
	}
	
	/**
	 * comprueba si el parametro es una cadena de caracteres.
	 * @param str cadena a comprobar
	 * @return true si el parámetro es una cadena de caracteres,false en caso contrario
	 */
	private boolean isAlphabetic(String str) {
		return str.chars().allMatch(Character :: isLetter);
	}
	
}



