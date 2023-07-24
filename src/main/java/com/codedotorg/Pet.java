package com.codedotorg;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pet {
    
    private String name;
    private int hunger;
    private int happiness;

    private static final String IMAGE_PATH = "file:src\\main\\java\\com\\codedotorg\\";

    private String happyImage;
    private String sadImage;
    private Image petImage;
    private ImageView petImageView;

    public Pet(String name, String happyImage, String sadImage) {
        this.name = name;
        this.happyImage = happyImage;
        this.sadImage = sadImage;
        this.hunger = 0;
        this.happiness = 100;
        setPetImage();
    }

    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public ImageView getPetImageView() {
        return petImageView;
    }

    public void feed() {
        if (hunger > 0) {
            this.hunger -= 10;
        }
    }

    public void increaseHunger() {
        if (hunger < 100) {
            this.hunger += 10;
        }
    }

    public void play() {
        if (happiness < 100) {
            this.happiness += 10;
        }
    }

    public void decreaseHappiness() {
        if (happiness > 0) {
            this.happiness -= 10;
        }
    }

    public void updateImage() {
        if (happiness < 50 || hunger > 50) {
            petImage = new Image(IMAGE_PATH + sadImage);
        }
        else {
            petImage = new Image(IMAGE_PATH + happyImage);
        }

        petImageView.setImage(petImage);
    }

    private void setPetImage() {
        this.petImage = new Image(IMAGE_PATH + happyImage);
        this.petImageView = new ImageView(petImage);
        petImageView.setFitWidth(300);
        petImageView.setPreserveRatio(true);
    }

    public String toString() {
        return name + "\nHunger: " + hunger + " | Happiness: " + happiness;
    }

}
