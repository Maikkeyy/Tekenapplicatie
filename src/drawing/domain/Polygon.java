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
public class Polygon extends DrawingItem {
    private Point[] vertices;
    private double weight;

    public Polygon(Color color, Point anchor, double width, double height,
            Point[] vertices, double weight) {
        super(color, anchor, width, height);
        this.vertices = vertices;
        this.weight = weight;
    }
    
    @Override
    public Point getAnchor() {
        return anchor;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
    
    public Point[] getVertices() {
        return this.vertices;
    }
    
    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }
    
    public double getWeight() {
        return this.weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "Polygon, Anchor: " + this.anchor.getX() + " : " + this.anchor.getY() + " Color: " + super.color + " Afmeting: " + this.width +
                " x " + this.height;
    }

    @Override
    public DrawingItem replicateCurrentItem() {
        return new Polygon(color, anchor, width, height, vertices, weight);
    }
    
}
