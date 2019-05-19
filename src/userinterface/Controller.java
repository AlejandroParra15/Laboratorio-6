package userinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Championship;

public class Controller {
	
	private Championship ch;
	// FXML VARIABLES
	@FXML
	private TextField tfLoadData;
	@FXML
	private Label lbMessageLoad;
	@FXML
	private TextField tfSearchSpectators;
	@FXML
	private Label timeSpectators;
	@FXML
	private Label lbMessageSpectators;
	@FXML
	private TextField tfSearchParticipantes;
	@FXML
	private Label timeParticipantes;
	@FXML
	private Label lbMessageParticipantes;
	@FXML
	private ImageView imvPhoto;
	@FXML
	private TextField tfID;
	@FXML
	private TextField tfFirstName;
	@FXML
	private TextField tfLastName;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfGender;
	@FXML
	private TextField tfCountry;
	@FXML
	private TextField tfBirthday;

	// Variables
	String path;

	public void initialize() {
		ch = new Championship();
	}

	@FXML
	void loadData(ActionEvent event) {
		try {
			ch.loadTextFile(path, ",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void openFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
		File f = fc.showOpenDialog(null);
		if (f != null)
			tfLoadData.setText(f.getAbsolutePath());
		path = f.getAbsolutePath();

	}

	@FXML
	void searchParticipantes(ActionEvent event) {

	}

	@FXML
	void searchSpectators(ActionEvent event) {

	}

}
