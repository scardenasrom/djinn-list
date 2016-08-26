package com.example.scardenas.djinnlist;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.DrawableRes;

@EActivity
public class BaseActivity extends AppCompatActivity {

    protected static final String GOLDEN_SUN = "goldenSun";
    protected static final String LOST_AGE = "lostAge";
    protected static final String VENUS = "Venus";
    protected static final String MARS = "Mars";
    protected static final String JUPITER = "Jupiter";
    protected static final String MERCURY = "Mercury";

    @ColorRes(R.color.venus_primary) public int colorVenusPrimary;
    @ColorRes(R.color.venus_secondary) public int colorVenusSecondary;
    @ColorRes(R.color.mars_primary) public int colorMarsPrimary;
    @ColorRes(R.color.mars_secondary) public int colorMarsSecondary;
    @ColorRes(R.color.jupiter_primary) public int colorJupiterPrimary;
    @ColorRes(R.color.jupiter_secondary) public int colorJupiterSecondary;
    @ColorRes(R.color.mercury_primary) public int colorMercuryPrimary;
    @ColorRes(R.color.mercury_secondary) public int colorMercurySecondary;

    @DrawableRes(R.drawable.venus_djinn) public Drawable drawableVenusDjinn;
    @DrawableRes(R.drawable.mars_djinn) public Drawable drawableMarsDjinn;
    @DrawableRes(R.drawable.jupiter_djinn) public Drawable drawableJupiterDjinn;
    @DrawableRes(R.drawable.mercury_djinn) public Drawable drawableMercuryDjinn;

    protected void makeViewDisappear(View view) {
        if (view.getVisibility() != View.GONE) {
            view.setAnimation(AnimationUtils.loadAnimation(BaseActivity.this, R.anim.fade_out));
            view.setVisibility(View.GONE);
        }
    }

    protected void makeViewAppear(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            view.setAnimation(AnimationUtils.loadAnimation(BaseActivity.this, R.anim.fade_in));
        }
    }

}
