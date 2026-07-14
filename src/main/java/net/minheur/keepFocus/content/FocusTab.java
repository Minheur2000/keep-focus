package net.minheur.keepFocus.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.minheur.potoflux.screen.tabs.BaseVTab;
import net.minheur.potoflux.translations.Translations;

public class FocusTab extends BaseVTab<BorderPane> {
    @Override
    protected void instantiate() {
        PANEL = new BorderPane();
        vContent = new VBox(20);
        vContent.setAlignment(Pos.CENTER);
        vContent.setPadding(new Insets(25));

        PANEL.setCenter(vContent);
        vContent.getStyleClass().addAll("focusMod", "tabContent");
    }

    @Override
    protected void setPanel() {
        // title
        Label title = new Label("Focus"); // todo
        title.getStyleClass().add("focus-title"); // TODO

        // task
        Label taskLabel = new Label("Task"); // todo

        TextField taskField = new TextField();
        taskField.setPromptText("Enter a task for this session..."); // todo

        VBox taskBox = new VBox(5, taskLabel, taskField);
        taskBox.setMaxWidth(350);

        // timer
        Label timer = new Label("25:00");
        timer.getStyleClass().add("timer"); // TODO

        // buttons
        Button start = new Button("▶ Start"); // todo
        Button pause = new Button("⏸ Pause"); // todo
        Button stop = new Button("■ Stop"); // todo

        HBox buttons = new HBox(10, start, pause, stop);
        buttons.setAlignment(Pos.CENTER);

        // session info
        Label session = new Label("Session 1 / 4"); // todo

        VBox card = new VBox(20);
        card.getStyleClass().add("card");
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(420);

        card.getChildren().addAll(
                taskBox,
                timer,
                buttons,
                session
        );

        vContent.getChildren().addAll(
                title,
                card
        );
    }

    @Override
    protected String getTitle() {
        return Translations.get("keep_focus:tabs.focus.title");
    }

    @Override
    public String getName() {
        return Translations.get("keep_focus:tabs.focus.name");
    }

    @Override
    protected boolean doPreset() {
        return false;
    }
}
