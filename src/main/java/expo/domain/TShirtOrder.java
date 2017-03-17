package expo.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mstahv
 */
public class TShirtOrder {

    @NotEmpty
    @Email
    private String email  = "";
    @NotEmpty
    private String name = "";
    @NotEmpty
    private String shirtSize;

    public TShirtOrder() {
    }

    public TShirtOrder(String name, String email, String shirtSize) {
        this.name = name;
        this.email = email;
        this.shirtSize = shirtSize;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }
    
}
