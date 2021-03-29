/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice;

import coheal.services.user.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class HomePageHolderController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;
    double xOffset, yOffset;
    private Stage stage;
    @FXML
    private AnchorPane taskSideBar;
    @FXML
    private AnchorPane eventSideBar;
    @FXML
    private AnchorPane sessionSideBar;
    @FXML
    private AnchorPane recipeSideBar;
    @FXML
    private AnchorPane bookSideBar;
    @FXML
    private AnchorPane homeSideBar;
    @FXML
    private AnchorPane profileSlider;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label userName;
    @FXML
    private Label userEmail;
    @FXML
    private Label userName1;
    @FXML
    private AnchorPane profileSideBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeSideBar.getStyleClass().add("selectedMenu");
        taskSideBar.getStyleClass().add("unselectedMenu");
        eventSideBar.getStyleClass().add("unselectedMenu");
        sessionSideBar.getStyleClass().add("unselectedMenu");
        recipeSideBar.getStyleClass().add("unselectedMenu");
        bookSideBar.getStyleClass().add("unselectedMenu");
        profileSideBar.getStyleClass().add("unselectedMenu");
        userName.setText(UserSession.getFirst_name() + " " + UserSession.getLast_name());
        userName1.setText(UserSession.getFirst_name() + " " + UserSession.getLast_name());
        userEmail.setText(UserSession.getEmail());

        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/HomePage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomePageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) label.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void homePageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(0);
        slide.play();
        homeMenu();
    }

    @FXML
    private void taskPageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(94);
        slide.play();
        taskMenu();
    }

    @FXML
    private void bookPageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(167);
        slide.play();
        bookMenu();
    }

    @FXML
    private void eventPageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(241);
        slide.play();
        eventMenu();
    }

    @FXML
    private void sessionPageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(314);
        slide.play();
        sessionMenu();
    }

    @FXML
    private void recipePageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(386);
        slide.play();
        recipeMenu();
    }

    @FXML
    private void profilePageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(491);
        slide.play();
        profileMenu();
    }

    public void bookMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("selectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/book/BookPage.fxml")));
    }

    public void taskMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("selectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskPage.fxml")));

    }

    public void eventMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("selectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");
        
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/EventPage.fxml")));
    }

    public void sessionMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("selectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");
        
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionPage.fxml")));
    }

    public void recipeMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("selectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");
        
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipePage.fxml")));
    }

    public void profileMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("selectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/user/ProfileUser.fxml")));
    }

    public void homeMenu() throws IOException {
        taskSideBar.getStyleClass().removeAll(taskSideBar.getStyleClass());
        taskSideBar.getStyleClass().add("menu");
        taskSideBar.getStyleClass().add("unselectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

        eventSideBar.getStyleClass().removeAll(eventSideBar.getStyleClass());
        eventSideBar.getStyleClass().add("menu");
        eventSideBar.getStyleClass().add("unselectedMenu");

        sessionSideBar.getStyleClass().removeAll(sessionSideBar.getStyleClass());
        sessionSideBar.getStyleClass().add("menu");
        sessionSideBar.getStyleClass().add("unselectedMenu");

        recipeSideBar.getStyleClass().removeAll(recipeSideBar.getStyleClass());
        recipeSideBar.getStyleClass().add("menu");
        recipeSideBar.getStyleClass().add("unselectedMenu");

        bookSideBar.getStyleClass().removeAll(bookSideBar.getStyleClass());
        bookSideBar.getStyleClass().add("menu");
        bookSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("selectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/HomePage.fxml")));
    }

    @FXML
    private void profileAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(profileSlider);
        slide.setToX(-324);
        slide.play();
    }

    @FXML
    private void backAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(profileSlider);
        slide.setToX(0);
        slide.play();
    }

    public AnchorPane getPageHolder() {
        return pageHolder;
    }

    @FXML
    private void userPorfileAction(MouseEvent event) throws IOException {
        profileMenu();
        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(0.4));
        slide2.setNode(profileSlider);
        slide2.setToX(0);
        slide2.play();
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(491);
        slide.play();
        profileMenu();
    }

    @FXML
    private void signOutAction(MouseEvent event) throws IOException {
        UserSession.cleanUserSession();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        Stage stage1 = (Stage) slider.getScene().getWindow();
        stage1.close();
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
    public void removeChildren(){
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
    }
}