package com.jhbb.baking_time.view.ui;

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

public class RecipeDetailsFragment extends Fragment {

    private RecyclerView mStepsRecyclerView;
    private StepsAdapter mStepsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RecipeDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        mStepsRecyclerView = view.findViewById(R.id.steps_recycler_view);
        mStepsRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mStepsAdapter = new StepsAdapter();

        mStepsRecyclerView.setAdapter(mStepsAdapter);
        mStepsRecyclerView.setLayoutManager(mLayoutManager);

        List<StepModel> stepList = Parcels.unwrap(getArguments().getParcelable("stepsList"));
        if (stepList != null) {
            mStepsAdapter.setStepsDataset(stepList);
        }

        return view;
    }
}
