package add;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

// This Class to handle action from different nods on add window
public class AddHandler implements EventHandler<ActionEvent> {

	// Make instance of the same handler class
	private static AddHandler instance;

	// Make object of AddPresenter to use it when action happened
	private static AddPresenter view;

	// private constructor to don't make any other object
	private AddHandler() {
	}

	// This method to return the instance 
	public static AddHandler getInstance(AddPresenter add) {
		view = add;
		if (instance == null)
			instance = new AddHandler();
		return instance;
	}
	/*
	 * Note : I do this way to force use one object form this class
	 * 		  because no need more then one
	 */

	// This method is override to handle the actions
	@Override
	public void handle(ActionEvent node) {

		// This if to know on what nods the action happened
		if (node.getSource() instanceof Button) {
			Button btn = (Button) node.getSource();

			// This switch to know what button in clicked
			switch (btn.getText()) {
			case "Save":
				view.saveBtnClick();
				break;

			case "Cancel":
				view.cancelBtnClick();
				break;

			}
		} else if (node.getSource() instanceof RadioButton) {
			RadioButton btn = (RadioButton) node.getSource();
			
			// This switch to know what radio button is selected
			switch (btn.getText()) {
			case "Yes":
				view.yesRBtnClick();
				break;
			case "No":
				view.noRBtnClick();
				break;
			}
		}
	}
}
