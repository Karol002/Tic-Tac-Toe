package com.tictactoe.builders.presentation;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ItemsSetter {

    public ChoiceBox<String> setChoiceBox(String [] choices, String startText, double width, double height, double layoutX, double layoutY, boolean visible) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        for (int i=0; i<choices.length; i++) {
            choiceBox.getItems().add(choices[i]);
        }

        choiceBox.setValue(startText);
        choiceBox.setPrefSize(width, height);
        choiceBox.setLayoutX(layoutX); choiceBox.setLayoutY(layoutY);

        choiceBox.setVisible(visible);
        return choiceBox;
    }

    public Label setLabel(String text, double width, double height, double layoutX, double layoutY, double fontSize, boolean visible) {
        Label label = new Label();

        label.setText(text);
        label.setPrefSize(width, height);
        label.setLayoutX(layoutX); label.setLayoutY(layoutY);
        label.setFont(Font.font(fontSize));
        label.setVisible(visible);

        return label;
    }

    public TextField setTextField(double width, double height, double layoutX, double layoutY, int font, boolean visible) {
        TextField textField = new TextField();

        textField.setPrefSize(width, height);
        textField.setLayoutX(layoutX); textField.setLayoutY(layoutY);
        textField.setFont(Font.font(font));
        textField.setVisible(visible);

        return  textField;
    }

    public Button setButton(String text, double width, double height, double layoutX, double layoutY, int font, boolean visible) {
        Button button = new Button();

        button.setText(text);
        button.setPrefSize(width, height);
        button.setLayoutX(layoutX); button.setLayoutY(layoutY);
        button.setVisible(visible);
        button.setFont(Font.font(font));

        return button;
    }
}