/**
 * 
 */
package com.moltude.secretSanta;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author scottwi
 *
 */
public class Santa {
	private HashMap<String,String> personAndFamily = new HashMap<String,String>();
	private HashMap<String,String> giverGivee = new HashMap<String,String>();
	
	/**
	 * 
	 */
	public  Santa() { 
		
	}
	
	/**
	 * Adds a person to nameTranslation and to personAndFamily
	 * @param name
	 */
	public void addPerson(String firstName, String lastName) {
		addPeople(new String[] { lastName+","+firstName } );
	}
	
	/**
	 * Adds everyone in String[] names to nameTranslation and to personAndFamily
	 * @param names
	 */
	public void addPeople(String [] names) {
		for(String name : names) {
			personAndFamily.put(name.substring(name.indexOf(",")+1) , name.substring(0, name.indexOf(",") ) );
		}
	}
	
	/**
	 * Print the results of the random ordering
	 * 
	 * Sample output
	 * 
	 	Scott gives to Ann
		Clare gives to Ivan
		Larry gives to Erik
		Randall gives to Ron
		Maureen gives to Chris
		Ron gives to Scott
		Ann gives to Amanda
		Erik gives to Marilyn
		Chris gives to Maureen
		Amanda gives to Randall
		Ivan gives to Clare
		Marilyn gives to Larry
	 */
	public  void printResults() {
		Iterator <String>iterator = giverGivee.keySet().iterator();
		while(iterator.hasNext()) {
			String giver = iterator.next();
			String givee = giverGivee.get(giver);
			System.out.println(giver + " gives to " + givee );
		}	
	}

	/**
	 * Called to generate the pairings 
	 * 
	 */
	public void getSecrets() {
		// Primary loop controlling the program
		Iterator<String> iterator = personAndFamily.keySet().iterator();
		// For each item in the personAndFamily HashMap do the following
		while(iterator.hasNext()) {
			// Store the person ID in the giver variable
			String giver = iterator.next(); 
			// Call getGivee() and store the result in the givee variable
			String givee = getGivee(giver, personAndFamily.get(giver));
			// Put the giver and givee numbers into the giverGivee HashMap
			giverGivee.put(giver, givee);
		}
	}
	
	/**
	 * Gets a random number between the low value and high value
	 * @param low
	 * @param high
	 * @return
	 */
	private int getRandomNumber(int low, int high) {
		int t = -1;
		do {
			double rand = Math.random();
			// Math.random retuns a decimal value between 0 and 1; we need an 
			// integer so the random decimal is divided by .01 and converted to 
			// an int, truncating the decimal reminader but giving us up to a whole two digit number
			// this is stored in the variable t
			t = (int) (rand/.01);		
			// if the random value is larger than high or smaller than low then do the loop again
		} while(t > high || t < low);
		// return the random number
		return t;
	}
	
	/**
	 * Gets the givee, will only return once the critera of isAlreadySet are not true 
	 * @param giver
	 * @param giverFamily
	 * @return
	 */
	private String getGivee(String giver, String giverFamily) {
		do {
			// Gets a random number and assigns it to givee
			Integer giveeIndex = getRandomNumber(1, personAndFamily.size());
			String givee = (String) personAndFamily.keySet().toArray()[giveeIndex-1];
			// Gets the Family ID of the random givee
			String giveeFam = (String) personAndFamily.values().toArray()[giveeIndex-1];
			// If the givee family does not match the giver family and neither the 
			// givee or giver has already been establish then return the givee
			if(giveeFam != giverFamily && !isAlreadySet( giver, givee )) {
				return givee;
			}
			// keeps relooping until the above conditions are met
		} while( true );
	}

	/**
	 * Checks to see whether the giver or givee has already been set, 
	 * returns true if either of them has been set false if not
	 * 
	 * @param giver
	 * @param givee
	 * @return
	 */
	private boolean isAlreadySet(String giver, String givee) {
		// Used to check for closed loops
		String giveeAsGiver = giverGivee.get(givee);
		
		// if the HashMap giverGivee contains pairing for the variable giver then return true
		if(giverGivee.containsKey(giver ) ) {
			return true;
		}
		// if the HashMap giverGivee contains pairing for the variable givee then return true
		else if (giverGivee.containsValue( givee )) {
			return true;
		}
		// Giver/Givee closed loop
		else if (giveeAsGiver != null && giver == giveeAsGiver) {
			return true;
		}
		// if both the giver and givee have not been paired up then return false. Returning false means this is a
		// valid pairing the the pairing will be added to the giverGivee HashMap
		else 
			return false;
	}
	
	
}
