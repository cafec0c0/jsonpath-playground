package net.adambruce;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class QueryPane extends HBox {

    private final Context context;

    public QueryPane(Context context) {
        setPadding(new Insets(0, 0, 8, 0));
        setSpacing(8);
        setAlignment(Pos.CENTER_LEFT);

        this.context = context;

        Label label = new Label("Expression:");
        TextField queryField = new TextField();

        StringBinding styleBinding = Bindings.createStringBinding(() ->
                        "-fx-font-family: \"" + context.getFontFamily() + "\";" +
                                "-fx-font-size: " + context.getFontSize(),
                context.fontFamilyProperty(), context.fontSizeProperty()
        );
        queryField.styleProperty().bind(styleBinding);

        context.expressionProperty().bind(queryField.textProperty());
        HBox.setHgrow(queryField, Priority.ALWAYS);

        Button settingsButton = new Button();
        settingsButton.setOnAction(this::showSettings);
        settingsButton.setMinSize(0, 0);

        // Make sure we're the same height as the query field. Bind width to height so we're a square
        settingsButton.prefHeightProperty().bind(queryField.heightProperty());
        settingsButton.prefWidthProperty().bind(settingsButton.heightProperty());

        FontIcon icon = new FontIcon();
        icon.setIconLiteral("fas-cog");
        settingsButton.setGraphic(icon);
        settingsButton.setAlignment(Pos.CENTER);
        settingsButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        settingsButton.setTooltip(new Tooltip("Preferences"));

        getChildren().setAll(label, queryField, settingsButton);
    }

    private void showSettings(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(new SettingsPane(context)));
        stage.setTitle("Preferences");
        stage.showAndWait();
    }
}
