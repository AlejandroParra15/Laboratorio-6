package modelTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import CustomsExceptions.CVSFileNotFoundException;
import model.Championship;
import model.Spectators;

class ChampionshipTest {
	
	//------------------------------
	// Associations
	//------------------------------
	private Championship ch;
	private Spectators toAdd;
	private Spectators toAdd1;
	
	//-------------------------------------
	// Scenarios
	//-------------------------------------
	public void setUpScenary1() {
		
	}
	public void setUpScenary2() {
		ch = new Championship();
	}
	public void setUpScenary3() {
		toAdd = new Spectators("99-9999999", "Archer", "Shireff", "ashireff0@freewebs.com", "Male", "China", "https://robohash.org/eiusmolestiaequi.jpg?size=50x50&set=set1","12/8/2018" );
		toAdd1 = new Spectators("00-0000000", "Archer", "Shireff", "ashireff0@freewebs.com", "Male", "China", "https://robohash.org/eiusmolestiaequi.jpg?size=50x50&set=set1","12/8/2018" );
	}
	//-------------------------------------
	// Tests
	//-------------------------------------
	@Test
	void ChampionshipTest() {
		Championship newCh = new Championship();
		assertNotNull("The Championship object has not been instatianted", newCh);
		assertTrue("The getter method is not returning the correct value", newCh.getRoot()==null);
		assertTrue("The getter method is not returning the correct value", newCh.getCounter()==0);
		assertTrue("The getter method is not returning the correct value", newCh.getLinkedListSize()==0);
		assertTrue("The getter method is not returning the correct value", newCh.getTreeSize()==0);
		assertTrue("The getter method is not returning the correct value", newCh.participantsSerchTime()==0);
		assertTrue("The getter method is not returning the correct value", newCh.spectatorsSerchTime()==0);
	}
	
	@Test
	void loadTextFileTest() {
		setUpScenary2();
		try {
			ch.loadTextFile("C:\\Users\\usuario\\eclipse-workspace\\Laboratorio-6\\data\\database.csv", ",");
			assertTrue("The Binary Tree has not been created", ch.getRoot()!=null);
			assertTrue("The Binary Tree has not been created properly", ch.getRoot().getId().compareTo(ch.getRoot().getRight().getId())<=0);
			assertTrue("The Binary Tree has not been created properly", ch.getRoot().getId().compareTo(ch.getRoot().getLeft().getId())>=0);
			assertTrue("The linkedList has not been created", ch.getFirst()!=null);
			assertTrue("The linkedList has not been created properly", ch.getFirst().getNext()!=null);
			assertTrue("The linkedList has not been created properly", ch.getFirst().getNext().getPrev()==ch.getRoot());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CVSFileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void addSpectatorTest() {
		setUpScenary3();
		setUpScenary2();
		try {
			ch.loadTextFile("C:\\Users\\usuario\\eclipse-workspace\\Laboratorio-6\\data\\dataForTest.csv", ",");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CVSFileNotFoundException e) {
			e.printStackTrace();
		}

		ch.addSpectator(toAdd);
		assertTrue("The method is not adding the node in the correct position", ch.getRoot().getRight().getRight().getId().equals("99-9999999"));
		setUpScenary2();
		try {
			ch.loadTextFile("C:\\Users\\usuario\\eclipse-workspace\\Laboratorio-6\\data\\dataForTest.csv", ",");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CVSFileNotFoundException e) {
			e.printStackTrace();
		}
		ch.addSpectator(toAdd1);
		assertTrue("The method is not adding the node in the correct position", ch.getRoot().getLeft().getId().equals("00-0000000"));
		setUpScenary2();
		ch.addSpectator(toAdd1);
		assertTrue("The method is not adding the node in the correct position", ch.getRoot().getId().equals("00-0000000"));
	}
	
	@Test
	void addToLinkedListTest() {
		setUpScenary2();
		setUpScenary3();
		ch.addToLinkedList(toAdd);
		assertTrue("The list were not created", ch.getRoot()==null);
		ch.addToLinkedList(toAdd1);
		assertTrue("The node were not added properly", ch.getFirst().getNext().getId().equals("00-0000000"));
	}
	
	@Test
	void getLastTest() {
		setUpScenary2();
		setUpScenary3();
		ch.addToLinkedList(toAdd);
		ch.addToLinkedList(toAdd1);
		assertTrue("The method is not returning the last node", ch.getLast().getId().equals(toAdd1.getId()));
	}
	
	@Test
	void searchParcitipantsTest() {
		setUpScenary2();
		try {
			ch.loadTextFile("C:\\Users\\usuario\\eclipse-workspace\\Laboratorio-6\\data\\database.csv", ",");
		} catch (IOException | CVSFileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull("The method did not find the node", ch.searchParcitipants("48-5389696"));
		assertNotNull("The method did not find the node", ch.searchParcitipants("82-1659441"));
		assertNull("The method returned the incorrect node", ch.searchParcitipants("48-53896923"));
		assertNull("The method returned the incorrect node", ch.searchParcitipants("a8-53896923"));
	}
	
	@Test
	void searchSpectatorsTest() {
		setUpScenary2();
		try {
			ch.loadTextFile("C:\\Users\\usuario\\eclipse-workspace\\Laboratorio-6\\data\\database.csv", ",");
		} catch (IOException | CVSFileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull("The method did not find the node", ch.searchSpectators("48-5389696"));
		assertNotNull("The method did not find the node", ch.searchSpectators("82-1659441"));
		assertNull("The method returned the incorrect node", ch.searchSpectators("48-53896923"));
		assertNull("The method returned the incorrect node", ch.searchSpectators("a8-53896923"));
	}
	
	@Test
	void calculateTime() {
		setUpScenary2();
		long x=100;
		long y=90;
		ch.calculateTime(y, x, true);
		assertTrue("The method is no calculating the the time properly",ch.participantsSerchTime()==10);
		ch.calculateTime(y, x, false);
		assertTrue("The method is no calculating the the time properly",ch.spectatorsSerchTime()==10);
	}
	
}
