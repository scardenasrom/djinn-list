package com.example.scardenas.djinnlist.ui;

import com.example.scardenas.djinnlist.BaseActivity;
import com.example.scardenas.djinnlist.PreferenceManager;
import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.data.Djinni;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private PreferenceManager preferenceManager;

    @AfterViews
    public void initialize() {
        initialConfiguration();
    }

    private void initialConfiguration() {
        preferenceManager = new PreferenceManager(MainActivity.this);
        if (preferenceManager.isFirstTimeLaunch()) {
            prepareAllData();
        }
    }

    /**
     * Prepares all djinns, and store them in preferences
     */
    private void prepareAllData() {
        preferenceManager.setFirstTimeLaunch(false);
        prepareGoldenSunDjinns();
        prepareLostAgeDjinns();
    }

    private void prepareGoldenSunDjinns() {
        String[] names = getResources().getStringArray(R.array.golden_sun_djinn_names);
        String[] elements = getResources().getStringArray(R.array.golden_sun_djinn_elements);
        String[] flavors = getResources().getStringArray(R.array.golden_sun_djinn_flavors);
        String[] locations = getResources().getStringArray(R.array.golden_sun_djinn_locations);
        String[] catchingDescriptions = getResources().getStringArray(R.array.golden_sun_djinn_catching_descriptions);
        String[] battleEffects = getResources().getStringArray(R.array.golden_sun_djinn_battle_effects);
        String[] setBonus = getResources().getStringArray(R.array.golden_sun_djinn_set_bonus);
        int[] fights = getResources().getIntArray(R.array.golden_sun_djinn_fights);
        List<Djinni> djinnList = new ArrayList<>(28);
        for (int i = 0; i < 28; i++) {
            Djinni djinni = new Djinni();
            djinni.setOrder(i+1);
            djinni.setName(names[i]);
            djinni.setElement(elements[i]);
            djinni.setFlavor(flavors[i]);
            djinni.setLocation(locations[i]);
            djinni.setCatchingDescription(catchingDescriptions[i]);
            djinni.setBattleEffect(battleEffects[i]);
            djinni.setCaught(false);
            djinni.setFights(fights[i]==1);
            djinni.setSetBonus(setBonus[i]);
            djinnList.add(djinni);
        }
        preferenceManager.setGoldenSunDjinns(djinnList);
    }

    private void prepareLostAgeDjinns() {
        String[] names = getResources().getStringArray(R.array.lost_age_djinn_names);
        String[] elements = getResources().getStringArray(R.array.lost_age_djinn_elements);
        String[] flavors = getResources().getStringArray(R.array.lost_age_djinn_flavors);
        String[] locations = getResources().getStringArray(R.array.lost_age_djinn_locations);
        String[] catchingDescriptions = getResources().getStringArray(R.array.lost_age_djinn_catching_descriptions);
        String[] battleEffects = getResources().getStringArray(R.array.lost_age_djinn_battle_effects);
        String[] setBonus = getResources().getStringArray(R.array.lost_age_djinn_set_bonus);
        int[] fights = getResources().getIntArray(R.array.lost_age_djinn_fights);
        List<Djinni> djinnList = new ArrayList<>(44);
        for (int i = 0; i < 44; i++) {
            Djinni djinni = new Djinni();
            djinni.setOrder(i+1);
            djinni.setName(names[i]);
            djinni.setElement(elements[i]);
            djinni.setFlavor(flavors[i]);
            djinni.setLocation(locations[i]);
            djinni.setCatchingDescription(catchingDescriptions[i]);
            djinni.setBattleEffect(battleEffects[i]);
            djinni.setCaught(false);
            djinni.setFights(fights[i]==1);
            djinni.setSetBonus(setBonus[i]);
            djinnList.add(djinni);
        }
        preferenceManager.setLostAgeDjinns(djinnList);
    }

    @Click(R.id.main_image_view_golden_sun)
    public void onClickGoldenSun() {
        DjinnListActivity_.intent(MainActivity.this).game(GOLDEN_SUN).start();
    }

    @Click(R.id.main_image_view_the_lost_age)
    public void onClickTheLostAge() {
        DjinnListActivity_.intent(MainActivity.this).game(LOST_AGE).start();
    }

    @Click(R.id.main_profile_resume_text)
    public void onClickProfileResume() {
        ProfileActivity_.intent(MainActivity.this).start();
    }

}