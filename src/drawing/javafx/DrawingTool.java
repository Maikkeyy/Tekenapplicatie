/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import drawing.domain.*;
import drawing.domain.Color;
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Maikkeyy
 */
public class DrawingTool extends Application {
    private Drawing drawing; 
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    private IPaintable paintable; 
    private PersistencyMediator persistMediator;
    
    public DrawingTool() throws IOException {
        this.persistMediator = new SerializationMediator("C:\\Users\\Mike\\Dropbox\\JCC\\Week 4\\Tekenapplicatie\\tekening.ser"); 
        /*new DatabaseMediator("jdbc:mysql://localhost:3306/drawings", 
                "Mike", "nhjk043n7"); */
        this.drawing = persistMediator.load("tekening");
        this.drawingCanvas = new Canvas(250, 250);
        this.gc = drawingCanvas.getGraphicsContext2D();
        this.paintable = new JavaFXPaintable(gc);      
    }
    
    @Override
    public void start(Stage primaryStage) {
  
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 300);
        
        primaryStage.setTitle("Tekenapplicatie!");
        primaryStage.setScene(scene);
        draw();
        root.getChildren().add(drawingCanvas);
      //  persistMediator.save(drawing);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void draw() {
       /* Oval oval = new Oval(Color.RED, new Point(50, 50), 40, 80, 2);
        Oval oval2 = new Oval(Color.GREEN, new Point(70, 70), 100, 80, 10);
        PaintedText text = new PaintedText(Color.BLACK, new Point(20, 20), 20, 100,
        "Maikkeyy", "Serif");
        File file = new File("C:\\Users\\Mike\\Desktop\\JCC Images\\viking.jpg");
        Image image = new Image(Color.WHITE, new Point(150, 150), 100, 100, file);
        
        Point[] points = new Point[5];
        points[0] = new Point(20,80);
        points[1] = new Point(50,110);
        points[2] = new Point(34,140);
        points[3] = new Point(15,140);
        points[4] = new Point(11,110);
                
        Polygon polygon = new Polygon(Color.BLUE, points , 2);
        drawing.addItem(oval);
        drawing.addItem(text);
        drawing.addItem(polygon);
        drawing.addItem(oval2);
        drawing.addItem(image); */
        drawing.paintUsing(paintable);
    }
    
}
