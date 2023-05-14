package base;

import javafx.stage.Stage;

//This class to be a base of add and edit base stages
public abstract class BaseStage extends Stage implements BasePresenter {

	// Constructor
	public BaseStage() {
		// TODO Auto-generated constructor stub

		// Set a max and min height and width window
		super.setMaxHeight(450);
		super.setMaxWidth(650);
		super.setMinHeight(350);
		super.setMinWidth(550);
	}

	// This method to close the window when click on t he cancel button
	@Override
	public void cancelBtnClick() {
		// TODO Auto-generated method stub
		close();
	}

}
