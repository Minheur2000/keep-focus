package net.minheur.keepFocus.defs;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.potoflux.loader.mod.events.RegisterTabsEvent;
import net.minheur.potoflux.registry.RegistryList;
import net.minheur.potoflux.screen.tabs.Tab;
import net.minheur.potoflux.utils.SmartSupplier;
import net.minheur.potoflux.utils.ressourcelocation.ResourceLocation;
import net.minheur.keepFocus.content.FocusTab;

public class Tabs {
    private static final RegistryList<Tab> LIST = new RegistryList<>();

    // example tab
    public static final SmartSupplier<Tab> FOCUS = LIST.add(() -> new Tab(new ResourceLocation(KeepFocus.MOD_ID, "focus"), FocusTab.class));

    public static void register(RegisterTabsEvent event) {
        LIST.register(event.reg);
    }
}
