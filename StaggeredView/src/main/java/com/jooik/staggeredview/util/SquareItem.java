package com.jooik.staggeredview.util;

import android.graphics.Typeface;

/**
 * Demo implememtation for a square item which is used within a szaggered
 * view...
 */
public class SquareItem
    implements ISquareItem
{
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    private String title;
    private int image;

    private Typeface font;
    private int fontsize_small;
    private int fontsize_medium;
    private int fontsize_large;
    private int fontColor;
    private ShadowProperties shadowProperties;

    // ------------------------------------------------------------------------
    // constructors
    // ------------------------------------------------------------------------

    public SquareItem(String label,int image)
    {
        this.title = label;
        this.image = image;
    }

    // ------------------------------------------------------------------------
    // public usage & helpers...
    // ------------------------------------------------------------------------

    public void applyFontStyle(int fontsize_large,int fontsize_medium,
                               int fontsize_small, Typeface font, int fontColor,
                               ShadowProperties shadowProperties)
    {
        this.fontsize_large = fontsize_large;
        this.fontsize_medium = fontsize_medium;
        this.fontsize_small = fontsize_small;

        this.font = font;
        this.fontColor = fontColor;
        this.shadowProperties = shadowProperties;
    }

    // ------------------------------------------------------------------------
    // GETTER & SETTER
    // ------------------------------------------------------------------------

    @Override
    public int getImage()
    {
        return this.image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    @Override
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public int getFontSize(SquareType squareType)
    {
        switch (squareType)
        {
            case LARGE_ITEM:
                return fontsize_large;
            case MEDIUM_ITEM:
                return fontsize_medium;
            case SMALL_ITEM:
                return fontsize_small;
        }

        return -1;
    }

    @Override
    public Typeface getFont(SquareType squareType)
    {
        return this.font;
    }

    @Override
    public int getFontColor(SquareType squareType)
    {
        return this.fontColor;
    }

    @Override
    public ShadowProperties getShadowProperties(SquareType squareType)
    {
        return this.shadowProperties;
    }
}
