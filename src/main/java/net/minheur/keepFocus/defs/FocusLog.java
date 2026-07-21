package net.minheur.keepFocus.defs;

import net.minheur.potoflux.logger.ILogCategory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FocusLog implements ILogCategory {
    FOCUS("keepFocus"),
    SAVES(FOCUS, "saves");

    private final String code;
    private final String[] more;

    FocusLog(String code, String... more) {
        this.code = code;
        this.more = more;
    }

    FocusLog(@NotNull ILogCategory parent, String... more) {
        this.code = parent.code();
        List<String> allMore = new ArrayList<>();
        allMore.addAll(Arrays.stream(parent.more()).toList());
        allMore.addAll(Arrays.stream(more).toList());
        this.more = allMore.toArray(String[]::new);
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String[] more() {
        return more;
    }

}
