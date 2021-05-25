package by.pomidor.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import by.pomidor.helpers.BeanHelper;
import by.pomidor.helpers.Navigator;
import by.pomidor.repositories.CountryRepository;
import by.pomidor.repositories.CountryRepositoryImpl;
import by.pomidor.repositories.UserRepository;
import by.pomidor.services.WatchWorldMapGameServiceImpl;
import by.pomidor.services.WholeWorldMapGameServiceImpl;
import by.pomidor.services.WorldMapGameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {

    private final Navigator navigator = Navigator.getInstance();
    private final CountryRepository countryRepository = BeanHelper.getInstance().getCountryRepository();
    private final UserRepository userRepository = BeanHelper.getInstance().getUserRepository();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startGameButton;

    @FXML
    private Button seeTheMapButton;

    @FXML
    private ImageView imageButtonRulesOfTheGame;

    @FXML
    private ImageView imageButtonCloseApp;

    @FXML
    void seeTheMapButtonOnAction(ActionEvent event) {
        navigator.navigate(new WatchWorldMapGameServiceImpl(), event);
    }

    @FXML
    void startGameButtonOnAction(ActionEvent event) {
        WorldMapGameService gameService = new WholeWorldMapGameServiceImpl(countryRepository, userRepository);
        navigator.navigate(gameService, event);
    }

    @FXML
    void onImageMouseClickedAction(MouseEvent event) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("../exitfromgamemode.fxml"));
            root.lookup("#yesExit").setOnMouseClicked(e -> {
                navigator.navigate("registration.fxml", event);
                navigator.closeCurrentStage(e);
            });
            root.lookup("#noExit").setOnMouseClicked(navigator::closeCurrentStage);
            modalStage.setScene(new Scene(root));
            modalStage.show();
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }

    @FXML
    void rulesImageViewOnMouseClickedAction(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../rules.fxml"));
            root.lookup("#backToMenuImage").setOnMouseClicked(e -> {
                navigator.navigate("menu.fxml", e);
            });
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(new Scene(root));
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert startGameButton != null : "fx:id=\"startGameButton\" was not injected: check your FXML file 'menu.fxml'.";
        assert seeTheMapButton != null : "fx:id=\"seeTheMapButton\" was not injected: check your FXML file 'menu.fxml'.";
        assert imageButtonRulesOfTheGame != null : "fx:id=\"imageButtonRulesOfTheGame\" was not injected: check your FXML file 'menu.fxml'.";
        assert imageButtonCloseApp != null : "fx:id=\"imageButtonCloseApp\" was not injected: check your FXML file 'menu.fxml'.";
    }
}
