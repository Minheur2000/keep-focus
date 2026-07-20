package net.minheur.keepFocus.defs;

import net.minheur.keepFocus.KeepFocus;
import net.minheur.keepFocus.save.SessionSavingHandler;
import net.minheur.potoflux.actionRuns.regs.ActionRun;
import net.minheur.potoflux.loader.mod.events.RegisterRunsEvent;
import net.minheur.potoflux.registry.RegistryList;
import net.minheur.potoflux.utils.SmartSupplier;
import net.minheur.potoflux.utils.ressourcelocation.ResourceLocation;

public class Runs {

    private static final RegistryList<ActionRun> LIST_CLOSE = new RegistryList<>();
    private static final RegistryList<ActionRun> LIST_START_LOGIC = new RegistryList<>();

    public static final SmartSupplier<ActionRun> LOAD_TIMERS = LIST_START_LOGIC.add(() -> new ActionRun(
            new ResourceLocation(KeepFocus.MOD_ID, "load"), SessionSavingHandler::load
    ));
    public static final SmartSupplier<ActionRun> SAVE_TIMERS = LIST_CLOSE.add(() -> new ActionRun(
            new ResourceLocation(KeepFocus.MOD_ID, "save"), SessionSavingHandler::save
    ));

    public static void register(RegisterRunsEvent event) {
        LIST_CLOSE.register(event.closeReg);
        LIST_START_LOGIC.register(event.startLogicReg);
    }

}
