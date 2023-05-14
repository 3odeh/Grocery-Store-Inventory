package base;

import data.Inventory;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.stage.WindowEvent;
import model.Brand;
import model.Item;

//This class to be a base of modify and delete stages
public abstract class EditBaseStage extends BaseStage implements EditBasePresenter, EventHandler<WindowEvent> {

	// Make references of addGridPane and inventory to use it
	private EditBaseGridPane paneGp;
	private Inventory store;

	// Make references of item when the search button click and found it
	private Item fItem;

	// this variable to know if we on the brand mode or not
	private boolean isBrand;

	// Constructor
	public EditBaseStage(Inventory store) {
		this.store = store;

		// set window handler when close the window then the nods return to default
		super.setOnCloseRequest(this);
	}

	// To get pane form subclass
	public void setPaneGp(EditBaseGridPane paneGp) {
		this.paneGp = paneGp;
	}

	// this method return the item found
	public Item getfItem() {
		return fItem;
	}

	// this method return the mode (if brand or item)
	public boolean isBrand() {
		return isBrand;
	}

	// this method when the user enter the mode (if brand or item) change it
	@Override
	public void brandCBFill() {
		ComboBox<String> cb = paneGp.getCb();
		if (cb.getValue() != null && "brand".equals(cb.getValue().toLowerCase().trim())) {
			paneGp.isBrandBtn(true);
			isBrand = true;
			paneGp.getSearchBtn().setVisible(true);

		} else if (cb.getValue() != null && "not brand".equals(cb.getValue().toLowerCase().trim())) {
			paneGp.isBrandBtn(false);
			isBrand = false;
			paneGp.getSearchBtn().setVisible(true);
			
		} else {
			paneGp.isBrandBtn(false);
			paneGp.getSearchBtn().setVisible(false);
			paneGp.onSearchBtn(false);
			paneGp.printError("Please select brand or not brand");
		}

	}

	// this method get data from user and search if exits or not
	@Override
	public void searchBtnClick() {

		// get combo box to get the entered value
		ComboBox<String> cb = paneGp.getCb();
		String message = "";
		try {
			// if user select brand mode
			if (cb.getValue() != null && "brand".equals(cb.getValue().toLowerCase().trim())) {
				// get brand data from user (just type and brand name)
				Brand brand = paneGp.getBrandData(true);
				fItem = brand;
				// if the input is wrong don't get it
				if (brand == null)
					return;

				// get brand data from database store
				brand = store.getBrand(brand.getBrand(), brand.getType());

				// if the brand is found
				if (brand != null) {
					
					message = "The brand has found";
					paneGp.onSearchBtn(true);

					// if delete window print price and quantity data to user in text fields
					if (paneGp.getTitleMainBtn().equals("Delete"))
						paneGp.setData(brand);

					// if modify window print price and quantity data to user in the label
					else
						message +="\n" + brand.getBrand() + " " + brand.getType() + " - in stock: " + brand.getQuantity()
								+ ", price: ";
				} else {
					paneGp.onSearchBtn(false);
					fItem = null;
					showWarning(store.getMessage());
				}

				// if user select item mode
			} else if (cb.getValue() != null && "not brand".equals(cb.getValue().toLowerCase().trim())) {

				// get item data from user (just type)
				Item item = paneGp.getItemData(true);

				// if the input is wrong don't get it
				if (item == null) {
					return;
				}

				// get item data from database store
				item = store.getItem(item.getType());

				// if the item is found
				if (item != null) {
					fItem = item;
					paneGp.onSearchBtn(true);
					message = "The item has found";

					// if delete window print price and quantity data to user
					if (paneGp.getTitleMainBtn().equals("Delete"))
						paneGp.setData(item);

					// if modify window print price and quantity data to user in the label
					else
						message +="\n" + item.getType() + " - in stock: " + item.getQuantity() + ", price: "
								+ item.getPrice();

				} else {
					paneGp.onSearchBtn(false);
					fItem = null;
					showWarning(store.getMessage());
				}

			} else {
				// if user doen't selected mode
				message = "Please select brand or not brand";
				paneGp.onSearchBtn(false);
			}
		} catch (Exception e) {
			// if there is any error or wrong
			paneGp.onSearchBtn(false);
			showWarning(e.getMessage());
		}
		// print to user the message
		paneGp.printError(message);
	}

	// this method is override to return to dfault form
	@Override
	public void cancelBtnClick() {
		// TODO Auto-generated method stub
		super.cancelBtnClick();
		paneGp.returnToDefault();
	}

	// this method to return to default form when the user press on the exit button
	@Override
	public void handle(WindowEvent arg0) {
		// TODO Auto-generated method stub
		paneGp.returnToDefault();
	}

	// This method to show the stage warning when there is a error
	public abstract void showWarning(String text);

}
