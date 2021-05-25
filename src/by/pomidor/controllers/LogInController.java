package by.pomidor.controllers;

import by.pomidor.domain.User;
import by.pomidor.exceptions.WorldMapGameException;
import by.pomidor.helpers.BeanHelper;
import by.pomidor.helpers.Navigator;
import by.pomidor.helpers.Session;
import by.pomidor.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LogInController {

    private final UserService userService = BeanHelper.getInstance().getUserService();
    private final Navigator navigator = Navigator.getInstance();
    private String previousPlayerName = "";
    private String previousPlayerCode = "";

    @FXML
    private TextField player_name;

    @FXML
    private Button registration_button;

    @FXML
    private TextField player_cod;

    @FXML
    private void registrationButtonOnAction(ActionEvent event) {
        try {
            userService.register(player_name.getText(), player_cod.getText(), player_cod.getText());
            navigator.navigateToAlert("Вы успешно зарегистрировалсь.");
        } catch (WorldMapGameException e) {
            navigator.navigateToAlert(e.getMessage());
        }
    }

    @FXML
    private void logInButtonOnAction(ActionEvent event) {
        try {
            User user = userService.logIn(player_name.getText(), player_cod.getText());
            Session.getInstance().setCurrentUser(user);
            navigator.navigate("menu.fxml", event);
        } catch (WorldMapGameException e) {
            navigator.navigateToAlert(e.getMessage());
        }
    }

    @FXML
    void playerNameOnKeyTyped(KeyEvent event) {
        char letter = event.getCharacter().toCharArray()[0];
        if (letter != 8 && (!Character.isLetter(letter) || player_name.getText().length() > 12)) {
            player_name.setText(previousPlayerName);
        } else {
            previousPlayerName = player_name.getText();
        }
    }

    @FXML
    void playerCodeOnKeyTyped(KeyEvent event) {
        char letter = event.getCharacter().toCharArray()[0];
        if (letter != 8 && !Character.isLetter(letter)) {
            player_cod.setText(previousPlayerCode);
        } else {
            previousPlayerCode = player_cod.getText();
        }
    }

    @FXML
    void initialize() {
        assert player_name != null : "fx:id=\"player_name\" was not injected: check your FXML file 'registration.fxml'.";
        assert registration_button != null : "fx:id=\"registration_button\" was not injected: check your FXML file 'registration.fxml'.";
        assert player_cod != null : "fx:id=\"player_cod\" was not injected: check your FXML file 'registration.fxml'.";

    }
}

