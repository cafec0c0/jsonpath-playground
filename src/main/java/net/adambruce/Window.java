package net.adambruce;

import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class Window extends BorderPane {

    public Window(Context context) {
        setPadding(new Insets(8));
        SplitPane splitPane = new SplitPane();

        splitPane.getItems().setAll(new JsonPane(context), new ResultPane(context));

        setTop(new QueryPane(context));
        setCenter(splitPane);
    }

}
