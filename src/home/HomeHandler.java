package home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

//This Class to handle action from different nods on home window
public class HomeHandler implements EventHandler<ActionEvent> {

	// Make instance of the same handler class
	private static HomeHandler instance;
	
	// Make object of HomePresenter to use it when action happened
	private static HomePresenter view;

	// private constructor to don't make any other object
	private HomeHandler(HomePresenter v) {
		view = v;
	}

	// This method to return the instance 
	public static HomeHandler getInstance(HomePresenter add) {

		if (instance == null && add != null)
			instance = new HomeHandler(add);

		return instance;
	}

	/*
	 * Note : I do this way to force use one object form this class
	 * 		  because no need more then one
	 */
	
	// This method is override to handle the actions
	@Override
	public void handle(ActionEvent node) {

		// This if to know if the action happened on the button or not
		if (node.getSource() instanceof Button) {
			Button btn = (Button) node.getSource();

			// This switch to know what button in clicked
			switch (btn.getText()) {
			case "Modify":
				view.modifyBtnClick();
				break;

			case "Add":
				view.addBtnClick();
				break;
			case "Delete":
				view.deleteBtnClick();
				break;
			case "Stock Report":
				view.stockReportBtnClick();
				break;
			}
		}
	}
	
	
}
