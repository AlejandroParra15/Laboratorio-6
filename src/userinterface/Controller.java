package userinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CustomsExceptions.CVSFileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Championship;
import model.Spectators;

public class Controller {

	private Championship ch;
	// FXML VARIABLES
	@FXML
	private Label TimeS;

	@FXML
	private Label timeP;
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
	@FXML
	private HBox hboxDraw;

	// Variables
	String path;

	public void initialize() {
		ch = new Championship();
	}

	@FXML
	void spectatorDraw(ActionEvent event) {
		List<Spectators> spectators = ch.getInfo();
		
		for (int i = 0; i < 20; i++) {
			Line line1 = new Line(0,0,50,0);
			Line line2 = new Line(0,0,50,0);
			VBox vbox = new VBox(20); 
			vbox.setAlignment(Pos.CENTER); 
			vbox.getChildren().add(line1);
			vbox.getChildren().add(line2);
			Image img = new Image(spectators.get(i).getImage());
			hboxDraw.getChildren().add(new ImageView(img));
			hboxDraw.getChildren().add(vbox);
		}
	}

	@FXML
	void loadData(ActionEvent event) {
		try {
			ch.loadTextFile(path, ",");
		} catch (IOException | CVSFileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void openFile(ActionEvent event) {
		try {
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
			File f = fc.showOpenDialog(null);
			if (f != null)
				tfLoadData.setText(f.getAbsolutePath());
			path = f.getAbsolutePath();
		} catch (NullPointerException e) {

		}

	}

	@FXML
	void searchParticipants(ActionEvent event) {
		Spectators sx = ch.searchParcitipants(tfSearchParticipantes.getText());
		if (sx != null) {
			lbMessageParticipantes.setText("");
			tfID.setText(sx.getId());
			tfFirstName.setText(sx.getFirstName());
			tfLastName.setText(sx.getLastName());
			tfEmail.setText(sx.getEmail());
			tfGender.setText(sx.getGender());
			tfCountry.setText(sx.getCountry());
			tfBirthday.setText(sx.getBirthdate());
			timeP.setText(ch.getTimeP());
			imvPhoto.setImage(new Image(sx.getImage()));
		} else {
			lbMessageParticipantes.setText("The participant does not exit");
			tfID.setText("");
			tfFirstName.setText("");
			tfLastName.setText("");
			tfEmail.setText("");
			tfGender.setText("");
			tfCountry.setText("");
			tfBirthday.setText("");
			timeP.setText("");
			imvPhoto.setImage(null);
		}
	}

	@FXML
	void searchSpectators(ActionEvent event) {

		Spectators sx = ch.searchSpectators(tfSearchSpectators.getText());
		System.out.println(ch.getRoot().getId());
		if (sx != null) {
			lbMessageParticipantes.setText("");
			tfID.setText(sx.getId());
			tfFirstName.setText(sx.getFirstName());
			tfLastName.setText(sx.getLastName());
			tfEmail.setText(sx.getEmail());
			tfGender.setText(sx.getGender());
			tfCountry.setText(sx.getCountry());
			tfBirthday.setText(sx.getBirthdate());
			TimeS.setText(ch.getTimeS());
			imvPhoto.setImage(new Image(sx.getImage()));
		} else {
			lbMessageParticipantes.setText("The participant does not exit");
			tfID.setText("");
			tfFirstName.setText("");
			tfLastName.setText("");
			tfEmail.setText("");
			tfGender.setText("");
			tfCountry.setText("");
			tfBirthday.setText("");
			timeP.setText("");
			imvPhoto.setImage(null);
		}
	}

}
