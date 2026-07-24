package net.minheur.keepFocus.content;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FocusTimer {
    private final BiConsumer<String, Double> updateTimerText;

    private Duration totalTime;
    private Duration timeLeft;

    private boolean hasTimer = false;

    private final Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                timeLeft = timeLeft.subtract(Duration.seconds(1));

                updateTimer();

                if (timeLeft.lessThanOrEqualTo(Duration.ZERO)) {
                    timeLeft = Duration.ZERO;
                    updateTimer();
                    end();
                }
            })
    );

    public FocusTimer(BiConsumer<String, Double> updateTimerText) {
        this.updateTimerText = updateTimerText;
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void run(@NotNull Duration duration) {
        hasTimer = true;
        timeLeft = duration;
        totalTime = duration;

        updateTimer();
        timeline.play();
    }

    public void pause() {
        if (!hasTimer) return;
        timeline.pause();
    }
    public void unpause() {
        if (!hasTimer) return;
        timeline.play();
    }

    private void end() {
        hasTimer = false;
        timeline.stop();
        SoundManager.play(SoundManager.PLOC);
        FocusController.sessionEnded();
    }

    public void cancel() {
        hasTimer = false;
        timeline.stop();
    }

    private void updateTimer() {
        double progress = (totalTime.subtract(timeLeft).toSeconds()) / totalTime.toSeconds();

        long totalSeconds = (long) timeLeft.toSeconds();

        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        updateTimerText.accept(String.format("%02d:%02d", minutes, seconds), progress);
    }

    public boolean hasTimer() {
        return hasTimer;
    }
}
