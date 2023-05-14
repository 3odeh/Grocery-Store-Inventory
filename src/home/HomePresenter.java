package home;


//This interface to link between the home handler and the home stage
public interface HomePresenter {

	// Those constants css style use it on nods
	public final String CSS_HOME_LABEL_TITLE_STYLE = "-fx-text-fill: #5a4f65; "
			+ "-fx-font: bold italic 20pt \"Serif\";" + "-fx-font-size: 25;";

	public final String CSS_HOME_BUTTONS_STYLE = " -fx-background-color: white;" + " -fx-border-color: black;"
			+ "-fx-border-radius: 5;" + "-fx-font-size: 15;";

	public final String UML_HOME_IMAGEVIEW_IMAGE_LINK = "https://i.ibb.co/prjsGqv/inventory-Management.jpg";

	public final String PATH_HOME_IMAGEVIEW_IMAGE = "inventoryManagement.jpg";

	public final String CSS_HOME_BORDERPANE_STYLE = " -fx-background-color: #c0c0c0;";

	// when the add button is click
	public void addBtnClick();

	// when the modify button is click
	public void modifyBtnClick();

	// when the delete button is click
	public void deleteBtnClick();

	// when the stock report button is click
	public void stockReportBtnClick();

}
