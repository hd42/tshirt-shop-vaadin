package expo;

import static org.linkki.core.ui.section.annotations.AvailableValuesType.DYNAMIC;

import java.util.List;

import org.linkki.core.ui.components.ItemCaptionProvider.ToStringCaptionProvider;
import org.linkki.core.ui.section.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import expo.domain.TShirtOrder;


@SpringUI
@Theme("mytheme")
public class MyUI extends UI {

    // This Spring service is your backend. It has been autowired thanks to springframework
    @Autowired
    MyService service;

    @Override
    protected void init(VaadinRequest request) {
        DefaultPmoPage shirtPage = new DefaultPmoPage(new ShirtPmo(service));
        shirtPage.init();
        setContent(shirtPage);
    }
    
	@UISection(caption="Welcome")
	public static class ShirtPmo{

	    private MyService service;
		private TShirtOrder order;
	    
	    public ShirtPmo(MyService service){
			this.service = service;
			order = new TShirtOrder();
	    }
	    
	    @ModelObject
	    public TShirtOrder getOrder(){
	    	return order;
	    }
		
		@UITextField(position=10, label="Name")
		public void name() {
		}
		
		@UITextField(position=20, label="Email")
		public void email() {
		}
		
		@UIComboBox(position=30, content=DYNAMIC, itemCaptionProvider=ToStringCaptionProvider.class)
		public void shirtSize() {
		}
		public List<String> getShirtSizeAvailableValues(){
			return service.getSizes();
		}

		@UIButton(position=40, showLabel=false, caption="Place order", shortcutKeyCode=ShortcutAction.KeyCode.ENTER)
		public void order(){
			service.placeOrder(order);
		}
		
	}
}
