package com.example.imageviewswapper.ui.main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.imageviewswapper.R;

public class VideoWalkFragment extends Fragment {
    public static String getCurrent() {
        return current;
    }

    public static void setCurrent(String current) {
        VideoWalkFragment.current = current;
    }

    private static String current;
    public static VideoWalkFragment newInstance() {
        return new VideoWalkFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("currentTag", this, (requestKey, bundle) -> {
            setCurrent(bundle.getString("current"));
        });

        return inflater.inflate(R.layout.video_player, container, false);
    }

    @Nullable
    @Override
    public void onViewCreated(View mainView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(mainView, savedInstanceState);
        VideoView video = (VideoView) mainView.findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(getContext());
        video.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.one_to_two));
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        video.setOnCompletionListener((mediaPlayer) -> {
            String result = "";
            if (Integer.parseInt(current.split("_")[0]) == 1) {
                result = result.concat("2_").concat(current.split("_")[1]);
            } else if (Integer.parseInt(current.split("_")[0]) == 2) {
                result = result.concat("1_").concat(current.split("_")[1]);
            }
            Toast.makeText(getContext(), "video completed!", Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();
            bundle.putString("newCurrent", result);

            getParentFragmentManager().setFragmentResult("newTag", bundle);

            Fragment keyLocations = new KeyLocationsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.video_player_container, keyLocations);
            transaction.disallowAddToBackStack();
            transaction.commit();
        });
        video.setOnPreparedListener((mediaPlayer) -> {
            mediaPlayer.start();
        });
    }

}