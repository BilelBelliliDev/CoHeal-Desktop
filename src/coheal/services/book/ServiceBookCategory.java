/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.book;

import coheal.entities.book.BookCategory;
import static coheal.services.book.Constants.projectPath;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author Marwen
 */
public class ServiceBookCategory {
         Connection cnx;
        public ServiceBookCategory()  {
                cnx=MyConnection.getInstance().getConnection();
        }
        
      public void AjouterBookCategory(BookCategory bc){
               try {
                        Statement stm=cnx.createStatement();
                        
                
                String query="INSERT INTO book_category(  name ,img_url) VALUES ('"+bc.getName()+"','"+bc.getImgUrl()+"')";
                stm.executeUpdate(query);
                
                }
                 catch (SQLException ex) {
                        System.out.println("le");
                } 
      }
        
       public ObservableList<BookCategory>AfficherBookCat()throws SQLException {
                Statement stm=cnx.createStatement();
                        String query="SELECT * FROM book_category WHERE is_deleted = 0 ";
                        ResultSet rst=stm.executeQuery(query);
                         ObservableList<BookCategory> data = FXCollections.observableArrayList();
                          while(rst.next()){
                                BookCategory bc= new BookCategory();
                                bc.setCatId(rst.getInt("cat_id"));
                                bc.setName(rst.getString("name"));
                                bc.setImgUrl(rst.getString("img_url"));
                                ImageView img = null;
                                String url = "file:///" + projectPath + "\\src\\coheal\\resources\\images\\bookCat\\" + rst.getString("img_url");
                        img = new ImageView(url);
                        img.setFitHeight(100);
                        img.setFitWidth(100);
                        bc.setImg(img);
                                
                                data.add(bc);
                               
                                
                        }
                          return data;
               
       }
      
     public void supprimerBookCat(int id){
             try{
              Statement stm=cnx.createStatement();
            String query;
            query ="update book_category set is_deleted = 1,deleted_at=CURRENT_TIMESTAMP  WHERE cat_id  = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
     public void modifierBookCat(int id, BookCategory bc){
                    try {
            Statement stm=cnx.createStatement();
            String query;
            query ="update book_category set name = '"+bc.getName()+"', img_url = '"+bc.getImgUrl()+"' where cat_id = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
       public ObservableList<BookCategory>Recherche(int t)throws SQLException {
                Statement stm=cnx.createStatement();
                        String query="SELECT * FROM book_category WHERE is_deleted = 0 AND cat_id =" + t + " ";
                        ResultSet rst=stm.executeQuery(query);
                         ObservableList<BookCategory> data = FXCollections.observableArrayList();
                          while(rst.next()){
                                BookCategory bc= new BookCategory();
                                bc.setCatId(rst.getInt("cat_id"));
                                bc.setName(rst.getString("name"));
                                bc.setImgUrl(rst.getString("img_url"));
                                
                                data.add(bc);
                               
                                
                        }
                          return data;
               
       }
}
