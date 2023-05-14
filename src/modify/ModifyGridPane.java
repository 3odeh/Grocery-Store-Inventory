package modify;

import base.EditBaseGridPane;

//This class to design the modify window
public class ModifyGridPane extends EditBaseGridPane{

	// Constructor
	public ModifyGridPane(ModifyHandler event) {
		super(event);
	}

	// This method is override to return the name of button
	@Override
	public String getTitleMainBtn() {
		return "Update";
	}

}
