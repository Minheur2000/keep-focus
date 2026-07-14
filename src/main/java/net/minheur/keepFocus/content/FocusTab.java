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
import org.jetbrains.annotations.Nullable;

import static net.minheur.potoflux.Functions.formatMessage;

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

    private Button start;
    private Button pause;
    private Button stop;

    private Label session;
    private TextField taskField;
    private Label timer;

    @Override
    protected void setPanel() {
        // title
        Label title = new Label("Focus"); // todo
        title.getStyleClass().add("focus-title"); // TODO

        // task
        Label taskLabel = new Label("Task"); // todo

        taskField = new TextField();
        taskField.setPromptText("Enter a task for this session..."); // todo

        VBox taskBox = new VBox(5, taskLabel, taskField);
        taskBox.setMaxWidth(350);

        // timer
        timer = new Label("25:00");
        timer.getStyleClass().add("timer"); // TODO

        // buttons
        start = new Button("▶ Start"); // todo
        pause = new Button("⏸ Pause"); // todo
        stop = new Button("■ Stop"); // todo

        stop.setDisable(true);
        pause.setDisable(true);

        start.setOnAction(event -> FocusController.startSession(taskField.getText()));
        pause.setOnAction(event -> FocusController.pauseSession());
        stop.setOnAction(event -> FocusController.stopSession());

        HBox buttons = new HBox(10, start, pause, stop);
        buttons.setAlignment(Pos.CENTER);

        // session info
        session = new Label(formatMessage(Translations.get("keep_focus:tabs.focus.sessionLabel"), 1, 4));

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

    public void setObjectiveLocked(boolean locked) {
        taskField.setEditable(!locked);
    }
    public void updateSessionStage(int actual, int total) {
        session.setText(formatMessage(Translations.get("keep_focus:tabs.focus.sessionLabel"), actual, total));
    }
    public void updateButtonStates(@Nullable Boolean startEnabled, @Nullable Boolean pauseEnabled, @Nullable Boolean stopEnabled) {
        if (startEnabled != null) start.setDisable(!startEnabled);
        if (pauseEnabled != null) pause.setDisable(!pauseEnabled);
        if (stopEnabled != null) stop.setDisable(!stopEnabled);
    }
    public void updateTimerLabel(String content) {
        timer.setText(content);
    }
    public void updateObjectiveLabel(String content) {
        taskField.setText(content);
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
