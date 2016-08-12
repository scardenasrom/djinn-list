package com.example.scardenas.djinnlist.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.scardenas.djinnlist.BaseActivity;
import com.example.scardenas.djinnlist.PreferenceManager;
import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.adapter.DjinnAdapter;
import com.example.scardenas.djinnlist.data.Djinni;
import com.example.scardenas.djinnlist.widget.DjinniView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;
import java.util.List;

@EActivity(R.layout.activity_djinn_list)
public class DjinnListActivity extends BaseActivity {

    @Extra("game")
    String game;

    @ViewById(R.id.djinn_list_recycler_view) RecyclerView djinnRecyclerView;

    PreferenceManager preferenceManager;

    private List<Djinni> djinniList;
    private DjinnAdapter djinnAdapter;

    @AfterViews
    public void initialize() {
        preferenceManager = new PreferenceManager(DjinnListActivity.this);
        switch (game) {
            case GOLDEN_SUN:
                djinniList = preferenceManager.getGoldenSunDjinns();
                break;
            case LOST_AGE:
                djinniList = preferenceManager.getLostAgeDjinns();
                break;
        }
        Collections.sort(djinniList);
        configureRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (game) {
            case GOLDEN_SUN:
                djinniList = preferenceManager.getGoldenSunDjinns();
                break;
            case LOST_AGE:
                djinniList = preferenceManager.getLostAgeDjinns();
                break;
        }
        Collections.sort(djinniList);
        refreshRecyclerView();
    }

    private void configureRecyclerView() {
        djinnAdapter = new DjinnAdapter(DjinnListActivity.this, djinniList, new DjinniView.DjinniItemClickListener() {
            @Override
            public void onDjinniClick(Djinni djinni) {
                DjinniDetailActivity_.intent(DjinnListActivity.this).djinni(djinni).gameName(game).start();
            }

            @Override
            public void onCaughtCheckBoxClick(Djinni djinni) {
                djinniList.get(djinniList.lastIndexOf(djinni)).setCaught(!djinni.isCaught());
                djinnAdapter.notifyDataSetChanged();
                switch (game) {
                    case GOLDEN_SUN:
                        preferenceManager.setGoldenSunDjinns(djinniList);
                        break;
                    case LOST_AGE:
                        preferenceManager.setLostAgeDjinns(djinniList);
                        break;
                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DjinnListActivity.this);
        djinnRecyclerView.setLayoutManager(layoutManager);
        djinnRecyclerView.setItemAnimator(new DefaultItemAnimator());
        djinnRecyclerView.setAdapter(djinnAdapter);
    }

    private void refreshRecyclerView() {
        djinnAdapter.setDjinnList(djinniList);
        djinnAdapter.notifyDataSetChanged();
    }

}
