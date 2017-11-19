package nextdot.com.nextecom.model;


import io.realm.RealmObject;

/**
 * Created by Admin on 4/14/2017.
 */

public class AddToCart extends RealmObject {

    private String cart_product_name, cart_product_id, cart_product_price ;
    private int cart_product_quantity ;

    public int getCart_product_quantity() {
        return cart_product_quantity;
    }

    public void setCart_product_quantity(int cart_product_quantity) {
        this.cart_product_quantity = cart_product_quantity;
    }

    public String getCart_product_name() {
        return cart_product_name;
    }

    public void setCart_product_name(String cart_product_name) {
        this.cart_product_name = cart_product_name;
    }

    public String getCart_product_id() {
        return cart_product_id;
    }

    public void setCart_product_id(String cart_product_id) {
        this.cart_product_id = cart_product_id;
    }

    public String getCart_product_price() {
        return cart_product_price;
    }

    public void setCart_product_price(String cart_product_price) {
        this.cart_product_price = cart_product_price;
    }
}
