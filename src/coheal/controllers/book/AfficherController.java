/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;

import coheal.controllers.report.RateAlertUIController;
import coheal.controllers.report.RatePopupUIController;
import coheal.controllers.report.ReportPopupUIController;
import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import coheal.services.report.RateService;
import coheal.services.user.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Marwen
 */
public class AfficherController implements Initializable {

    boolean canModify = false;
    private int selectedId;
    //Report/Rate Module (Bilel Bellili)
    private ServiceUser su = new ServiceUser();
    private RateService sr = new RateService();

    @FXML
    private TableView<Book> tabviewsbook;
    @FXML
    private TableColumn<Book, String> imaglivre;
    @FXML
    private TableColumn<Book, String> titrelivre;
    @FXML
    private TableColumn<Book, String> auteurlivre;
    @FXML
    private TableColumn<Book, String> desclivre;
    @FXML
    private TextField selectimage;
    @FXML
    private TextField selecttitre;
    @FXML
    private TextField selectauteur;
    @FXML
    private TextField selectdescription;
    @FXML
    private TableColumn<Book, Integer> livId;
    @FXML
    private ComboBox<Integer> userIdBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceBook sb = new ServiceBook();
            imaglivre.setCellValueFactory(new PropertyValueFactory<Book, String>("imgUrl"));
            titrelivre.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            auteurlivre.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
            desclivre.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
            livId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));

            tabviewsbook.setItems(sb.AfficherBook2());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabviewsbook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tabviewsbook.getSelectionModel().getSelectedItem() != null) {
                    Book selectedBook = tabviewsbook.getSelectionModel().getSelectedItem();
                    selecttitre.setText(selectedBook.getTitle());
                    selectdescription.setText(selectedBook.getDescription());
                    selectauteur.setText(selectedBook.getAuthor());
                    selectimage.setText(selectedBook.getImgUrl());

                    selectedId = selectedBook.getBookId();
                    canModify = true;

                }
            }
        });
        //Report/Rate Module (Bilel Bellili)
        int num = su.AfficherPersonne().size();
        for (int i = 0; i < num; i++) {
            userIdBox.getItems().add(su.AfficherPersonne().get(i).getUserId());
        }
    }

    @FXML
    private void supprimeract(ActionEvent event) {
        ServiceBook sb = new ServiceBook();
        System.out.println(selectedId);
        if (canModify) {
            sb.supprimerBook(selectedId);
            try {
                tabviewsbook.setItems(sb.AfficherBook2());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't delete please select an item form the table");
        }
    }

    @FXML
    private void modifieract(ActionEvent event) {
        if (canModify) {
            Book b = new Book();
            ServiceBook sb = new ServiceBook();
            b.setImgUrl(selectimage.getText());
            b.setTitle(selecttitre.getText());
            b.setAuthor(selectauteur.getText());
            b.setDescription(selectdescription.getText());
            sb.modifierBook(selectedId, b);
            try {
                tabviewsbook.setItems(sb.AfficherBook2());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("can't modify please select an item form the table");
        }
    }

    @FXML
    private void retour1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/accueil.fxml"));
        Parent root = loader.load();
        AccueilController s2 = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    //Report/Rate Module (Bilel Bellili)
    @FXML
    private void ratePopupAction(ActionEvent event) throws IOException {
        if (sr.isRated(selectedId, userIdBox.getValue(), "Book")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Book");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Book");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    //Report/Rate Module (Bilel Bellili)
    @FXML
    private void reportPopupAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/reportui/ReportPopupUI.fxml"));
        Parent root = loader.load();
        ReportPopupUIController c = loader.getController();
        c.setData(selectedId, userIdBox.getValue(), "Book");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void rechercheAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/restrecherhce.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
