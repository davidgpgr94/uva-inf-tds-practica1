package uva.tds.pr1.equipo09;

import java.util.ArrayList;

public class Group extends Contact {
	
	private ArrayList<Contact> miembros;
	
	public Group (String nombre, Contact miembro) {
		super(nombre);
		miembros = new ArrayList<Contact>();
		añadirMiembro(miembro);
	}
	
	public Group (String nombre, Contact[] miembros) {
		super(nombre);
		this.miembros = new ArrayList<Contact>();
		añadirMiembros(miembros);
	}
	
	public void añadirMiembro(Contact miembro) {
		miembros.add(miembro);
	}
	
	public void añadirMiembros(Contact[] miembros) {
		for (int i = 0; i < miembros.length; i++) {
			this.miembros.add(miembros[i]);			
		}
	}

}
