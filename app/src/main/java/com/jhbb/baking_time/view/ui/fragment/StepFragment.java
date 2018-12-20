package com.jhbb.baking_time.view.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.StepModel;

import org.parceler.Parcels;

public class StepFragment extends Fragment {

    TextView mStepInstructionTextView;
    TextView mStepDescriptionTextView;

    Button mNextButton;
    Button mPreviousButton;

    OnNextClickListener mNextCallback;
    OnPreviousClickListener mPreviousCallback;

    SimpleExoPlayer mPlayer;
    SimpleExoPlayerView mPlayerView;

    public interface OnNextClickListener {
        void onNextClickListener();
    }

    public interface OnPreviousClickListener {
        void onPreviousClickListener();
    }

    public StepFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        StepModel currentStep = Parcels.unwrap(getArguments().getParcelable("currentStep"));
        if (currentStep != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT
                    || getResources().getBoolean(R.bool.isLargeScreen)) {
                mStepInstructionTextView = view.findViewById(R.id.step_instruction_text_view);
                mStepDescriptionTextView = view.findViewById(R.id.step_description_text_view);
                mNextButton = view.findViewById(R.id.next_button);
                mPreviousButton = view.findViewById(R.id.previous_button);

                mStepInstructionTextView.setText(currentStep.getDescription());

                if (mStepDescriptionTextView != null) {
                    mStepDescriptionTextView.setText(currentStep.getShortDescription());
                }

                if (mNextButton != null) {
                    mNextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mNextCallback.onNextClickListener();
                        }
                    });
                }

                if (mPreviousButton != null) {
                    mPreviousButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPreviousCallback.onPreviousClickListener();
                        }
                    });
                }
            }

            mPlayerView = view.findViewById(R.id.simple_exo_player);
            if (!TextUtils.isEmpty(currentStep.getVideoURL())) {
                mPlayerView.setVisibility(View.VISIBLE);
                initializePlayer(Uri.parse(currentStep.getVideoURL()));
            } else {
                mPlayerView.setVisibility(View.GONE);
            }
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void initializePlayer(Uri mediaUri) {
        if (mPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mPlayer);

            String userAgent = Util.getUserAgent(getContext(), "BakingTime");
            MediaSource mediaSource
                    = new ExtractorMediaSource(
                    mediaUri,
                    new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}
