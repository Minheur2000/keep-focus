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
    }

    // tabs helper
    private TranslationBuilder addFocusTab(String... children) {
        return addTab("focus", children);
    }
}
