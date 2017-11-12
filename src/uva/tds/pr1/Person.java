package uva.tds.pr1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representación de una persona en la libreta electrónica.
 * 
 * @author ginquin
 * @author davidgo
 */
public class Person extends Contact {
	private String nombre;
	private String apellido;
	private List<String> emails;
	private Map<String, EnumKindOfPhone> telefonos;

	/**
	 * Constructor de una persona.Inicializa una persona con los atributos
	 * imprescindibles.
	 * 
	 * @param alias
	 *            Identificador unico para una persona.No puede ser vacio, ni
	 *            nulo.
	 * @param nombre
	 *            Nombre de una persona.No debe ser vacio, ni nulo.
	 * @param apellido
	 *            Apellido de una persona. Puede no tener apellidos.
	 * @param emails
	 *            Emails de una persona. Como minimo debe tener uno
	 * @param telefonos
	 *            Telefonos de una persona. Puede no tener telefonos.
	 * @throws IllegalArgumentException
	 *             Alias es vacio o nulo. @see Contact
	 * @throws IllegalArgumentException
	 *             Nombre es vacio o nulo.
	 * @throws IllegalArgumentException
	 *             Apellido es nulo.
	 * @throws IllegalArgumentException
	 *             Sin emails o algun email no es correcto.
	 */
	public Person(String alias, String nombre, String apellido, String[] emails,
			Map<String, EnumKindOfPhone> telefonos) {
		super(alias);
		if (emails == null || emails.length == 0)
			throw new IllegalArgumentException("Ha de tener al menos un email");
		this.emails = new ArrayList<String>();
		this.telefonos = new HashMap<String, EnumKindOfPhone>();
		setNombre(nombre);
		setApellido(apellido);
		añadirEmails(emails);
		if (telefonos != null)
			añadirTelefonos(telefonos);

	}

	/**
	 * Devuelve un mapa de telefonos con el número y el tipo.
	 * 
	 * @return telefonos con tipo.
	 */
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
	 * 
	 * @return nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve el apellido de la persona.
	 * 
	 * @return apellido.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Devuelve los emails de la persona
	 * 
	 * @return emails.
	 */
	public String[] getEmails() {
		return emails.toArray(new String[1]);
	}

	/**
	 * Permite añadir un email a los emails de la persona.
	 * 
	 * @param email
	 *            Email nuevo a introducir. No puede ser null, ni vacio y debe
	 *            ser válido.
	 * @throws IllegalArgumentException
	 *             El email es vacio o nulo.
	 * @throws IllegalArgumentException
	 *             El email no es válido.
	 */
	public void añadirEmail(String email) {
		if (email == "" || email == null) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}

		emails.add(email);
	}

	/**
	 * Permite añadir una lista de emails a los emails de la persona.
	 * 
	 * @param emails
	 *            Lista de emails a introducir.
	 * @throws IllegalArgumentException
	 *             La lista de emails no puede ser null.
	 * @throws IllegalArgumentException
	 *             La lista de emails debe contener al menos un elemento.
	 * @throws IllegalArgumentException
	 *             Algun email no es correcto.
	 */
	public void añadirEmails(String[] emails) {
		if (emails == null)
			throw new IllegalArgumentException("El parámetro no puede ser null.");
		if (emails.length == 0)
			throw new IllegalArgumentException("El parámetro no puede ser un array sin elementos.");

		for (String email : emails) {
			añadirEmail(email);
		}
	}

	/**
	 * Permite añadir un telefono con tipo a los de telefonos de la persona.
	 * 
	 * @param numero
	 *            Numero de telefono.Debe tener una longitud de 9 digitos.
	 * @param tipo
	 *            Tipo del telefono. @see EnumKindOfPhone
	 * @throws IllegalArgumentException
	 *             El número de telefono no puede ser null.
	 * @throws IllegalArgumentException
	 *             Telefono con longitud incorrecta.
	 */
	public void añadirTelefono(String numero, EnumKindOfPhone tipo) {
		if (numero == null)
			throw new IllegalArgumentException("El número de telefono no puede ser null");
		if (numero.length() != 9)
			throw new IllegalArgumentException("El teléfono \"" + numero + "\" no es válido.");

		telefonos.put(numero, tipo);
	}

	/**
	 * Permite añadir una lista de telefonos a los telefonos de la persona.
	 * 
	 * @param telefonos
	 *            Lista de telefonos a introducir.
	 * @throws IllegalArgumentException
	 *             la lista de telefonos no puede ser null.
	 * @throws IllegalArgumentException
	 *             la lista de telefonos debe contener al menos un elemento.
	 */
	public void añadirTelefonos(Map<String, EnumKindOfPhone> telefonos) {
		if (telefonos == null)
			throw new IllegalArgumentException("El argumento telefonos no puede ser null.");
		if (telefonos.isEmpty())
			throw new IllegalArgumentException("El argumento telefonos no puede estar vacío.");

		for (String numero : telefonos.keySet()) {
			añadirTelefono(numero, telefonos.get(numero));
		}
	}

	/**
	 * Permite establecer el nombre de una persona.
	 * 
	 * @param nombre
	 *            Nombre a introducir. No puede ser nulo, ni vacio.
	 * @throws IllegalArgumentException
	 *             Nombre es vacio o nulo.
	 */
	private void setNombre(String nombre) {
		if (nombre == null || nombre == "")
			throw new IllegalArgumentException(
					"El nombre ha de ser " + nombre == null ? "no null." : "un String con valor.");
		this.nombre = nombre;
	}

	/**
	 * Permite establecer el apellido de una persona.
	 * 
	 * @param apellido
	 *            Apellido a introducir.
	 * @throws IllegalArgumentException
	 *             El apellido es nulo.
	 */
	private void setApellido(String apellido) {
		if (apellido == null)
			throw new IllegalArgumentException("El apellido no puede ser null (si puede ser una cadena vacía).");
		this.apellido = apellido;
	}

	/**
	 * Sobreescribe el metodo equals para que tome en cuenta todos los atributos
	 * de una persona.
	 */
	@Override
	public boolean equals(Object otro) {
		if (!(otro instanceof Person))
			return false;
		if (!(((Person) otro).getId() == this.getId()))
			return false;
		if (!(((Person) otro).getNombre() == this.getNombre()))
			return false;
		if (!(((Person) otro).getApellido() == this.getApellido()))
			return false;
		for (String email : ((Person) otro).getEmails()) {
			if (!emails.contains(email))
				return false;
		}
		for (String numero : ((Person) otro).getTelefonos().keySet()) {
			if (!telefonos.containsKey(numero))
				return false;
			if (telefonos.get(numero) != ((Person) otro).getTelefonos().get(numero))
				return false;
		}
		return true;
	}

	/**
	 * Genera una cadena que representa una persona en formato xml.
	 * 
	 * @return persona Persona en formato xml.
	 */
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
