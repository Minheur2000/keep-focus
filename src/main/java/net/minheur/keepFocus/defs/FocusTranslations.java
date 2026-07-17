package net.minheur.keepFocus.defs;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.potoflux.translations.AbstractTranslationsRegistry;

public class FocusTranslations extends AbstractTranslationsRegistry {
    public FocusTranslations() {
        super(KeepFocus.MOD_ID);
    }

    @Override
    protected void makeTranslation() {
        addFocusTab("name")
                .en("Focus Timer");
        addFocusTab("title")
                .en("Keep Focus");

        addFocusTab("sessionLabel")
                .en("Session $$1 / $$2")
                .fr("Session $$1 / $$2");

        addFocusTab("task")
                .en("Task")
                .fr("Tache");
        addFocusTab("task", "prompt")
                .en("Enter a task for this session...")
                .fr("Entrez une tache pour cette session...");

        addFocusTab("button", "start")
                .en("▶ Start")
                .fr("▶ Démarrer");
        addFocusTab("button", "pause")
                .en("⏸ Pause")
                .fr("⏸ Pause");
        addFocusTab("button", "stop")
                .en("■ Stop")
                .fr("■ Stop");

        add("failedSession")
                .en("Can't start session!")
                .fr("Impossible de démarrer la session !");
        add("failedSession", "objective")
                .en("Please enter an objective for this session.")
                .fr("Veillez entrer un objectif pour cette session.");
        add("failedSession", "sessionAmount")
                .en("Please choose at least 1 session!")
                .fr("Veuillez choisir au moins 1 session");
        add("failedSession", "duration")
                .en("Please enter at least 1 minute!")
                .fr("Veuillez entrer au moins 1 minute !");

        add("sessionFinished")
                .en("Your session if finished!\nStart the next one")
                .fr("Votre session est temriné !\nDémarrez la suivante");
        add("sessionAmount")
                .en("Amount of sessions")
                .fr("Nombre de sessions");

        add("pause")
                .en("Pause (Minutes)")
                .fr("Pause (Minutes)");
        add("session")
                .en("Session (Minutes)")
                .fr("Session (Minutes)");
        add("endPause")
                .en("Long pause (Minutes)")
                .fr("Pause longue (Minutes)");
    }

    // tabs helper
    private TranslationBuilder addFocusTab(String... children) {
        return addTab("focus", children);
    }
}
