package delete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

//This Class to handle action from different nods on delete window
public class DeleteHandler implements EventHandler<ActionEvent> {

	// Make instance of the same handler class
	private static DeleteHandler instance;
	
	// Make object of DeletePresenter to use it when action happened
	private static DeletePresenter view;
	
	// private constructor to don't make any other object
	private DeleteHandler(DeletePresenter add) {
		view = add;
	}

	// This method to return the instance
	public static DeleteHandler getInstance(DeletePresenter add) {

		if (instance == null && add != null)
			instance = new DeleteHandler(add);

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
			case "Delete":
				view.deleteBtnClick();
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
