package com.example.imageviewswapper.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imageviewswapper.R;

public class KeyLocationsFragment extends Fragment {
    public static String getCurrentTag() {
        return currentTag;
    }

    public static void setCurrentTag(String currentTag) {
        KeyLocationsFragment.currentTag = currentTag;
    }

    private static String currentTag = null;

    public static KeyLocationsFragment newInstance() {
        return new KeyLocationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mainView = inflater.inflate(R.layout.key_locations, container, false);

        getParentFragmentManager().setFragmentResultListener("newTag", this, (requestKey, bundle) -> {
            setCurrentTag(bundle.getString("newCurrent"));
        });

        if (getCurrentTag() != null) {
            String[] result = getCurrentTag().split("_");
            switch (Integer.parseInt(result[0])) {
                case 1:
                    result[0] = "one";
                    break;
                case 2:
                    result[0] = "two";
                    break;
            }
            switch (Integer.parseInt(result[1])) {
                case 1:
                    result[1] = "one";
                    break;
                case 2:
                    result[1] = "two";
                    break;
                case 3:
                    result[1] = "three";
                    break;
                case 4:
                    result[1] = "four";
                    break;
            }
            resetCompass("point_"+result[0]+"_"+result[1], mainView);
        } else {
            resetCompass("point_one_one", mainView);
        }
        return mainView;
    }

    @Nullable
    @Override
    public void onViewCreated(View mainView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(mainView, savedInstanceState);
        ImageView carousel_image = (ImageView) mainView.findViewById(R.id.carousel_image);

        ImageView previous_view = (ImageView) mainView.findViewById(R.id.previous_view);
        ImageView next_view = (ImageView) mainView.findViewById(R.id.next_view);
        Button walk_next_point = (Button) mainView.findViewById(R.id.walk_next_point);
        Button warp_next_point = (Button) mainView.findViewById(R.id.warp_next_point);

        walk_next_point.setOnClickListener((view -> {
            Bundle result = new Bundle();
            result.putString("current", carousel_image.getTag().toString());

            getParentFragmentManager().setFragmentResult("currentTag", result);

            Fragment videoFragment = new VideoWalkFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main, videoFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }));

        warp_next_point.setOnClickListener((view) -> {
            String[] currentImage = carousel_image.getTag().toString().split("_");
            switch(Integer.parseInt(currentImage[0])) {
                case 1:
                    currentImage[0] = "two";
                    if (Integer.parseInt(currentImage[1]) == 1) {
                        currentImage[1] = "one";
                    } else if (Integer.parseInt(currentImage[1]) == 2) {
                        currentImage[1] = "two";
                    } else if (Integer.parseInt(currentImage[1]) == 3) {
                        currentImage[1] = "three";
                    } else if (Integer.parseInt(currentImage[1]) == 4) {
                        currentImage[1] = "four";
                    }
                    break;
//                    if (Integer.parseInt(currentImage[1]) == 1) {
//                        currentImage[0] = "two";
//                    } else if (Integer.parseInt(currentImage[1]) == 2) {
//                        currentImage[0] = "three";
//                    } else if (Integer.parseInt(currentImage[1]) == 3 || Integer.parseInt(currentImage[1]) == 4) {
//                        currentImage[0] = "four";
//                    }
//                    break;
                case 2:
                    currentImage[0] = "one";
                    if (Integer.parseInt(currentImage[1]) == 1) {
                        currentImage[1] = "one";
                    } else if (Integer.parseInt(currentImage[1]) == 2) {
                        currentImage[1] = "two";
                    } else if (Integer.parseInt(currentImage[1]) == 3) {
                        currentImage[1] = "three";
                    } else if (Integer.parseInt(currentImage[1]) == 4) {
                        currentImage[1] = "four";
                    }
                    break;
            }
            String result = "point_".concat(currentImage[0]).concat("_").concat(currentImage[1]);
            resetCompass(result, mainView);
        });

        next_view.setOnClickListener((view) -> {
            String[] currentImage = carousel_image.getTag().toString().split("_");

            switch(Integer.parseInt(currentImage[1])) {
                case 1:
                    currentImage[1] = "two";
                    break;
                case 2:
                    currentImage[1] = "three";
                    break;
                case 3:
                    currentImage[1] = "four";
                    break;
                case 4:
                    currentImage[1] = "one";
                    break;
            }
            switch(Integer.parseInt(currentImage[0])) {
                case 1:
                    currentImage[0] = "one";
                    break;
                case 2:
                    currentImage[0] = "two";
                    break;
                case 3:
                    currentImage[0] = "three";
                    break;
                case 4:
                    currentImage[0] = "four";
                    break;
            }

            String result = "point_".concat(currentImage[0]).concat("_").concat(currentImage[1]);
            resetCompass(result, mainView);
        });

        previous_view.setOnClickListener((view) -> {
            String[] currentImage = carousel_image.getTag().toString().split("_");

            switch(Integer.parseInt(currentImage[1])) {
                case 1:
                    currentImage[1] = "four";
                    break;
                case 2:
                    currentImage[1] = "one";
                    break;
                case 3:
                    currentImage[1] = "two";
                    break;
                case 4:
                    currentImage[1] = "three";
                    break;
            }
            switch(Integer.parseInt(currentImage[0])) {
                case 1:
                    currentImage[0] = "one";
                    break;
                case 2:
                    currentImage[0] = "two";
                    break;
                case 3:
                    currentImage[0] = "three";
                    break;
                case 4:
                    currentImage[0] = "four";
                    break;
            }

            String result = "point_".concat(currentImage[0]).concat("_").concat(currentImage[1]);
            resetCompass(result, mainView);
        });

    }

