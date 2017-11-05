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
	
	public Person(String alias, String nombre, String apellido, String[] emails, Map<String, EnumKindOfPhone> telefonos) {
		super(alias);
		if(nombre==null || nombre=="") throw new IllegalArgumentException("El nombre ha de ser " + nombre==null?"no null.":"un String con valor.");
		if (emails.length == 0) throw new IllegalArgumentException("Ha de tener al menos un email");
		this.emails = new ArrayList<String>();
		this.telefonos = new HashMap<String, EnumKindOfPhone>();
		setNombre(nombre);
		setApellido(apellido);
		añadirEmails(emails);
		añadirTelefonos(telefonos);
	}
	
	public Map<String, EnumKindOfPhone> getTelefonos() {
		return telefonos;
	}
	
	/**
	 * Devuelve el alias de la persona
	 */
	public String getId() {
		return super.getId();
	}
	
	public String getNombre() {
		return nombre; 
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String[] getEmails() {
		return emails.toArray(new String[1]);
	}
	
	public void añadirEmail(String email) {
		if (!email.contains("@") || email == "" || email == null) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}
		emails.add(email);
	}
	
	public void añadirEmails(String[] emails) {
		for (String email : emails) {
			añadirEmail(email);
		}
	}
	
	public void añadirTelefono(String numero, EnumKindOfPhone tipo) {
		if (numero.length() != 9) throw new IllegalArgumentException("El teléfono \"" + numero + "\" no es válido.");
		telefonos.put(numero, tipo);		
	}
	
	public void añadirTelefonos(Map<String, EnumKindOfPhone> telefonos) {
		for (String numero : telefonos.keySet()) {
			añadirTelefono(numero, telefonos.get(numero));
		}
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
