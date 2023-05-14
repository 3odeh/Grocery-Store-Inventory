package stock_report;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

//This class to design the stroke report window
public class StockReportGridPane extends GridPane {

	// The nods of grid pane
	private Label headingLb;
	private CheckBox fileCB, textAreaCB;
	private TextField fileNameTF;
	private Button exportBtn;
	private TextArea reportStrokeTA;

	// Constructor
	public StockReportGridPane(StockReportHandler event) {

		// Initially nods , controls and layouts
		headingLb = new Label("The following options can be used to print a report");
		fileCB = new CheckBox("Export a copy to a file");
		textAreaCB = new CheckBox("Text Area");
		fileNameTF = new TextField();
		exportBtn = new Button("Export");
		reportStrokeTA = new TextArea();

		// Add the component on grid pane
		super.add(headingLb, 1, 1, 2, 1);
		super.add(fileCB, 1, 2);
		super.add(textAreaCB, 2, 2);

		// To make horizontally and vertically space between the nods
		super.setHgap(10);
		super.setVgap(10);
		
		// To make space between the grid pane and scene
		super.setPadding(new Insets(15));

		// Put the handler for any action on the buttons or check boxes
		fileCB.setOnAction(event);
		textAreaCB.setOnAction(event);
		exportBtn.setOnAction(event);

		//disable to user write on the text area 
		reportStrokeTA.setEditable(false);

		// Set a custom style on the heading label
		headingLb.setStyle(StockReportPresenter.CSS_LABEL_TITLE_STYLE);
	}

	// This method to get file name
	public String getFileName() {
		return fileNameTF.getText();
	}
	
	// This method to get file check box
	public CheckBox getFileCB() {
		return fileCB;
	}

	// This method to get text area text box
	public CheckBox getTextAreaCB() {
		return textAreaCB;
	}

	// This method to put export the report to the file mode
	public void isFileCB(boolean check) {
		if (check) {
			add(exportBtn, 1, 4);
			add(fileNameTF, 1, 3);
		} else {
			getChildren().removeAll(exportBtn, fileNameTF);

		}
	}

	// This method to put the report on the text area mode
	public void isTextAreaCB(boolean check) {
		if (check) {
			add(reportStrokeTA, 2, 5);
		} else {
			getChildren().remove(reportStrokeTA);
		}
	}

	// this method to put text on the text area
	public void setAreaText(String text) {
		reportStrokeTA.setText(text);
	}

	// this method to return the nods to the default form
	public void returnToDefult() {
	
		fileCB.setSelected(false);
		textAreaCB.setSelected(false);
		isFileCB(false);
		isTextAreaCB(false);
		fileNameTF.setText("");
	}
}
