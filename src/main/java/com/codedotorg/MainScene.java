package com.codedotorg;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainScene extends PetApp {

    /** The pet for the user to interact with */
    private Pet pet;

    /** The label to display the pet's current stats */
    private Label petInfoLabel;

    /** The prefix for the happy image file name */
    private static final String PET_HAPPY_IMAGE = "happy_";

    /** The prefix for the sad image file name */
    private static final String PET_SAD_IMAGE = "sad_";
    
    /**
     * This class represents the main scene of the application. It extends the Scene class
     * and contains a pet object and a pet info label. The constructor initializes the pet
     * object with the given name and pet type, and sets the pet info label with the pet's information.
     * 
     * @param window The stage to which the scene is attached.
     * @param width The width of the scene.
     * @param height The height of the scene.
     * @param name The name of the pet.
     * @param petType The type of the pet.
     */
    public MainScene(Stage window, int width, int height, String name, String petType) {
        super(window, width, height);
        
        String happyImagePrefix = getImageFile(petType, "happy");
        String sadImagePrefix = getImageFile(petType, "sad");
        
        pet = new Pet(name, happyImagePrefix, sadImagePrefix);
        petInfoLabel = new Label(pet.toString());
        petInfoLabel.setId("titleLabel");
    }

    /**
     * Displays the main scene by creating a VBox layout, setting and
     * showing the scene, and running the virtual pet.
     */
    public void showMainScene() {
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
