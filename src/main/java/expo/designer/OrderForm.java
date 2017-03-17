package expo.designer;


import java.util.List;

public class OrderForm extends OrderFormDesign {

    public void setSizes(List<String> sizes) {
        shirtSize.setItems(sizes);
    }
}
