package expo;

import org.linkki.core.binding.BindingContext;
import org.linkki.core.binding.BindingManager;
import org.linkki.core.binding.DefaultBindingManager;
import org.linkki.core.binding.validation.ValidationService;
import org.linkki.core.ui.page.AbstractPage;
import org.linkki.core.ui.section.DefaultPmoBasedSectionFactory;

public class DefaultPmoPage extends AbstractPage{

	private static final long serialVersionUID = 1L;

	private DefaultBindingManager bindingManager;
	private DefaultPmoBasedSectionFactory sectionFactory;
	private Object[] pmos;

	public DefaultPmoPage(Object... pmos) {
		this.pmos = pmos;
		bindingManager = new DefaultBindingManager(ValidationService.NOP_VALIDATION_SERVICE);
		sectionFactory = new DefaultPmoBasedSectionFactory();
	}
	@Override
	public void createContent() {
		BindingContext bindingContext = getBindingContext();
        
        //Create section and add to page
		for (Object pmo : pmos) {
			add(sectionFactory.createSection(pmo, bindingContext));
		}
        
        //update UI
        bindingContext.updateUIBindings();
	}

	@Override
	protected BindingManager getBindingManager() {
		return bindingManager;
	}
}