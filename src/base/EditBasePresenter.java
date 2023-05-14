package base;

//This interface to be a base of delete and modify presenter
public interface EditBasePresenter extends BasePresenter {

	// This constant css style use it on node
	public final String CSS_COMBOBOX_STYLE = " -fx-font-size: 15;";

	// when the search button is click
	public void searchBtnClick();

	// when the mode brand or not is selected
	public void brandCBFill();

	// when the ok button is click
	public void okBtnClick();

}
