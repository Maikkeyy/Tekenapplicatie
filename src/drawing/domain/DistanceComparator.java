/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.Comparator;

/**
 *
 * @author Maikkeyy
 */
public class DistanceComparator implements Comparator<DrawingItem> {

    @Override
    public int compare(DrawingItem a, DrawingItem b) {
                double aX = a.getAnchor().getX();
                double aY = a.getAnchor().getY();
        
                double distanceA = Math.sqrt((aX) * (aX) + (aY) * (aY));
        
                double bX = b.getAnchor().getX();
                double bY = b.getAnchor().getY();
        
                double distanceB = Math.sqrt((bX) * (bX) + (bY) * (bY));
                
                return (int) (distanceA - distanceB);
    }
    
}
