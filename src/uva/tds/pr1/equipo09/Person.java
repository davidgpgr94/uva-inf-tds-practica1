package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xerces.internal.util.TeeXMLDocumentFilterImpl;

public class Person extends Contact {
	private String nombre;
	private String apellido;
	private List<String> emails;
	private Map<String, EnumKindOfPhone> telefonos;

	public Person(String alias, String nombre, String apellido, String[] emails, Map<String, EnumKindOfPhone> telefonos) {
		super(alias);
		if (nombre == null || nombre == "") {
			throw new IllegalArgumentException("El nombre ha de ser " + nombre == null ? "no null." : "un String con valor.");
		}
		if (emails.length == 0) { //Nos aseguramos de que al menos tenga un email
			throw new IllegalArgumentException("Ha de tener al menos un email"); 
		}
		this.emails = new ArrayList<String>();
		this.telefonos = new HashMap<String, EnumKindOfPhone>();
		setNombre(nombre);
		setApellido(apellido);
		añadirEmails(emails);
		if(telefonos != null) añadirTelefonos(telefonos);
	}

	public Map<String, EnumKindOfPhone> getTelefonos() {
		return telefonos;
	}

	/**
	 * Devuelve el alias de la persona.
	 */
	public String getId() {
		return super.getId();
	}
	
	/**
	 * Devuelve el nombre de la persona.
	 * @return nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Devuelve el apellido de la persona.
	 * @return apellido.
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Devuelve los emails de la persona
	 * @return  emails.
	 */
	public String[] getEmails() {
		return emails.toArray(new String[1]);
	}

	public void añadirEmail(String email) {
		if (email == null || email == "") throw new IllegalArgumentException("El email no puede ser nulo ni vacío.");
		if (!email.contains("@")) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}
		emails.add(email);
	}

	public void añadirEmails(String[] emails) {
		if (emails == null) throw new IllegalArgumentException("El parámetro no puede ser null.");
		if (emails.length == 0) throw new IllegalArgumentException("El parámetro no puede ser un array sin elementos.");
		for (String email : emails) {
			añadirEmail(email);
		}
	}

	public void añadirTelefono(String numero, EnumKindOfPhone tipo) {
		if (numero == null) throw new IllegalArgumentException("El numero de telefono no puede ser null");
		if (numero.length() != 9)
			throw new IllegalArgumentException("El teléfono \"" + numero + "\" no es válido.");
		telefonos.put(numero, tipo);
	}

	public void añadirTelefonos(Map<String, EnumKindOfPhone> telefonos) {
		if (telefonos == null) throw new IllegalArgumentException("El argumento telefonos no puede ser null.");
		if (telefonos.isEmpty()) throw new IllegalArgumentException("El argumento telefonos no puede estar vacío.");
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

	@Override
	public boolean equals(Object otro) {
		if ( !(otro instanceof Person) ) return false;
		if ( !(((Person)otro).getId() == this.getId()) ) return false;
		if ( !(((Person)otro).getNombre() == this.getNombre()) ) return false;
		if ( !(((Person)otro).getApellido() == this.getApellido()) ) return false;
		for (String email : ((Person)otro).getEmails() ) {
			if (!emails.contains(email)) return false;
		}
		for (String numero : ((Person)otro).getTelefonos().keySet() ) {
			if (!telefonos.containsKey(numero)) return false;
			if (telefonos.get(numero) != ((Person)otro).getTelefonos().get(numero)) return false;
		}
		return true;
	}
	
	public String toXmlString() {
		String persona = " <persona alias=\"" + getId() + "\">";
		persona += "\n   <apellidos>" + getApellido() + "</apellidos>";
		persona += "\n   <nombre>" + getNombre() + "</nombre>";

		for (int i = 0; i < getEmails().length; i++) {
			persona += "\n   <email>" + getEmails()[i] + "</email>";
		}

		if (getTelefonos().size() > 0) {
			for (String telef : getTelefonos().keySet()) {
				persona += "\n   <telef tipo=\"" + getTelefonos().get(telef) + "\">" + telef + "</telef>";
			}
		}

		persona += "\n </persona>";
		return persona;
	}

}
