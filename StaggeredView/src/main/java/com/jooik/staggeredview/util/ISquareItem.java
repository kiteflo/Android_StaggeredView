package com.jooik.staggeredview.util;

import android.graphics.Typeface;

/**
 * An interface definition enabling us to program against while setting up
 * a staggered view - each view item within the staggered view NEEDS to implemt
 * this interface.
 *
 * Anything which should be applicable to all of your view items (for example a
 * label) will be defined within this definition.
 */
public interface ISquareItem
{
    /**
     * Get image (R.drawable style...) for the item.
     * @return
     */
    public int getImage();

    /**
     * Get title for box which the will be displayed within the square image...
     * @return
     */
    public String getTitle();

    /**
     * Font size for image square...each square should define itself how it
     * looks like. As the item might look differently (small, large, medium...)
     * this method takes a parameter passing the rendering type into the method, so
     * you can finally decide within this method which font size should be used for
     * a large/medium/small square.
     * @return
     */
    public int getFontSize(SquareType squareType);

    /**
     * Font used for the labeling within this item. As the item might look differently
     * (small, large, medium...) this method takes a parameter passing the rendering type
     * into the method, so you can finally decide within this method which font size
     * should be used for a large/medium/small square.
     *
     * Should return -1 in case of unknown SquareType.
     * @return
     */
    public Typeface getFont(SquareType squareType);

    /**
     * Font color used within this item.
     * @param squareType
     * @return
     */
    public int getFontColor(SquareType squareType);

    /**
     * Shadow properties for the given item...
     * @param squareType
     * @return
     */
    public ShadowProperties getShadowProperties(SquareType squareType);
}
