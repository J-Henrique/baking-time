
package com.jhbb.baking_time.view.ui.activity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.StepModel;
import com.jhbb.baking_time.view.ui.fragment.StepFragment;

import org.parceler.Parcels;

import java.util.List;

public class StepActivity extends AppCompatActivity
        implements StepFragment.OnPreviousClickListener, StepFragment.OnNextClickListener {

    List<StepModel> mStepList;
    int mCurrentStepIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fitLayout();

        setContentView(R.layout.activity_step);

        mCurrentStepIndex = getIntent().getIntExtra("stepIndex", 0);
        mStepList = Parcels.unwrap(getIntent().getParcelableExtra("stepList"));

        if (mStepList != null) {
            StepModel currentStep = mStepList.get(mCurrentStepIndex);

            Bundle args = new Bundle();
            args.putParcelable("currentStep", Parcels.wrap(currentStep));

            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.current_step_container, stepFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        fitLayout();
    }

    @Override
    public void onNextClickListener() {
        if (mCurrentStepIndex + 1 <= mStepList.size() - 1) {
            mCurrentStepIndex++;
        }

        refreshFragment();
    }

    @Override
    public void onPreviousClickListener() {
        if (mCurrentStepIndex - 1 >= 0) {
            mCurrentStepIndex--;
        }

        refreshFragment();
    }

    private void refreshFragment() {
        Bundle args = new Bundle();
        args.putParcelable("currentStep", Parcels.wrap(mStepList.get(mCurrentStepIndex)));

        StepFragment stepFragment = new StepFragment();
        stepFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.current_step_container, stepFragment)
                .commit();
    }

    private void fitLayout() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

            getWindow().getDecorView().setSystemUiVisibility(uiOptions);

            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }
}
