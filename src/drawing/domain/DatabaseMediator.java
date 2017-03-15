/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMediator implements PersistencyMediator {
    private Properties props;
    private Connection con;
    
    public DatabaseMediator(String connString, String username, String password) throws FileNotFoundException, IOException { // Uitzoeken hoe het zit met deze exceptions
        props = new Properties();
        props.setProperty("url", connString);
        props.setProperty("username", username);
        props.setProperty("password", password);
    }

    @Override
    public Drawing load(String nameDrawing){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        Drawing drawing = new Drawing(nameDrawing);
        ResultSet rsId = null;
        int Id;
        
        try {
            initConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
           stmt = con.prepareStatement("SELECT Id FROM drawing WHERE Name = ?");
           stmt.setString(1, nameDrawing);
                  
            rsId = stmt.executeQuery();
            rsId.next();
            Id = rsId.getInt(1);
            
            stmt2 = con.prepareStatement("SELECT * FROM drawingitem di\n" +
                                        "LEFT JOIN image i ON i.Id = di.Id\n" +
                                        "LEFT JOIN oval o ON o.Id = di.Id\n" +
                                        "LEFT JOIN paintedtext pt ON pt.Id = di.Id\n" +
                                        "WHERE Drawing_Id = ?");
            
            stmt2.setInt(1, Id);
            
            rs = stmt2.executeQuery();
            
                while (rs.next()) {
                    if("oval".equals(rs.getString("type"))) {
                        Oval oval = mapOval(rs);                         
                        drawing.addItem(oval);
                    }
                    if("image".equals(rs.getString("type"))) {
                        Image image = mapImage(rs);
                        drawing.addItem(image);
                    }
                    if("paintedtext".equals(rs.getString("type"))) {
                        PaintedText text = mapText(rs);
                        drawing.addItem(text);
                    }

                }
            } catch(SQLException ex) {

            }
     
        return drawing;
    }
    
    // Mapping functions to translate database data to objects
    private Oval mapOval(ResultSet rs) throws SQLException {
        Oval oval = new Oval();
        oval.setColor(Color.valueOf(rs.getString("Color")));
        oval.setAnchor(new Point(rs.getDouble("x"), rs.getDouble("y")));
        oval.setWidth(rs.getDouble("Width"));
        oval.setHeight(rs.getDouble("Height"));
        oval.setWeight(rs.getDouble("Weight"));
        
        return oval;
    }
    
    private Image mapImage(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setColor(Color.valueOf(rs.getString("Color")));
        image.setAnchor(new Point(rs.getDouble("x"), rs.getDouble("y")));
        image.setWidth(rs.getDouble("Width"));
        image.setHeight(rs.getDouble("Height"));
        image.setFile(new File(rs.getString("FilePath")));
        
        return image;
    }
    
    private PaintedText mapText(ResultSet rs) throws SQLException {
        PaintedText text = new PaintedText();
        text.setColor(Color.valueOf(rs.getString("Color")));
        text.setAnchor(new Point(rs.getDouble("x"), rs.getDouble("y")));
        text.setWidth(rs.getDouble("Width"));
        text.setHeight(rs.getDouble("Height"));
        text.setContent(rs.getString("Content"));
        text.setFontName(rs.getString("FontName"));
        
        return text;
    }
    
    @Override
    public Boolean save(Drawing drawing) {
        int autoId;
        
        try {
            initConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        PreparedStatement preparedStatement = null;
        String drawingSql = "INSERT INTO drawing (Name) VALUES (?)";
        
        String sql = "INSERT INTO drawingitem (Drawing_Id, Color, x, y, width, height, type)" 
                           + "VALUES(?,?,?,?,?,?,?)";
        
        try {
            preparedStatement = con.prepareStatement(drawingSql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, drawing.getName());
            
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            autoId = rs.getInt(1);
            
              for(DrawingItem item : drawing.getItems()) {
                    preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, autoId);
                    preparedStatement.setString(2, item.getColor().toString());
                    preparedStatement.setDouble(3, item.getAnchor().getX());
                    preparedStatement.setDouble(4, item.getAnchor().getY());
                    preparedStatement.setInt(5, (int) item.getWidth());
                    preparedStatement.setInt(6, (int) item.getHeight());
                    
                  if(item instanceof Oval) {     
                    preparedStatement.setString(7, "oval");

                    preparedStatement.executeUpdate();

                    ResultSet rs2 = preparedStatement.getGeneratedKeys();
                    Oval oval = (Oval)item;
                    
                    // execute query oval table
                    persistOval(rs2, oval);    
                }   
                if(item instanceof PaintedText) {
                    preparedStatement.setString(7, "text");

                    preparedStatement.executeUpdate();

                    ResultSet rs2 = preparedStatement.getGeneratedKeys();               
                    PaintedText text = (PaintedText)item;
                   
                     // execute query paintedtext table
                    persistText(rs2, text);  
                }   
                if(item instanceof Image) {
                    preparedStatement.setString(7, "image");

                    preparedStatement.executeUpdate();

                    ResultSet rs2 = preparedStatement.getGeneratedKeys(); 
                    Image image = (Image)item;
                    
                    // execute query image table
                    persistImage(rs2, image);
                }   
                if(item instanceof Polygon) {
                    preparedStatement.setString(7, "polygon");
                    
                    preparedStatement.executeUpdate();
                    
                    ResultSet rs2 = preparedStatement.getGeneratedKeys(); 
                    Polygon polygon = (Polygon)item;
                    
                    // execute query polygon table
                    persistPolygon(rs2, polygon);  
                }   
        } 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if(con != null) {
                try {
                    closeConnection();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        return null;
    }
    
    private void persistOval(ResultSet rs, Oval oval) throws SQLException {
        rs.next();
        int itemId = rs.getInt(1);  
        
        String sql = "INSERT INTO oval (Id, Weight) VALUES (?,?)";
        PreparedStatement preStmt = con.prepareStatement(sql);
        
        preStmt.setInt(1, itemId);
        preStmt.setInt(2, (int)oval.getWeight());
        preStmt.executeUpdate();
    }
    
    private void persistText(ResultSet rs, PaintedText text) throws SQLException {
        rs.next();
        int itemId = rs.getInt(1);  
        
        String sql = "INSERT INTO PaintedText (Id, Content, FontName) VALUES (?,?,?)";
        PreparedStatement preStmt = con.prepareStatement(sql);
        
        preStmt.setInt(1, itemId);
        preStmt.setString(2, text.getContent());
        preStmt.setString(3, text.getFontName());
        preStmt.executeUpdate();
    }
    
    private void persistImage(ResultSet rs, Image image) throws SQLException {
        rs.next();
        int itemId = rs.getInt(1);  
        
        String sql = "INSERT INTO Image (Id, FilePath) VALUES (?,?)";
        PreparedStatement preStmt = con.prepareStatement(sql);
        
        preStmt.setInt(1, itemId);
        preStmt.setString(2, image.getFile().getAbsolutePath());
        preStmt.executeUpdate();
    }
    
    private void persistPolygon(ResultSet rs, Polygon polygon) throws SQLException {
        rs.next();
        int itemId = rs.getInt(1);  
        
        String sql = "INSERT INTO polygon (Id, Weight) VALUES (?,?)";
        PreparedStatement preStmt = con.prepareStatement(sql);
        
        preStmt.setInt(1, itemId);
        preStmt.setInt(3, (int) polygon.getWeight());
        preStmt.executeUpdate();
    }

    @Override
    public Boolean init(Properties props) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void closeConnection() throws SQLException {
        con.close();
    }
    
    private void initConnection() throws SQLException {
        con = DriverManager.getConnection(props.getProperty("url"), 
                props.getProperty("username"), props.getProperty("password"));
           
    }
}
