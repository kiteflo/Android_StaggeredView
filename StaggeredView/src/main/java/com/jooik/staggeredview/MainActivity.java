package com.jooik.staggeredview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jooik.staggeredview.frags.StaggeredViewFragment;
import com.jooik.staggeredview.util.ISquareItem;
import com.jooik.staggeredview.util.ShadowProperties;
import com.jooik.staggeredview.util.SquareItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaggeredViewFragment staggeredViewFragment = new StaggeredViewFragment();
        staggeredViewFragment.setItems(initItems());
        staggeredViewFragment.setPadding(15);
        staggeredViewFragment.setBackgroundColor("#000000");
        staggeredViewFragment.setAvoidDuplicateRandomLayouts(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, staggeredViewFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------

    /**
     * Generate some demo items...
     * @return
     */
    private List<ISquareItem> initItems()
    {
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/SignPainter_HouseScript.ttf");

        int font_large = 35;
        int font_medium = 28;
        int font_small = 20;
        int font_color = Color.WHITE;
        ShadowProperties shadowProperties = new ShadowProperties(30,0,0,Color.BLACK);

        List<ISquareItem> items = new ArrayList<ISquareItem>();
        SquareItem sq1 = new SquareItem("Alm-Käse",R.drawable.cheese_almkaese);
        sq1.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq1);
        SquareItem sq2 = new SquareItem("Appenzeller",R.drawable.cheese_appenzeller);
        sq2.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq2);
        SquareItem sq3 = new SquareItem("Aragon",R.drawable.cheese_aragon);
        sq3.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq3);
        SquareItem sq4 = new SquareItem("Asiago",R.drawable.cheese_asiago);
        sq4.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq4);
        SquareItem sq5 = new SquareItem("Banon",R.drawable.cheese_banon);
        sq5.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq5);
        SquareItem sq6 = new SquareItem("Bavariablu",R.drawable.cheese_bavariablu);
        sq6.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq6);
        SquareItem sq7 = new SquareItem("Beaufort",R.drawable.cheese_beaufort);
        sq7.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq7);
        SquareItem sq8 = new SquareItem("Belpaese",R.drawable.cheese_belpaese);
        sq8.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq8);
        SquareItem sq9 = new SquareItem("Bergader Edelpilz",R.drawable.cheese_bergader_edelpilz);
        sq9.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq9);
        SquareItem sq10 = new SquareItem("Bergkäse",R.drawable.cheese_bergkaese);
        sq10.applyFontStyle(font_large,font_medium,font_small,face,font_color,shadowProperties);
        items.add(sq10);

        return items;
    }

}
