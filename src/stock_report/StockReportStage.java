package stock_report;

import data.Inventory;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//This class to stock report to print the report
public class StockReportStage extends Stage implements StockReportPresenter, EventHandler<WindowEvent> {

	// Make references of Stroke Report and inventory to use it
	private StockReportGridPane paneGp;
	private Inventory store;

	// Make references of handler and warning stage
	private StockReportHandler event;
	private Stage warningStage;

	// Constructor
	public StockReportStage(Inventory store) {
		this.store = store;

		// Get instance of handler
		event = StockReportHandler.getInstance(this);

		// Initially the add grid pane
		paneGp = new StockReportGridPane(event);

		// Make scene and put the stock report grid pane on it
		Scene scene = new Scene(paneGp, 700, 400);

		// Set scene on the stage
		super.setScene(scene);

		// Set stage title
		super.setTitle("Stock Report");

		// set window handler when close the window then the nods return to default
		super.setOnCloseRequest(this);

		// Set a max and min height and width window
		super.setMaxHeight(450);
		super.setMaxWidth(750);
		super.setMinHeight(350);
		super.setMinWidth(650);
	}

	// this method when the user select the export check box show the text field and
	// the button to enter the file name
	@Override
	public void exportCBClick() {
		// TODO Auto-generated method stub
		paneGp.isFileCB(paneGp.getFileCB().isSelected());
	}

	// this method when the user select the export check box show the text area and
	// print he report
	@Override
	public void textAreaCBClick() {
		// TODO Auto-generated method stub

		paneGp.isTextAreaCB(paneGp.getTextAreaCB().isSelected());
		if (paneGp.getTextAreaCB().isSelected()) {
			
			store.stockReport();
			paneGp.setAreaText(store.getMessage());
		}
	}

	// this method get entered file and print the report on the file
	@Override
	public void exportFileBtnClick() {
		// TODO Auto-generated method stub

		// get file name from the user
		String fileName = paneGp.getFileName();

		// Don't do any thing when the input is null or is empty
		if (fileName == null || fileName.isEmpty()) {
			showWarning("The file name is empty");
			return;
		}

		// for capital latter
		fileName = fileName.toLowerCase();
		
		// warning the user if the file is not txt
		if (!fileName.contains(".txt")) {
			showWarning("The file name must be text file (FileName.txt)");
			return;
		}
		// print on the file
		if (fileName != null && !fileName.isEmpty()) {
			try {
				store.printOnFile(fileName);
				showWarning("Export is successfully");
			} catch (Exception e) {
				showWarning(e.getMessage());
			}

		} else
			showWarning("Please enter the file name");

	}

	// this method make a window waning
	private void showWarning(String text) {
		VBox box = new VBox();
		Scene scene = new Scene(box, 300, 100);
		warningStage = new Stage();

		Label label = new Label(text);
		Button button = new Button("Ok");

		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(label, button);
		button.setOnAction(event);
		label.setStyle(" -fx-font-size: 15;");
		box.setSpacing(5);

		warningStage.setScene(scene);
		warningStage.setTitle("Warning");
		warningStage.showAndWait();
	}

	// this close the warning window when click on the ok button
	@Override
	public void okBtnClick() {
		// TODO Auto-generated method stub
		warningStage.close();
	}

	// this method to return to default form when the user press on the exit button
	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		paneGp.returnToDefult();
	}

}
