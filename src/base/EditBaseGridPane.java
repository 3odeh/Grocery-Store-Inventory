package base;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

//This class to design the add and modify window
public abstract class EditBaseGridPane extends BaseGridPane {

	// The nods of grid pane
	private TextField brandNameTF;
	private Button searchBtn;
	private HBox hb;
	private ComboBox<String> cb;

	// Constructor
	public EditBaseGridPane(EventHandler<ActionEvent> event) {
		super(event);

		// Initially nods , controls and layouts
		brandNameTF = new TextField();
		searchBtn = new Button("Search");
		hb = new HBox();
		cb = new ComboBox<>();

		// Add the search button on HBox and set space between nods
		hb.getChildren().add(searchBtn);
		hb.setSpacing(10);

		// Add values (Brand or Not Brand) to the combo box and make it editable and put
		// the handler for any action on it
		cb.getItems().addAll("Brand", "Not Brand");
		cb.setEditable(true);
		cb.setOnAction(event);

		// Add the component on grid pane
		super.add(cb, 2, 0);
		super.add(hb, 3, 1, 4, 1);

		// Put handler for any action on the search button and set it non visible
		searchBtn.setOnAction(event);
		searchBtn.setVisible(false);

		// put search for item or brand mode
		onSearchBtn(false);

		// set style css on the components
		brandNameTF.setStyle(EditBasePresenter.CSS_TEXTFIELD_STYLE);
		searchBtn.setStyle(EditBasePresenter.CSS_BUTTONS_STYLE);

		// set text to let user know what is input
		brandNameTF.setText("Brand Name");
		super.getTypeTF().setText("Item Type");
		cb.setStyle(EditBasePresenter.CSS_COMBOBOX_STYLE);

//		// set key handler to combo box
//		cb.getEditor().setOnKeyPressed(this);

	}

	// This method to return the search button
	public Button getSearchBtn() {
		return searchBtn;
	}

	// This method to return the combo box
	public ComboBox<String> getCb() {
		return cb;
	}

	// This method is override to return all nods to default form(to the initial
	// values
	// and form)
	@Override
	public void returnToDefault() {
		super.returnToDefault();
		isBrandBtn(false);
		brandNameTF.setText("Brand Name");
		super.getTypeTF().setText("Item Type");
		cb.setValue("");
		searchBtn.setVisible(false);
		onSearchBtn(false);
	}

	// this method to get the brand name from subclass
	@Override
	public abstract String getTitleMainBtn();

	// this method to change from item to brand or the opposite
	@Override
	public void isBrandBtn(boolean check) {

		if (check) {
			if (hb.getChildren().get(0) != brandNameTF)
				hb.getChildren().add(0, brandNameTF);

		} else {
			if (hb.getChildren().get(0) == brandNameTF)
				hb.getChildren().remove(brandNameTF);
			if(!hb.getChildren().contains(searchBtn))
				hb.getChildren().add(0, searchBtn);
		}
	}

	// This method to return the input brand to use it on super class
	@Override
	public String getBrandName() {
		String brand = brandNameTF.getText();
		if (brand == null || brand.isEmpty())
			return null;
		return brand;
	}

//	// this method to order the values when the user enter in the combo box and there logical error
//	@Override
//	public void handle(KeyEvent arg0) {
//		String intputText = cb.getEditor().getText().toLowerCase();
//		if ("brand ".startsWith(intputText)) {
//			cb.getItems().sort(Comparator.naturalOrder());
//
//		} else if ("not brand".startsWith(intputText)) {
//			cb.getItems().sort(Comparator.reverseOrder());
//
//		}
//
//	}
}
