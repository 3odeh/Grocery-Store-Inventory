package modify;

import base.EditBaseStage;
import data.Inventory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Brand;
import model.Item;

//This class to update item or brand 
public class ModifySage extends EditBaseStage implements ModifyPresenter {

	// Make references of addGridPane and inventory to use it
	private ModifyGridPane paneGp;
	private Inventory store;

	// Make references of handler and warning stage
	private ModifyHandler event;
	private Stage warningStage;

	// Constructor
	public ModifySage(Inventory store) {
		super(store);
		this.store = store;

		// Get instance of handler
		event = ModifyHandler.getInstance(this);

		// Initially the modify grid pane
		paneGp = new ModifyGridPane(event);

		// Make scene and put the update grid pane on it
		Scene scene = new Scene(paneGp, 600, 400);

		// Set scene on the stage
		super.setScene(scene);

		// Set stage title
		super.setTitle("Update item");

		// sent pane to the superclass
		super.setPaneGp(paneGp);
	}

	// this method to update the data
	@Override
	public void updateBtnClick() {
		
		searchBtnClick();
		Item updateItem = super.getfItem();
		if(updateItem == null) {
			paneGp.printError("");
			return;
		}
		// get mode to know what is brand or not
		if (super.isBrand())
			// get brand data from super class after searching
			updateItem = paneGp.getBrandData(false);
		else
			// get item data from super class after searching
			updateItem = paneGp.getItemData(false);
		try {
			if (updateItem != null) {
				if (updateItem instanceof Brand) {
					// update the brand data					
					store.update(((Brand) updateItem).getBrand(), updateItem.getType(), updateItem.getQuantity());
					store.update(((Brand) updateItem).getBrand(), updateItem.getType(), updateItem.getPrice());
					
					Brand brand = store.getBrand(((Brand) updateItem).getBrand(), updateItem.getType());
					paneGp.printError(brand.getBrand() + " " + brand.getType() + " - in stock: " + brand.getQuantity()
							+ ", price: ");

				} else {
					// update the item data
					store.update(updateItem.getType(), updateItem.getPrice());
					store.update(updateItem.getType(), updateItem.getQuantity());
					
					
					Item item = store.getItem(updateItem.getType());
					paneGp.printError(item.getType() + " - in stock: " + item.getQuantity() + ", price: "
							+ item.getPrice());
				}
			}
		} catch (Exception e) {
			paneGp.onSearchBtn(false);
			showWarning(store.getMessage());
		}

	}

	// this method make a window waning
	@Override
	public void showWarning(String text) {

		VBox box = new VBox();
		Scene scene = new Scene(box, 300, 100);
		warningStage = new Stage();

		Label label = new Label(text);
		Button button = new Button("Ok");

		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(label, button);
		button.setOnAction(event);
		box.setSpacing(5);

		warningStage.setScene(scene);
		warningStage.setTitle("Warning");
		warningStage.showAndWait();

	}

	// this close the warning window when click on the ok button
	@Override
	public void okBtnClick() {
		warningStage.close();
	}

}
