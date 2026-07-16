package net.minheur.keepFocus.content;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    public static final Media PLOC = new Media(SoundManager.class.getResource("/sounds/ploc.wav").toExternalForm());

    public static void play(Media media) {
        MediaPlayer player = new MediaPlayer(media);
        player.setOnEndOfMedia(player::dispose);
        player.play();
    }

}
