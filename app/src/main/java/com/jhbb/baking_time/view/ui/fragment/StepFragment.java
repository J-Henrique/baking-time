package com.jhbb.baking_time.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.StepModel;

import org.parceler.Parcels;

public class StepFragment extends Fragment {

    TextView mStepInstructionTextView;

    Button mNextButton;
    Button mPreviousButton;

    OnNextClickListener mNextCallback;
    OnPreviousClickListener mPreviousCallback;

    public interface OnNextClickListener {
        void onNextClickListener();
    }

    public interface OnPreviousClickListener {
        void onPreviousClickListener();
    }

    public StepFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mNextCallback = (OnNextClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnNextClickListener");
        }

        try {
            mPreviousCallback = (OnPreviousClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnPreviousClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        mStepInstructionTextView = view.findViewById(R.id.step_instruction_text_view);
        mNextButton = view.findViewById(R.id.next_button);
        mPreviousButton = view.findViewById(R.id.previous_button);

        StepModel currentStep = Parcels.unwrap(getArguments().getParcelable("currentStep"));
        if (currentStep != null) {
            mStepInstructionTextView.setText(currentStep.getDescription());
        }

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNextCallback.onNextClickListener();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviousCallback.onPreviousClickListener();
            }
        });

        return view;
    }
}
