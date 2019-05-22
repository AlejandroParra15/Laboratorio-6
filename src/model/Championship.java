package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CustomsExceptions.CVSFileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Championship {

	// ------------------------------
	// Associations
	// ------------------------------
	private Spectators root;
	private Spectators first;

	// ------------------------------
	// Attributes
	// ------------------------------
	private int treeSize;
	private int counter;
	private int linkedListSize;
	private long participantsSerchTime;
	private long spectatorsSerchTime;
	private ObservableList<String> countrys;

	// ------------------------------
	// Constructor
	// ------------------------------
	public Championship() {
		root = null;
		treeSize = 0;
		counter = 0;
		linkedListSize = 0;
		participantsSerchTime = 0;
		spectatorsSerchTime = 0;
		countrys = FXCollections.observableArrayList();
	}

	// ------------------------------
	// Getters and Setters
	// ------------------------------
	public String getTimeP() {
		return "TIME: " + participantsSerchTime + " ms";
	}

	public String getTimeS() {
		return "TIME: " + spectatorsSerchTime + " ms";
	}

	public Spectators getRoot() {
		return root;
	}

	public int getTreeSize() {
		return treeSize;
	}

	public int getCounter() {
		return counter;
	}

	public int getLinkedListSize() {
		return linkedListSize;
	}

	public long participantsSerchTime() {
		return participantsSerchTime;
	}

	public long spectatorsSerchTime() {
		return spectatorsSerchTime;
	}

	public Spectators getFirst() {
		return first;
	}

	// ------------------------------
	// Methods
	// ------------------------------
	public void loadTextFile(String path, String sep) throws IOException, CVSFileNotFoundException {

		if (path != null) {
			File f = new File(path);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				// System.out.println(line);
				if (line.charAt(0) != '#') {
					String[] parts = line.split(sep);
					String id = parts[0];
					String firstName = parts[1];
					String lastName = parts[2];
					String email = parts[3];
					String gender = parts[4];
					String country = parts[5];
					verifyCountry(country);
					String image = parts[6];
					String birthdate = parts[7].substring(0, 8);
					Spectators sx = new Spectators(id, firstName, lastName, email, gender, country, image, birthdate);
					addSpectator(sx);
					if (counter % 2 == 0)
						addToLinkedList(sx);
					counter++;
					treeSize++;
				}
				line = br.readLine();

			}
		} else {
			throw new CVSFileNotFoundException();
		}
	}

	public void verifyCountry(String ct) {
		int x = 0;
		for (int i = 0; i < countrys.size(); i++) {
			if (countrys.get(i).equals(ct)) {
				x++;
			}
		}
		if (x == 0) {
			countrys.add(ct);
		}
	}

	public void addSpectator(Spectators sx) {
		Spectators toAdd = sx;
		if (root == null) {
			root = toAdd;
		} else {
			Spectators current = root;
			boolean added = false;

			while (!added) {

				if (toAdd.compare(current) > 0) {
					if (current.getRight() == null) {
						current.setRight(toAdd);
						added = true;
					} else {
						current = current.getRight();
					}
				} else {
					if (current.getLeft() == null) {
						current.setLeft(toAdd);
						added = true;
					} else {
						current = current.getLeft();
					}

				}
			}
		}
	}

	public void addToLinkedList(Spectators sx) {
		Spectators toAdd = sx;

		if (first == null) {
			first = toAdd;
		} else {
			Spectators lastone = getLast();
			toAdd.setPrev(lastone);
			lastone.setNext(toAdd);
		}
		linkedListSize++;
	}

	public Spectators getLast() {
		Spectators current = first;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		return current;
	}

	public List<Spectators> getInfo() {
		Spectators current = first;
		List<Spectators> spectators = new ArrayList<>();
		if (first != null)
			spectators.add(current);
		while (current.getNext() != null) {
			current = current.getNext();
			spectators.add(current);
		}
		return spectators;
	}

	public Spectators searchParcitipants(String idx) {
		long x = System.currentTimeMillis();
		Spectators sx = null;
		Spectators current = first;
		if (current != null) {
			while (current.getNext() != null) {
				if (current.compareTo(idx) == 0) {
					sx = current;
				}
				current = current.getNext();
			}
		}
		long y = System.currentTimeMillis();
		calculateTime(x, y, true);
		return sx;
	}

	public Spectators searchSpectators(String idx) {
		long x = System.currentTimeMillis();
		Spectators found = null;
		Spectators current = root;
		boolean keep = true;
		if (root != null) {
			while (current != null && keep) {
				if (current.getId().equals(idx)) {
					found = current;
					keep = false;
				} else if (current.getId().compareTo(idx) >= 0) {
					current = current.getLeft();
				} else if (current.getId().compareTo(idx) <= 0) {
					current = current.getRight();
				}
			}
		}
		long y = System.currentTimeMillis();
		calculateTime(x, y, false);
		return found;
	}

	public void calculateTime(long x, long y, boolean w) {
		System.out.println("Timey" + y);
		System.out.println("Timex" + x);
		long time = y - x;
		if (w)
			participantsSerchTime = time;
		else
			spectatorsSerchTime = time;

	}

	public ObservableList<String> getCountrys() {
		return countrys;
	}

	public void setCountrys(ObservableList<String> countrys) {
		this.countrys = countrys;
	}

}
