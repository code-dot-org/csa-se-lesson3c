package com.codedotorg;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PetApp {

    /** The main window to display the app */
    private Stage window;

    /** The width of the scene to display in the app */
    private int width;

    /** The height of the scene to display in the app */
    private int height;

    /**
     * Constructs a new PetApp object with the specified window, width, and height.
     *
     * @param window the Stage object representing the application window
     * @param width the width of the application window
     * @param height the height of the application window
     */
    public PetApp(Stage window, int width, int height) {
        this.window = window;
        this.width = width;
        this.height = height;

        window.setTitle("Virtual Pet");
    }

    /**
     * Returns the window of the PetApp.
     *
     * @return the window of the PetApp
     */
    public Stage getWindow() {
        return window;
    }

    /**
     * Returns the width of the pet.
     *
     * @return the width of the pet
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the pet.
     *
     * @return the height of the pet
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Starts the pet application with the given name and pet type.
     * Creates a new MainScene object with the given parameters and shows the main scene.
     *
     * @param name the name of the pet owner
     * @param petType the type of pet
     */
    public void startApp(String name, String petType) {
        MainScene mainScene = new MainScene(window, width, height, name, petType);
        mainScene.showMainScene();
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

}
