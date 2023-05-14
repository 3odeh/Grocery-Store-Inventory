package modify;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


//This Class to handle action from different nods on modify window
public class ModifyHandler implements EventHandler<ActionEvent> {

	
	// Make instance of the same handler class
	private static ModifyHandler instance;
	
	// Make object of ModifyPresenter to use it when action happened
	private static ModifyPresenter view;
	
	// private constructor to don't make any other object
	private ModifyHandler(ModifyPresenter add) {
		view = add;
	}

	// This method to return the instance
	public static ModifyHandler getInstance(ModifyPresenter add) {

		if (instance == null && add != null)
			instance = new ModifyHandler(add);

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
			case "Update":
				view.updateBtnClick();
				break;

			case "Cancel":
				view.cancelBtnClick();
				break;
			case "Search":
				view.searchBtnClick();
				break;
			case "Ok":
				view.okBtnClick();
				break;
			}
		} else if (node.getSource() instanceof ComboBox) {
			view.brandCBFill();
		}
	}

}