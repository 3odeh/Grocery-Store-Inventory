package delete;

import base.EditBaseGridPane;

//This class to design the delete window
public class DeleteGridPane extends EditBaseGridPane{

	// Constructor
	public DeleteGridPane(DeleteHandler event) {
		super(event);
	}

	// This method is override to return the name of button
	@Override
	public String getTitleMainBtn() {
		return "Delete";
	}

	

}
