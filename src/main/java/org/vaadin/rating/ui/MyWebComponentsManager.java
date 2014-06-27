package org.vaadin.rating.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.Page;

@SuppressWarnings("serial")
public class MyWebComponentsManager {
    
    public MyWebComponentsManager() {
        initializeJs();
    }
    
    void addComponent(String tag, String... attrs) {
        initializeWebComponent(tag);
        String js = "var el = document.createElement('" + tag + "');";
        for (int i = 1; i < attrs.length; i += 2) {
            js += "el." + attrs[i-1]+ " = '" + attrs[i]+ "';";
        }
        js += "document.body.appendChild(el);";
        Page.getCurrent().getJavaScript().execute(js);
    }
    
    private List<String> initializedComponents = new ArrayList<String>();
    private void initializeWebComponent(String tag) {
        if (!initializedComponents.contains(tag)) {
            initializedComponents.add(tag);
            String js = 
                    "var el = document.createElement('link');"
                  + "el.rel = 'import';"
                  + "el.href = 'VAADIN/wc/" + tag + ".html';"
                  + "document.head.appendChild(el);";
            Page.getCurrent().getJavaScript().execute(js);
        }
    }
    
    private boolean initializedJs = false;
    void initializeJs() {
        if (!initializedJs) {
            initializedJs = true;
            String js = 
                    "el = document.createElement('script');"
                  + "el.type = 'text/javascript';"
                  + "el.src = 'VAADIN/js/platform/platform.js';"
                  + "document.head.appendChild(el);";
            Page.getCurrent().getJavaScript().execute(js);
        }
    }
}
