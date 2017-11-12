package uva.tds.pr1.equipo09;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import uva.tds.pr1.Contact;
import uva.tds.pr1.Group;
import uva.tds.pr1.Person;
/**
 * Clase encargada en la ejecución del programa y hacer las pruebas.
 * @author ginquin
 * @author davidgo
 */
public class Main {
	static String ola;

	public static void main(String[] args) {
		File file = new File("src/uva/tds/pr1/equipo09/libreta.xml");
		File impFile = new File("src/uva/tds/pr1/equipo09/libretaMod.xml");
		EContactSystemImpl eContactImple = (EContactSystemImpl) EContactSystemImpl.contactSystemFactory();
		eContactImple.loadFrom(file.toPath());

		// Crear nuevo usuario correcto.
		String emails[] = { "asdf@gmail.com" };
		eContactImple.createNewPerson("gino", "ginquin", "quintana", emails, null);

		// Crear usuario incorrecto con mismo id
		try {
			eContactImple.createNewPerson("jesus", "Danielin", "angulo", emails, null);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Crear nuevo grupo correcto.
		Set<Contact> contactos = new HashSet<Contact>();
		contactos.add((Contact)eContactImple.getPersonByNickname("ginquin"));
		contactos.add((Contact)eContactImple.getPersonByNickname("Manolin"));
		eContactImple.createNewGroup("Enemigos", contactos.toArray(new Contact[1]));

		// Crear nuevo grupo con mismo id.
		try {
			eContactImple.createNewGroup("Amigos", contactos.toArray(new Contact[1]));
		} catch (Exception e) {
			System.out.println(e);
		}

		// Eliminar contacto de grupo
		try {
			eContactImple.removeContactFromGroup((Contact)eContactImple.getPersonByNickname("Danielin"),
					eContactImple.getGroupByName("Amigos"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Eliminar contacto grupo sistema
		eContactImple.removeContactFromSystem(eContactImple.getAnyContactById("Amigos"));
		//Eliminar contacto persona sistema
		eContactImple.removeContactFromSystem(eContactImple.getAnyContactById("ginquin"));
		//Eliminar contacto persona sistema incorrecto
		try {
			eContactImple.removeContactFromSystem(eContactImple.getAnyContactById("Manolin"));
		} catch (Exception e) {
			System.out.println(e);
		}
		

		//Obtener contacto Persona
		 Person persona= (Person) eContactImple.getAnyContactById("Danielin");
		 System.out.println(persona.getId());
		// Obtener contacto Grupo
		 Group grupo= (Group) eContactImple.getAnyContactById("Enemigos");
		 System.out.println(grupo.getId());
		
		//Obtener contacto Persona
		 Person persona1= (Person) eContactImple.getPersonByNickname("Danielin");
		 System.out.println(persona1.getId());
		//Obtener grupo
		 Group grupo1= eContactImple.getGroupByName("Enemigos");
		 System.out.println(grupo1.getId());
		 
		// //Creamos un xml si ha cambiado la libreta
		eContactImple.updateTo(impFile.toPath());
		System.out.println("¿Ha sido cargado el XML? R:" + eContactImple.isXMLLoaded());

	}
}
