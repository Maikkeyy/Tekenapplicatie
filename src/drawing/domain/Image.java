/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.File;

/**
 *
 * @author Maikkeyy
 */
public class Image extends DrawingItem {
    private File file;
    
    public Image(Color color, Point anchor, double width, double height,
            File file)  {
        super(color, anchor, width, height);
        this.file = file;
    }
    
    @Override
    public Point getAnchor() {
        return this.anchor;
    }
    
    public void setAnchor(Point anchor) {
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
    
    public File getFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        return "Image, Anchor: " + this.anchor.getX() + " : " + this.anchor.getY() + " Color: " + super.color + " Afmeting: " + this.width +
                " x " + this.height;
    }

    @Override
    public DrawingItem replicateCurrentItem() {
        return new Image(color, anchor, width, height, file);
    }
   
    
}
