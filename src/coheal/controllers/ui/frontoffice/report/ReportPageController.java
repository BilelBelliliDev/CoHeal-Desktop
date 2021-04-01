/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ReportPageController implements Initializable {

    AnchorPane taskItem, bookItem, eventItem, sessionItem, recipeItem, openItem, closedItem, allItem;
    private boolean filterIsDisplayed = false;
    @FXML
    private AnchorPane reportPane;
    @FXML
    private Label noUrgReportsLabel;
    @FXML
    private Label noNewReportsLabel;
    @FXML
    private FlowPane filtersPane;
    @FXML
    private AnchorPane filterDialogPane;
    @FXML
    private JFXCheckBox tasksCheckBox;
    @FXML
    private JFXCheckBox booksCheckBox;
    @FXML
    private JFXCheckBox eventsCheckBox;
    @FXML
    private JFXCheckBox sessionsCheckBox;
    @FXML
    private JFXCheckBox recipesCheckBox;
    @FXML
    private JFXCheckBox openCheckBox;
    @FXML
    private JFXCheckBox closedCheckBox;
    @FXML
    private JFXCheckBox allCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomOut(filterDialogPane).play();
        checkBoxListeners();
    }

    @FXML
    private void filtreAction(MouseEvent event) {
        if (filterIsDisplayed) {
            filterIsDisplayed = false;
            new ZoomOut(filterDialogPane).play();
        } else {
            filterIsDisplayed = true;
            new ZoomIn(filterDialogPane).play();

        }
    }

    @FXML
    private void clearFiltersAction(MouseEvent event) {
        tasksCheckBox.setSelected(false);
        eventsCheckBox.setSelected(false);
        booksCheckBox.setSelected(false);
        sessionsCheckBox.setSelected(false);
        recipesCheckBox.setSelected(false);
        allCheckBox.setSelected(false);
        openCheckBox.setSelected(false);
        closedCheckBox.setSelected(false);
    }

    public void checkBoxListeners() {
        tasksCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        taskItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Tasks");
                    filtersPane.getChildren().add(taskItem);
                    allCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(taskItem);
                }
            }
        });
        booksCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        bookItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Books");
                    filtersPane.getChildren().add(bookItem);
                    allCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(bookItem);
                }
            }
        });
        eventsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        eventItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Events");
                    filtersPane.getChildren().add(eventItem);
                    allCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(eventItem);
                }
            }
        });
        sessionsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        sessionItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Sessions");
                    filtersPane.getChildren().add(sessionItem);
                    allCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(sessionItem);
                }
            }
        });
        recipesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        recipeItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Recipes");
                    filtersPane.getChildren().add(recipeItem);
                    allCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(recipeItem);
                }
            }
        });
        openCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        openItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Open");
                    filtersPane.getChildren().add(openItem);

                } else {
                    filtersPane.getChildren().remove(openItem);
                }
            }
        });
        closedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        closedItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Closed");
                    filtersPane.getChildren().add(closedItem);

                } else {
                    filtersPane.getChildren().remove(closedItem);
                }
            }
        });
        allCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));

                    try {
                        allItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("All");
                    filtersPane.getChildren().add(allItem);
                    tasksCheckBox.setSelected(false);
                    eventsCheckBox.setSelected(false);
                    booksCheckBox.setSelected(false);
                    sessionsCheckBox.setSelected(false);
                    recipesCheckBox.setSelected(false);
                } else {
                    filtersPane.getChildren().remove(allItem);
                }
            }
        });
    }

}
