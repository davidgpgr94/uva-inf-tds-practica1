package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person extends Contact {
	private String nombre;
	private String apellido;
	private List<String> emails;
	private Map<String, EnumKindOfPhone> telefonos;
	
	/*public Person(String alias, String nombre, String mail) {
		super(alias);
		assert alias != null && nombre != null && mail != null;
		emails = new ArrayList<String>();
		telefonos = new HashMap<String, EnumKindOfPhone>();
		setApellido(null);
		setNombre(nombre);
		añadirEmail(mail);
	}
	
	public Person(String alias, String nombre, String mail, String apellido) {
		this(alias, nombre, mail);
		setApellido(apellido);
	}*/
	
	public Person(String alias, String nombre, String[] emails) {
		super(alias);
		this.emails = new ArrayList<String>();
		telefonos = new HashMap<String, EnumKindOfPhone>();
		setNombre(nombre);
		añadirEmails(emails);
	}
	
	public Person(String alias, String nombre, List<String> emails) {
		super(alias);
		this.emails = new ArrayList<String>();
		telefonos = new HashMap<String, EnumKindOfPhone>();
		setNombre(nombre);
		añadirEmails(emails);
	}
	
	public Person(String alias, String nombre, String[] emails, Map<String, EnumKindOfPhone> telefonos) {
		this(alias, nombre, emails);
		añadirTelefonos(telefonos);
	}
	
	public Map<String, EnumKindOfPhone> getTelefonos() {
		return telefonos;
	}
	
	public String getAlias() {
		return getId();
	}
	
	public String getNombre() {
		return nombre; 
	}
	
	public String[] getEmails() {
		return emails.toArray(new String[1]);
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void añadirEmail(String email) {
		this.emails.add(email);
	}

	public void añadirEmails(String[] emails) {
		for (int i = 0; i < emails.length; i++) {
			añadirEmail(emails[i]);
		}
	}
	public void añadirEmails(List<String> emails) {
		this.emails= emails;
	}
	
	private void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void añadirTelefono(String numero, EnumKindOfPhone tipo) {
		telefonos.put(numero, tipo);		
	}
	
	public void añadirTelefonos(Map<String, EnumKindOfPhone> telefonos) {		
		this.telefonos.putAll(telefonos);
		
	}


}
