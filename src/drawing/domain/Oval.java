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
public class Oval extends DrawingItem {
    private double weight;

    public Oval(Color color, Point anchor, double width, double height, double weight) {
        super(color, anchor, width, height);
        this.weight = weight;
    }
    
    @Override
    public Point getAnchor() {
        return this.anchor;
    } 
    
    public void setAnchor(Point anchor) {
        previousState = replicateCurrentItem();
        this.anchor = anchor;
    }

    @Override
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    @Override 
    public String toString() {
        return "Oval, Anchor: " + this.anchor.getX() + " : " + this.anchor.getY() + " Color: " + super.color + " Afmeting: " + this.width +
                " x " + this.height;
    }

    @Override
    public DrawingItem replicateCurrentItem() {
        return new Oval(color, anchor, width, height, weight);
    }
    
    @Override
    public void revert() {
        if(previousState == null) {
            System.out.println("Could not revert to state(no state was found).");
            return;
        }
        
        super.revert();
        Oval oval = (Oval)previousState;
        width = oval.width;
        height = oval.height;
        
    }

}
