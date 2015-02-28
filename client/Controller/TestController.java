package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Dag Frode on 27.02.2015.
 */
public class TestController{
    private Pane parentPane;

public TestController( Pane parentPane) throws Exception {
    System.out.println(parentPane);
    this.parentPane = parentPane;
    Parent root = FXMLLoader.load(getClass().getResource("../View/Test.fxml"));
    this.parentPane.getChildren().addAll(root);
}
}
