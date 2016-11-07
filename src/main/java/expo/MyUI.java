package expo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.label.RichText;

@Theme("valo")
@SpringUI
public class MyUI extends UI {

    // This Spring service is your backend. It has been autowired thanks to springframework
    @Autowired
    MyService service;

    final Label text = new RichText().withMarkDownResource("/welcome.md");

    final TextField shirtSize = new TextField("Shirt size");
    // TODO add name and email TextFields as well

    @Override
    protected void init(VaadinRequest request) {

        // Add the two textfields created above to a layout and make 
        // that the main layout of the UI
        final VerticalLayout layout = new VerticalLayout(text, shirtSize);
        setContent(layout);

        // Create and add a button to the screen (http://demo.vaadin.com/sampler/#ui/interaction/button)
        Button button = new Button("Place order");
        layout.addComponents(button);
        
        button.addClickListener(e -> {
            // TODO call 'service' with the data collected form the form
            Notification.show("FIXME, Just testing!");
        });

        // Some visual styling for the layout for nicer look 'n' feel. 
        // Alternatively doable in CSS/Sass
        layout.setMargin(true);
        layout.setSpacing(true);
    }

}
