package com.example.scardenas.djinnlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.scardenas.djinnlist.R;
import com.example.scardenas.djinnlist.data.Djinni;
import com.example.scardenas.djinnlist.widget.DjinniView;

import java.util.List;

public class DjinnAdapter extends RecyclerView.Adapter<DjinniView> {

    private Context context;
    private List<Djinni> djinnList;
    private DjinniView.DjinniItemClickListener djinniItemClickListener;

    public DjinnAdapter(Context context, List<Djinni> djinnList, DjinniView.DjinniItemClickListener djinniItemClickListener) {
        this.context = context;
        this.djinnList = djinnList;
        this.djinniItemClickListener = djinniItemClickListener;
    }

    public void setDjinnList(List<Djinni> djinnList) {
        this.djinnList = djinnList;
    }

    @Override
    public DjinniView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DjinniView(LayoutInflater.from(context).inflate(R.layout.view_djinni, parent, false), context, djinniItemClickListener);
    }

    @Override
    public void onBindViewHolder(DjinniView djinniView, int position) {
        djinniView.bind(djinnList.get(position));
    }

    @Override
    public int getItemCount() {
        return djinnList.size();
    }

}
