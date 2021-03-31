/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.book;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class BookBackPageController implements Initializable {

        double xOffset, yOffset;
        boolean canModify = false;
        private int selectedId;
        @FXML
        private ScrollPane bookPane;
        @FXML
        private JFXTextField recherchetf;
        @FXML
        private TableView<Book> tabviewsbook;
        @FXML
        private TableColumn<Book, String> titrelivre;
        @FXML
        private TableColumn<Book, String> auteurlivre;
        @FXML
        private TableColumn<Book, String> desclivre;
        @FXML
        private TableColumn<Book, Integer> livId;
        @FXML
        private TableColumn<Book, ImageView> imageView;
        @FXML
        private TextField selectimage;
        @FXML
        private TextField selecttitre;
        @FXML
        private TextField selectauteur;
        @FXML
        private TextField selectdescription;
        @FXML
        private PieChart stattt;
        @FXML
        private AnchorPane page;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                stat();
                try {
                        ServiceBook sb = new ServiceBook();
                        imageView.setCellValueFactory(new PropertyValueFactory<Book, ImageView>("img"));
                        titrelivre.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
                        auteurlivre.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
                        desclivre.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
                        livId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));

                        tabviewsbook.setItems(sb.AfficherBook2());
                } catch (SQLException ex) {
                        Logger.getLogger(BookBackPageController.class.getName()).log(Level.SEVERE, null, ex);
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
        }

        @FXML
        private void Appelbookadd(MouseEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/book/addbook.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                HomePageHolderController hpc = new HomePageHolderController();
                hpc.setStage(stage);
                stage.show();
                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                        xOffset = mouseEvent.getSceneX();
                        yOffset = mouseEvent.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                        stage.setX(mouseEvent.getScreenX() - xOffset);
                        stage.setY(mouseEvent.getScreenY() - yOffset);
                        stage.setOpacity(0.85f);
                });
                root.setOnMouseReleased((MouseEvent mouseEvent) -> {
                        stage.setOpacity(1.0f);
                });
        }

        @FXML
        private void searchaction(MouseEvent event) {
                try {
                        ServiceBook sb = new ServiceBook();
                        imageView.setCellValueFactory(new PropertyValueFactory<Book, ImageView>("img"));
                        titrelivre.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
                        auteurlivre.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
                        desclivre.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
                        livId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));
                        int t = Integer.valueOf(recherchetf.getText());
                        tabviewsbook.setItems(sb.Rechercher(t));
                } catch (SQLException ex) {
                        Logger.getLogger(BookBackPageController.class.getName()).log(Level.SEVERE, null, ex);
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
                                Logger.getLogger(BookBackPageController.class.getName()).log(Level.SEVERE, null, ex);
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
                                Logger.getLogger(BookBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } else {
                        System.out.println("can't modify please select an item form the table");
                }
        }

        private void stat() {
                ServiceBook sb = new ServiceBook();
                String n1 = null;
                int v1 = 0;
                String n2 = null;
                int v2 = 0;
                String n3 = null;
                int v3 = 0;
                String n4 = null;
                int v4 = 0;
                String n5 = null;
                int v5 = 0;
                for (int i = 0; i < 4; i++) {
                        try {
                                n1 = (sb.vue().get(0).getTitle());
                                v1 = (sb.vue().get(0).getViews());
                                n2 = (sb.vue().get(1).getTitle());
                                v2 = (sb.vue().get(1).getViews());
                                n3 = (sb.vue().get(2).getTitle());
                                v3 = (sb.vue().get(2).getViews());
                                n4 = (sb.vue().get(3).getTitle());
                                v4 = (sb.vue().get(3).getViews());
                                n5 = (sb.vue().get(4).getTitle());
                                v5 = (sb.vue().get(4).getViews());

                        } catch (SQLException ex) {
                                System.out.println("rech controller errr");
                        }
                }
                ObservableList<PieChart.Data> PieChartData;
                PieChartData = FXCollections.observableArrayList(
                        new PieChart.Data(n1, v1),
                        new PieChart.Data(n2, v2),
                        new PieChart.Data(n3, v3),
                        new PieChart.Data(n4, v4),
                        new PieChart.Data(n5, v5));
                stattt.setData(PieChartData);
        }

        @FXML
        private void refreshact(MouseEvent event) {
                try {
                        ServiceBook sb = new ServiceBook();
                        imageView.setCellValueFactory(new PropertyValueFactory<Book, ImageView>("img"));
                        titrelivre.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
                        auteurlivre.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
                        desclivre.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
                        livId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));
                        tabviewsbook.setItems(sb.AfficherBook2());
                } catch (SQLException ex) {
                        Logger.getLogger(BookBackPageController.class.getName()).log(Level.SEVERE, null, ex);
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
        }

        @FXML
        private void bookCatappel(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/book/bookCatBackPage.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                HomePageHolderController hpc = new HomePageHolderController();
                hpc.setStage(stage);
                stage.show();
                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                        xOffset = mouseEvent.getSceneX();
                        yOffset = mouseEvent.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                        stage.setX(mouseEvent.getScreenX() - xOffset);
                        stage.setY(mouseEvent.getScreenY() - yOffset);
                        stage.setOpacity(0.85f);
                });
                root.setOnMouseReleased((MouseEvent mouseEvent) -> {
                        stage.setOpacity(1.0f);
                });

        }
       
       

}
