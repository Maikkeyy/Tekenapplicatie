/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import drawing.domain.Drawing;
import drawing.domain.Image;
import drawing.domain.Oval;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maikkeyy
 */
public class DrawingTest {
    private Drawing drawing;
    
    public DrawingTest() {
        drawing = new Drawing();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void TestAddItem() {
        Oval oval = new Oval();
        drawing.addItem(oval);
        
        Assert.assertEquals(1, drawing.getItems().size());
        Assert.assertEquals(oval, drawing.getItems().get(0));
        
        Image image = new Image();
        drawing.addItem(image);
        
        Assert.assertEquals(2, drawing.getItems().size());
        Assert.assertEquals(image, drawing.getItems().get(1));
    }
}
