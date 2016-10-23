package james.monochrome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import james.monochrome.R;
import james.monochrome.adapters.SceneryAdapter;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.views.HorizontalViewPager;

public class RowFragment extends Fragment {

    private static final String ARG_ROW = "row";

    private HorizontalViewPager viewPager;
    private SceneryAdapter adapter;
    private RowData data;

    private int item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_row, container, false);
        viewPager = (HorizontalViewPager) v.findViewById(R.id.viewPager);
        data = (RowData) getArguments().getSerializable(ARG_ROW);

        adapter = new SceneryAdapter(data, getChildFragmentManager());
        viewPager.setAdapter(adapter);
        if (item > 0 && item < adapter.getCount()) viewPager.setCurrentItem(item, false);
        return v;
    }

    public int getCurrentItem() {
        if (viewPager != null) return viewPager.getCurrentItem();
        else return item;
    }

    public void setCurrentItem(int item) {
        if (viewPager != null && item >= 0 && item < adapter.getCount()) viewPager.setCurrentItem(item, false);
        else this.item = item;
    }

    public int getCount() {
        if (adapter != null) return adapter.getCount();
        else return 0;
    }

    public void moveUp() {
        if (viewPager == null || adapter == null) return;
        adapter.getItem(getCurrentItem()).moveUp();
    }

    public void moveDown() {
        if (viewPager == null || adapter == null) return;
        adapter.getItem(getCurrentItem()).moveDown();
    }

    public void moveLeft() {
        if (viewPager == null || adapter == null) return;
        SceneryFragment fragment = adapter.getItem(getCurrentItem());
        if (fragment.getCharacterX() == 0 && getCurrentItem() > 0) setCurrentItem(getCurrentItem() - 1);
        else fragment.moveLeft();
    }

    public void moveRight() {
        if (viewPager == null || adapter == null) return;
        SceneryFragment fragment = adapter.getItem(getCurrentItem());
        if (fragment.getCharacterX() == 9 && getCurrentItem() < getCount()) setCurrentItem(getCurrentItem() + 1);
        else fragment.moveRight();
    }

    public int getCharacterX() {
        if (viewPager == null || adapter == null) return 0;
        return adapter.getItem(getCurrentItem()).getCharacterX();
    }

    public int getCharacterY() {
        if (viewPager == null || adapter == null) return 0;
        return adapter.getItem(getCurrentItem()).getCharacterY();
    }

    public static RowFragment getInstance(RowData data) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ROW, data);

        RowFragment fragment = new RowFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
