package net.adambruce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Context context = new Context();

        Scene scene = new Scene(new Window(context));
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setTitle("JsonPath Playground");
        stage.show();

        context.themeProperty().subscribe(theme -> Application.setUserAgentStylesheet(theme.getStyle()));
    }

}