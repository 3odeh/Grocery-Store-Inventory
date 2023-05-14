package application;

import data.Inventory;
import home.HomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// start the program
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {

		// make object of Inventory to use it
		Inventory store = new Inventory("groceries");
		
		// make object of home stage home and open it
		HomeStage stage = new HomeStage(store);
		stage.show();
	}

}