    private void resetCompass(String result, View mainView) {
        ImageView carousel_image = (ImageView) mainView.findViewById(R.id.carousel_image);

        ImageView north = (ImageView) mainView.findViewById(R.id.north);
        ImageView east = (ImageView) mainView.findViewById(R.id.east);
        ImageView south = (ImageView) mainView.findViewById(R.id.south);
        ImageView west = (ImageView) mainView.findViewById(R.id.west);

        north.getLayoutParams().height = 147;
        north.getLayoutParams().width = 131;
        south.getLayoutParams().height = 121;
        south.getLayoutParams().width = 105;
        east.getLayoutParams().height = 105;
        east.getLayoutParams().width = 121;
        west.getLayoutParams().height = 105;
        west.getLayoutParams().width = 121;

        north.requestLayout();
        east.requestLayout();
        south.requestLayout();
        west.requestLayout();

        DrawableCompat.setTint(north.getDrawable(), ContextCompat.getColor(getContext(), R.color.white));
        DrawableCompat.setTint(east.getDrawable(), ContextCompat.getColor(getContext(), R.color.white));
        DrawableCompat.setTint(south.getDrawable(), ContextCompat.getColor(getContext(), R.color.white));
        DrawableCompat.setTint(west.getDrawable(), ContextCompat.getColor(getContext(), R.color.white));
        setBackgroundImage(result, carousel_image, north, east, south, west);
    }

    private void setBackgroundImage(String result, ImageView carousel_image, ImageView north, ImageView east, ImageView south, ImageView west) {
        switch (result) {
            case "point_one_one":
                carousel_image.setImageResource(R.drawable.point_one_one);
                carousel_image.setTag("1_1");
                DrawableCompat.setTint(north.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                north.getLayoutParams().height = 147;
                north.getLayoutParams().width = 131;
                north.requestLayout();
                break;
            case "point_one_two":
                carousel_image.setImageResource(R.drawable.point_one_two);
                carousel_image.setTag("1_2");
                DrawableCompat.setTint(east.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                east.getLayoutParams().height = 131;
                east.getLayoutParams().width = 147;
                east.requestLayout();
                break;
            case "point_one_three":
                carousel_image.setImageResource(R.drawable.point_one_three);
                carousel_image.setTag("1_3");
                DrawableCompat.setTint(south.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                south.getLayoutParams().height = 147;
                south.getLayoutParams().width = 131;
                south.requestLayout();
                break;
            case "point_one_four":
                carousel_image.setImageResource(R.drawable.point_one_four);
                carousel_image.setTag("1_4");
                DrawableCompat.setTint(west.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                west.getLayoutParams().height = 131;
                west.getLayoutParams().width = 147;
                west.requestLayout();
                break;
            case "point_two_one":
                carousel_image.setImageResource(R.drawable.point_two_one);
                carousel_image.setTag("2_1");
                DrawableCompat.setTint(north.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                north.getLayoutParams().height = 147;
                north.getLayoutParams().width = 131;
                north.requestLayout();
                break;
            case "point_two_two":
                carousel_image.setImageResource(R.drawable.point_two_two);
                carousel_image.setTag("2_2");
                DrawableCompat.setTint(east.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                east.getLayoutParams().height = 131;
                east.getLayoutParams().width = 147;
                east.requestLayout();
                break;
            case "point_two_three":
                carousel_image.setImageResource(R.drawable.point_two_three);
                carousel_image.setTag("2_3");
                DrawableCompat.setTint(south.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                south.getLayoutParams().height = 147;
                south.getLayoutParams().width = 131;
                south.requestLayout();
                break;
            case "point_two_four":
                carousel_image.setImageResource(R.drawable.point_two_four);
                carousel_image.setTag("2_4");
                DrawableCompat.setTint(west.getDrawable(), ContextCompat.getColor(getContext(), R.color.red));
                west.getLayoutParams().height = 131;
                west.getLayoutParams().width = 147;
                west.requestLayout();
//                case "point_three_one":
//
//                    break;
//                case "point_three_two":
//
//                    break;
//                case "point_three_three":
//
//                    break;
//                case "point_three_four":
//
//                    break;
//                case "point_four_one":
//
//                    break;
//                case "point_four_two":
//
//                    break;
//                case "point_four_three":
//
//                    break;
//                case "point_four_four":
//
//                    break;
        }
    }
}
