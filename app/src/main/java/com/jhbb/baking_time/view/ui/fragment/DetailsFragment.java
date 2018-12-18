package com.jhbb.baking_time.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.StepModel;
import com.jhbb.baking_time.view.adapter.StepsAdapter;

import org.parceler.Parcels;

import java.util.List;

public class DetailsFragment extends Fragment
        implements StepsAdapter.OnStepAdapterClickListener {

    private static final String TAG = DetailsFragment.class.getSimpleName();

    private RecyclerView mStepsRecyclerView;
    private StepsAdapter mStepsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    OnStepClickListener mCallback;

    @Override
    public void onStepAdapterClickListener(int itemClickedIndex) {
        mCallback.onStepClickListener(itemClickedIndex);
    }

    public interface OnStepClickListener {
        void onStepClickListener(int itemClickedIndex);
    }

    public DetailsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        mStepsRecyclerView = view.findViewById(R.id.steps_recycler_view);
        mStepsRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mStepsAdapter = new StepsAdapter(this);

        mStepsRecyclerView.setAdapter(mStepsAdapter);
        mStepsRecyclerView.setLayoutManager(mLayoutManager);

        List<StepModel> stepList = Parcels.unwrap(getArguments().getParcelable("stepsList"));
        if (stepList != null) {
            mStepsAdapter.setStepsDataset(stepList);
        }

        return view;
    }
}
