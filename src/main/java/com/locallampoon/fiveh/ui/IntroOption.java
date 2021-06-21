package com.locallampoon.fiveh.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum IntroOption {
    NEW(), QUIT();

    private static final List<IntroOption> OPTIONS;

    static {
        OPTIONS = new ArrayList<>();
        // add all options to List
        Collections.addAll(OPTIONS, IntroOption.values());
    }

    public static List<IntroOption> getOptions() {
        return Collections.unmodifiableList(OPTIONS);
    }
}
