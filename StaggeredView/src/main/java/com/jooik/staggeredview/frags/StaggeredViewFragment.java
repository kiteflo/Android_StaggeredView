package com.jooik.staggeredview.frags;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jooik.staggeredview.R;
import com.jooik.staggeredview.util.ISquareItem;
import com.jooik.staggeredview.util.SquareType;

import java.util.List;
import java.util.Random;

/**
 * Setup staggered view...
 */
public class StaggeredViewFragment extends Fragment
{
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    // data
    private List<ISquareItem> items;

    // padding (="border frames" displayed between the items...)
    private int padding = 15;

    private String backgroundColor = "#000000";

    // it might be necessary to get a really "mixed" view, random does not
    // guarantee so...
    private boolean avoidDuplicateRandomLayouts = false;

    // ------------------------------------------------------------------------
    // interface implementation
    // ------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayout llFrame = (LinearLayout)view.findViewById(R.id.ll_frame);

        // apply layout properties (color etc.)
        llFrame.setBackgroundColor(Color.parseColor(backgroundColor));

        // required for dynamic width/height calculations
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        // layout helper....
        LinearLayout outerBox = null;
        LinearLayout innerBox = null;
        boolean doubleBoxInTheLeft = false;
        GridCreationState creationState = GridCreationState.START_NEW_ROW;
        RowStyle rowStyle = null;

        for (ISquareItem item : items)
        {
            // create new row in layout and randomly generate the first item
            // which the will be added to the row. The first tem determines all
            // further layout steps for this row...
            if (creationState == GridCreationState.START_NEW_ROW)
            {
                Random random = new Random();
                int randomInt = random.nextInt(3-0);
                SquareType squareType = translateRandomIdToSquareType(randomInt);
                RowStyle newRowStyle = translateRandomIdToRowLayoutEnum(randomInt);

                // avoid row style series with the same layout...
                if (avoidDuplicateRandomLayouts && rowStyle != null)
                {
                    while (newRowStyle == rowStyle)
                    {
                        randomInt = random.nextInt(3-0);
                        squareType = translateRandomIdToSquareType(randomInt);
                        newRowStyle = translateRandomIdToRowLayoutEnum(randomInt);
                    }
                }

                // add outer layoutbox...
                outerBox = new LinearLayout(view.getContext());
                outerBox.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(width, height/3);
                outerBox.setLayoutParams(boxParams);

                // first box deserves "special" handling regarding padding...
                if (item.equals(items.get(0)))
                {
                    outerBox.setPadding(padding,padding,padding,padding);
                }
                else
                {
                    outerBox.setPadding(padding,0,padding,padding);
                }
                llFrame.addView(outerBox);

                switch (squareType)
                {
                    case LARGE_ITEM:
                    {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height/3);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.LARGE_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);

                        outerBox.addView(rl);
                        creationState = GridCreationState.START_NEW_ROW;
                        rowStyle = RowStyle.LARGE_ONLY;
                        break;
                    }
                    case MEDIUM_ITEM:
                    {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3*2, height/3);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.MEDIUM_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);

