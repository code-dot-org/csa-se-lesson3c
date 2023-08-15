package com.codedotorg;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class PetApp {

    /** The main window to display the app */
    private Stage window;

    /** The width of the scene to display in the app */
    private int width;

    /** The height of the scene to display in the app */
    private int height;

    /** The pet for the user to interact with */
    private Pet pet;

    /** The label to display the pet's current stats */
    private Label petInfoLabel;

    /** The prefix for the happy image file name */
    private static final String PET_HAPPY_IMAGE = "happy_";

    /** The prefix for the sad image file name */
    private static final String PET_SAD_IMAGE = "sad_";

    /**
     * This class represents a Pet Application that creates a new Pet object with the given name and petType, 
     * and initializes the happy and sad image prefixes for the Pet object. It also creates a Label object 
     * to display the Pet object's information.
     * 
     * @param window the Stage object to display the Pet object's information
     * @param width the width of the window
     * @param height the height of the window
     * @param name the name of the Pet object
     * @param petType the type of the Pet object
     */
    public PetApp(Stage window, int width, int height, String name, String petType) {
        this.window = window;
        this.width = width;
        this.height = height;

        String happyImagePrefix = getImageFile(petType, "happy");
        String sadImagePrefix = getImageFile(petType, "sad");
        
        pet = new Pet(name, happyImagePrefix, sadImagePrefix);
        petInfoLabel = new Label(pet.toString());
        petInfoLabel.setId("titleLabel");
    }
    
    /**
     * This method starts the Virtual Pet application by setting the primary stage, creating the main layout, 
     * setting and showing the scene, and running the virtual pet.
     * 
     * @param primaryStage the primary stage of the application
     */
    public void startApp(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Virtual Pet");

        VBox mainLayout = createMainLayout();
        setAndShowScene(mainLayout);

        runVirtualPet();
    }

    /**
     * Creates the main layout for the PetApp.
     * This method creates a VBox layout and adds a pet information label, a pet
     * image view, a feed button and a play button to it.
     * 
     * @return the VBox layout containing the pet information label, pet image view, feed button and play button.
     */
    public VBox createMainLayout() {
        VBox tempLayout = new VBox(20);
        tempLayout.setAlignment(Pos.CENTER);

        HBox buttonLayout = createButtonLayout();

        tempLayout.getChildren().addAll(petInfoLabel, pet.getPetImageView(), buttonLayout);

        return tempLayout;
    }

    /**
     * Creates a horizontal layout containing two buttons: a feed button and a play button.
     * 
     * @return the HBox layout containing the buttons
     */
    public HBox createButtonLayout() {
        HBox tempLayout = new HBox(10);
        tempLayout.setAlignment(Pos.CENTER);

        Button feedButton = createFeedButton();
        Button playButton = createPlayButton();

        tempLayout.getChildren().addAll(feedButton, playButton);

        return tempLayout;
    }

    /**
     * Creates a button that feeds the pet when clicked.
     * 
     * @return the created button
     */
    public Button createFeedButton() {
        Button tempButton = new Button("Feed");

        tempButton.setOnAction(event -> {
            pet.feed();
            updatePetInfoLabel();
            pet.updateImage();
        });

        return tempButton;
    }

    /**
     * Creates a button that triggers the play action for the pet.
     * 
     * @return the play button
     */
    public Button createPlayButton() {
        Button tempButton = new Button("Play");

        tempButton.setOnAction(event -> {
            pet.play();
            updatePetInfoLabel();
            pet.updateImage();
        });

        return tempButton;
    }

    /**
     * Sets the current layout as the root of a new scene, sets the scene
     * to the window, and shows the window.
     * 
     * @param currentLayout the layout to be set as the root of the new scene
     */
    public void setAndShowScene(VBox currentLayout) {
        Scene currentScene = new Scene(currentLayout, width, height);
        currentScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        window.setScene(currentScene);
        window.show();
    }

    /**
     * Updates the pet information label with the string representation of the pet object.
     */
    public void updatePetInfoLabel() {
        petInfoLabel.setText(pet.toString());
    }

    /**
     * Runs the virtual pet by creating a timeline that updates the pet's hunger and
     * happiness levels every 5 seconds, updates the pet's information label, and
     * updates the pet's image. The timeline is set to run indefinitely.
     */
    public void runVirtualPet() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            pet.increaseHunger();
            pet.decreaseHappiness();
            updatePetInfoLabel();
            pet.updateImage();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Returns the image file name for a given pet type and status.
     * 
     * @param petType the type of the pet (either "Dog" or "Cat").
     * @param status the status of the pet (either "happy" or anything else).
     * @return the image file name for the given pet type and status.
     */
    public String getImageFile(String petType, String status) {
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
