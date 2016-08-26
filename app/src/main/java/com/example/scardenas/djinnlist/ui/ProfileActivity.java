package com.example.scardenas.djinnlist.ui;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scardenas.djinnlist.BaseActivity;
import com.example.scardenas.djinnlist.PreferenceManager;
import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.adapter.DjinnAdapter;
import com.example.scardenas.djinnlist.data.Djinni;
import com.example.scardenas.djinnlist.widget.DjinniView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends BaseActivity {

    @ViewById(R.id.profile_layout_summarize) LinearLayout layoutSumaraize;
    @ViewById(R.id.profile_djinn_list_layout) RelativeLayout layoutDjinnList;
    @ViewById(R.id.profile_djinn_list_recycler_view) RecyclerView recyclerViewDjinnList;

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
                    case VENUS:
                        goldenSunVenusObtained++;
                        break;
                    case MARS:
                        goldenSunMarsObtained++;
                        break;
                    case JUPITER:
                        goldenSunJupiterObtained++;
                        break;
                    case MERCURY:
                        goldenSunMercuryObtained++;
                        break;
                }
            }
        }
        writeGoldenSunCount();
    }

    private void writeGoldenSunCount() {
        textViewGoldenSunVenusCount.setText(getString(R.string.profile_golden_sun_count, goldenSunVenusObtained));
        if (goldenSunVenusObtained == 7) {
            textViewGoldenSunVenusCount.setTextColor(getResources().getColor(R.color.color_accent));
        }
        else {
            textViewGoldenSunVenusCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewGoldenSunMarsCount.setText(getString(R.string.profile_golden_sun_count, goldenSunMarsObtained));
        if (goldenSunMarsObtained == 7) {
            textViewGoldenSunMarsCount.setTextColor(getResources().getColor(R.color.color_accent));
        } else {
            textViewGoldenSunMarsCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewGoldenSunJupiterCount.setText(getString(R.string.profile_golden_sun_count, goldenSunJupiterObtained));
        if (goldenSunJupiterObtained == 7) {
            textViewGoldenSunJupiterCount.setTextColor(getResources().getColor(R.color.color_accent));
        } else {
            textViewGoldenSunJupiterCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewGoldenSunMercuryCount.setText(getString(R.string.profile_golden_sun_count, goldenSunMercuryObtained));
        if (goldenSunMercuryObtained == 7) {
            textViewGoldenSunMercuryCount.setTextColor(getResources().getColor(R.color.color_accent));
        } else {
            textViewGoldenSunMercuryCount.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void countLostAgeDjinn() {
        for (Djinni djinni: lostAgeDjinn) {
            if (djinni.isCaught()) {
                switch (djinni.getElement()) {
                    case VENUS:
                        lostAgeVenusObtained++;
                        break;
                    case MARS:
                        lostAgeMarsObtained++;
                        break;
                    case JUPITER:
                        lostAgeJupiterObtained++;
                        break;
                    case MERCURY:
                        lostAgeMercuryObtained++;
                        break;
                }
            }
        }
        writeLostAgeCount();
    }

    private void writeLostAgeCount() {
        textViewLostAgeVenusCount.setText(getString(R.string.profile_lost_age_count, lostAgeVenusObtained));
        if (lostAgeVenusObtained == 11) {
            textViewLostAgeVenusCount.setTextColor(getResources().getColor(R.color.color_accent));
        }
        else {
            textViewLostAgeVenusCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewLostAgeMarsCount.setText(getString(R.string.profile_lost_age_count, lostAgeMarsObtained));
        if (lostAgeMarsObtained == 11) {
            textViewLostAgeMarsCount.setTextColor(getResources().getColor(R.color.color_accent));
        }
        else {
            textViewLostAgeMarsCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewLostAgeJupiterCount.setText(getString(R.string.profile_lost_age_count, lostAgeJupiterObtained));
        if (lostAgeJupiterObtained == 11) {
            textViewLostAgeJupiterCount.setTextColor(getResources().getColor(R.color.color_accent));
        }
        else {
            textViewLostAgeJupiterCount.setTextColor(getResources().getColor(R.color.white));
        }

        textViewLostAgeMercuryCount.setText(getString(R.string.profile_lost_age_count, lostAgeMercuryObtained));
        if (lostAgeMercuryObtained == 11) {
            textViewLostAgeMercuryCount.setTextColor(getResources().getColor(R.color.color_accent));
        }
        else {
            textViewLostAgeMercuryCount.setTextColor(getResources().getColor(R.color.white));
        }
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
                textViewGoldenSunVenusCount.setText(getString(R.string.profile_golden_sun_count, 0));
                textViewGoldenSunVenusCount.setTextColor(getResources().getColor(R.color.white));
                textViewGoldenSunMarsCount.setText(getString(R.string.profile_golden_sun_count, 0));
                textViewGoldenSunMarsCount.setTextColor(getResources().getColor(R.color.white));
                textViewGoldenSunJupiterCount.setText(getString(R.string.profile_golden_sun_count, 0));
                textViewGoldenSunJupiterCount.setTextColor(getResources().getColor(R.color.white));
                textViewGoldenSunMercuryCount.setText(getString(R.string.profile_golden_sun_count, 0));
                textViewGoldenSunMercuryCount.setTextColor(getResources().getColor(R.color.white));
                textViewLostAgeVenusCount.setText(getString(R.string.profile_lost_age_count, 0));
                textViewLostAgeVenusCount.setTextColor(getResources().getColor(R.color.white));
                textViewLostAgeMarsCount.setText(getString(R.string.profile_lost_age_count, 0));
                textViewLostAgeMarsCount.setTextColor(getResources().getColor(R.color.white));
                textViewLostAgeJupiterCount.setText(getString(R.string.profile_lost_age_count, 0));
                textViewLostAgeJupiterCount.setTextColor(getResources().getColor(R.color.white));
                textViewLostAgeMercuryCount.setText(getString(R.string.profile_lost_age_count, 0));
                textViewLostAgeMercuryCount.setTextColor(getResources().getColor(R.color.white));
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

    @Click(R.id.profile_golden_sun_venus_star)
    public void onGoldenSunVenusStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(VENUS, GOLDEN_SUN);
    }

    @Click(R.id.profile_golden_sun_mars_star)
    public void onGoldenSunMarsStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(MARS, GOLDEN_SUN);
    }

    @Click(R.id.profile_golden_sun_jupiter_star)
    public void onGoldenSunJupiterStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(JUPITER, GOLDEN_SUN);
    }

    @Click(R.id.profile_golden_sun_mercury_star)
    public void onGoldenSunMercuryStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(MERCURY, GOLDEN_SUN);
    }

    @Click(R.id.profile_lost_age_venus_star)
    public void onLostAgeVenusStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(VENUS, LOST_AGE);
    }

    @Click(R.id.profile_lost_age_mars_star)
    public void onLostAgeMarsStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(MARS, LOST_AGE);
    }

    @Click(R.id.profile_lost_age_jupiter_star)
    public void onLostAgeJupiterStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(JUPITER, LOST_AGE);
    }

    @Click(R.id.profile_lost_age_mercury_star)
    public void onLostAgeMercuryStarClicked() {
        makeViewDisappear(layoutSumaraize);
        makeViewAppear(layoutDjinnList);
        configureDjinnList(MERCURY, LOST_AGE);
    }

    protected void configureDjinnList(String element, final String gameName) {
        List<Djinni> storedDjinnList = new ArrayList<>();
        List<Djinni> djinnList = new ArrayList<>();
        if (gameName.equals(GOLDEN_SUN)) {
            storedDjinnList = preferenceManager.getGoldenSunDjinns();
        } else if (gameName.equals(LOST_AGE)) {
            storedDjinnList = preferenceManager.getLostAgeDjinns();
        }
        if (storedDjinnList.size()>0) {
            for (Djinni djinni: storedDjinnList) {
                if (djinni.getElement().equals(element)) {
                    djinnList.add(djinni);
                }
            }
        }
        Collections.sort(djinnList);
        DjinnAdapter djinnAdapter = new DjinnAdapter(ProfileActivity.this, djinnList, new DjinniView.DjinniItemClickListener() {
            @Override
            public void onDjinniClick(Djinni djinni) {
                DjinniDetailActivity_.intent(ProfileActivity.this).djinni(djinni).gameName(gameName).comingFromProfile(true).start();
            }

            @Override
            public void onCaughtCheckBoxClick(Djinni djinni) {
                DjinniDetailActivity_.intent(ProfileActivity.this).djinni(djinni).gameName(gameName).comingFromProfile(true).start();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProfileActivity.this);
        recyclerViewDjinnList.setLayoutManager(layoutManager);
        recyclerViewDjinnList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDjinnList.setAdapter(djinnAdapter);
    }

    @Override
    public void onBackPressed() {
        if (layoutDjinnList.getVisibility() == View.VISIBLE) {
            makeViewDisappear(layoutDjinnList);
            makeViewAppear(layoutSumaraize);
        } else {
            super.onBackPressed();
        }
    }

}
