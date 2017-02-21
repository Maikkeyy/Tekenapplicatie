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
public interface IPaintable {
    void paint(Oval oval);
    void paint(Polygon polygon);
    void paint(PaintedText text);
    void paint(Image image); 
}
