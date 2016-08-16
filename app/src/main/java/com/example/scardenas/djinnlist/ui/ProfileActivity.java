package com.example.scardenas.djinnlist.ui;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

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

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends BaseActivity {

    @ViewById(R.id.profile_golden_sun_venus_count) TextView textViewGoldenSunVenusCount;
    @ViewById(R.id.profile_golden_sun_mars_count) TextView textViewGoldenSunMarsCount;
    @ViewById(R.id.profile_golden_sun_jupiter_count) TextView textViewGoldenSunJupiterCount;
    @ViewById(R.id.profile_golden_sun_mercury_count) TextView textViewGoldenSunMercuryCount;

    @ViewById(R.id.profile_lost_age_venus_count) TextView textViewLostAgeVenusCount;
    @ViewById(R.id.profile_lost_age_mars_count) TextView textViewLostAgeMarsCount;
    @ViewById(R.id.profile_lost_age_jupiter_count) TextView textViewLostAgeJupiterCount;
    @ViewById(R.id.profile_lost_age_mercury_count) TextView textViewLostAgeMercuryCount;

    private PreferenceManager preferenceManager;

    private List<Djinni> goldenSunDjinn;
    private List<Djinni> lostAgeDjinn;

    private int goldenSunVenusObtained = 0;
    private int goldenSunMarsObtained = 0;
    private int goldenSunJupiterObtained = 0;
    private int goldenSunMercuryObtained = 0;

    private int lostAgeVenusObtained = 0;
    private int lostAgeMarsObtained = 0;
    private int lostAgeJupiterObtained = 0;
    private int lostAgeMercuryObtained = 0;

    @AfterViews
    public void initialize() {
        preferenceManager = new PreferenceManager(ProfileActivity.this);
        goldenSunDjinn = preferenceManager.getGoldenSunDjinns();
        lostAgeDjinn = preferenceManager.getLostAgeDjinns();
        countGoldenSunDjinn();
        countLostAgeDjinn();
    }

    private void countGoldenSunDjinn() {
        for (Djinni djinni: goldenSunDjinn) {
            if (djinni.isCaught()) {
                switch (djinni.getElement()) {
                    case "Venus":
                        goldenSunVenusObtained++;
                        break;
                    case "Mars":
                        goldenSunMarsObtained++;
                        break;
                    case "Jupiter":
                        goldenSunJupiterObtained++;
                        break;
                    case "Mercury":
                        goldenSunMercuryObtained++;
                        break;
                }
            }
        }
        writeGoldenSunCount();
    }

    private void writeGoldenSunCount() {
        textViewGoldenSunVenusCount.setText(getString(R.string.number, goldenSunVenusObtained));
        textViewGoldenSunMarsCount.setText(getString(R.string.number, goldenSunMarsObtained));
        textViewGoldenSunJupiterCount.setText(getString(R.string.number, goldenSunJupiterObtained));
        textViewGoldenSunMercuryCount.setText(getString(R.string.number, goldenSunMercuryObtained));
    }

    private void countLostAgeDjinn() {
        for (Djinni djinni: lostAgeDjinn) {
            if (djinni.isCaught()) {
                switch (djinni.getElement()) {
                    case "Venus":
                        lostAgeVenusObtained++;
                        break;
                    case "Mars":
                        lostAgeMarsObtained++;
                        break;
                    case "Jupiter":
                        lostAgeJupiterObtained++;
                        break;
                    case "Mercury":
                        lostAgeMercuryObtained++;
                        break;
                }
            }
        }
        writeLostAgeCount();
    }

    private void writeLostAgeCount() {
        textViewLostAgeVenusCount.setText(getString(R.string.number, lostAgeVenusObtained));
        textViewLostAgeMarsCount.setText(getString(R.string.number, lostAgeMarsObtained));
        textViewLostAgeJupiterCount.setText(getString(R.string.number, lostAgeJupiterObtained));
        textViewLostAgeMercuryCount.setText(getString(R.string.number, lostAgeMercuryObtained));
    }

    @Click(R.id.profile_btn_reset_data)
    public void onResetDataClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle(R.string.profile_reset_data_dialog_title);
        builder.setMessage(R.string.profile_reset_data_dialog_message);
        builder.setNegativeButton(R.string.profile_reset_data_dialog_neg_btn, null);
        builder.setPositiveButton(R.string.profile_reset_data_dialog_pos_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prepareGoldenSunDjinns();
                prepareLostAgeDjinns();
                Snackbar.make(textViewGoldenSunVenusCount, R.string.profile_reset_data_snackbar, Snackbar.LENGTH_SHORT).show();
                textViewGoldenSunVenusCount.setText("0");
                textViewGoldenSunMarsCount.setText("0");
                textViewGoldenSunJupiterCount.setText("0");
                textViewGoldenSunMercuryCount.setText("0");
                textViewLostAgeVenusCount.setText("0");
                textViewLostAgeMarsCount.setText("0");
                textViewLostAgeJupiterCount.setText("0");
                textViewLostAgeMercuryCount.setText("0");
            }
        });
        AlertDialog resetDataDialog = builder.create();
        resetDataDialog.show();
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

}
