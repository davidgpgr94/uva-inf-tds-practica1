package uva.tds.pr1.equipo09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uva.tds.pr1.EnumKindOfPhone;
import uva.tds.pr1.Person;

/**
 * Representación de una persona en la libreta electrónica.
 * 
 * @author ginquin
 * @author davidgo
 */
public class PersonImpl extends ContactImpl implements Person {
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
	public PersonImpl(String alias, String nombre, String apellido, String[] emails,
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

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#getTelefonos()
	 */
	@Override
	public Map<String, EnumKindOfPhone> getTelefonos() {
		return telefonos;
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#getId()
	 */
	@Override
	public String getId() {
		return super.getId();
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#getApellido()
	 */
	@Override
	public String getApellido() {
		return apellido;
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#getEmails()
	 */
	@Override
	public String[] getEmails() {
		return emails.toArray(new String[1]);
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#añadirEmail(java.lang.String)
	 */
	@Override
	public void añadirEmail(String email) {
		if (email == "" || email == null) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("El email \"" + email + "\" no es válido.");
		}

		emails.add(email);
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#añadirEmails(java.lang.String[])
	 */
	@Override
	public void añadirEmails(String[] emails) {
		if (emails == null)
			throw new IllegalArgumentException("El parámetro no puede ser null.");
		if (emails.length == 0)
			throw new IllegalArgumentException("El parámetro no puede ser un array sin elementos.");

		for (String email : emails) {
			añadirEmail(email);
		}
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#añadirTelefono(java.lang.String, uva.tds.pr1.EnumKindOfPhone)
	 */
	@Override
	public void añadirTelefono(String numero, EnumKindOfPhone tipo) {
		if (numero == null)
			throw new IllegalArgumentException("El número de telefono no puede ser null");
		if (numero.length() != 9)
			throw new IllegalArgumentException("El teléfono \"" + numero + "\" no es válido.");

		telefonos.put(numero, tipo);
	}

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#añadirTelefonos(java.util.Map)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#equals(java.lang.Object)
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

	/* (non-Javadoc)
	 * @see uva.tds.pr1.Person#toXmlString()
	 */
	@Override
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
