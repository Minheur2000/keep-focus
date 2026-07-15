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
    public final Duration sessionDuration;
    public final Duration pauseDuration;

    public FocusSession(int totalSessions, String objective, FocusTimer timer, Consumer<String> updateObjectiveText,
                        Duration sessionDuration, Duration pauseDuration) {
        this.totalSessions = totalSessions;
        this.sessionsObjective = objective;
        this.timer = timer;
        this.updateObjective = updateObjectiveText;
        this.sessionDuration = sessionDuration;
        this.pauseDuration = pauseDuration;
    }

    public boolean isFinished() {
        return finishedSessions >= totalSessions && donePause;
    }

    public void start() {
        if (timer.hasTimer()) timer.unpause();
        else if (donePause) timer.run(sessionDuration);
        else timer.run(pauseDuration);
    }

    public void updateSessionDone() {
        if (donePause) {
            finishedSessions++;
            donePause = false;
        }
        else donePause = true;
    }
}
