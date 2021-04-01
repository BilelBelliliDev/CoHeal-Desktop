/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import coheal.entities.report.Report;
import coheal.services.report.ReportService;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
    ReportService reportService = new ReportService();
    List<Report> reportsList, filteredReportsList, fList;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new ZoomOut(filterDialogPane).play();
        filteredReportsList = new ArrayList();
        fList = new ArrayList();
        checkBoxListeners();
        reportsList = reportService.allReportsList();

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
        openCheckBox.setSelected(false);
        closedCheckBox.setSelected(false);
        filteredReportsList = new ArrayList();
        fList = new ArrayList();
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
                    if (closedCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("task")).filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        fList.addAll(reportsList);
                        System.out.println(fList.size());
                    } else if (openCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("task")).filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("task"))
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        fList.addAll(filteredReportsList);
                        System.out.println(filteredReportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(taskItem);
                    if (!isChecked()) {
                        filteredReportsList = new ArrayList();
                        reportsList = reportService.allReportsList();
                    } else {
                        if (closedCheckBox.isSelected()) {
                            fList = fList.stream()
                                    .filter(r -> !r.getType().contains("task")).filter(r -> r.isIsClosed())
                                    .collect(Collectors.toList());
                            System.out.println(fList.size());
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("task"))
                                    .collect(Collectors.toList());
                        } else {
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("task"))
                                    .collect(Collectors.toList());
                            System.out.println(filteredReportsList.size());
                        }
                    }
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
                    if (closedCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("book")).filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        fList.addAll(reportsList);
                        System.out.println(fList.size());
                    } else if (openCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("book")).filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("book"))
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                        fList.addAll(filteredReportsList);
                    }
                } else {
                    filtersPane.getChildren().remove(bookItem);
                    if (!isChecked()) {
                        filteredReportsList = new ArrayList();
                        reportsList = reportService.allReportsList();
                    } else {
                        if (closedCheckBox.isSelected()) {
                            fList = fList.stream()
                                    .filter(r -> !r.getType().contains("book")).filter(r -> r.isIsClosed())
                                    .collect(Collectors.toList());
                            System.out.println(fList.size());
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("book"))
                                    .collect(Collectors.toList());
                        } else {
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("book"))
                                    .collect(Collectors.toList());
                            System.out.println(filteredReportsList.size());
                        }
                    }
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
                    if (closedCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("event")).filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        fList.addAll(reportsList);
                        System.out.println(fList.size());
                    } else if (openCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("event")).filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("event"))
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        fList.addAll(filteredReportsList);
                        System.out.println(filteredReportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(eventItem);
                    if (!isChecked()) {
                        filteredReportsList = new ArrayList();
                        reportsList = reportService.allReportsList();
                    } else {
                        if (closedCheckBox.isSelected()) {
                            fList = fList.stream()
                                    .filter(r -> !r.getType().contains("event")).filter(r -> r.isIsClosed())
                                    .collect(Collectors.toList());
                            System.out.println(fList.size());
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("event"))
                                    .collect(Collectors.toList());
                        } else {
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("event"))
                                    .collect(Collectors.toList());
                            System.out.println(filteredReportsList.size());
                        }
                    }
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
                    if (closedCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("session")).filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        fList.addAll(reportsList);
                        System.out.println(fList.size());
                    } else if (openCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("session")).filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("session"))
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        fList.addAll(filteredReportsList);
                        System.out.println(filteredReportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(sessionItem);
                    if (!isChecked()) {
                        filteredReportsList = new ArrayList();
                        reportsList = reportService.allReportsList();
                    } else {
                        if (closedCheckBox.isSelected()) {
                            fList = fList.stream()
                                    .filter(r -> !r.getType().contains("session")).filter(r -> r.isIsClosed())
                                    .collect(Collectors.toList());
                            System.out.println(fList.size());
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("session"))
                                    .collect(Collectors.toList());
                        } else {
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("session"))
                                    .collect(Collectors.toList());
                            System.out.println(filteredReportsList.size());
                        }
                    }
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
                    if (closedCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("recipe")).filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        fList.addAll(reportsList);
                        System.out.println(fList.size());
                    } else if (openCheckBox.isSelected()) {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("recipe")).filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.getType().contains("recipe"))
                                .collect(Collectors.toList());
                        filteredReportsList.addAll(reportsList);
                        fList.addAll(filteredReportsList);
                        System.out.println(filteredReportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(recipeItem);
                    if (!isChecked()) {
                        filteredReportsList = new ArrayList();
                        reportsList = reportService.allReportsList();
                    } else {
                        if (closedCheckBox.isSelected()) {
                            fList = fList.stream()
                                    .filter(r -> !r.getType().contains("recipe")).filter(r -> r.isIsClosed())
                                    .collect(Collectors.toList());
                            System.out.println(fList.size());
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("recipe"))
                                    .collect(Collectors.toList());
                        } else {
                            filteredReportsList = filteredReportsList.stream()
                                    .filter(r -> !r.getType().contains("recipe"))
                                    .collect(Collectors.toList());
                            System.out.println(filteredReportsList.size());
                        }
                    }
                }
            }
        });
        openCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));
                    closedCheckBox.setSelected(false);
                    try {
                        openItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Open");
                    filtersPane.getChildren().add(openItem);
                    if (typeIsChecked()) {
                        reportsList = reportService.allReportsList();
                        reportsList = filteredReportsList.stream()
                                .filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(openItem);
                    if (typeIsChecked()) {
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> !r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    }
                }
            }
        });
        closedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/FilterItem.fxml"));
                    openCheckBox.setSelected(false);
                    try {
                        closedItem = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FilterItemController c = loader.getController();
                    c.setData("Closed");
                    filtersPane.getChildren().add(closedItem);
                    if (typeIsChecked()) {
                        reportsList = reportService.allReportsList();
                        reportsList = filteredReportsList.stream()
                                .filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    }
                } else {
                    filtersPane.getChildren().remove(closedItem);
                    if (typeIsChecked()) {
                        System.out.println(filteredReportsList.size());
                    } else {
                        reportsList = reportService.allReportsList();
                        reportsList = reportsList.stream()
                                .filter(r -> r.isIsClosed())
                                .collect(Collectors.toList());
                        System.out.println(reportsList.size());
                    }
                }
            }
        });

    }

    public boolean isChecked() {
        return tasksCheckBox.isSelected() || eventsCheckBox.isSelected() || booksCheckBox.isSelected() || sessionsCheckBox.isSelected() || recipesCheckBox.isSelected() || closedCheckBox.isSelected() || openCheckBox.isSelected();
    }

    public boolean typeIsChecked() {
        return tasksCheckBox.isSelected() || eventsCheckBox.isSelected() || booksCheckBox.isSelected() || sessionsCheckBox.isSelected() || recipesCheckBox.isSelected();
    }
}
