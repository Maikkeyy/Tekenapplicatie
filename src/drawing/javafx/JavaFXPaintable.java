/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;
import drawing.domain.*;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Maikkeyy
 */
public class JavaFXPaintable implements IPaintable {
    private GraphicsContext graphics;
    
    public JavaFXPaintable(GraphicsContext gc) {
        this.graphics = gc;
    }
    
    @Override
    public void paint(Oval oval) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Polygon polygon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(PaintedText text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
