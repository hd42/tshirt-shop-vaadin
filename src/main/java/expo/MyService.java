package expo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import expo.domain.TShirtOrder;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Service
class MyService {
    
    private Validator validator;
    
    @PostConstruct
    void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    /**
     * Posts an order with given details. All details are required.
     * 
     * @param name The name for the customer
     * @param email The email for the customer
     * @param shirtSize The size of shirt
     */
    public void placeOrder(String name, String email, String shirtSize) {
        
        try {
            TShirtOrder order = new TShirtOrder(name, email, shirtSize);
            
            Set<ConstraintViolation<TShirtOrder>> validate = validator.validate(order);
            if(!validate.isEmpty()) {
                Notification.show("All parameters must be set and valid!", Notification.Type.ERROR_MESSAGE);
                return;
            }
            
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<TShirtOrder> entity = new HttpEntity<>(order,headers);
            String url = "http://vaad.in/orderTShirt";
            // String url = "http://localhost:8080/orderTShirt";
            try {
                restTemplate.put(url, entity);
                Notification notification = new Notification("Congrats!", "Your details were submitted correctly, you should soon receive a confirmation email with tips to get started with Vaadin.");
                notification.setDelayMsec(5000);
                notification.show(Page.getCurrent());
            } catch (RestClientException e) {
                Notification.show("Sending order failed: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return;
            }

        } catch (Exception ex) {
                Notification.show("Sending order failed: " + ex.getMessage().substring(0, 28) + "...", Notification.Type.ERROR_MESSAGE);
                ex.printStackTrace();
        }
    }

}
