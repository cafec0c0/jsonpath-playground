package net.adambruce;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Context {
    private final StringProperty jsonContent = new SimpleStringProperty("");
    private final StringProperty expression = new SimpleStringProperty("");

    private final StringProperty fontFamily = new SimpleStringProperty("monospace");
    private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>(Theme.PRIMER_LIGHT);
    private final IntegerProperty fontSize = new SimpleIntegerProperty(14);
    private final BooleanProperty wrapJson = new SimpleBooleanProperty(true);

    public String getJsonContent() {
        return jsonContent.get();
    }

    public StringProperty jsonContentProperty() {
        return jsonContent;
    }

    public String getExpression() {
        return expression.get();
    }

    public StringProperty expressionProperty() {
        return expression;
    }

    public String getFontFamily() {
        return fontFamily.get();
    }

    public StringProperty fontFamilyProperty() {
        return fontFamily;
    }

    public Theme getTheme() {
        return theme.get();
    }

    public ObjectProperty<Theme> themeProperty() {
        return theme;
    }

    public int getFontSize() {
        return fontSize.get();
    }

    public IntegerProperty fontSizeProperty() {
        return fontSize;
    }

    public boolean isWrapJson() {
        return wrapJson.get();
    }

    public BooleanProperty wrapJsonProperty() {
        return wrapJson;
    }
}
