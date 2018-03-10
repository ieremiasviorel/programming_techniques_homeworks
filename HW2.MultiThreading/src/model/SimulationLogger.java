package model;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimulationLogger {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(SimulationLogger.class.getName());
	private static FileHandler fh = null;
	 
	public static void init() {
		try {
			 fh = new FileHandler("Data.log", false);
		}
		catch (SecurityException | IOException e) {
			 e.printStackTrace();
		}
		 
		Logger l = Logger.getLogger("");
		fh.setFormatter(new SimpleFormatter());
		l.addHandler(fh);
		l.setLevel(Level.CONFIG);
	}
}