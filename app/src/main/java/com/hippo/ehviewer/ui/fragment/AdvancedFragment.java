/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.hippo.ehviewer.AppConfig;
import com.hippo.ehviewer.R;
import com.hippo.util.LogCat;
import com.hippo.util.ReadableTime;

import java.io.File;

public class AdvancedFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private static final String KEY_DUMP_LOGCAT = "dump_logcat";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.advanced_settings);

        Preference dumpLogcat = findPreference(KEY_DUMP_LOGCAT);

        dumpLogcat.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (KEY_DUMP_LOGCAT.equals(key)) {
            boolean ok;
            File file = null;
            File dir = AppConfig.getExternalLogcatDir();
            if (dir != null) {
                file = new File(dir, "logcat-" + ReadableTime.getFilenamableTime(System.currentTimeMillis()) + ".txt");
                ok = LogCat.save(file);
            } else {
                ok = false;
            }
            Resources resources = getResources();
            Toast.makeText(getActivity(),
                    ok ? resources.getString(R.string.settings_advanced_dump_logcat_to, file.getPath()) :
                            resources.getString(R.string.settings_advanced_dump_logcat_failed), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}