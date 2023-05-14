package add;

import base.BasePresenter;

// This interface to link between the add handler and the add stage
public interface AddPresenter extends BasePresenter {

	// when the save button is click
	public void saveBtnClick();

	// when the yes radio button is select
	public void yesRBtnClick();

	// when the no radio button is select
	public void noRBtnClick();

}
