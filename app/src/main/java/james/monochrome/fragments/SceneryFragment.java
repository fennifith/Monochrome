package james.monochrome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import james.monochrome.R;
import james.monochrome.data.SceneryData;
import james.monochrome.views.CharacterImageView;
import james.monochrome.views.SceneryImageView;

public class SceneryFragment extends Fragment {

    private static final String ARG_SCENERY_DATA = "scenery";

    private CharacterImageView character;
    private SceneryImageView imageView;
    private SceneryData data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scenery, container, false);
        character = (CharacterImageView) v.findViewById(R.id.character);
        imageView = (SceneryImageView) v.findViewById(R.id.scenery);
        data = (SceneryData) getArguments().getSerializable(ARG_SCENERY_DATA);

        character.setScenery(data);
        imageView.setScenery(data);
        return v;
    }

    public void moveLeft() {
        if (imageView != null) character.moveLeft();
    }

    public void moveRight() {
        if (imageView != null) character.moveRight();
    }

    public void moveUp() {
        if (imageView != null) character.moveUp();
    }

    public void moveDown() {
        if (imageView != null) character.moveDown();
    }

    public int getCharacterX() {
        return character.getCharacterX();
    }

    public int getCharacterY() {
        return character.getCharacterY();
    }

    public static SceneryFragment getInstance(SceneryData data) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCENERY_DATA, data);

        SceneryFragment fragment = new SceneryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
