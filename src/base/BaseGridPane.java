package base;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.*;

//This class for add , modify and delete grid pane
public abstract class BaseGridPane extends GridPane {

	// The nods of grid pane
	private Label typeLb, quantityLb, priceLb, errorLb;
	private TextField typeTF, quantityTF, priceTF;
	private Button cancelBtn, mainBtn;

	// Constructor
	public BaseGridPane(EventHandler<ActionEvent> event) {

		// Initially nods , controls and layouts
		typeLb = new Label("Type");
		quantityLb = new Label("Quantity");
		priceLb = new Label("Price");
		errorLb = new Label();
		typeTF = new TextField();
		quantityTF = new TextField();
		priceTF = new TextField();
		cancelBtn = new Button("Cancel");
		mainBtn = new Button(getTitleMainBtn());
		HBox hb = new HBox();

		hb.getChildren().addAll(mainBtn, cancelBtn);
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER_RIGHT);

		// To make horizontally and vertically space between the nods
		super.setHgap(10);
		super.setVgap(10);

		// To make space between the grid pane and scene
		super.setPadding(new Insets(15, 0, 0, 0));

		// Add the component to the grid pane
		super.add(typeLb, 1, 1);
		super.add(quantityLb, 1, 2);
		super.add(priceLb, 1, 3);
		super.add(errorLb, 1, 6, 4, 6);
		super.add(typeTF, 2, 1);
		super.add(quantityTF, 2, 2);
		super.add(priceTF, 2, 3);
		super.add(hb, 2, 5);

		// Put the handler for any action on the buttons
		cancelBtn.setOnAction(event);
		mainBtn.setOnAction(event);

		// Set a custom style on the components
		cancelBtn.setStyle(BasePresenter.CSS_BUTTONS_STYLE);
		mainBtn.setStyle(BasePresenter.CSS_BUTTONS_STYLE);

		typeLb.setStyle(BasePresenter.CSS_LABEL_STYLE);
		quantityLb.setStyle(BasePresenter.CSS_LABEL_STYLE);
		priceLb.setStyle(BasePresenter.CSS_LABEL_STYLE);
		errorLb.setStyle(BasePresenter.CSS_ERROR_LABEL_STYLE);

		typeTF.setStyle(BasePresenter.CSS_TEXTFIELD_STYLE);
		quantityTF.setStyle(BasePresenter.CSS_TEXTFIELD_STYLE);
		priceTF.setStyle(BasePresenter.CSS_TEXTFIELD_STYLE);
		
		super.setStyle(BasePresenter.CSS_GRIDPANE_STYLE);
	}

	// This method to return the type text field to use it on the subclasses
	public TextField getTypeTF() {
		return typeTF;
	}

	// This method to get brand data input from user and search argument for
	// search if its true we dont need quantity value and price
	public Brand getBrandData(boolean search) {

		// get type and brand from user
		String type = typeTF.getText();
		String brandName = getBrandName();

		// if user dont enter the type
		if (type == null || type.isEmpty()) {
			errorLb.setText("The type is empty");
			return null;

			// if user dont enter the brand
		} else if (brandName == null || brandName.isEmpty()) {
			errorLb.setText("The Brand Name is empty");
			return null;
		}

		int quantity = 0;
		double price = 0;

		// ignore the quantity value if search is true
		if (!search)
			try {
				// get quantity from user
				quantity = Integer.parseInt(quantityTF.getText());

			} catch (Exception e) {

				// this exception if the user enter non number value
				errorLb.setText("Please check the value of quantity (must be a number or integer)");
				return null;
			}

		// ignore the price value if search is true
		if (!search)
			try {
				// get price from user
				price = Double.parseDouble(priceTF.getText());

				// If user enter minus value
				if (price < 0) {
					errorLb.setText("Please check the value of price (must be a positive)");
					return null;
				}
			} catch (Exception e) {
				// this exception if the user enter non number value
				errorLb.setText("Please check the value of price (must be a number)");
				return null;
			}

		// Make object of brand and return it
		Brand brand = (Brand) new Brand(brandName.toLowerCase().trim(), type.toLowerCase().trim()).setPrice(price)
				.setQuantity(quantity);
		return brand;
	}

	// This method to get item data input from user and search argument for
	// search if its true we dont need quantity value and price
	public Item getItemData(boolean search) {

		// get type from user
		String type = typeTF.getText();

		// if user dont enter the type
		if (type == null || type.isEmpty()) {
			errorLb.setText("The type is empty");
			return null;
		}

		int quantity = 0;
		double price = 0;

		// ignore the quantity value if search is true
		if (!search)
			try {
				// get quantity from user
				quantity = Integer.parseInt(quantityTF.getText());

			} catch (Exception e) {

				// this exception if the user enter non number value
				errorLb.setText("Please check the value of quantity (must be a number or integer)");
				return null;
			}

		// ignore the price value if search is true
		if (!search)
			try {
				// get price from user
				price = Double.parseDouble(priceTF.getText());

				// If user enter minus value
				if (price < 0) {
					errorLb.setText("Please check the value of price (must be a positive)");
					return null;
				}
			} catch (Exception e) {
				// this exception if the user enter non number value
				errorLb.setText("Please check the value of price (must be a number)");
				return null;
			}

		// Make object of item and return it
		Item item = new Item(type.toLowerCase().trim()).setPrice(price).setQuantity(quantity);
		return item;

	}

	// This method to show to the user message
	public void printError(String error) {
		if (error != null)
			errorLb.setText(error);
	}

	// this method for search button to disable some nods if there is any wrong
	public void onSearchBtn(boolean check) {
		if (!check) {
			quantityTF.setText("");
			priceTF.setText("");
			errorLb.setText("");
		}
		quantityTF.setDisable(!check);
		priceTF.setDisable(!check);
		mainBtn.setDisable(!check);
	}

	// this method to return the nods to the default form
	public void returnToDefault() {
		quantityTF.setText("");
		priceTF.setText("");
		typeTF.setText("");
		errorLb.setText("");
	}

	// this method to set quantity and price to the text fields 
	public void setData(Item item) {
		quantityTF.setText(item.getQuantity() + "");
		priceTF.setText(item.getPrice() + "");
	}

	// this method to get the brand name from subclass
	public abstract String getBrandName();

	// this method to get the button name from subclass
	public abstract String getTitleMainBtn();

	// this method to let the subclass change from item to brand or the opposite
	public abstract void isBrandBtn(boolean check);

}
