package by.pomidor.facades;

import by.pomidor.domain.Country;
import by.pomidor.domain.svg.SvgGroup;
import by.pomidor.domain.svg.SvgPath;
import by.pomidor.domain.svg.SvgRootElement;
import by.pomidor.helpers.Navigator;
import by.pomidor.helpers.Session;
import by.pomidor.services.WorldMapGameService;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class SvgMapSceneWorker {
    private static final String FONT_FAMILY = "Times New Roman";
    private static final double FONT_SIZE = 30.0;

    private final Navigator navigator;
    private WorldMapGameService gameService;
    private final SvgRootElement svgRootElement;
    private final ScrollPane rootScrollPane;
    private Pane rootPane;
    private Group rootGroup;
    private BorderPane rootBorderPane;
    private final List<Group> countryGroups;
    private final List<SVGPath> countryPaths;
    private Scene mainScene;
    private ImageView backImageView;
    private BorderPane topBorderPane;
    private Label questionLabel;
    private HBox countHBox;
    private Label countLabel;
    private Label totalCountLabel;

    public SvgMapSceneWorker(SvgRootElement svgRootElement, Navigator navigator) {
        this.navigator = navigator;
        this.svgRootElement = svgRootElement;
        this.countryGroups = new LinkedList<>();
        this.countryPaths = new LinkedList<>();
        this.rootGroup = new Group();
        this.rootPane = new Pane(rootGroup);

        initialize();

        rootScrollPane = new ScrollPane(rootPane);
        rootScrollPane.setPannable(true);

        rootBorderPane = new BorderPane(rootScrollPane);
        rootBorderPane.setTop(topBorderPane);
        mainScene = new Scene(rootBorderPane, 862.0, 422.0);
    }

    public void show(Stage stage, WorldMapGameService gameService) {
        this.gameService = gameService;
        stage.setScene(mainScene);
        stage.show();
        start();
    }

    public void start() {
        questionLabel.setText(gameService.nextQuestion());
        countLabel.setText(gameService.count() == null ? null : gameService.count().toString());
        totalCountLabel.setText(gameService.totalCount() == null ? null : gameService.totalCount().toString());
        gameService.start(Session.getInstance().getCurrentUser());
    }

    private void initialize() {
        initializeRootGroup();
        initializeTopBorderPane();
    }

    private void initializeTopBorderPane() {
        topBorderPane = new BorderPane();
        questionLabel = buildLabel("", FONT_FAMILY, FONT_SIZE);
        topBorderPane.setCenter(questionLabel);
        topBorderPane.setLeft(getBackImageView());

        countLabel = buildLabel("", FONT_FAMILY, FONT_SIZE);
        totalCountLabel = buildLabel("", FONT_FAMILY, FONT_SIZE);
        countHBox = new HBox(countLabel, buildLabel(" / ", FONT_FAMILY, FONT_SIZE), totalCountLabel);
        topBorderPane.setRight(countHBox);
    }

    private void initializeRootGroup() {
        for (SvgGroup svgMarkUpGroup : svgRootElement.getGroups()) {
            Group countryGroup = new Group();//достаем g
            for (SvgPath path : svgMarkUpGroup.getPathes()) {//достаем границы стран и островков Path читает xvg
                SVGPath svgPath = createSVGPath(svgMarkUpGroup, countryGroup, path);//класс JavaFX  чтобы островки были
                countryGroup.getChildren().add(svgPath);
                countryPaths.add(svgPath);
            }
            rootGroup.getChildren().add(countryGroup); //чтобы было видно все островки группы (страны)
            countryGroups.add(countryGroup);
        }
    }

    private SVGPath createSVGPath(SvgGroup g, Group group, SvgPath path) {
        SVGPath svgPath = new SVGPath();//создаем SVGPath из JavaFX, переносим свойства из XML в SVGPath
        svgPath.setContent(path.getPathDefinition());
        svgPath.setFill(Paint.valueOf(svgRootElement.getFill()));
        svgPath.setStroke(Paint.valueOf(svgRootElement.getStroke()));
        svgPath.setStrokeLineCap(StrokeLineCap.valueOf(svgRootElement.getStrokeLinecap().toUpperCase()));
        svgPath.setStrokeLineJoin(StrokeLineJoin.valueOf(svgRootElement.getStrokeLinejoin().toUpperCase()));
        svgPath.setStrokeWidth(Double.parseDouble(svgRootElement.getStrokeWidth()));

        svgPath.setOnMouseClicked(e -> svgPathOnMouseClicked(e, g, group, path));
        svgPath.setOnMouseEntered(e -> svgPathOmMouseEntered(g, group));
        svgPath.setOnMouseExited(e -> svgPathOnMouseExited(g, group));
        return svgPath;
    }

    private void svgPathOnMouseClicked(MouseEvent event, SvgGroup g, Group group, SvgPath path) {
        showCountryInfoModalStage(gameService.answer(g.getClazz()), g.getClazz());
        countLabel.setText(gameService.count() == null ? null : gameService.count().toString());
        if (gameService.isEnded()) {
            gameService.end();
            showResultScene(event);
            return;
        }
        String question = gameService.nextQuestion();
        if (question == null) {
            questionLabel.setText(g.getClazz());
            return;
        }
        questionLabel.setText(question);
        System.out.println(g.getClazz() + " is clicked");
    }

    private void svgPathOmMouseEntered(SvgGroup g, Group group) {
        group.getChildren().forEach(node -> ((SVGPath) node).setFill(Paint.valueOf("purple")));
        System.out.println(g.getClazz() + " is intered");
    }

    private void svgPathOnMouseExited(SvgGroup g, Group group) {
        group.getChildren().forEach(node -> ((SVGPath) node).setFill(Paint.valueOf(svgRootElement.getFill())));
        System.out.println(g.getClazz() + " is exeted");
    }

    private void showCountryInfoModalStage(Boolean answer, String message) {
        if (answer == null) {
            return;
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../country-info.fxml"));

            Country country = gameService.getCurrentCountry();

            try (InputStream inputStream = new FileInputStream(country.getFlagImagePath())) {
                ((ImageView) root.getChildrenUnmodifiable().get(0)).setImage(new Image(inputStream));
            }

            ((Label) root.getChildrenUnmodifiable().get(1)).setText("Ваш ответ: " + message);
            Label label = ((Label) root.getChildrenUnmodifiable().get(2));
            if (answer) {
                label.setText("Верно!");
                label.setTextFill(Paint.valueOf("green"));
            } else {
                label.setText("Неверно!");
                label.setTextFill(Paint.valueOf("red"));
            }
            ((TextArea) root.getChildrenUnmodifiable().get(3)).setText(country.getDescription());

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setScene(new Scene(root));
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageView getBackImageView() {
        if (backImageView == null) {
            backImageView = new ImageView("assets/back.png");
            backImageView.setFitHeight(46.0);
            backImageView.setFitWidth(71.0);
            backImageView.setLayoutX(34.0);
            backImageView.setLayoutY(10.0);
            backImageView.setPickOnBounds(true);
            backImageView.setPreserveRatio(true);
            backImageView.setOnMouseClicked(this::end);
        }
        return backImageView;
    }

    private void end(Event event) {
        try {
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("../pause.fxml"));
            root.lookup("#finishTheGameButton").setOnMouseClicked(e -> {
                navigator.navigate("menu.fxml", event);
                navigator.closeCurrentStage(e);
            });
            root.lookup("#continueTheGameButton").setOnMouseClicked(e -> {
                navigator.closeCurrentStage(e);
                gameService.end();
            });
            modalStage.setScene(new Scene(root));
            modalStage.show();
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }

    private void showResultScene(Event event) {
        navigator.navigate("results.fxml", event);
    }

    private static Label buildLabel(String text, String fontFamily, double size) {
        Label label = new Label(text);
        label.setFont(Font.font(fontFamily, size));
        return label;
    }
}
