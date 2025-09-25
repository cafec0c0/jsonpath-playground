package net.adambruce;

import atlantafx.base.theme.Styles;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class JsonPane extends VBox {

    public JsonPane(Context context) {
        setPadding(new Insets(8, 8, 0, 0));
        setSpacing(8);

        Label title = new Label("JSON");
        title.getStyleClass().add(Styles.TITLE_4);

        TextArea jsonArea = new TextArea();
        VBox.setVgrow(jsonArea, Priority.ALWAYS);

        jsonArea.wrapTextProperty().bind(context.wrapJsonProperty());

        StringBinding styleBinding = Bindings.createStringBinding(() ->
                "-fx-font-family: \"" + context.getFontFamily() + "\";" +
                "-fx-font-size: " + context.getFontSize(),
                context.fontFamilyProperty(), context.fontSizeProperty()
        );
        jsonArea.styleProperty().bind(styleBinding);
        context.jsonContentProperty().bind(jsonArea.textProperty());

        getChildren().setAll(title, jsonArea);
    }
}
