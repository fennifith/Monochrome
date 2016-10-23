package james.monochrome.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.fragments.SceneryFragment;

public class SceneryAdapter extends FragmentStatePagerAdapter {

    private RowData row;
    private List<SceneryFragment> sceneryFragments;

    public SceneryAdapter(RowData row, FragmentManager fm) {
        super(fm);
        this.row = row;

        sceneryFragments = new ArrayList<>();
        for (SceneryData scenery : row.getRow()) {
            sceneryFragments.add(SceneryFragment.getInstance(scenery));
        }
    }

    @Override
    public SceneryFragment getItem(int position) {
        return sceneryFragments.get(position);
    }

    @Override
    public int getCount() {
        return sceneryFragments.size();
    }
}
