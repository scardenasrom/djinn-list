package com.example.scardenas.djinnlist.ui;

import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scardenas.djinnlist.BaseActivity;
import com.example.scardenas.djinnlist.PreferenceManager;
import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.data.Djinni;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;
import java.util.List;

@EActivity(R.layout.activity_djinni_detail)
public class DjinniDetailActivity extends BaseActivity {

    @Extra("gameName")
    String gameName;
    @Extra("djinni")
    Djinni djinni;

    @ViewById(R.id.djinni_detail_app_bar) AppBarLayout appBarLayout;
    @ViewById(R.id.djinni_detail_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @ViewById(R.id.djinni_detail_screenshot_parallax) ImageView imageViewParallaxScreenshot;
    @ViewById(R.id.djinni_detail_toolbar) Toolbar toolbar;
    @ViewById(R.id.djinni_detail_image_view_floating_left) ImageView imageViewFloatingIconLeft;
    @ViewById(R.id.djinni_detail_image_view_floating_right) ImageView imageViewFloatingIconRight;
    @ViewById(R.id.djinni_detail_location_screen) ImageView imageViewLocationScreen;
    @ViewById(R.id.djinni_detail_flavor) TextView textViewFlavor;
    @ViewById(R.id.djinni_detail_caught_box) ImageView imageViewCaughtBox;
    @ViewById(R.id.djinni_detail_label_how_to_obtain) TextView labelHowToObtain;
    @ViewById(R.id.djinni_detail_text_how_to_obtain) TextView textViewHowToObtain;
    @ViewById(R.id.djinni_detail_label_battle_effect) TextView labelBattleEffect;
    @ViewById(R.id.djinni_detail_text_battle_effect) TextView textViewBattleEffect;
    @ViewById(R.id.djinni_detail_label_set_bonus) TextView labelSetBonus;
    @ViewById(R.id.djinni_detail_text_set_bonus) TextView textViewSetBonus;

    private PreferenceManager preferenceManager;
    private List<Djinni> djinniList;

    int[] screenshots = new int[28];
    int[] locationScreenshots = new int[28];

    boolean caught;

    @AfterViews
    public void initialize() {
        initialConfiguration();
    }

    private void initialConfiguration() {
        preferenceManager = new PreferenceManager(DjinniDetailActivity.this);
        switch (gameName) {
            case GOLDEN_SUN:
                djinniList = preferenceManager.getGoldenSunDjinns();
                break;
            case LOST_AGE:
                djinniList = preferenceManager.getLostAgeDjinns();
                break;
        }
        Collections.sort(djinniList);

        toolbar.setTitle(djinni.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch (gameName) {
            case GOLDEN_SUN:
                TypedArray screenshotsTempGS = getResources().obtainTypedArray(R.array.golden_sun_screenshots);
                for (int i = 0; i < screenshotsTempGS.length(); i++) {
                    screenshots[i] = screenshotsTempGS.getResourceId(i, -1);
                }
                screenshotsTempGS.recycle();
                TypedArray locationsTempGS = getResources().obtainTypedArray(R.array.golden_sun_location_screens);
                for (int i = 0; i < locationsTempGS.length(); i++) {
                    locationScreenshots[i] = locationsTempGS.getResourceId(i, -1);
                }
                locationsTempGS.recycle();
                break;
            case LOST_AGE:
                TypedArray screenshotsTempLA = getResources().obtainTypedArray(R.array.lost_age_screenshots);
                for (int i = 0; i < screenshotsTempLA.length(); i++) {
                    screenshots[i] = screenshotsTempLA.getResourceId(i, -1);
                }
                screenshotsTempLA.recycle();
                TypedArray locationsTemp = getResources().obtainTypedArray(R.array.lost_age_location_screens);
                for (int i = 0; i < locationsTemp.length(); i++) {
                    locationScreenshots[i] = locationsTemp.getResourceId(i, -1);
                }
                locationsTemp.recycle();
                break;
        }
        configureVisuals();
    }

    private void configureVisuals() {
        configureCaughtBox();
        imageViewParallaxScreenshot.setImageDrawable(getResources().getDrawable(screenshots[djinni.getOrder()-1]));
        imageViewLocationScreen.setImageDrawable(getResources().getDrawable(locationScreenshots[djinni.getOrder()-1]));
        textViewFlavor.setText(djinni.getFlavor());
        textViewHowToObtain.setText(djinni.getCatchingDescription());
        textViewBattleEffect.setText(djinni.getBattleEffect());
        textViewSetBonus.setText(djinni.getSetBonus());
        switch (djinni.getElement()) {
            case "Venus":
                configureVenusDjinni();
                break;
            case "Mars":
                configureMarsDjinni();
                break;
            case "Jupiter":
                configureJupiterDjinni();
                break;
            case "Mercury":
                configureMercuryDjinni();
                break;
        }
    }

    private void configureCaughtBox() {
        if (djinni.isCaught()) {
            caught = true;
            imageViewCaughtBox.setImageDrawable(getResources().getDrawable(R.drawable.ic_djinni_caught));
        } else {
            caught = false;
            imageViewCaughtBox.setImageDrawable(getResources().getDrawable(R.drawable.ic_djinni_not_caught));
        }
        imageViewCaughtBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caught = !caught;
                if (caught) {
                    imageViewCaughtBox.setImageDrawable(getResources().getDrawable(R.drawable.ic_djinni_caught));
                    djinniList.get(djinni.getOrder()-1).setCaught(true);
                    switch (gameName) {
                        case GOLDEN_SUN:
                            preferenceManager.setGoldenSunDjinns(djinniList);
                            break;
                        case LOST_AGE:
                            preferenceManager.setLostAgeDjinns(djinniList);
                            break;
                    }
                } else {
                    imageViewCaughtBox.setImageDrawable(getResources().getDrawable(R.drawable.ic_djinni_not_caught));
                    djinniList.get(djinni.getOrder()-1).setCaught(false);
                    switch (gameName) {
                        case GOLDEN_SUN:
                            preferenceManager.setGoldenSunDjinns(djinniList);
                            break;
                        case LOST_AGE:
                            preferenceManager.setLostAgeDjinns(djinniList);
                            break;
                    }
                }
            }
        });
    }

    private void configureVenusDjinni() {
        imageViewFloatingIconLeft.setImageDrawable(getResources().getDrawable(R.drawable.venus_djinn));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.venus_secondary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.venus_primary));
        labelHowToObtain.setBackgroundColor(getResources().getColor(R.color.venus_primary));
        labelBattleEffect.setBackgroundColor(getResources().getColor(R.color.venus_primary));
        labelSetBonus.setBackgroundColor(getResources().getColor(R.color.venus_primary));
    }

    private void configureMarsDjinni() {
        imageViewFloatingIconRight.setImageDrawable(getResources().getDrawable(R.drawable.mars_djinn));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.mars_secondary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.mars_primary));
        labelHowToObtain.setBackgroundColor(getResources().getColor(R.color.mars_primary));
        labelBattleEffect.setBackgroundColor(getResources().getColor(R.color.mars_primary));
        labelSetBonus.setBackgroundColor(getResources().getColor(R.color.mars_primary));
    }

    private void configureJupiterDjinni() {
        imageViewFloatingIconRight.setImageDrawable(getResources().getDrawable(R.drawable.jupiter_djinn));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.jupiter_secondary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.jupiter_primary));
        labelHowToObtain.setBackgroundColor(getResources().getColor(R.color.jupiter_primary));
        labelBattleEffect.setBackgroundColor(getResources().getColor(R.color.jupiter_primary));
        labelSetBonus.setBackgroundColor(getResources().getColor(R.color.jupiter_primary));
    }

    private void configureMercuryDjinni() {
        imageViewFloatingIconLeft.setImageDrawable(getResources().getDrawable(R.drawable.mercury_djinn));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.mercury_secondary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.mercury_primary));
        labelHowToObtain.setBackgroundColor(getResources().getColor(R.color.mercury_primary));
        labelBattleEffect.setBackgroundColor(getResources().getColor(R.color.mercury_primary));
        labelSetBonus.setBackgroundColor(getResources().getColor(R.color.mercury_primary));
    }

    @OptionsItem(android.R.id.home)
    public void goBack() {
        finish();
    }

}
