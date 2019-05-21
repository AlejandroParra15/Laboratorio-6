package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Spectators;

class SpectatorsTest {
	
	//------------------------------
	// Associations
	//------------------------------
	private Spectators toAdd;
	private Spectators toAdd1;
	
	//-------------------------------------
	// Scenarios
	//-------------------------------------
	public void setUpScenary1() {
			
	}
	public void setUpScenary2() {
		toAdd = new Spectators("99-9999999", "Archer", "Shireff", "ashireff0@freewebs.com", "Male", "China", "https://robohash.org/eiusmolestiaequi.jpg?size=50x50&set=set1","12/8/2018" );
		toAdd1 = new Spectators("00-0000000", "Archer", "Shireff", "ashireff0@freewebs.com", "Male", "China", "https://robohash.org/eiusmolestiaequi.jpg?size=50x50&set=set1","12/8/2018" );
	}
	
	//-------------------------------------
	// Tests
	//-------------------------------------
	@Test
	void Spectators() {
	  setUpScenary1();
	  String p1,p2,p3,p4,p5,p6,p7;
	  p1="29-0609001";
	  p2="Ginger";
	  p3="Romei";
	  p4="gromei2@freewebs.com";
	  p5="Male";
	  p5="China";
	  p6="https://robohash.org/etaccusamusullam.png?size=50x50&set=set1";
	  p7="11/24/2018";

	  Spectators sx=new Spectators(p1, p2, p3, p4, p5, p6, p7,p7);
	  assertTrue("The node were not created properly", sx.getId().equals(p1));
	  assertTrue("The node were not created properly", sx.getFirstName().equals(p2));
	  assertTrue("The node were not created properly", sx.getLastName().equals(p3));
	  assertTrue("The node were not created properly", sx.getEmail().equals(p4));
	  assertTrue("The node were not created properly", sx.getGender().equals(p5));
	  assertTrue("The node were not created properly", sx.getBirthdate().equals(p7));
	}
	
	@Test 
	void compareToTest(){
		setUpScenary2();
		assertTrue("The method is not working properly", toAdd.compareTo("0")>=0);
		assertTrue("The method is not working properly", toAdd1.compareTo("999999999999999")<=0);
		assertTrue("The method is not working properly", toAdd.compareTo("99-9999999")==0);
	}


}