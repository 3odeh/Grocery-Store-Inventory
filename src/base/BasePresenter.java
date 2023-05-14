package base;

//This interface to be a base of presenter
public interface BasePresenter {

	// Those constants css style use it on nods
	public final String CSS_BUTTONS_STYLE = " -fx-background-color: white; -fx-border-color: black;"
			+ "-fx-border-radius: 5; -fx-font-size: 15;";

	public final String CSS_GRIDPANE_STYLE = " -fx-background-color: #c0c0c0;";

	public final String CSS_LABEL_STYLE = "-fx-font-weight: bold; -fx-font-size: 15;";

	public final String CSS_ERROR_LABEL_STYLE = "-fx-text-fill: red; -fx-font-size: 15;";

	public final String CSS_TEXTFIELD_STYLE = "-fx-text-fill: green; -fx-font-size: 15;";


	// when the cancel button is click
	public void cancelBtnClick();

}
