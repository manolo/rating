package org.vaadin.rating.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.vaadin.rating.service.RatingService;
import org.vaadin.rating.service.User;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("rating")
@Widgetset("org.vaadin.rating.RatingWidgetSet")
@SuppressWarnings("serial")
@CDIUI
public class RatingUI extends UI
{

    @Inject
    CDIViewProvider viewProvider;

    @Inject
    RatingService service;

    @Inject
    User user;

    Navigator navigator = new Navigator(this, this);

    @Override
    protected void init(VaadinRequest request) {
        navigator.addProvider(viewProvider);
        // Alpha level CDI add-on is still quite chatty, lets cut the noice
        Logger.getLogger(CDIViewProvider.class.getCanonicalName()).setLevel(Level.SEVERE);

        // Add our hello-world web-component to the UI
        MyWebComponentsManager webComp = new MyWebComponentsManager();
        webComp.addComponent("hello-world", "who", "vaadin", "more", "}>");
    }
}
