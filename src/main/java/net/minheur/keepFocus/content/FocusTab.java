package net.minheur.keepFocus.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.minheur.potoflux.screen.tabs.BaseVTab;
import net.minheur.potoflux.translations.Translations;
import org.jetbrains.annotations.NotNull;
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

    private StackPane content;
    private VBox taskCard;
    private VBox queryCard;

    private Button start;
    private Button pause;
    private Button stop;

    private Label session;
    private Label task;
    private Label timer;

    @Override
    protected void setPanel() {
        // title
        Label title = new Label(Translations.get("keep_focus:tabs.focus.title"));
        title.getStyleClass().add("focus-title");

        content = new StackPane();
        taskCard = createTaskCard();
        queryCard = createQueryCard();

        vContent.getChildren().addAll(
                title,
                content
        );

        queryMod();
    }

    private @NotNull VBox createQueryCard() {
        Label taskLabel = new Label(Translations.get("keep_focus:tabs.focus.task"));

        TextField taskField = new TextField();
        taskField.setPromptText(Translations.get("keep_focus:tabs.focus.task.prompt"));

        VBox taskBox = new VBox(5, taskLabel, taskField);
        taskBox.setMaxWidth(350);

        // timers
        HBox sessionTimer = new HBox(15);
        sessionTimer.setMaxWidth(350);
        TextField sessionMinutes = new TextField("25");
        sessionTimer.getChildren().addAll(sessionMinutes, new Label(Translations.get("keep_focus:minutes")));

        HBox pauseTimer = new HBox(15);
        pauseTimer.setMaxWidth(350);
        TextField pauseMinutes = new TextField("5");
        pauseTimer.getChildren().addAll(pauseMinutes, new Label(Translations.get("keep_focus:minutes")));

        // session
        Label sessionTitle = new Label(Translations.get("keep_focus:sessionAmount"));
        TextField sessionField = new TextField("4");
        sessionField.setPromptText("4");

        HBox session = new HBox(15, sessionTitle, sessionField);
        session.setMaxWidth(350);

        VBox card = new VBox(20);
        card.getStyleClass().add("card");
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(420);

        // button
        Button save = new Button("Start session");
        save.setOnAction(event -> FocusController.makeSession(
                taskField.getText(),
                sessionMinutes.getText(),
                pauseMinutes.getText(),
                sessionField.getText()
        ));

        card.getChildren().addAll(
                taskBox,
                sessionTimer, pauseTimer,
                session,
                save
        );
        return card;
    }
    private @NotNull VBox createTaskCard() {
        // task
        Label taskLabel = new Label(Translations.get("keep_focus:tabs.focus.task"));
        task = new Label();

        VBox taskBox = new VBox(5, taskLabel, task);
        taskBox.setMaxWidth(350);

        // timer
        timer = new Label("25:00");
        timer.getStyleClass().add("timer");

        // buttons
        start = new Button(Translations.get("keep_focus:tabs.focus.button.start"));
        pause = new Button(Translations.get("keep_focus:tabs.focus.button.pause"));
        stop = new Button(Translations.get("keep_focus:tabs.focus.button.stop"));

        stop.setDisable(true);
        pause.setDisable(true);

        start.setOnAction(event -> FocusController.startSession());
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
        return card;
    }

    public void queryMod() {
        content.getChildren().setAll(queryCard);
    }
    public void taskMod() {
        content.getChildren().setAll(taskCard);
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
        task.setText(content);
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
