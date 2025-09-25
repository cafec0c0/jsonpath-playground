package net.adambruce;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;

public enum Theme {
    PRIMER_LIGHT("Primer Light", new PrimerLight().getUserAgentStylesheet()),
    PRIMER_DARK("Primer Dark", new PrimerDark().getUserAgentStylesheet()),
    CUPERTINO_DARK("Cupertino Dark", new CupertinoDark().getUserAgentStylesheet()),
    CUPERTINO_LIGHT("Cupertino Light", new CupertinoLight().getUserAgentStylesheet()),
    DRACULA("Dracula", new Dracula().getUserAgentStylesheet()),
    NORD_DARK("Nord Dark", new NordDark().getUserAgentStylesheet()),
    NORD_LIGHT("Nord Light", new NordLight().getUserAgentStylesheet()),
    ;

    private final String name;
    private final String style;

    Theme(String name, String style) {
        this.name = name;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }
}
