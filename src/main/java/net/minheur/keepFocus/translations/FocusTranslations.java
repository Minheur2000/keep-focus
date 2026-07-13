package net.minheur.keepFocus.translations;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.potoflux.translations.AbstractTranslationsRegistry;

public class FocusTranslations extends AbstractTranslationsRegistry {
    public FocusTranslations() {
        super(KeepFocus.MOD_ID);
    }

    @Override
    protected void makeTranslation() {
        addYourTab("name")
                .en("Focus Timer");
        addYourTab("title")
                .en("Keep Focus");
    }

    // tabs helper
    private TranslationBuilder addYourTab(String... children) {
        return addTab("focus", children);
    }
}
