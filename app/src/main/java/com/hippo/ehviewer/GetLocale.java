package com.hippo.ehviewer;

import android.content.res.Resources;

import java.util.Locale;

public class GetLocale {
    public static Locale getLocale() {
        Locale locale = null;
        String language = Settings.getAppLanguage();
        if (language != null && !language.equals("system")) {
            String[] split = language.split("-");
            if (split.length == 1) {
                locale = new Locale(split[0]);
            } else if (split.length == 2) {
                locale = new Locale(split[0], split[1]);
            } else if (split.length == 3) {
                locale = new Locale(split[0], split[1], split[2]);
            }
        }
        if (locale == null) {
            locale = Resources.getSystem().getConfiguration().locale;
        }

        return locale;
    }
}
