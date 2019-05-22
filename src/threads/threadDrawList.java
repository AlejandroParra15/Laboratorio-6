package threads;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import model.Championship;
import model.Spectators;
import userinterface.Controller;

public class threadDrawList extends Thread {
	
	private Controller ct;
	private Championship ch;

	public threadDrawList(Controller controller, Championship champion) {
		ct = controller;
		ch = champion;
	}

	@Override
	public void run() {
		List<Spectators> spectators = ch.getInfo();

		for (int i = 0; i < spectators.size(); i++) {
			if (spectators.get(i).getCountry().equals(ct.COUNTRY)) {
				Line line1 = new Line(0, 0, 50, 0);
				Line line2 = new Line(0, 0, 50, 0);
				VBox vbox = new VBox(20);
				vbox.setAlignment(Pos.CENTER);
				vbox.getChildren().add(line1);
				vbox.getChildren().add(line2);
				//
				Image img = new Image(spectators.get(i).getImage());
				Label lbName = new Label(spectators.get(i).getFirstName() + " " + spectators.get(i).getLastName());
				lbName.setPrefWidth(100);
				Label lbID = new Label(spectators.get(i).getId());
				//
				VBox vbox2 = new VBox(15);
				vbox2.setAlignment(Pos.CENTER);
				vbox2.getChildren().add(new ImageView(img));
				vbox2.getChildren().add(lbID);
				vbox2.getChildren().add(lbName);
				ct.draw(vbox2);
				ct.draw(vbox);
			}
		}
	}

}
