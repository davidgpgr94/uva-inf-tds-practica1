package uva.tds.pr1.equipo09;

import java.io.File;

public class Main {
	static String ola;
	public static void main(String[] args) {
		File file= new File("src/uva/tds/pr1/equipo09/libreta.xml");
		EContactSystemImpl imple= new EContactSystemImpl();
		imple.loadFrom(file.toPath());
		System.out.println("¿Ha sido cargado el XML? R:" + imple.isXMLLoaded());
		
	}
}
