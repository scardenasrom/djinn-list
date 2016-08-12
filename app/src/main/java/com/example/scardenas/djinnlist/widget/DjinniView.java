package com.example.scardenas.djinnlist.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.data.Djinni;

import java.util.Random;

public class DjinniView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private LinearLayout layoutRootView;
    private TextView textViewOrder;
    private TextView textViewName;
    private TextView textViewFlavor;
    private TextView textViewLocation;
    private ImageView imageViewCaughtBox;
    private ImageView imageViewFights;

    private ImageView imageViewVenusDjinni;
    private ImageView imageViewMarsDjinni;
    private ImageView imageViewJupiterDjinni;
    private ImageView imageViewMercuryDjinni;

    private final Context context;

    private Djinni djinni;

    private DjinniItemClickListener djinniItemClickListener;

    public DjinniView(View itemView, Context context, DjinniItemClickListener djinniItemClickListener) {
        super(itemView);
        this.context = context;
        this.djinniItemClickListener = djinniItemClickListener;

        layoutRootView = (LinearLayout)itemView.findViewById(R.id.view_djinni_root_view);
        textViewOrder = (TextView)itemView.findViewById(R.id.view_djinni_order);
        textViewName = (TextView)itemView.findViewById(R.id.view_djinni_name);
        textViewFlavor = (TextView)itemView.findViewById(R.id.view_djinni_flavor);
        textViewLocation = (TextView)itemView.findViewById(R.id.view_djinni_location);
        imageViewCaughtBox = (ImageView)itemView.findViewById(R.id.view_djinni_caught_box);
        imageViewFights = (ImageView)itemView.findViewById(R.id.view_djinni_fights);

        imageViewVenusDjinni = (ImageView)itemView.findViewById(R.id.view_djinni_image_1);
        imageViewMarsDjinni = (ImageView)itemView.findViewById(R.id.view_djinni_image_2);
        imageViewJupiterDjinni = (ImageView)itemView.findViewById(R.id.view_djinni_image_4);
        imageViewMercuryDjinni = (ImageView)itemView.findViewById(R.id.view_djinni_image_3);

        layoutRootView.setOnClickListener(this);
        imageViewCaughtBox.setOnClickListener(this);
    }

    public Djinni getDjinni() {
        return this.djinni;
    }

    public void bind(Djinni djinni) {
        this.djinni = djinni;
        textViewOrder.setText("" + djinni.getOrder());
        textViewName.setText(djinni.getName());
        textViewFlavor.setText(djinni.getFlavor());
        textViewLocation.setText(djinni.getLocation());
        if (djinni.isCaught()) {
            imageViewCaughtBox.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_djinni_caught));
        } else {
            imageViewCaughtBox.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_djinni_not_caught));
        }
        if (djinni.doesItFight()) {
            imageViewFights.setVisibility(View.VISIBLE);
        } else {
            imageViewFights.setVisibility(View.GONE);
        }
        switch (this.djinni.getElement()) {
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

    private void configureVenusDjinni() {
        imageViewVenusDjinni.setImageDrawable(context.getResources().getDrawable(R.drawable.venus_djinn));
        imageViewMarsDjinni.setImageDrawable(null);
        imageViewJupiterDjinni.setImageDrawable(null);
        imageViewMercuryDjinni.setImageDrawable(null);
        layoutRootView.setBackground(context.getResources().getDrawable(R.drawable.selector_venus_djinni));
    }

    private void configureMarsDjinni() {
        imageViewVenusDjinni.setImageDrawable(null);
        imageViewMarsDjinni.setImageDrawable(context.getResources().getDrawable(R.drawable.mars_djinn));
        imageViewJupiterDjinni.setImageDrawable(null);
        imageViewMercuryDjinni.setImageDrawable(null);
        layoutRootView.setBackground(context.getResources().getDrawable(R.drawable.selector_mars_djinni));
    }

    private void configureJupiterDjinni() {
        imageViewVenusDjinni.setImageDrawable(null);
        imageViewMarsDjinni.setImageDrawable(null);
        imageViewJupiterDjinni.setImageDrawable(context.getResources().getDrawable(R.drawable.jupiter_djinn));
        imageViewMercuryDjinni.setImageDrawable(null);
        layoutRootView.setBackground(context.getResources().getDrawable(R.drawable.selector_jupiter_djinni));
    }

    private void configureMercuryDjinni() {
        imageViewVenusDjinni.setImageDrawable(null);
        imageViewMarsDjinni.setImageDrawable(null);
        imageViewJupiterDjinni.setImageDrawable(null);
        imageViewMercuryDjinni.setImageDrawable(context.getResources().getDrawable(R.drawable.mercury_djinn));
        layoutRootView.setBackground(context.getResources().getDrawable(R.drawable.selector_mercury_djinni));
    }

    @Override
    public void onClick(View view) {
        if (view == imageViewCaughtBox) {
            djinniItemClickListener.onCaughtCheckBoxClick(djinni);
        } else if (view == layoutRootView) {
            djinniItemClickListener.onDjinniClick(djinni);
        }
    }

    public interface DjinniItemClickListener {
        void onDjinniClick(Djinni djinni);
        void onCaughtCheckBoxClick (Djinni djinni);
    }

}
