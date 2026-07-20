package net.minheur.keepFocus.save;

import net.minheur.keepFocus.KeepFocus;

import java.nio.file.Path;

public class SessionSavingHandler {

    private static final Path savedSessionsList = KeepFocus.getModDir().resolve("savedSessions.json");
    private static final Path savedSessionsDir = KeepFocus.getModDir().resolve("savedSessions");

    public static void load() {
    }
    public static void save() {
    }

}
