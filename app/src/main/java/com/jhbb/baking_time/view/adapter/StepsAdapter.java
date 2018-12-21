package com.jhbb.baking_time.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.entity.StepModel;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private static final String TAG = StepsAdapter.class.getSimpleName();

    List<StepModel> mStepsDataset;
    OnStepAdapterClickListener mCallback;

    public interface OnStepAdapterClickListener {
        void onStepAdapterClickListener(int itemClickedIndex);
    }

    public StepsAdapter(OnStepAdapterClickListener mCallback) {
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.step_card, viewGroup, false);

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, final int i) {
        StepModel step = mStepsDataset.get(i);
        Log.v(TAG, "Binding step: " + step.getShortDescription());

        if (step != null) {
            stepsViewHolder.mStepTextView.setText(step.getShortDescription());
        }

        stepsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onStepAdapterClickListener(i);
            }
        });
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
