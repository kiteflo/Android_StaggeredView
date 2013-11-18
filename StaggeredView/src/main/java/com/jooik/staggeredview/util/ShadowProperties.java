package com.jooik.staggeredview.util;

/**
 * Contains necessary properties in order to generate a shadow for a
 * font. This is just a simple property aggregator...
 */
public class ShadowProperties
{
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    float radius;
    int dx;
    int dy;
    int color;

    // ------------------------------------------------------------------------
    // constructors
    // ------------------------------------------------------------------------

    public ShadowProperties(float radius,int dx,int dy,int color)
    {
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    // ------------------------------------------------------------------------
    // GETTER & SETTER
    // ------------------------------------------------------------------------

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public int getDx()
    {
        return dx;
    }

    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public int getDy()
    {
        return dy;
    }

    public void setDy(int dy)
    {
        this.dy = dy;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
