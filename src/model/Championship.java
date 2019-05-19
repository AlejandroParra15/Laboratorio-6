package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Championship {
	
	//------------------------------
	// Associations
	//------------------------------
	private Spectators root;
	private Spectators first;

	//------------------------------
	// Constructor 
	//------------------------------
	public Championship() {
	
	}
	
	//------------------------------
	// Methods 
	//------------------------------
	public void loadTextFile(String path, String sep) throws IOException{
		File f =  new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		while(line != null) {
			if(line.charAt(0)!='#') {
				System.out.println(line);
				String[] parts = line.split(sep);
				String id = parts[0];
				String firstName = parts[1];
				String lastName = parts[2];
				String email = parts[3];
				String gender = parts[4];
				String country = parts[5];
				String birthdate = parts[6];
				Spectators sx = new Spectators(id, firstName, lastName, email, gender, country, birthdate);
				addSpectator(sx);
			}
			line=br.readLine();
			
		}	
	}
	
	public void addSpectator(Spectators sx) {
		Spectators toAdd = sx;
		if(root==null){
			root = toAdd;
		  }else{
		     Spectators current = root;
		     boolean added = false;
		      
		     while(!added){
		           
		           if(toAdd.compare(current) > 0){
		               if(current.getRight() == null){
		                  current.setRight(toAdd);
		                  added = true;
		               }else{
		 	          current = current.getRight();
		              }
		           }else{
				if(current.getLeft() == null){
		                  current.setLeft(toAdd);
		                  added = true;
		               }else{
		 	          current = current.getLeft();
		               }

			   } 
		      }
		  }
	}
}