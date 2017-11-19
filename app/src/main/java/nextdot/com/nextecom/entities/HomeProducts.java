package nextdot.com.nextecom.entities;

import java.io.Serializable;

/**
 * Created by Zahan on 9/4/2016.
 */
public class HomeProducts  implements Serializable {
    String product_id,product_image, product_name, product_price, product_sale_percent,product_sale_price,product_warranty,product_emi;
    String product_specification, product_details;

    public int getCartvalue() {
        return cartvalue;
    }

    public void setCartvalue(int cartvalue) {
        this.cartvalue = cartvalue;
    }

    int cartvalue ;

    public String getProduct_specification() {
        return product_specification;
    }

    public void setProduct_specification(String product_specification) {
        this.product_specification = product_specification;
    }

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
    }

    public String getProduct_emi() {
        return product_emi;
    }

    public void setProduct_emi(String product_emi) {
        this.product_emi = product_emi;
    }

    public String getProduct_warranty() {
        return product_warranty;
    }

    public void setProduct_warranty(String product_warranty) {
        this.product_warranty = product_warranty;
    }

    float product_rating;

    public String getProduct_sale_price() {
        return product_sale_price;
    }

    public void setProduct_sale_price(String product_sale_price) {
        this.product_sale_price = product_sale_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public float getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(float product_rating) {
        this.product_rating = product_rating;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_sale_percent() {
        return product_sale_percent;
    }

    public void setProduct_sale_percent(String product_sale_percent) {
        this.product_sale_percent = product_sale_percent;
    }
}
