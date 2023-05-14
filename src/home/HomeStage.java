package home;

import add.AddStage;
import data.Inventory;
import delete.DeleteStage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modify.ModifySage;
import stock_report.StockReportStage;

//This class is a home window that let the user go to another window
public class HomeStage extends Stage implements HomePresenter, EventHandler<WindowEvent> {

	// Make references of home border pane and inventory to use it
	private HomeBorderPane pane;
	private Inventory store;
	private HomeHandler event;

	// Make references of all stages to open them when the user clicked
	private AddStage add;
	private ModifySage modify;
	private DeleteStage delete;
	private StockReportStage stockReport;

	public HomeStage(Inventory store) {
		this.store = store;

		// Get instance of handler
		event = HomeHandler.getInstance(this);

		// Initially the home border pane
		pane = new HomeBorderPane(event);

		// Initially the stages
		modify = new ModifySage(store);
		add = new AddStage(store);
		delete = new DeleteStage(store);
		stockReport = new StockReportStage(store);

		// Make scene and put the home border pane on it
		Scene scene = new Scene(pane, 800, 550);

		// Set scene on the stage and title
		super.setScene(scene);
		super.setTitle("Inventory Management System");

		// set window handler when close the window then the all windows will close
		super.setOnCloseRequest(this);

		// Set a max and min height and width window
		super.setMaxHeight(600);
		super.setMaxWidth(900);
		super.setMinHeight(550);
		super.setMinWidth(700);

		// read data from the data base file
		store.readFromDataFile();
	}

	// this method when the user click on the add button the add window will show
	public void addBtnClick() {
		// this if to know if the add window is showing
		if (!add.isShowing())
			add.show();
	}

	// this method when the user click on the modify button the modify window will
	// show
	@Override
	public void modifyBtnClick() {
		// this if to know if the modify window is showing
		if (!modify.isShowing())
			modify.show();
	}

	// this method when the user click on the delete button the delete window will
	// show
	@Override
	public void deleteBtnClick() {
		// this if to know if the delete window is showing
		if (!delete.isShowing())
			delete.show();
	}

	// this method when the user click on the stock report button the stock report
	// window will show
	@Override
	public void stockReportBtnClick() {
		// this if to know if the stockReport window is showing
		if (!stockReport.isShowing())
			stockReport.show();
	}

	@Override
	public void handle(WindowEvent event) {

		// save data on the file before when close
		store.printOnDataFile();

		// this set of if statement to know if the any window is showing to close it
		if (add.isShowing())
			add.close();
		if (modify.isShowing())
			modify.close();
		if (delete.isShowing())
			delete.close();
		if (stockReport.isShowing())
			stockReport.close();

	}

}
