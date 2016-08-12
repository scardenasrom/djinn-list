package com.example.scardenas.djinnlist;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.scardenas.djinnlist.data.Djinni;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreferenceManager {

    private static final String PREF_NAME = "general-prefs";
    private static final String IS_FIRST_TIME_LAUNCH_PREF = "isFirstTimeLaunch";
    private static final String GOLDEN_SUN_DJINNS = "goldenSunDjinns";
    private static final String LOST_AGE_DJINNS = "lostAgeDjinns";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    Gson gson;

    public PreferenceManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new Gson();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_PREF, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH_PREF, true);
    }

    public void setGoldenSunDjinns(List<Djinni> djinns) {
        Set<String> djinnsStringSet = new HashSet<>(djinns.size());
        for (Djinni djinni : djinns) {
            String djinnString = gson.toJson(djinni, Djinni.class);
            djinnsStringSet.add(djinnString);
        }
        editor.putStringSet(GOLDEN_SUN_DJINNS, djinnsStringSet);
        editor.apply();
    }

    public List<Djinni> getGoldenSunDjinns() {
        List<Djinni> djinnsList = new ArrayList<>();
        Set<String> djinnsStringSet = pref.getStringSet(GOLDEN_SUN_DJINNS, new HashSet<String>());
        for (String djinnString: djinnsStringSet) {
            Djinni djinni = gson.fromJson(djinnString, Djinni.class);
            djinnsList.add(djinni);
        }
        return djinnsList;
    }

    public void setLostAgeDjinns(List<Djinni> djinns) {
        Set<String> djinnsStringSet = new HashSet<>(djinns.size());
        for (Djinni djinni : djinns) {
            String djinnString = gson.toJson(djinni, Djinni.class);
            djinnsStringSet.add(djinnString);
        }
        editor.putStringSet(LOST_AGE_DJINNS, djinnsStringSet);
        editor.apply();
    }

    public List<Djinni> getLostAgeDjinns() {
        List<Djinni> djinnsList = new ArrayList<>();
        Set<String> djinnsStringSet = pref.getStringSet(LOST_AGE_DJINNS, new HashSet<String>());
        for (String djinnString: djinnsStringSet) {
            Djinni djinni = gson.fromJson(djinnString, Djinni.class);
            djinnsList.add(djinni);
        }
        return djinnsList;
    }

}
