package net.minheur.keepFocus.content;

import javafx.scene.control.Alert;
import net.minheur.keepFocus.defs.Tabs;
import net.minheur.potoflux.PotoFlux;
import net.minheur.potoflux.translations.Translations;
import net.minheur.potoflux.ui.UiUtils;
import net.minheur.potoflux.utils.SmartSupplier;
import org.jetbrains.annotations.NotNull;

public class FocusController {
    private static final SmartSupplier<FocusTab> focusTab = new SmartSupplier<>(() -> ((FocusTab) PotoFlux.app.getTabMap().get(Tabs.FOCUS.get())));

    public static void startSession(@NotNull String objective) {
        if (objective.trim().isEmpty()) {
            UiUtils.showErrorPane(Translations.get("keep_focus:noObjective.content"),
                    Translations.get("keep_focus:noObjective.header"));
            return;
        }

        if (FocusSession.actualSession == null || FocusSession.actualSession.isFinished()) {
            FocusSession.actualSession = new FocusSession(4, objective,
                    new FocusTimer(focusTab.get()::updateTimerLabel),
                    focusTab.get()::updateObjectiveLabel);
            resetTime();
        }

        FocusSession session = FocusSession.actualSession;

        focusTab.get().setObjectiveLocked(true);
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

        resetTime();

        if (FocusSession.actualSession.isFinished()) {
            FocusSession.actualSession = null;
            focusTab.get().setObjectiveLocked(false);
            focusTab.get().updateObjectiveLabel("");
        }

    }

    private static void show(@NotNull Alert a) {
        if (a.showAndWait().isEmpty()) show(a);
    }

    private static void resetTime() {
        long totalSeconds = ((long) (FocusSession.actualSession.donePause ? FocusSession.actualSession.sessionDuration :
                FocusSession.actualSession.pauseDuration).toSeconds());
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        focusTab.get().updateTimerLabel(String.format("%02d:%02d", minutes, seconds));
    }
}
