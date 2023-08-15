package com.codedotorg;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) {
        PetApp virtualPet = new PetApp(primaryStage, 500, 500);
        virtualPet.startApp("Fido", "Dog");

        // PetSelectionScene petSelectionScene = new PetSelectionScene(primaryStage);
        // petSelectionScene.startApp();
    }

}