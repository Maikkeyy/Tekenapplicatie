/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Maikkeyy
 */
public class Drawing {
    private String name;
    private List<DrawingItem> items = new ArrayList<DrawingItem>();
      
    public String getName() { return name; }
    
    public void setName(String name) { this.name = name; }
    
    public List<DrawingItem> getItems() { return items; }
    
    public void addItem(DrawingItem item) {
        items.add(item);
    }
    
    public void removeItem(DrawingItem item) {
        items.remove(item);
    }
}
