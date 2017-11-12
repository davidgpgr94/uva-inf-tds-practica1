package uva.tds.pr1;

import java.util.Map;

public interface Person {

	/**
	 * Devuelve un mapa de telefonos con el número y el tipo.
	 * 
	 * @return telefonos con tipo.
	 */
	Map<String, EnumKindOfPhone> getTelefonos();

	/**
	 * Devuelve el alias de la persona.
	 */
	String getId();

	/**
	 * Devuelve el nombre de la persona.
	 * 
	 * @return nombre.
	 */
	String getNombre();

	/**
	 * Devuelve el apellido de la persona.
	 * 
	 * @return apellido.
	 */
	String getApellido();

	/**
	 * Devuelve los emails de la persona
	 * 
	 * @return emails.
	 */
	String[] getEmails();

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
	void añadirEmail(String email);

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
	void añadirEmails(String[] emails);

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
	void añadirTelefono(String numero, EnumKindOfPhone tipo);

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
	void añadirTelefonos(Map<String, EnumKindOfPhone> telefonos);

	/**
	 * Sobreescribe el metodo equals para que tome en cuenta todos los atributos
	 * de una persona.
	 */
	boolean equals(Object otro);

	/**
	 * Genera una cadena que representa una persona en formato xml.
	 * 
	 * @return persona Persona en formato xml.
	 */
	String toXmlString();

}