package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.*;

// this class to read or write on files
public class FileEditor extends File {

	//this constant of database file name
	private final static String MY_DATA_FILE_NAME = "inventoryDatabase.txt";
 
	private ArrayList<Item> items ;
	//Constructor
	public FileEditor() throws IOException {
		super(MY_DATA_FILE_NAME);
		
		//Create file if not exit
		if (!exists()) {
			System.out.println("Create new file");
			createNewFile();
		}
		
		items = new ArrayList<>();
	}

	//this method to read data from fill and put it in the array
	public ArrayList<Item> readMyData() {

		// if the array is null don't do any thing
		if (items == null)
			return null;

		// read data from file
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this))) {

			while (true) {
				Item item = (Item) objectInputStream.readObject();
				items.add(item);
			}
		} catch (Exception e) {
			System.out.println("Read from data file is done");
		}
		return items;

	}

	//this method to write the report on the input file from user
	public void printStock(String nameFile, String text) throws IllegalArgumentException, FileNotFoundException {

		//if the report is null or empty or the file name is null don't do any thing
		if (text == null || text.isEmpty() || nameFile == null)
			return;

		File file = new File(nameFile);
		if (!file.exists())
			throw new IllegalArgumentException("the file is not exists");

		// write report to the file file
		try (PrintWriter printWriter = new PrintWriter(file)) {
			if (!MY_DATA_FILE_NAME.equals(file.getName())) {
				printWriter.write(text);
			} else {
				// if the input file name is my database file 
				throw new IllegalArgumentException("You cant write the report on the data file");
			}
		} catch (FileNotFoundException e) {
			// if there is exception throw it 
			throw e;
		}
	}

	// this method to write data when the program is close
	public void writeMyData(ArrayList<Item> items) {

		// if the array is null don't do any thing
		if (items == null || items.isEmpty()) {
			return;
		}

		// write data to the file
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(this))) {
			for (int j = 0; j < items.size(); j++) {
				output.writeObject(items.get(j));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
