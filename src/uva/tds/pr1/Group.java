package uva.tds.pr1;

public interface Group {

	/**
	 * Devuelve true si el miembro esta en el grupo, false en caso contrario.
	 * @param miembro miembro que se desea saber si  esta en el grupo.
	 * @return true si esta en el grupo, false en caso contrario.
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 */
	boolean contieneA(Contact miembro);

	/**
	 * Añade un nuevo miembro al grupo. Si el nuevo miembro ya pertenece al grupo, el método no tiene efecto.
	 * @param miembro Nuevo miembro.
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 */
	void añadirMiembro(Contact miembro);

	/**
	 * Permite añadir una lista de miembros al grupo. si algun miembro ya existe no lo añade.
	 * @param miembros Lista de miembros para añadir al grupo.
	 *  @throws IllegalArgumentException La lista de miembros no puede ser null y contener al menos un miembro.
	 */
	void añadirMiembros(Contact[] miembros);

	/**
	 * Devuelve los miembros del grupo.
	 * @return miembros que pertenecen al grupo.
	 */
	Contact[] getMiembros();

	/**
	 * Devuelve el nombre del grupo
	 */
	String getId();

	/**
	 * Elimina la primera ocurrencia del miembro en el grupo
	 * 
	 * @param miembro Miembro a borrar del grupo.
	 * @return true si el miembro pertenecía al grupo, false en caso contrario
	 * @throws IllegalArgumentException El miembro no puede ser nulo.
	 * @throws IllegalStateException El grupo solo tiene un miembro.
	 */
	boolean eliminarMiembro(Contact miembro);

	/**
	 * Sobreescribe el metodo equals para que tome en cuenta todos los atributos
	 * de un grupo.
	 */
	boolean equals(Object otro);

	/**
	 * Genera una cadena que representa un grupo en formato xml.
	 * 
	 * @return grupo Grupo en formato xml.
	 */
	String toXmlString();

}