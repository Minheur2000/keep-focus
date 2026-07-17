package net.minheur.keepFocus.content;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import net.minheur.keepFocus.defs.Tabs;
import net.minheur.potoflux.PotoFlux;
import net.minheur.potoflux.translations.Translations;
import net.minheur.potoflux.ui.UiUtils;
import net.minheur.potoflux.utils.SmartSupplier;
import org.jetbrains.annotations.NotNull;

public class FocusController {
    private static final SmartSupplier<FocusTab> focusTab = new SmartSupplier<>(() -> ((FocusTab) PotoFlux.app.getTabMap().get(Tabs.FOCUS.get())));

    public static void makeSession(
            String objective,
            String sessionMinutesString,
            String pauseMinuteString,
            String endPauseString,
            String sessionAmountString
    ) {
        // empty checks
        if (objective.trim().isEmpty()) { // obj empty
            UiUtils.showErrorPane(Translations.get("keep_focus:failedSession.objective"),
                    Translations.get("keep_focus:failedSession"));
            return;
        }

        int sessionMinutes = Integer.parseInt(sessionMinutesString);
        int pauseMinutes = Integer.parseInt(pauseMinuteString);
        int longPauseMinutes = Integer.parseInt(endPauseString);
        int sessionInt = Integer.parseInt(sessionAmountString);

        if (isTimerIncorrect(sessionMinutes)) return;
        if (isTimerIncorrect(pauseMinutes)) return;
        if (isTimerIncorrect(longPauseMinutes)) return;

        if (sessionInt < 1) { // session 1 minimum
            UiUtils.showErrorPane(Translations.get("keep_focus:failedSession.sessionAmount"),
                    Translations.get("keep_focus:failedSession"));
        }

        Duration session = Duration.minutes(sessionMinutes);
        Duration pause = Duration.minutes(pauseMinutes);
        Duration endPause = Duration.minutes(longPauseMinutes);

        FocusSession.actualSession = new FocusSession(sessionInt, objective,
                new FocusTimer(focusTab.get()::updateTimerLabel),
                focusTab.get()::updateObjectiveLabel,
                session, pause, endPause);
        focusTab.get().taskMod();
        focusTab.get().updateObjectiveLabel(objective);
        focusTab.get().updateButtonStates(true, false, false);
        focusTab.get().updateSessionStage(1, sessionInt);
        resetTime();
    }

    public static void startSession() {
        FocusSession session = FocusSession.actualSession;

        focusTab.get().updateButtonStates(false, true, true);

        session.start();

    }
    public static void pauseSession() {
        focusTab.get().updateButtonStates(true, false, null);
        FocusSession.actualSession.timer.pause();
    }
    public static void stopSession() {
        focusTab.get().updateButtonStates(true, false, false);
        FocusSession.actualSession.timer.cancel();

        resetTime();
    }

    public static void sessionEnded() {
        FocusSession.actualSession.updateSessionDone();
        focusTab.get().updateButtonStates(false, false, false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, Translations.get("keep_focus:sessionFinished"), UiUtils.okButton.get());
        show(alert);

        focusTab.get().updateButtonStates(true, false, false);
        focusTab.get().updateSessionStage(FocusSession.actualSession.donePause ?
                FocusSession.actualSession.finishedSessions +1 : FocusSession.actualSession.finishedSessions,
                FocusSession.actualSession.totalSessions);
        focusTab.get().updateObjectiveLabel(FocusSession.actualSession.donePause ?
                FocusSession.actualSession.sessionsObjective :
                Translations.get("keep_focus:pause"));

        resetTime();

        if (FocusSession.actualSession.isFinished()) {
            FocusSession.actualSession = null;
            focusTab.get().updateObjectiveLabel("");
            focusTab.get().queryMod();
        }

    }

    private static void show(@NotNull Alert a) {
        Platform.runLater(() -> {
            if (a.showAndWait().isEmpty()) show(a);
        });
    }

    private static void resetTime() {
        long totalSeconds = ((long) (FocusSession.actualSession.donePause ? FocusSession.actualSession.sessionDuration :
                FocusSession.actualSession.pauseDuration).toSeconds());
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        focusTab.get().updateTimerLabel(String.format("%02d:%02d", minutes, seconds));
    }

    private static boolean isTimerIncorrect(int minutes) {
        if (minutes < 0) { // negative minutes
            UiUtils.showErrorPane(Translations.get("keep_focus:failedSession.duration"),
                    Translations.get("keep_focus:failedSession"));
            return true;
        }
        return false;
    }
}
