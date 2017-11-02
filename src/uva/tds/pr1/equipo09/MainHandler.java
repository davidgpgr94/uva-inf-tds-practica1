package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MainHandler extends DefaultHandler {

	private String alias, gNombre;
	private String idRef;
	private String nombre;
	private String apellido;
	private boolean siNombre;
	private boolean siEmail;
	private boolean siTelefono;
	private boolean siApellido;
	private EnumKindOfPhone tipo;
	private Map<String, EnumKindOfPhone> telefonos;
	private List<String> emails;
	private List<Contact> miembros;
	private Libreta libreta = new Libreta();

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		emails = new ArrayList<String>();
		telefonos = new HashMap<String, EnumKindOfPhone>();
		miembros = new ArrayList<Contact>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		switch (qName) {
		case "libreta":
			break;
		case "persona":
			alias = attributes.getValue(0);
			break;

		case "grupo":
			gNombre = attributes.getValue(0);
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
			tipo = EnumKindOfPhone.valueOf(attributes.getValue(0));

			siTelefono = true;
			break;

		case "miembro":
			idRef = attributes.getValue(0);
			if (libreta.getContacto(idRef) != null) {
				miembros.add(libreta.getContacto(idRef));
			}
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
			Person per = (Person) libreta.getContacto(alias);
			if (per == null) {
				//System.out.println(emails.toArray(new String[1]).length);
				per = new Person(alias, nombre, siApellido ? apellido : "", emails.toArray(new String[1]), telefonos);
			}
			emails = new ArrayList<String>(); // cade vez que encuentre una persona incializa un nuevo array de emails.
			telefonos = new HashMap<String, EnumKindOfPhone>();// cade vez que encuentre una persona incializa un nuevo array de telefono.
			libreta.añadirContacto(per);
			break;

		case "grupo":
			Group grupo = (Group) libreta.getContacto(gNombre);
			if (grupo == null) {
				grupo = new Group(gNombre, miembros.toArray(new Contact[1]));
			}
			miembros = new ArrayList<Contact>();
			libreta.añadirContacto(grupo);
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
			// Quitar?
			break;

		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		if (siNombre)
			nombre = new String(ch, start, length);
		if (siApellido)
			apellido = new String(ch, start, length);
		if (siEmail) {
			String email = new String(ch, start, length);
			emails.add(email);
		}
		if (siTelefono) {
			String telefono = new String(ch, start, length);
			telefonos.put(telefono, tipo);
		}
	}

	public Libreta getLibreta() {
		return libreta;
	}

}
