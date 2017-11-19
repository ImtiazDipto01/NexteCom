package nextdot.com.nextecom.model;

/**
 * Created by sakib on 4/9/2017.
 */

public class CartItemModel {
    private String Cart_product_name, Cart_product_id, Cart_product_price ;
    private int Cart_Item_Quantity ;

    public int getCart_Item_Quantity() {
        return Cart_Item_Quantity;
    }

    public void setCart_Item_Quantity(int cart_Item_Quantity) {
        Cart_Item_Quantity = cart_Item_Quantity;
    }

    public String getCart_product_name() {
        return Cart_product_name;
    }

    public void setCart_product_name(String cart_product_name) {
        Cart_product_name = cart_product_name;
    }

    public String getCart_product_id() {
        return Cart_product_id;
    }

    public void setCart_product_id(String cart_product_id) {
        Cart_product_id = cart_product_id;
    }

    public String getCart_product_price() {
        return Cart_product_price;
    }

    public void setCart_product_price(String cart_product_price) {
        Cart_product_price = cart_product_price;
    }
}
