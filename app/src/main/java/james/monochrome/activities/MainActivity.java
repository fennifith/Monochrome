package james.monochrome.activities;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import james.monochrome.R;
import james.monochrome.adapters.RowAdapter;
import james.monochrome.fragments.RowFragment;
import james.monochrome.utils.MapUtils;
import james.monochrome.views.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager viewPager;
    private RowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (VerticalViewPager) findViewById(R.id.viewPager);

        adapter = new RowAdapter(MapUtils.getMapList(this, "default"), getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveUp();
            }
        });

        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveDown();
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLeft();
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveRight();
            }
        });

        FrameLayout buttonLayout = (FrameLayout) findViewById(R.id.buttonLayout);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)  buttonLayout.getLayoutParams();
        layoutParams.width = Math.abs(size.x - size.y) / 2;
        layoutParams.height = Math.abs(size.x - size.y) / 2;
        findViewById(R.id.buttonLayout).setLayoutParams(layoutParams);
    }

    public void moveUp() {
        RowFragment rowFragment = adapter.getItem(viewPager.getCurrentItem());
        if (rowFragment.getCharacterY() == 0 && viewPager.getCurrentItem() > 0) viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, false);
        else rowFragment.moveUp();
    }

    public void moveDown() {
        RowFragment rowFragment = adapter.getItem(viewPager.getCurrentItem());
        if (rowFragment.getCharacterY() == 9 && viewPager.getCurrentItem() < adapter.getCount()) viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, false);
        else rowFragment.moveDown();
    }

    public void moveLeft() {
        adapter.getItem(viewPager.getCurrentItem()).moveLeft();
    }

    public void moveRight() {
        adapter.getItem(viewPager.getCurrentItem()).moveRight();
    }
}
