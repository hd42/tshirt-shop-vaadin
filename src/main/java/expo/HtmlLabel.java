package expo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

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
