package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.controllers.StartViewController;
import presentation.views.StartView;

public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		
		new StartViewController(new StartView());
	}
}