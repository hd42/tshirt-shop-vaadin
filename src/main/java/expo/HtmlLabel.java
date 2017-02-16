package expo;

import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HtmlLabel extends Label {

    public HtmlLabel(String resource) {
        try {
            setValue(FileUtils.readFileToString(new File(getClass().getClassLoader().getResource(resource).getFile())));
            setContentMode(ContentMode.HTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
