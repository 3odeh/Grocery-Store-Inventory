package home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

//This class to design the home window
public class HomeBorderPane extends BorderPane {

	// The nods of border pane
	private Label titleLb;
	private Button addBtn, modifyBtn, deletBtn, stockReportBtn;
	private Image image;

	// Constructor
	public HomeBorderPane(HomeHandler event) {

		// Initially nods , controls and layouts
		titleLb = new Label("Inventory Management System");
		addBtn = new Button("Add");
		modifyBtn = new Button("Modify");
		deletBtn = new Button("Delete");
		stockReportBtn = new Button("Stock Report");
		try {
			image = new Image(new FileInputStream(new File(HomePresenter.PATH_HOME_IMAGEVIEW_IMAGE)));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(250);
			iv.setFitWidth(300);
			iv.setRotate(45);
			super.setCenter(iv);
		} catch (FileNotFoundException e) {
			try {
				// this exception when the image does not exist so i upload the image into
				// Internet and use the link on Image View
				ImageView iv = new ImageView(HomePresenter.UML_HOME_IMAGEVIEW_IMAGE_LINK);
				iv.setFitHeight(250);
				iv.setFitWidth(300);
				iv.setRotate(45);
				super.setCenter(iv);
				System.out.println("upload the image is Successfully");
			} catch (Exception e2) {
				// if the link is not valid
				System.out.println("The image file and link does not exist");
			}
		}

		// make a HBox object and add the buttons in it and set space between them and
		// put them in the center
		HBox hb = new HBox(addBtn, modifyBtn, deletBtn, stockReportBtn);
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		
		// put the title on the center and set css style
		titleLb.setStyle(HomePresenter.CSS_HOME_LABEL_TITLE_STYLE);
		super.setAlignment(titleLb, Pos.CENTER);
		
		// set space between the pane and scene,and set background
		super.setPadding(new Insets(10));
		super.setStyle(HomePresenter.CSS_HOME_BORDERPANE_STYLE);

		// add title and buttons on the pane
		super.setTop(titleLb);
		super.setBottom(hb);

		// put handler for any action on the buttons
		addBtn.setOnAction(event);
		modifyBtn.setOnAction(event);
		deletBtn.setOnAction(event);
		stockReportBtn.setOnAction(event);

		// set style css on the components 
		addBtn.setStyle(HomePresenter.CSS_HOME_BUTTONS_STYLE);
		modifyBtn.setStyle(HomePresenter.CSS_HOME_BUTTONS_STYLE);
		deletBtn.setStyle(HomePresenter.CSS_HOME_BUTTONS_STYLE);
		stockReportBtn.setStyle(HomePresenter.CSS_HOME_BUTTONS_STYLE);

	}

}
