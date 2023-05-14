package stock_report;

//This interface to link between the stock report handler and the stock report stage
public interface StockReportPresenter {
	
	// This constant css style use it on node
	public final String CSS_LABEL_TITLE_STYLE = "-fx-font-weight: bold; -fx-font-size: 20;";
	
	// when the export check box is selected
	public void exportCBClick();
	
	// when the text area check box is selected
	public void textAreaCBClick();
	
	// when the export button is click
	public void exportFileBtnClick();
	
	// when the ok button is click
	public void okBtnClick();

}
