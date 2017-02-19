/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

/**
 *
 * @author Maikkeyy
 */
public abstract class DrawingItem {
    protected Color color;
    protected Point anchor;
    protected double width;
    protected double height;
    
    protected DrawingItem previousState = null;
    
    public DrawingItem(Color color, Point anchor, double width, double height) {
        this.color = color;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }
    
    public Color getColor() { return color; }
    public void setColor(Color color) { 
        previousState = replicateCurrentItem();
        this.color = color; }
    
    public abstract Point getAnchor();
    public abstract double getWidth();
    public abstract double getHeight();
    public abstract DrawingItem replicateCurrentItem();
    
    @Override
    public String toString() {
        return color.toString();
    }
    
    public void revert() {
        color = previousState.color;
        anchor = previousState.anchor;
        
        if(previousState.previousState == null) {
            previousState = null;
        }
        else {
            previousState = previousState.previousState;
        }
    }
}
