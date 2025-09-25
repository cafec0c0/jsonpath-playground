package net.adambruce;

import atlantafx.base.theme.Styles;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.List;

public class SettingsPane extends BorderPane {

    private static final List<String> FONT_FAMILIES = Font.getFamilies().stream()
            .filter(font -> !font.startsWith("Font Awesome"))
            .toList();

    private final StringProperty fontFamily = new SimpleStringProperty();
    private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
    private final IntegerProperty fontSize = new SimpleIntegerProperty();
    private final BooleanProperty wrapLines = new SimpleBooleanProperty();

    private final Context context;

    public SettingsPane(Context context) {
        this.context = context;

        initValues();

        setPadding(new Insets(8));

        GridPane gridPane = new GridPane(8, 8);

        BorderPane.setMargin(gridPane, new Insets(0, 0, 24, 0));

        Label themeLabel = new Label("Theme:");
        gridPane.add(themeLabel, 0, 0);

        ComboBox<Theme> themeCombo = new ComboBox<>();
        themeCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Theme theme) {
                return theme.getName();
            }

            @Override
            public Theme fromString(String s) {
                return Arrays.stream(Theme.values())
                        .filter(theme -> theme.getName().equals(s))
                        .findFirst()
                        .orElseThrow();
            }
        });
        themeCombo.getItems().setAll(Theme.values());
        themeCombo.getSelectionModel().select(theme.get());
        theme.bind(themeCombo.getSelectionModel().selectedItemProperty());
        themeCombo.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(themeCombo, 1, 0);
        GridPane.setHgrow(themeCombo, Priority.ALWAYS);

        Label fontLabel = new Label("Font Family:");
        gridPane.add(fontLabel, 0, 1);

        ComboBox<String> fontFamilyCombo = new ComboBox<>();
        fontFamilyCombo.getItems().setAll(FONT_FAMILIES);
        fontFamilyCombo.getSelectionModel().select(fontFamily.get());
        fontFamily.bind(fontFamilyCombo.getSelectionModel().selectedItemProperty());
        fontFamilyCombo.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(fontFamilyCombo, 1, 1);
        GridPane.setHgrow(fontFamilyCombo, Priority.ALWAYS);

        Label fontSizeLabel = new Label("Font Size:");
        gridPane.add(fontSizeLabel, 0, 2);

        Spinner<Integer> fontSizeSpinner = new Spinner<>();
        fontSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, fontSize.get()));
        fontSize.bind(fontSizeSpinner.valueProperty());
        fontSizeSpinner.setEditable(true);
        fontSizeSpinner.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(fontSizeSpinner, 1, 2);
        GridPane.setHgrow(fontSizeSpinner, Priority.ALWAYS);

        Label wrapTextLabel = new Label("Wrap JSON:");
        gridPane.add(wrapTextLabel, 0, 3);

        CheckBox wrapTextCheckBox = new CheckBox();
        wrapTextCheckBox.setMaxWidth(Double.MAX_VALUE);
        wrapTextCheckBox.setSelected(wrapLines.get());
        wrapLines.bind(wrapTextCheckBox.selectedProperty());
        gridPane.add(wrapTextCheckBox, 1, 3);
        GridPane.setHgrow(wrapTextCheckBox, Priority.ALWAYS);

        setCenter(gridPane);


        HBox buttons = new HBox();
        buttons.setSpacing(8);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button okButton = new Button("OK");
        okButton.getStyleClass().add(Styles.ACCENT);
        okButton.setOnAction(_ -> {
            applyChanges();
            ((Stage)getScene().getWindow()).close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(_ -> ((Stage)getScene().getWindow()).close());

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(_ -> applyChanges());

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().setAll(okButton, cancelButton, applyButton);

        setBottom(buttonBar);
    }

    private void initValues() {
        fontFamily.set(context.getFontFamily());
        theme.set(context.getTheme());
        fontSize.set(context.getFontSize());
        wrapLines.set(context.isWrapJson());
    }

    private void applyChanges() {
        context.fontFamilyProperty().set(fontFamily.get());
        context.themeProperty().set(theme.get());
        context.fontSizeProperty().set(fontSize.get());
        context.wrapJsonProperty().set(wrapLines.get());
    }
}
