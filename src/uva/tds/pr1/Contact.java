package uva.tds.pr1;

public interface Contact {

	/**
	 * Devuelve el Id del contacto
	 * @return
	 */
	String getId();

	void cambiarId(String nuevo);

	/**
	 * Sobreescribe el metodo equals para comparar contactos por identificación.
	 */
	boolean equals(Object otro);

}