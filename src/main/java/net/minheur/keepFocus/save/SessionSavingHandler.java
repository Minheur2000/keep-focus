package net.minheur.keepFocus.save;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.keepFocus.defs.FocusLog;
import net.minheur.potoflux.Functions;
import net.minheur.potoflux.logger.PtfLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SessionSavingHandler {

    private static final Path savedSessionsList = KeepFocus.getModDir().resolve("savedSessions.json");
    private static final Path savedSessionsDir = KeepFocus.getModDir().resolve("savedSessions");

    public static void load() {
        if (!Files.exists(savedSessionsDir)) return; // todo: empty list
        String content;
        try {
            content = Files.readString(savedSessionsList);
        } catch (IOException e) {
            e.printStackTrace();
            PtfLogger.error("Failed to read savedSessionsList.json!", FocusLog.SAVES);
            return; // todo: empty list
        }

    }
    public static void save() {
    }

}