                        outerBox.addView(rl);
                        creationState = GridCreationState.ADDED_MEDIUM;
                        rowStyle = RowStyle.MEDIUM_SMALL_SMALL;
                        break;
                    }
                    case SMALL_ITEM:
                    {
                        doubleBoxInTheLeft = true;

                        // add inner layoutbox...
                        innerBox = new LinearLayout(view.getContext());
                        innerBox.setOrientation(LinearLayout.VERTICAL);
                        innerBox.setPadding(0,0,padding,0);
                        boxParams = new LinearLayout.LayoutParams(width/3, height/3);
                        innerBox.setLayoutParams(boxParams);
                        outerBox.addView(innerBox);

                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3, height/3/2);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.SMALL_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);
                        innerBox.addView(rl);

                        creationState = GridCreationState.ADDED_SMALL_TOP_LEFT;
                        rowStyle = RowStyle.SMALL_SMALL_MEDIUM;
                        break;
                    }
                }
            }

            else
            {
                switch (creationState)
                {
                    case ADDED_MEDIUM:
                    {
                        // add inner layoutbox...
                        innerBox = new LinearLayout(view.getContext());
                        innerBox.setOrientation(LinearLayout.VERTICAL);
                        innerBox.setPadding(padding,0,0,0);
                        LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(width/3, height/3);
                        innerBox.setLayoutParams(boxParams);
                        outerBox.addView(innerBox);

                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3, height/3/2);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.SMALL_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);
                        innerBox.addView(rl);

                        creationState = GridCreationState.ADDED_SMALL_TOP_RIGHT;
                        break;
                    }
                    case ADDED_SMALL_TOP_RIGHT:
                    {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3, height/3/2);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);
                        rl.setPadding(0,padding,0,0);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.SMALL_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);
                        innerBox.addView(rl);

                        creationState = GridCreationState.START_NEW_ROW;
                        break;
                    }
                    case ADDED_SMALL_TOP_LEFT:
                    {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3, height/3/2);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);
                        rl.setPadding(0, padding, 0, 0);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.SMALL_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);
                        innerBox.addView(rl);

                        creationState = GridCreationState.ADDED_SMALL_BOTTOM_LEFT;
                        break;
                    }
                    case ADDED_SMALL_BOTTOM_LEFT:
                    {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width/3*2, height/3);
                        RelativeLayout rl = new RelativeLayout(view.getContext());
                        rl.setLayoutParams(layoutParams);

                        ImageView iv = initImage(item);
                        TextView tv = initText(SquareType.MEDIUM_ITEM,item);
                        rl.addView(iv);
                        rl.addView(tv);
                        outerBox.addView(rl);

                        creationState = GridCreationState.START_NEW_ROW;
                        break;
                    }
                }
            }
        }

        return view;
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------

    /**
     * Setup an image square which can be added to the layout in a central way...that's the reason
     * this method has been created.
     * @param item
     * @return
     */
    private ImageView initImage(ISquareItem item)
    {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        ImageView iv = new ImageView(getActivity());
        iv.setLayoutParams(params);
        iv.setImageResource(item.getImage());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return iv;
    }

    private TextView initText(SquareType squareType,
                              ISquareItem item)
    {
        // apply properties set within the square properties
        // (each square know how it should look like! approach)
        TextView tv = new TextView(getActivity());
        tv.setText(item.getTitle());
        tv.setTypeface(item.getFont(squareType));
        tv.setTextColor(item.getFontColor(squareType));
        tv.setShadowLayer(item.getShadowProperties(squareType).getRadius(),
                item.getShadowProperties(squareType).getDx(),
                item.getShadowProperties(squareType).getDy(),
                item.getShadowProperties(squareType).getColor());
        tv.setTextSize(item.getFontSize(squareType));

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(param);
        tv.setPadding(10,10,10,10);

        return tv;
    }

    /**
     * Translate a random ID to the corresponding enum layout type...smarter reading...
     * @param randomID
     * @return
     */
    private SquareType translateRandomIdToSquareType(int randomID)
    {
        switch (randomID)
        {
            case 0: return SquareType.LARGE_ITEM;
            case 1: return SquareType.MEDIUM_ITEM;
            case 2: return SquareType.SMALL_ITEM;
        }

        return null;
    }

    /**
     * Translate a random ID to the corresponding enum layout type...smarter reading...
     * @param randomID
     * @return
     */
    private RowStyle translateRandomIdToRowLayoutEnum(int randomID)
    {
        switch (randomID)
        {
            case 0: return RowStyle.LARGE_ONLY;
            case 1: return RowStyle.MEDIUM_SMALL_SMALL;
            case 2: return RowStyle.SMALL_SMALL_MEDIUM;
        }

        return null;
    }

    // ------------------------------------------------------------------------
    // GETTER & SETTER
    // ------------------------------------------------------------------------

    public List<ISquareItem> getItems()
    {
        return items;
    }

    /**
     * Provide data items for the staggered list...
     * @param items
     */
    public void setItems(List<ISquareItem> items)
    {
        this.items = items;
    }

    public int getPadding()
    {
        return padding;
    }

    /**
     * Set padding (default=15dp)
     * @param padding
     */
    public void setPadding(int padding)
    {
        this.padding = padding;
    }

    public String getBackgroundColor()
    {
        return backgroundColor;
    }

    /**
     * Set background color as RGB (default=#000000=black). Passing
     * in color string ("red","green" etc.) will work as well in case
     * you dont wanna fire up a color hex translator ;-)
     * @param backgroundColor
     */
    public void setBackgroundColor(String backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public boolean getAvoidDuplicateRandomLayouts()
    {
        return avoidDuplicateRandomLayouts;
    }

    /**
     * Default = false => real random reuslt...the problem with 100% random results:
     * layouts might occure twice/tripple etc. so the layout look "regular" instead
     * of mixed. This flag can be used to avoid duplicate row types series, setting
     * this flag to true results in a check which ensures the next row looks 100%ly
     * different to the previous one...
     * @param avoidDuplicateRandomLayouts
     */
    public void setAvoidDuplicateRandomLayouts(boolean avoidDuplicateRandomLayouts)
    {
        this.avoidDuplicateRandomLayouts = avoidDuplicateRandomLayouts;
    }
    // ------------------------------------------------------------------------
    // others
    // ------------------------------------------------------------------------

    /**
     * The different staggerd row layouts.
     */
    public enum RowStyle
    {
        LARGE_ONLY,SMALL_SMALL_MEDIUM,MEDIUM_SMALL_SMALL;
    }

    /**
     * Different stages the UI might traverse while creating the UI programmatically.
     * Smarter than dealing with state 1,2,3....
     */
    public enum GridCreationState
    {
        ADDED_MEDIUM,ADDED_SMALL_TOP_RIGHT,ADDED_SMALL_TOP_LEFT,ADDED_SMALL_BOTTOM_LEFT, START_NEW_ROW;
    }
}