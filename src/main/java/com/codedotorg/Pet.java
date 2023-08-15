package com.codedotorg;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pet {
    
    /** The name of the pet */
    private String name;

    /** The hunger level of the pet */
    private int hunger;

    /** The happiness level of the pet */
    private int happiness;

    /** The image of the happy pet */
    private String happyImage;

    /** The image of the sad pet */
    private String sadImage;

    /** The image object containing the pet image */
    private Image petImage;

    /** The image view to display the image in the app */
    private ImageView petImageView;

    /**
     * Constructor for creating a Pet object with a given name, happy image, and sad image.
     * Initializes the pet's hunger to 0 and happiness to 100, and sets the pet's image.
     * 
     * @param name the name of the pet
     * @param happyImage the file path of the image to display when the pet is happy
     * @param sadImage the file path of the image to display when the pet is sad
     */
    public Pet(String name, String happyImage, String sadImage) {
        this.name = name;
        this.happyImage = happyImage;
        this.sadImage = sadImage;

        hunger = 0;
        happiness = 100;
        setPetImage();
    }

    /**
     * Returns the name of the pet.
     *
     * @return the name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hunger level of the pet.
     *
     * @return the hunger level of the pet
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Returns the happiness level of the pet.
     *
     * @return the happiness level of the pet.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Returns the ImageView object of the pet.
     *
     * @return the ImageView object of the pet.
     */
    public ImageView getPetImageView() {
        return petImageView;
    }

    /**
     * Decreases the hunger level of the pet by 10 if it is greater than 0.
     */
    public void feed() {
        if (hunger > 0) {
            hunger -= 10;
        }
    }

    /**
     * Increases the hunger level of the pet by 10, if the current hunger level is less than 100.
     */
    public void increaseHunger() {
        if (hunger < 100) {
            hunger += 10;
        }
    }

    /**
     * Increases the happiness level of the pet by 10 if it is less than 100.
     */
    public void play() {
        if (happiness < 100) {
            happiness += 10;
        }
    }

    /**
     * Decreases the happiness of the pet by 10 points if the current happiness is greater than 0.
     */
    public void decreaseHappiness() {
        if (happiness > 0) {
            happiness -= 10;
        }
    }

    /**
     * Updates the image of the pet based on its happiness and hunger levels.
     * If the happiness level is less than 50 or the hunger level is greater than 50,
     * the pet image is set to a sad image. Otherwise, the pet image is set to a happy image.
     */
    public void updateImage() {
        if (happiness < 50 || hunger > 50) {
            petImage = new Image(getClass().getResourceAsStream("/" + sadImage));
        }
        else {
            petImage = new Image(getClass().getResourceAsStream("/" + happyImage));
        }

        petImageView.setImage(petImage);
    }

    /**
     * Sets the pet image by creating a new Image object with the path to the happy image file,
     * creating a new ImageView object with the pet image, and setting the fit width to 300 and
     * preserve ratio to true.
     */
    public void setPetImage() {
        petImage = new Image(getClass().getResourceAsStream("/" + happyImage));
        petImageView = new ImageView(petImage);
        petImageView.setFitWidth(300);
        petImageView.setPreserveRatio(true);
    }

    /**
     * Returns a string representation of the Pet object.
     * The string contains the name of the pet, its hunger level, and its happiness level.
     *
     * @return a string representation of the Pet object
     */
    public String toString() {
        return name + "\nHunger: " + hunger + " | Happiness: " + happiness;
    }

}
