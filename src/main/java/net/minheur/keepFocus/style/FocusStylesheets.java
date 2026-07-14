package net.minheur.keepFocus.style;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.potoflux.loader.mod.events.RegisterStylesheetsEvent;
import net.minheur.potoflux.registry.RegistryList;
import net.minheur.potoflux.styles.StylesheetEntry;
import net.minheur.potoflux.utils.SmartSupplier;
import net.minheur.potoflux.utils.ressourcelocation.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FocusStylesheets {
    private static final RegistryList<StylesheetEntry> LIST = new RegistryList<>();

    public static final SmartSupplier<StylesheetEntry> FOCUS_TAB = LIST.add(() -> new StylesheetEntry(
            new ResourceLocation(KeepFocus.MOD_ID, "focusSheet"), buildExternal("/styles/focusTab.css")
    ));

    private static String buildExternal(String target) {
        return Objects.requireNonNull(
                FocusStylesheets.class.getResource(target)
        ).toExternalForm();
    }

    public static void register(@NotNull RegisterStylesheetsEvent event) {
        LIST.register(event.reg);
    }
}
