package com.codedotorg;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PetApp {

    private Stage window;
    private Pet pet;

    private static final String PET_HAPPY_IMAGE = "happy_";
    private static final String PET_SAD_IMAGE = "sad_";
    
    private Button feedButton;
    private Button playButton;
    private Label petInfoLabel;

    public PetApp(String name, String petType) {
        String happyImagePrefix = getImageFile(petType, "happy");
        String sadImagePrefix = getImageFile(petType, "sad");

        feedButton = new Button("Feed");
        playButton = new Button("Play");
        
        pet = new Pet(name, happyImagePrefix, sadImagePrefix);
        petInfoLabel = new Label(pet.toString());
    }
    
    public void startApp(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Virtual Pet");

        setFeedButtonAction();
        setPlayButtonAction();

        VBox vbox = new VBox(petInfoLabel, pet.getPetImageView(), feedButton, playButton);
        Scene scene = new Scene(vbox, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

        runVirtualPet();
    }

    private void setFeedButtonAction() {
        feedButton.setOnAction(event -> {
            pet.feed();
            updatePetInfoLabel();
            pet.updateImage();
        });
    }

    private void setPlayButtonAction() {
        playButton.setOnAction(event -> {
            pet.play();
            updatePetInfoLabel();
            pet.updateImage();
        });
    }

    private void updatePetInfoLabel() {
        petInfoLabel.setText(pet.toString());
    }

    private void runVirtualPet() {
        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(5),
                event -> {
                    pet.increaseHunger();
                    pet.decreaseHappiness();
                    updatePetInfoLabel();
                    pet.updateImage();
                }
            )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String getImageFile(String petType, String status) {
        String result = "";

        if (status.equals("happy")) {
            result += PET_HAPPY_IMAGE;

            if (petType.equals("Dog")) {
                result += "dog.png";
            }
            else {
                result += "cat.png";
            }
        }
        else {
            result += PET_SAD_IMAGE;

            if (petType.equals("Dog")) {
                result += "dog.png";
            }
            else {
                result += "cat.png";
            }
        }

        return result;
    }

}
