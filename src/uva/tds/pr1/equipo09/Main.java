package uva.tds.pr1.equipo09;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static String ola;
	public static void main(String[] args) {
		File file= new File("src/uva/tds/pr1/equipo09/libreta.xml");
		File impFile= new File("src/uva/tds/pr1/equipo09/libretaMod.xml");
		EContactSystemImpl eContactImple= new EContactSystemImpl();
		eContactImple.loadFrom(file.toPath());
		
		//Crear nuevo usuario.
		String emails[] = {"asdf@gmail.com"};
		eContactImple.createNewPerson("gino", "ginquin", "quintana", emails,null);
		
		
		
		
		//Crear nuevo contacto.
		Set<Contact> contactos= new HashSet<Contact>();
		contactos.add(eContactImple.getPersonByNickname("ginquin"));
		eContactImple.createNewGroup("amigos", contactos.toArray(new Contact[1]));
		
		
		//Eliminar contacto de grupo
		try {
			eContactImple.removeContactFromGroup(eContactImple.getPersonByNickname("Danielin"), eContactImple.getGroupByName("Amigos"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		//Eliminar contacto sistema
		eContactImple.removeContactFromSystem(eContactImple.getAnyContactById("Amigos"));
		
		//Obtener contacto 
			Contact contacto= eContactImple.getAnyContactById("ginquin");
			System.out.println(contacto.getId());
			
		//Obtener contacto  persona
			Person per= eContactImple.getPersonByNickname("Danielin");
			System.out.println(per.getId());
			
		//
			Group g= eContactImple.getGroupByName("Amigos");
			System.out.println(g.getId());
		//Creamos un xml si ha cambiado la  libreta
		eContactImple.updateTo(impFile.toPath());
		System.out.println("¿Ha sido cargado el XML? R:" + eContactImple.isXMLLoaded());
		
	}
}
