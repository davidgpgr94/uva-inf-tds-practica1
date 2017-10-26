package uva.tds.pr1.equipo09;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	
	public static void main(String[] args) {
		File file= new File("src/uva/tds/pr1/equipo09/libreta.xml");
		EContactSystemImpl imple= new EContactSystemImpl();
		imple.loadFrom(file.toPath());
		System.out.println(imple.isXMLLoaded());
	}

}