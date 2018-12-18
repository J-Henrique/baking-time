package com.jhbb.baking_time.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.StepModel;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    List<StepModel> mStepsDataset;

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.step_card, viewGroup, false);

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, int i) {
        StepModel step = mStepsDataset.get(i);

        if (step != null) {
            stepsViewHolder.mStepTextView.setText(step.getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mStepsDataset != null ? mStepsDataset.size() : 0;
    }

    public void setStepsDataset(List<StepModel> steps) {
        this.mStepsDataset = steps;
        notifyDataSetChanged();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView mStepTextView;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);

            mStepTextView = itemView.findViewById(R.id.step_text_view);
        }
    }
}
