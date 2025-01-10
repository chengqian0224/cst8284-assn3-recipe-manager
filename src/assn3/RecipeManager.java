/* 
 * File name: RecipeManager.java
 * Author: Cheng Qian, 041167176
 * Course: CST8284 - Object Oriented Programming (Java)
 * Assignment: Assignment 3
 * Date: November 29, 2024
 * Professor: Moshiur Rahman
 * Purpose: This file contains the class for loading recipes from the recipelist.txt file and saves a shopping list.
 */

package assn3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class loads recipes from the recipelist.txt file and saves a shopping list.
 * 
 * @author Cheng Qian
 * @version 1.0
 * @see java.io
 * @see java.util.ArrayList
 * @see java.util.List
 * @see java.util.Scanner
 */
public class RecipeManager {
	
	/**
	 * List containing all recipes loaded from the recipe file
	 */ 
	private List<Recipe> recipes;
	    
	    /**
	     * Constructor initializes empty recipe list
	     */
	    public RecipeManager() {
	        recipes = new ArrayList<>();
	    }
	    
	    /**
	     * Loads recipes from a text file called "recipelist.txt".
	     * Each recipe in the file should start with "Recipe" followed by its name.
	     * Each subsequent line should contain ingredient amounts in the format "ingredient amount".
	     * @throws FileNotFoundException if recipelist.txt is not found
	     * @throws IOException if error occurs while reading file
	     */
	    public void loadRecipes() throws FileNotFoundException, IOException {
	        try (Scanner scanner = new Scanner(new File("recipelist.txt"))) {
	            Recipe currentRecipe = null;
	            
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine().trim();
	                
	                // If a new recipe starts
	                if (line.startsWith("Recipe")) {
	                    // Save previous recipe into ArrayList recipes
	                	if (currentRecipe != null) {
	                        recipes.add(currentRecipe);
	                    }
	                    currentRecipe = new Recipe();
	                    // Extract the line without the beginning "Recipe " to set as Recipe name
	                    currentRecipe.setName(line.substring(7));
	                } else if (!line.isEmpty() && currentRecipe != null) {
	                    // Split String with space
	                	String[] parts = line.split("\\s+");
	                    // Ensure line is split into two parts
	                	if (parts.length == 2) {
	                        // Convert recipe ingredient's quantity to double
	                    	double value = Double.parseDouble(parts[1]);
	                        // Set ingredient's quantity value
	                    	switch (parts[0]) {
		                    	case "eggs": 
	                            	currentRecipe.setEggs(value); 
	                            	break;    
		                    	case "yeast": 
	                            	currentRecipe.setYeast(value); 
	                            	break;
		                    	case "flour": 
	                            	currentRecipe.setFlour(value); 
	                            	break;
		                    	case "sugar": 
		                            	currentRecipe.setSugar(value); 
		                            	break;                     
	                            case "butter": 
	                            	currentRecipe.setButter(value); 
	                            	break;
	                        }
	                    }
	                }
	            }
	            
	            // Save current recipe when recipelist.txt ends
	            if (currentRecipe != null) {
	                recipes.add(currentRecipe);
	            }
	        }
	    }
	    
	    /**
	     * Gets the list of all recipes
	     * @return List of Recipe objects
	     */
	    public List<Recipe> getRecipes() {
	        return recipes;
	    }
	    
	    /**
	     * Saves the shopping list to a file named "shoppinglist.txt".
	     * @param shoppingList String containing the formatted shopping list content to save
	     * @throws IOException if error occurs while writing file
	     */
	    public void saveShoppingList(String shoppingList) throws IOException {
	        try (FileWriter writer = new FileWriter("shoppinglist.txt")) {
	            writer.write(shoppingList);
	        }
	    }

}
