/* 
 * File name: RecipeManagerTest.java
 * Author: Cheng Qian, 041167176
 * Course: CST8284 - Object Oriented Programming (Java)
 * Assignment: Assignment 3
 * Date: November 29, 2024
 * Professor: Moshiur Rahman
 * Purpose: This file contains the driver class for the Recipe Manager program.
 */

package assn3;

import java.util.List;
import java.util.Scanner;

/**
 * Driver class for the Recipe Manager program.
 * Handles user interaction and displays information about recipes and orders.
 * 
 * @author Cheng Qian
 * @version 1.0
 * @see java.util.List
 * @see java.util.Scanner
 */
public class RecipeManagerTest {
	
	/**
	 * RecipeManager instance for managing recipes and shopping lists
	 */
	private static RecipeManager manager;
	
    /**
     * Scanner object for reading user input from console
     */
    private static Scanner scanner;

    /**
     * The main method that starts the program.
     * Initializes the RecipeManager and Scanner, loads recipes, and starts the main program loop.
     * 
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        manager = new RecipeManager();
        scanner = new Scanner(System.in);
        
        try {
            manager.loadRecipes();
            runProgram();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Runs the main program loop.
     * Displays the menu and processes user input until the user chooses to quit.
     */
    private static void runProgram() {
        boolean running = true;
        
        System.out.println("Welcome to Cheng Qian recipe manager.");
        
        
        while (running) {
        	printMenu();
        	System.out.print("Please enter your choice: ");
            
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
                
                switch (choice) {
                	// Reprint this menu.
                	case 0:
                    	printMenu();
                        break;
                    // Show available recipes.
                    case 1:
                    	showRecipes();
                        break;
                    // Create Shopping List.
                    case 2:
                        List<Recipe> recipes = manager.getRecipes();
                        boolean validBreadNumber = false;
                        boolean validQuantity = false;
                        int breadNumber = 0;

                        // Validate breadNumber.
                        while (!validBreadNumber) {
                            try {
                                System.out.print("Which bread would you like? ");
                                breadNumber = Integer.parseInt(scanner.nextLine());

                                if (breadNumber <= 0 || breadNumber > recipes.size()) {
                                    throw new IllegalArgumentException(
                                        "Invalid bread number. Please choose between 1 and " + recipes.size() + ".");
                                }
                                validBreadNumber = true;
                                
                            } catch (NumberFormatException e) {
                                System.out.printf("Please only type digits.\nValid input are digits from 1 to %d.%n", recipes.size());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        // Validate quantity.
                        while (!validQuantity) {
                            try {
                                System.out.print("How much of this bread would you like? ");
                                int quantity = Integer.parseInt(scanner.nextLine());
                                
                                createOrder(breadNumber, quantity);
                                validQuantity = true;
                                
                            } catch (NumberFormatException e) {
                                System.out.println("Please only type digits.");
                            }
                        }
                        break;
                    // Print Shopping List.
                    case 3:
                        printShoppingList();
                        break;
                    // Quit Program.
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Valid input are digits from 0 to 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only type digits.\nValid input are digits from 0 to 4.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }
    
    /**
     * Displays all available bread recipes with their numbers.
     * The recipes are numbered from 1 to n, where n is the total number of recipes.
     */
    private static void showRecipes() {
        System.out.println("Available Recipes:");
        List<Recipe> recipes = manager.getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, recipes.get(i).getName());
        }
    }
    
    /**
     * Creates an order for a specific bread type and quantity.
     * 
     * @param breadNumber the bread number
     * @param quantity the quantity to order
     */
    private static void createOrder(int breadNumber, int quantity) {
        try {
            // Get the recipe list
            List<Recipe> recipes = manager.getRecipes();
            
            Recipe selectedRecipe = recipes.get(breadNumber - 1);
            int currentQuantity = selectedRecipe.getQuantityOrdered();
            int newQuantity = currentQuantity + quantity;

            // Validate to ensure total quantity is not negative
            if (newQuantity < 0) {
                System.out.printf("Current quantity is %d, total quantity cannot be negative.%n", currentQuantity);
                return;
            }
            // Update the order quantity in the recipe
            else {
            	selectedRecipe.setQuantityOrdered(newQuantity);
            }
        } catch (Exception e) {
            System.out.println("Error processing order: " + e.getMessage());
        }
    }
    
    /**
     * Displays the shopping list and offers to save it to a file.
     * If user confirms, saves the list to shoppinglist.txt.
     * Only shows ingredients that are needed (quantity > 0).
     */
    private static void printShoppingList() {
        String shoppingList = generateShoppingList();
        System.out.println(shoppingList);
        
        System.out.print("Do you want to save this list (Y/n)? ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("Y")) {
            try {
                manager.saveShoppingList(shoppingList);
            } catch (Exception e) {
                System.out.println("Error saving shopping list: " + e.getMessage());
            }
        }
    }
    
    /**
     * Generates shopping list text based on ordered recipes.
     * Calculates total ingredients needed for all ordered recipes
     * and formats them into a readable string.
     * Only includes recipes with order quantity greater than 0.
     * @return Formatted shopping list string containing ordered items and total ingredients needed
     */
    private static String generateShoppingList() {
        StringBuilder sb = new StringBuilder();
        double totalYeast = 0, totalFlour = 0, totalSugar = 0, totalEggs = 0, totalButter = 0;

        // Get recipes from manager
        List<Recipe> recipes = manager.getRecipes();

        // Loop recipes
        for (Recipe recipe : recipes) {
            int qty = recipe.getQuantityOrdered();
            if (qty > 0) {
                sb.append(qty).append(" ").append(recipe.getName())
                  .append(" loaf/loaves.\n");

                totalYeast += recipe.getYeast() * qty;
                totalFlour += recipe.getFlour() * qty;
                totalSugar += recipe.getSugar() * qty;
                totalEggs += recipe.getEggs() * qty;
                totalButter += recipe.getButter() * qty;
            }
        }

        sb.append("\nYou will need a total of:\n");
        if (totalYeast > 0) sb.append(String.format("%.0f grams of yeast\n", totalYeast));
        if (totalFlour > 0) sb.append(String.format("%.0f grams of flour\n", totalFlour));
        if (totalSugar > 0) sb.append(String.format("%.0f grams of sugar\n", totalSugar));
        if (totalEggs > 0) sb.append(String.format("%.0f egg(s)\n", totalEggs));
        if (totalButter > 0) sb.append(String.format("%.0f grams of butter\n", totalButter));

        return sb.toString();
    }
}