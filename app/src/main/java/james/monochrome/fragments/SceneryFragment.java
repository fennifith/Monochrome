package james.monochrome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import james.monochrome.R;
import james.monochrome.data.SceneryData;
import james.monochrome.views.CharacterView;
import james.monochrome.views.SceneryView;

public class SceneryFragment extends Fragment {

    private static final String ARG_SCENERY_DATA = "scenery";

    private CharacterView character;
    private SceneryView scenery;
    private SceneryData data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scenery, container, false);
        character = (CharacterView) v.findViewById(R.id.character);
        scenery = (SceneryView) v.findViewById(R.id.scenery);
        data = (SceneryData) getArguments().getSerializable(ARG_SCENERY_DATA);

        character.setScenery(data);
        scenery.setScenery(data);
        return v;
    }

    public void moveLeft() {
        if (scenery != null) character.moveLeft();
    }

    public void moveRight() {
        if (scenery != null) character.moveRight();
    }

    public void moveUp() {
        if (scenery != null) character.moveUp();
    }

    public void moveDown() {
        if (scenery != null) character.moveDown();
    }

    public int getCharacterX() {
        return character.getCharacterX();
    }

    public int getCharacterY() {
        return character.getCharacterY();
    }

    public void setCharacterPosition(int x, int y) {
        character.setCharacterPosition(x, y);
    }

    public static SceneryFragment getInstance(SceneryData data) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCENERY_DATA, data);

        SceneryFragment fragment = new SceneryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
