/* 
 * File name: Recipe.java
 * Author: Cheng Qian, 041167176
 * Course: CST8284 - Object Oriented Programming (Java)
 * Assignment: Assignment 3
 * Date: November 29, 2024
 * Professor: Moshiur Rahman
 * Purpose: This file contains the basic elements of the recipe.
 */

package assn3;

/**
 * This class contains the basic elements of the recipe.
 * 
 * @author Cheng Qian
 * @version 1.0
 */
public class Recipe {
	
	/**
	 * The name of the bread recipe
	 */
	private String name;
	
	/**
	 * Number of eggs needed for this recipe
	 */
	private double eggs;
	
	/**
	 * Amount of yeast needed in grams
	 */
	private double yeast;
	
	/**
	 * Amount of flour needed in grams
	 */
	private double flour;
	
	/**
	 * Amount of sugar needed in grams
	 */
	private double sugar;
	
	/**
	 * Amount of butter needed in grams
	 */
    private double butter;
    
    /**
     * Number of loaves ordered of this recipe
     */
    private int quantityOrdered;
    
    /**
     * Default constructor for class Recipe
     */
    public Recipe() {
        this.quantityOrdered = 0;
    }

    /**
     * Gets the recipe name
     * @return the name of the recipe
     */
	public String getName() {
		return name;
	}

	/**
	 * Sets the recipe name
	 * @param name the recipe name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the number of eggs needed
	 * @return the number of eggs
	 */
	public double getEggs() {
		return eggs;
	}

	/**
	 * Sets the number of eggs needed
	 * @param eggs the number of eggs to set
	 */
	public void setEggs(double eggs) {
		this.eggs = eggs;
	}

	/**
	 * Gets the amount of yeast needed in grams
	 * @return the amount of yeast in grams
	 */
	public double getYeast() {
		return yeast;
	}

	/**
	 * Sets the amount of yeast needed in grams
	 * @param yeast the amount of yeast to set
	 */
	public void setYeast(double yeast) {
		this.yeast = yeast;
	}

	/**
	 * Gets the amount of flour needed in grams
	 * @return the amount of flour in grams
	 */
	public double getFlour() {
		return flour;
	}

	/**
	 * Sets the amount of flour needed in grams
	 * @param flour the amount of flour to set
	 */
	public void setFlour(double flour) {
		this.flour = flour;
	}

	/**
	 * Gets the amount of sugar needed in grams
	 * @return the amount of sugar in grams
	 */
	public double getSugar() {
		return sugar;
	}

	/**
	 * Sets the amount of sugar needed in grams
	 * @param sugar the amount of sugar to set
	 */
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	/**
	 * Gets the amount of butter needed in grams
	 * @return the amount of butter in grams
	 */
	public double getButter() {
		return butter;
	}

	/**
	 * Sets the amount of butter needed in grams
	 * @param butter the amount of butter to set
	 */
	public void setButter(double butter) {
		this.butter = butter;
	}

	/**
	 * Gets the number of loaves ordered of this recipe
	 * @return the quantity ordered
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * Sets the number of loaves ordered of this recipe
	 * @param quantityOrdered the quantity to order
	 */
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

}