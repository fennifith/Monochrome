package james.monochrome.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.data.RowData;
import james.monochrome.fragments.RowFragment;

public class RowAdapter extends FragmentStatePagerAdapter {

    private List<RowData> rows;
    private List<RowFragment> rowFragments;

    public RowAdapter(List<RowData> rows, FragmentManager fm) {
        super(fm);
        this.rows = rows;

        rowFragments = new ArrayList<>();
        for (RowData row : rows) {
            rowFragments.add(RowFragment.getInstance(row));
        }
    }

    @Override
    public RowFragment getItem(int position) {
        return rowFragments.get(position);
    }

    @Override
    public int getCount() {
        return rowFragments.size();
    }
}
