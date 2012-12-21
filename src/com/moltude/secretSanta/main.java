
/**
 * Written because I didn't want to do 
 * the matching for our secret santa by hand, 
 * this just seemed easier...albiet a bit stupid. 
 */

package com.moltude.secretSanta;


public class main {

	public static void main(String[] args) {
		/**
		 * Set all the person family values  		
		 */
		
		Santa santa = new Santa();
		santa.addPerson("Scott", "Williams");
		santa.addPerson("Randall", "Williams");
		santa.addPerson("Clare", "Williams");
		santa.addPerson("Larry", "Williams");
		santa.addPerson("Ron", "Ashman");
		santa.addPerson("Eric", "Ashman");
		santa.addPerson("Chris", "Ashman");
		santa.addPerson("Doc", "Brown");
		santa.addPerson("Howard", "Brown");
		santa.addPerson("Ralph", "Brown");
		santa.getSecrets(); 
		santa.printResults();				
	}
	
	

}
