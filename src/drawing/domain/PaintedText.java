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
public class PaintedText extends DrawingItem {
    private String content;
    private String fontName;
    
    public PaintedText(Color color, Point anchor, double width, double height,
            String content, String fontName)  {
        super(color, anchor, width, height);
        this.content = content;
        this.fontName = fontName;
    }
    
    @Override
    public Point getAnchor() {
        return anchor;
    }
    
    public void setAnchor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    
    @Override
    public String toString() {
        return "PaintedText, Anchor: " + this.anchor.getX() + " : " + this.anchor.getY() + " Color: " + super.color + " Afmeting: " + this.width +
                " x " + this.height;
    }

    @Override
    public DrawingItem replicateCurrentItem() {
        return new PaintedText(color, anchor, width, height, content, fontName);
    }
}
