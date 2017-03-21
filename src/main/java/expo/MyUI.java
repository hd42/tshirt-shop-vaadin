package expo;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


@SpringUI
@Theme("mytheme")
public class MyUI extends UI {

    // This Spring service is your backend. It has been autowired thanks to springframework
    @Autowired
    MyService service;

    @Override
    protected void init(VaadinRequest request) {
        final Label text = new HtmlLabel("welcome.html");

        final ComboBox<String> shirtSize = new ComboBox<>("Shirt size");
        shirtSize.setEmptySelectionAllowed(false);
        shirtSize.setItems(service.getSizes());

        TextField name = new TextField("Name");
        TextField email = new TextField("Email");



        // Create and add a button to the screen (http://demo.vaadin.com/sampler/#ui/interaction/button)
        Button button = new Button("Place order");
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        button.addClickListener(e -> {
            // TODO call 'service' with the data collected form the form
            Notification.show("FIXME, Just testing!");
        });


        // Add the two textfields created above to a layout and make
        // that the main layout of the UI
        setContent(new VerticalLayout(text, shirtSize, name, email, button));
    }

}
