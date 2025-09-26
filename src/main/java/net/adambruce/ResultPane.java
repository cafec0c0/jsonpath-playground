package net.adambruce;

import atlantafx.base.theme.Styles;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ResultPane extends VBox {


    private final BooleanProperty isResultError = new SimpleBooleanProperty(false);

    public ResultPane(Context context) {
        setPadding(new Insets(8, 0, 0, 8));
        setSpacing(8);

        Label title = new Label("Result");
        title.getStyleClass().add(Styles.TITLE_4);

        TextArea jsonArea = new TextArea();
        VBox.setVgrow(jsonArea, Priority.ALWAYS);
        jsonArea.wrapTextProperty().bind(context.wrapJsonProperty());
        jsonArea.textProperty().bind(context.jsonContentProperty());
        jsonArea.setEditable(false);
        isResultError.subscribe(isError -> jsonArea.pseudoClassStateChanged(Styles.STATE_DANGER, isError));

        StringBinding styleBinding = Bindings.createStringBinding(() ->
                        "-fx-font-family: \"" + context.getFontFamily() + "\";" +
                                "-fx-font-size: " + context.getFontSize(),
                context.fontFamilyProperty(), context.fontSizeProperty()
        );
        jsonArea.styleProperty().bind(styleBinding);

        jsonArea.textProperty().bind(
                Bindings.createStringBinding(() -> testExpression(context.getJsonContent(), context.getExpression()),
                context.jsonContentProperty(), context.expressionProperty())
        );

        getChildren().setAll(title, jsonArea);
    }

    private String testExpression(String json, String path) {
        isResultError.set(false);
        if (json.isBlank() || path.isBlank()) {
            return "";
        }

        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(JsonPath.read(json, path));
        } catch (Exception ex) {
            isResultError.set(true);
            return ex.getMessage();
        }
    }
}
