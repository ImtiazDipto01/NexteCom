package nextdot.com.nextecom.ui.adapters;

/**
 * Created by sakib on 3/22/2017.
 */

public class DataProvider {
    private String id ;
    private String name ;
    private String price ;
    private String product_image ;

    public String getId() {
        return id;
    }

    public void setProduct_image(String product_image){
        this.product_image = product_image ;
    }

    public String getProduct_image(){
        return this.product_image ;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DataProvider(String id, String name, String price, String product_image){

        this.id = id ;
        this.name = name ;
        this.price = price ;
        this.product_image = product_image ;
    }

}
