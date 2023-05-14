package add;

import base.BaseStage;
import data.Inventory;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import model.*;

// This class to save new items or brand 
public class AddStage extends BaseStage implements AddPresenter, EventHandler<WindowEvent> {

	// Make references of addGridPane and inventory to use it
	private AddGridPane paneGp;
	private Inventory store;

	// Constructor
	public AddStage(Inventory store) {
		this.store = store;

		// Get instance of handler
		AddHandler event = AddHandler.getInstance(this);

		// Initially the add grid pane
		paneGp = new AddGridPane(event);

		// Make scene and put the add grid pane on it
		Scene scene = new Scene(paneGp, 600, 400);

		// set window handler when close the window then the nods return to default
		super.setOnCloseRequest(this);

		// Set scene on the stage
		super.setScene(scene);

		// Set stage title
		super.setTitle("Add new item");
	}

	// This method to get item data or brand data from user and save it
	@Override
	public void saveBtnClick() {

		String message = "";
		if (paneGp.getYesRBtn().isSelected()) {

			// get data from user
			Brand brand = paneGp.getBrandData(false);

			// if the input is wrong don't save it
			if (brand == null)
				return;

			// try to save the brand data if exist
			store.newItem(brand.getBrand(), brand.getType(), brand.getQuantity(), brand.getPrice());
			message = store.getMessage();
			if (message.isEmpty()) {
				message = brand.getBrand() + " " + brand.getType() + " - in stock: " + brand.getQuantity() + ", price: "
						+ brand.getPrice();
			}

		} else {

			// get data from user
			Item item = paneGp.getItemData(false);

			// if the input is wrong don't save it
			if (item == null)
				return;

			// try to save the item data if exist
			store.newItem(item.getType(), item.getQuantity(), item.getPrice());

			message = store.getMessage();
			if (message.isEmpty()) {
				message = item.getType() + " - in stock: " + item.getQuantity() + ", price: " + item.getPrice();
			}

		}
		// show to user if there error or it save and show the data
		paneGp.printError(message);
	}

	// This override method to close the window and return the nods to default form
	@Override
	public void cancelBtnClick() {
		// TODO Auto-generated method stub
		super.cancelBtnClick();
		paneGp.returnToDefault();
	}

	// This override method to show to user the brand data
	@Override
	public void yesRBtnClick() {
		// TODO Auto-generated method stub
		paneGp.isBrandBtn(true);
	}

	// This override method to show to user the item data
	@Override
	public void noRBtnClick() {
		// TODO Auto-generated method stub
		paneGp.isBrandBtn(false);
	}

	// This method if the window close (by the exit button) then return nods to
	// default
	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		paneGp.returnToDefault();
	}

}
