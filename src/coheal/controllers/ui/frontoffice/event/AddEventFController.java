/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.entities.event.Event;
import coheal.entities.event.EventCategory;
import coheal.services.event.ServiceCategory;
import coheal.services.event.ServiceEvent;
import coheal.services.event.ServiceMail;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddEventFController implements Initializable {

    @FXML
    private JFXTextField price;
    @FXML
    private JFXRadioButton paid;
    @FXML
    private ToggleGroup type1;
    @FXML
    private JFXRadioButton free;
    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private JFXTextField Titre;
    @FXML
    private JFXTextArea Description;
    @FXML
    private JFXComboBox<String> comboEventCatg;
    @FXML
    private ImageView image;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXTextField location;
    private ServiceEvent se = new ServiceEvent();
    File f = null;
    Event e = new Event();
    ServiceCategory sc = new ServiceCategory();
    private String cat = "";
    private ToggleGroup group = new ToggleGroup();
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    private boolean titleV = false, descriptionV = false, locationV = false, priceV = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paid.setToggleGroup(group);
        free.setToggleGroup(group);
        price.disableProperty().bind(free.selectedProperty());
        for (int i = 0; i < sc.AfficherCategoryEvent().size(); i++) {
            comboEventCatg.getItems().add(sc.AfficherCategoryEvent().get(i).getName());
        }

        titleValidator();
        priceValidator();
        locationValidator();
        descriptionValidator();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) Titre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void listCategoryCombo(ActionEvent event) {
        cat = comboEventCatg.getValue();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        Image img = new Image("file:///" + f);
        image.setImage(img);
        if (f != null) {
            e.setImgUrl(f.getName());
        };
    }

    @FXML
    private void addEventAction(MouseEvent event) {
        e.setUserId(UserSession.getUser_id());
        e.setTitle(Titre.getText());
        e.setDescription(Description.getText());
        e.setStartDate(java.sql.Date.valueOf(startDate.getValue()));
        e.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        e.setLocation(location.getText());
        EventCategory ec = sc.getEventByName(cat);
        if (paid.isSelected() && price.getText() != "") {
            e.setPrice(Double.valueOf(price.getText()));
        }
        e.setCatId(ec.getCatId());
        e.setCat(ec);
        if (paid.isSelected()) {
            e.setPrice(Double.valueOf(price.getText()));
            e.setType("paid");
        } else {
            e.setType("free");
        }
        File dest = null;
        if (f != null) {
            dest = new File(projectPath + "/src/coheal/resources/images/events/" + f.getName());
        }
        try {
            if (dest != null) {
                if (FileUtils.contentEquals(f, dest)) {
                    System.out.println("existe");
                } else {
                    FileUtils.copyFile(f, dest);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        se.AddEvent(e);
        ServiceMail SM = new ServiceMail();

        String Msg = "Bonjour Mr/Mme , Il y'a un nouvel évènement bientôt , Veuillez consulter l'application pour plus de details  ";

        SM.sendmailfunc("farah.gargabou@esprit.tn", Msg);
    }

    public void titleValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        Titre.setValidators(valid);
        valid.setMessage("Title is not valid");
        Titre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    Titre.validate();
                    if (Titre.validate()) {
                        titleV = true;
                    } else {
                        titleV = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void descriptionValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        Description.setValidators(valid);
        valid.setMessage("Description is not valid");
        Description.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    Description.validate();
                    if (Description.validate()) {
                        descriptionV = true;
                    } else {
                        descriptionV = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void priceValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^(0|[1-9][0-9]*)$");
        price.setValidators(valid);
        valid.setMessage("Not valid");
        price.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    price.validate();
                    if (price.validate()) {
                        priceV = true;
                    } else {
                        priceV = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void locationValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        location.setValidators(valid);
        valid.setMessage("Not valid");
        location.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    location.validate();
                    if (location.validate()) {
                        locationV = true;
                    } else {
                        locationV = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
