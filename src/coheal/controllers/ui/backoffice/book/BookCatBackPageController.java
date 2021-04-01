/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.book;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.book.BookCategory;
import coheal.services.book.ServiceBookCategory;
import coheal.services.ui.UIService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
public class BookCatBackPageController implements Initializable {

        double xOffset, yOffset;

        @FXML
        private ScrollPane bookPane;
        @FXML
        private AnchorPane page;
        @FXML
        private JFXTextField recherchetf;
        @FXML
        private TableView<BookCategory> tabviewcat;
        
        @FXML
        private TableColumn<BookCategory, String> nomcol;
        @FXML
        private TableColumn<BookCategory, ImageView> imagecol;
       

        private int selectedId;
        private boolean canModify;
        @FXML
        private BarChart<?, ?> bars;
        @FXML
        private NumberAxis y;
        @FXML
        private CategoryAxis x;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                try {
                        statbar();
                } catch (SQLException ex) {
                        Logger.getLogger(BookCatBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                        ServiceBookCategory sbc = new ServiceBookCategory();
                        
                        nomcol.setCellValueFactory(new PropertyValueFactory<BookCategory, String>("name"));
                        imagecol.setCellValueFactory(new PropertyValueFactory<BookCategory, ImageView>("img"));

                        tabviewcat.setItems(sbc.AfficherBookCat());
                } catch (SQLException ex) {
                        Logger.getLogger(BookCatBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tabviewcat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                                //Check whether item is selected and set value of selected item to Label
                                if (tabviewcat.getSelectionModel().getSelectedItem() != null) {
                                        BookCategory selectedBook = tabviewcat.getSelectionModel().getSelectedItem();
                                        

                                        selectedId = selectedBook.getCatId();
                                        canModify = true;
                                        System.out.println(selectedId);

                                }
                        }

                });
        }

        @FXML
        private void Appelbookadd(MouseEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/book/addCat.fxml"));
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
                        ServiceBookCategory sbc = new ServiceBookCategory();
                       
                        nomcol.setCellValueFactory(new PropertyValueFactory<BookCategory, String>("name"));
                        imagecol.setCellValueFactory(new PropertyValueFactory<BookCategory, ImageView>("img"));
                        String t = recherchetf.getText();

                        tabviewcat.setItems(sbc.RechercheCatS(t));
                } catch (SQLException ex) {
                        Logger.getLogger(BookCatBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        @FXML
        private void refreshact(MouseEvent event) {
                try {
                        ServiceBookCategory sbc = new ServiceBookCategory();
                        
                        nomcol.setCellValueFactory(new PropertyValueFactory<BookCategory, String>("name"));
                        imagecol.setCellValueFactory(new PropertyValueFactory<BookCategory, ImageView>("img"));

                        tabviewcat.setItems(sbc.AfficherBookCat());
                } catch (SQLException ex) {
                        Logger.getLogger(BookCatBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tabviewcat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                                //Check whether item is selected and set value of selected item to Label
                                if (tabviewcat.getSelectionModel().getSelectedItem() != null) {
                                        BookCategory selectedBook = tabviewcat.getSelectionModel().getSelectedItem();
                                        
                                        selectedId = selectedBook.getCatId();
                                        canModify = true;
                                        System.out.println(selectedId);

                                }
                        }

                });
        }

        @FXML
        private void closeaction(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) bars.getScene().getWindow();
                stage.close();
        }
        public void statbar() throws SQLException{
                 UIService stc=new UIService();
                 
                 ServiceBookCategory sbc = new ServiceBookCategory();
                XYChart.Series set2 = new XYChart.Series<>();
                for(int i=0;i<3;i++){
                set2.getData().add(new XYChart.Data(sbc.AfficherBookCat().get(i).getName(),stc.ListerBooksByIdCatg(sbc.AfficherBookCat().get(i).getName()).size()));
                }
                bars.getData().addAll(set2);
        }

        @FXML
        private void supp(MouseEvent event) {
                ServiceBookCategory sbc = new ServiceBookCategory();
                System.out.println(selectedId);
               
                if (canModify) {
                        sbc.supprimerBookCat(selectedId);
                        
                        try {
                                tabviewcat.setItems(sbc.AfficherBookCat());
                        } catch (SQLException ex) {
                                Logger.getLogger(BookCatBackPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } else {
                        System.out.println("can't delete please select an item form the table");
                }
        }
         

}
