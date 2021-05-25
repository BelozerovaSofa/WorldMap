package by.pomidor.controllers;

import by.pomidor.domain.User;
import by.pomidor.helpers.BeanHelper;
import by.pomidor.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Comparator;
import java.util.List;

public class ResultsController {
    private static final String FONT_FAMILY = "Times New Roman";
    private static final double FONT_SIZE = 30.0;
    private static final String FONT_COLOR = "Green";
    private static final String TOP_MESSAGE = "Игрок №";

    private final UserRepository userRepository = BeanHelper.getInstance().getUserRepository();
    @FXML
    private GridPane resultGridPane;

    @FXML
    void initialize() {
        List<User> topUsers = userRepository
                .findLimitedAndSortedUsers(Comparator.comparing(User::getMaxCount), 5);

        int index = 0;
        for (User user : topUsers) {
            resultGridPane.addRow(index, buildLabel(TOP_MESSAGE + (index + 1)),
                    buildLabel(user.getLogin()), buildLabel(user.getMaxCount().toString()));
            index++;
        }
    }

    private static Label buildLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(FONT_FAMILY, FONT_SIZE));
        label.setTextFill(Paint.valueOf(FONT_COLOR));
        return label;
    }
}
