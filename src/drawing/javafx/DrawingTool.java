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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Maikkeyy
 */
public class DrawingTool extends Application {
    private Drawing drawing; 
    private Canvas drawingCanvas;
    private GraphicsContext gc = this.drawingCanvas.getGraphicsContext2D();
    private IPaintable paintable;
    
    public DrawingTool() {
        this.drawing = new Drawing();
        this.paintable = new JavaFXPaintable(gc);
        this.drawing.paintUsing(paintable);  
    }
    
    @Override
    public void start(Stage primaryStage) {
  
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void draw() {
        
    }
    
}
