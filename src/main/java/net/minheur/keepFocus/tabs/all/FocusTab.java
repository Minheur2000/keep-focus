package net.minheur.keepFocus.tabs.all;

import javafx.scene.layout.VBox;
import net.minheur.potoflux.screen.tabs.BaseVTab;
import net.minheur.potoflux.translations.Translations;

public class FocusTab extends BaseVTab<VBox> {
    @Override
    protected void instantiate() {
        PANEL = new VBox();
    }

    @Override
    protected void setPanel() {
        // add here content
    }

    @Override
    protected String getTitle() {
        return Translations.get("keep_focus:tabs.focus.title");
    }

    @Override
    public String getName() {
        return Translations.get("keep_focus:tabs.focus.name");
    }
}
