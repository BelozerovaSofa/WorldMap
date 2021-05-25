package by.pomidor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RulesController {

    @FXML
    private AnchorPane rulesFullScene;

    @FXML
    private Tab movingAroundTheScreen;

    @FXML
    private ImageView movingAroundTheScreenImage;

    @FXML
    private Tab choosingTheRightAnswer;

    @FXML
    private ImageView choosingTheRightAnswerImage;

    @FXML
    private ImageView backToMenuImage;

    @FXML
    void initialize() {
        assert backToMenuImage != null : "fx:id=\"imageButtonHome\" was not injected: check your FXML file 'sample.fxml'.";

    }

}