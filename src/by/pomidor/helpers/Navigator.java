package by.pomidor.helpers;

import by.pomidor.domain.svg.SvgRootElement;
import by.pomidor.facades.SvgMapSceneWorker;
import by.pomidor.repositories.SvgMapRepositoryImpl;
import by.pomidor.services.WorldMapGameService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private static Navigator INSTANCE;
    private final SvgMapSceneWorker svgMapSceneWorker;
    private final Map<String, Scene> scenes;
    private final Alert warningAlert;

    public static Navigator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Navigator();
        }
        return INSTANCE;
    }

    private Navigator() {
        scenes = new HashMap<>();
        SvgRootElement element = new SvgMapRepositoryImpl().getSvgRootElement("resources/world1.svg");
        svgMapSceneWorker = new SvgMapSceneWorker(element, this);

        warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("WorldMapGame");
    }

    public void navigate(WorldMapGameService gameService, ActionEvent event) {
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        svgMapSceneWorker.show(appStage, gameService);
    }

    public void navigate(String path, Event event) {
        try {
            Scene scene = scenes.get(path);
            if (scene == null) {
                Parent root = FXMLLoader.load(getClass().getResource("../" + path));
                scene = new Scene(root);
                scenes.put(path, scene);
            }
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
        } catch (IOException e) {
            //TODO Logger
            e.printStackTrace();
        }
    }

    public void navigateToAlert(String message) {
        warningAlert.setHeaderText(message);
        warningAlert.showAndWait();
    }

    public void navigateToModal(String path, Event event) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("../" + path));
            modalStage.setScene(new Scene(root));
            modalStage.show();
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }

    public void closeCurrentStage(Event event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
