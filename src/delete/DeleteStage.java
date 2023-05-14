package delete;

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

//This class to delete item or brand 
public class DeleteStage extends EditBaseStage implements DeletePresenter {

	// Make references of addGridPane and inventory to use it
	private DeleteGridPane paneGp;
	private Inventory store;

	// Make references of handler and warning stage
	private DeleteHandler event;
	private Stage warningStage;

	public DeleteStage(Inventory store) {
		super(store);
		this.store = store;

		// Get instance of handler
		event = DeleteHandler.getInstance(this);

		// Initially the delete grid pane
		paneGp = new DeleteGridPane(event);

		// Make scene and put the update grid pane on it
		Scene scene = new Scene(paneGp, 600, 400);

		// Set scene on the stage
		super.setScene(scene);

		// Set stage title
		super.setTitle("Delete item");

		// sent pane to the superclass
		super.setPaneGp(paneGp);
	}

	// this method to delete the item or brand
	@Override
	public void deleteBtnClick() {

		Item deleteItem;
		String message = "";
		try {
			// get data from super class after searching
			searchBtnClick();
			deleteItem = super.getfItem();
			if (deleteItem != null) {
				if (deleteItem instanceof Brand) {
					// delete the brand
					store.deleteItem(((Brand) deleteItem).getBrand(), deleteItem.getType());
					message = store.getMessage();
					if (message.isEmpty()) {
						paneGp.printError("The brand is deleted");
					} else {
						showWarning(message);
					}
					paneGp.onSearchBtn(false);
				} else {
					// delete the item
					store.deleteItem(deleteItem.getType());			
					if (message.isEmpty()) {
						paneGp.printError("The item is deleted");
					} else {
						showWarning(message);
					}
					paneGp.onSearchBtn(false);
				}
			}
		} catch (Exception e) {
			paneGp.onSearchBtn(false);
			showWarning(e.getMessage());
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
		label.setStyle(" -fx-font-size: 15;");
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
