package james.monochrome.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.R;
import james.monochrome.adapters.AboutAdapter;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 1));

        List<AboutAdapter.Item> items = new ArrayList<>();

        String[] contribNames = getResources().getStringArray(R.array.contrib_names);
        String[] contribDescs = getResources().getStringArray(R.array.contrib_descs);
        String[] contribUrls = getResources().getStringArray(R.array.contrib_urls);

        items.add(new AboutAdapter.HeaderItem(this, getString(R.string.title_contrib), null, false, null));

        for (int i = 0; i < contribNames.length; i++) {
            items.add(new AboutAdapter.TextItem(this, contribNames[i], contribDescs[i], contribUrls[i]));
        }

        String[] libNames = getResources().getStringArray(R.array.library_names);
        String[] libDescs = getResources().getStringArray(R.array.library_descs);
        String[] libUrls = getResources().getStringArray(R.array.library_urls);

        items.add(new AboutAdapter.HeaderItem(this, getString(R.string.title_libs), null, false, null));

        for (int i = 0; i < libNames.length; i++) {
            items.add(new AboutAdapter.TextItem(this, libNames[i], libDescs[i], libUrls[i]));
        }

        recycler.setAdapter(new AboutAdapter(this, items));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
