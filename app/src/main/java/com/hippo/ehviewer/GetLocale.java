package com.hippo.ehviewer;

import android.content.res.Resources;

import java.util.Locale;

public class GetLocale {
    public static Locale getLocale() {
        Locale locale = null;
        String language = Settings.getAppLanguage();
        if (language != null && !language.equals("system")) {
            String[] split = language.split("-");
            switch (split.length) {
                case 1:
                    locale = new Locale(split[0]);
                    break;
                case 2:
                    locale = new Locale(split[0], split[1]);
                    break;
                case 3:
                    locale = new Locale(split[0], split[1], split[2]);
                    break;
            }
        }
        if (locale == null) {
            locale = Resources.getSystem().getConfiguration().locale;
        }

        return locale;
    }
}
