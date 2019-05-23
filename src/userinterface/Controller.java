package userinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CustomsExceptions.CVSFileNotFoundException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Championship;
import model.Spectators;
import threads.threadDrawList;
import threads.threadDrawTree;

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
	@FXML
	private ComboBox<String> cbCountry;
	@FXML
	private ScrollPane scrollPane;

	// Variables
	String path;
	public static String COUNTRY;
	Pane pane;

	public void initialize() {
		ch = new Championship();
		lbMessageLoad.setVisible(false);
		pane = new Pane();
		cbCountry.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				COUNTRY = newValue;
			}

		});
	}

	@FXML
	void spectatorDraw(ActionEvent event) {
		pane.getChildren().clear();
		scrollPane.setContent(pane);
		threadDrawTree th = new threadDrawTree(this, ch);
		th.setDaemon(true);
		th.start();
	}

	@FXML
	void participantDraw(ActionEvent event) {
		scrollPane.setContent(hboxDraw);
		threadDrawList th = new threadDrawList(this, ch);
		th.setDaemon(true);
		th.start();
	}

	public void drawList(Node node) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				hboxDraw.getChildren().add(node);
			}
		});
	}
	
	public void drawTree(Node node) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				pane.getChildren().add(node);
			}
		});
	}

	@FXML
	void loadData(ActionEvent event) {
		try {
			ch.loadTextFile(path, ",");
		} catch (IOException | CVSFileNotFoundException e) {
			e.printStackTrace();
		}
		cbCountry.setItems(ch.getCountrys());
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
