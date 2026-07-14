package net.minheur.keepFocus.content;

import javafx.util.Duration;
import net.minheur.potoflux.translations.Translations;

import java.util.function.Consumer;

public class FocusSession {
    public static FocusSession actualSession;

    public int finishedSessions = 0;
    public final int totalSessions;
    public boolean donePause = true;

    public final String sessionsObjective;
    public final Consumer<String> updateObjective;

    public final FocusTimer timer;
    public final Duration sessionDuration = Duration.minutes(25);
    public final Duration pauseDuration = Duration.minutes(5);

    public FocusSession(int totalSessions, String objective, FocusTimer timer, Consumer<String> updateObjectiveText) {
        this.totalSessions = totalSessions;
        this.sessionsObjective = objective;
        this.timer = timer;
        this.updateObjective = updateObjectiveText;
    }

    public boolean isFinished() {
        return finishedSessions >= totalSessions && donePause;
    }

    public void start() {
        if (timer.hasTimer()) timer.unpause();
        else if (donePause) playSession();
        else playPause();
    }

    private void playPause() {
        updateObjective.accept(Translations.get("keep_focus:pause"));
        timer.run(pauseDuration);
    }
    private void playSession() {
        updateObjective.accept(sessionsObjective);
        timer.run(sessionDuration);
    }

    public void updateSessionDone() {
        if (donePause) {
            finishedSessions++;
            donePause = false;
        }
        else donePause = true;
    }
}
