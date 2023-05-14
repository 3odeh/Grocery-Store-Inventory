package add;

import base.BaseGridPane;
import base.BasePresenter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import model.*;

// This class to design the add window
public class AddGridPane extends BaseGridPane {

	// The nods of grid pane
	private Label brandLb, brandNameLb;
	private TextField brandNameTF;
	private RadioButton yesRBtn, noRBtn;

	// Constructor
	public AddGridPane(AddHandler event) {
		super(event);

		// Initially nods , controls and layouts
		brandLb = new Label("Brand");
		brandNameLb = new Label("Brand Name");
		brandNameTF = new TextField();
		HBox hb = new HBox();
		ToggleGroup tg = new ToggleGroup();
		yesRBtn = new RadioButton("Yes");
		noRBtn = new RadioButton("No");

		// To connect the yes and no radio button (just on of them is selected)
		yesRBtn.setToggleGroup(tg);
		noRBtn.setToggleGroup(tg);
		
		// to put no radio button select first
		tg.selectToggle(noRBtn);

		// Put handler for any action on the radio button
		yesRBtn.setOnAction(event);
		noRBtn.setOnAction(event);

		// Add the radio buttons on HBox and set space between them and put them in
		// the center
		hb.getChildren().addAll(yesRBtn, noRBtn);
		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER_LEFT);

		// Add the component on grid pane
		super.add(brandLb, 1, 0);
		super.add(hb, 2, 0);

		// set style css on the components 
		brandLb.setStyle(BasePresenter.CSS_LABEL_STYLE);
		brandNameLb.setStyle(BasePresenter.CSS_LABEL_STYLE);
		brandNameTF.setStyle(BasePresenter.CSS_TEXTFIELD_STYLE);
	}

	// This method to get yes brand radio button
	public RadioButton getYesRBtn() {
		return yesRBtn;
	}

	// This method to get no brand radio button
	public RadioButton getNoRBtn() {
		return noRBtn;
	}

	// This method is override to return all nods to default (to the initial values
	// and form)
	@Override
	public void returnToDefault() {
		super.returnToDefault();
		brandNameTF.setText("");
		isBrandBtn(false);
		noRBtn.setSelected(true);

	}

	// This method is override to show the brand name when the user chose the yes
	// brand radio button
	@Override
	public void isBrandBtn(boolean check) {
		if (check) {
			add(brandNameLb, 1, 4);
			add(brandNameTF, 2, 4);
		} else {
			getChildren().removeAll(brandNameLb, brandNameTF);
		}
	}

	// This method is override to return the name of button to use it on the super
	// class (put the value in the button)
	@Override
	public String getTitleMainBtn() {
		return "Save";
	}

	// This method is override to return the input value from user to use it on the
	// super class
	@Override
	public String getBrandName() {
		String brand = brandNameTF.getText();
		if (brand == null || brand.isEmpty()) {
			return null;
		}
		return brand;
	}

	// This method is override to get brand data input from user and search argument
	// for search if its true we dont need quantity value and price
	@Override
	public Brand getBrandData(boolean search) {
		if (yesRBtn.isSelected())
			return super.getBrandData(search);
		return null;
	}

	// This method is override to get item data input from user and search argument
	// for search if its true we don't need quantity value and price
	@Override
	public Item getItemData(boolean search) {
		if (noRBtn.isSelected())
			return super.getItemData(search);
		return null;
	}

}
