package br.com.ilegra.testeSelecao.config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author tiago marzari
 *
 */
public class FileConfig {
	
	public static final String EXTENSION_DAT = ".dat";
	public static final String EXTENSION_DONE_DAT = ".done.dat";

	public static final Path PATH_IN = Paths.get(System.getProperty("user.home") + "\\data\\in\\");
	public static final Path PATH_OUT = Paths.get(System.getProperty("user.home") + "\\data\\out\\");

}
