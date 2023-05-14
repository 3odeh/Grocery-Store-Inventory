package stock_report;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

//This Class to handle action from different nods on stock window
public class StockReportHandler implements EventHandler<ActionEvent> {

	// Make instance of the same handler class
	private static StockReportHandler instance;
	
	// Make object of stock report presenter to use it when action happened
	private static StockReportPresenter view;

	// private constructor to don't make any other object
	private StockReportHandler(StockReportPresenter v) {
		view = v;
	}

	// This method to return the instance 
	public static StockReportHandler getInstance(StockReportPresenter add) {

		if (instance == null && add != null)
			instance = new StockReportHandler(add);

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
			case "Export":
				view.exportFileBtnClick();
				break;
			case "Ok":
				view.okBtnClick();
				break;

			}

		} else if (node.getSource() instanceof CheckBox) {
			CheckBox cb = (CheckBox) node.getSource();

			// This if to know what check box is selected
			if (cb.getText().equals("Text Area")) {
				view.textAreaCBClick();

			} else {
				view.exportCBClick();
			}
		}

	}

}
