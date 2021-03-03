/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers;

import coheal.entities.Recipe.Recipe;
import coheal.services.RecipeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 *
 * @author BilelxOS
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField TitreTF;
    @FXML
    private TextArea DescTF;
    @FXML
    private Button imgImpBT;
    @FXML
    private Label Affichage;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException {
        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setTitle(TitreTF.getText());
        R.setDescription(DescTF.getText());
        R.setImgUrl(null);
        RS.Create_Recipe(1,"Lunch",R);
    }

    @FXML
    private void Bouton_Afficher(ActionEvent event) {
        RecipeService RS = new RecipeService();
        Affichage.setText(RS.Afficher_Recipe().toString());
    }
    
    @FXML
    private void Bouton_Modifier(ActionEvent event) {
       RecipeService RS = new RecipeService();
       Recipe R = new Recipe();
       R.setTitle(TitreTF.getText());
       R.setTitle(DescTF.getText());
       //R.setTitle(TitreTF.getText());
       RS.Update_Recipe(R, 1);
    }

    @FXML
    private void Bouton_Supprimer(ActionEvent event) {
       RecipeService RS = new RecipeService();
       System.out.println("");
    }

   
    /* @FXML
    private void Importer_img_button(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        String filename = f.getAbsolutePath();
        //ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_DEFAULT));

        //  *error* img.setIcon(imageIcon);

        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream (image);
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum; (readNum=fis.read(buf))!=-1; ){
                bos.write(buf,0,readNum);
            }
            byte[] person_image = bos.toByteArray();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }*/

    @FXML
    private void Importer_img_button(ActionEvent event) {
    }
    
    /*
     Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/FXMLDocument2.fxml"));
            Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    */
}