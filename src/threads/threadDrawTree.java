 package threads;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Championship;
import model.Spectators;
import userinterface.Controller;

public class threadDrawTree extends Thread{
	
	private Controller ct;
	private Championship ch;
	
	public threadDrawTree(Controller ct, Championship ch) {
		this.ct = ct;
		this.ch = ch;
	}
	
	@Override
	public void run() {
		List<Spectators> spec = ch.preorder2();
		Championship champion = new Championship();
		for (int i = 0; i < spec.size(); i++) {
			if (spec.get(i).getCountry().equals(ct.COUNTRY)) {
				Spectators s = spec.get(i);
				Spectators sp = new Spectators(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(),
						s.getGender(), s.getCountry(), s.getImage(), s.getBirthdate());
				champion.addSpectatorDraw(sp);
			}
		}
		List<Spectators> spectarosDraw = champion.preorder2();
		for (int i = 0; i < spectarosDraw.size(); i++) {
			VBox vbox = new VBox(15);
			vbox.setLayoutX(spectarosDraw.get(i).getX());
			vbox.setLayoutY(spectarosDraw.get(i).getY());
			vbox.setAlignment(Pos.CENTER);
			Label lbID = new Label(spectarosDraw.get(i).getId());
			Label lbName = new Label(spectarosDraw.get(i).getFirstName());
			ImageView img = new ImageView(new Image(spectarosDraw.get(i).getImage()));
			vbox.getChildren().addAll(img,lbID,lbName);
			ct.drawTree(vbox);
		}
	}
	
}
