package nextdot.com.nextecom.testdata;

import java.util.ArrayList;

import nextdot.com.nextecom.entities.Adverts;
import nextdot.com.nextecom.entities.Banner;
import nextdot.com.nextecom.entities.Category;
import nextdot.com.nextecom.entities.HomeItems;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.entities.Review;

/**
 * Created by Zahan on 9/3/2016.
 */
public class DemoData {

    public static ArrayList<Category> categories;
    public static ArrayList<Banner> banners;
    public static ArrayList<HomeItems> homeItemses;
    public static ArrayList<Adverts> advertses;
    public static ArrayList<HomeProducts> homeProductses;
    public static ArrayList<Review> reviews;

    static {
        categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setId("1");
        category1.setName("Hot Products");
        category1.setIcon("R.drawable.ic_launcher");

        Category category2 = new Category();
        category2.setId("2");
        category2.setName("Mobile & Tablet");
        category2.setIcon("R.drawable.ic_launcher");

        Category category3 = new Category();
        category3.setId("3");
        category3.setName("Electronics");
        category3.setIcon("R.drawable.ic_launcher");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        banners = new ArrayList<>();
        Banner banner1 = new Banner();
        banner1.setId("1");
        banner1.setImage("https://thumbs.dreamstime.com/t/landscape-nature-horizontal-storm-40559734.jpg");
        banner1.setProduct_id("1");

        Banner banner2 = new Banner();
        banner2.setId("2");
        banner2.setImage("https://thumbs.dreamstime.com/t/landscape-nature-horizontal-storm-40559734.jpg");
        banner2.setProduct_id("2");

        Banner banner3 = new Banner();
        banner3.setId("3");
        banner3.setImage("https://thumbs.dreamstime.com/t/landscape-nature-horizontal-storm-40559734.jpg");
        banner3.setProduct_id("3");

        Banner banner4 = new Banner();
        banner4.setId("4");
        banner4.setImage("https://thumbs.dreamstime.com/t/landscape-nature-horizontal-storm-40559734.jpg");
        banner4.setProduct_id("4");

        banners.add(banner1);
        banners.add(banner2);
        banners.add(banner3);
        banners.add(banner4);


        HomeProducts homeProducts1 = new HomeProducts();
        homeProducts1.setProduct_id("1");
        homeProducts1.setProduct_name("Samsung j6");
        homeProducts1.setProduct_price("1503");
        homeProducts1.setProduct_rating(4.5f);
        homeProducts1.setProduct_sale_percent("35%");
        homeProducts1.setProduct_image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017869-blue-jelly-icon-symbols-shapes-shape-square-frame.png");
        homeProducts1.setProduct_emi("51%off get soon");
        homeProducts1.setProduct_sale_price("1200");
        homeProducts1.setProduct_warranty("1 year post service");
        homeProducts1.setProduct_specification("Easily mobile at just 6 pounds, " +
                "the Toshiba Satellite A135-S4527 makes it easy to get your work" +
                " done with a large, bright 15.4-inch widescreen LCD. The XGA-resolution");
        homeProducts1.setProduct_details("this is details1, this is details1, this is details1, this is details1, this is details1, this is details1, this is details1, this is details1");

        HomeProducts homeProducts2 = new HomeProducts();
        homeProducts2.setProduct_id("2");
        homeProducts2.setProduct_name("Samsung j7");
        homeProducts2.setProduct_price("1502");
        homeProducts2.setProduct_rating(4.3f);
        homeProducts2.setProduct_sale_percent("20%");
        homeProducts2.setProduct_image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017869-blue-jelly-icon-symbols-shapes-shape-square-frame.png");
        homeProducts2.setProduct_emi("51%off get soon");
        homeProducts2.setProduct_sale_price("1200");
        homeProducts2.setProduct_warranty("1 year post service");
        homeProducts2.setProduct_specification("Easily mobile at just 6 pounds, " +
                "the Toshiba Satellite A135-S4527 makes it easy to get your work" +
                " done with a large, bright 15.4-inch widescreen LCD. The XGA-resolution " +
                "screen (1280 x 800) permits side-by-side viewing of documents for " +
                "increased productivity. It's also great for using as a media center, " +
                "with Toshiba's unique Express Media Player enabling you to bypass the " +
                "system and access CDs and DVDs with a touch of button. This affordable notebook " +
                "PC is powered by Intel's 1.73 GHz Core Duo T2080 processor, which provides an " +
                "optimized, multithreaded architecture for improved gaming and multitasking" +
                " performance and efficient power consumption.maximum)");
        homeProducts2.setProduct_details("this is details2, this is details2, this is details2, this is details2, this is details2, this is details2");

        HomeProducts homeProducts3 = new HomeProducts();
        homeProducts3.setProduct_id("3");
        homeProducts3.setProduct_name("Samsung j8 sdad  asda asd asd  aswdasd asdsa as");
        homeProducts3.setProduct_price("1500012110");
        homeProducts3.setProduct_rating(4.1f);
        homeProducts3.setProduct_sale_percent("21%");
        homeProducts3.setProduct_image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017906-blue-jelly-icon-symbols-shapes-square.png");
        homeProducts3.setProduct_emi("51%off get soon");
        homeProducts3.setProduct_sale_price("1200256455");
        homeProducts3.setProduct_warranty("1 year post service");
        homeProducts3.setProduct_specification("Easily mobile at just 6 pounds, " +
                "the Toshiba Satellite A135-S4527 makes it easy to get your work");
        homeProducts3.setProduct_details("this is details3, this is details3, this is details3 , this is details3, this is details3, this is details3");


        HomeProducts homeProducts4 = new HomeProducts();
        homeProducts4.setProduct_id("4");
        homeProducts4.setProduct_name("Samsung j9");
        homeProducts4.setProduct_price("");
        homeProducts4.setProduct_rating(2.3f);
        homeProducts4.setProduct_sale_percent("");
        homeProducts4.setProduct_image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017869-blue-jelly-icon-symbols-shapes-shape-square-frame.png");
        homeProducts4.setProduct_emi("51%off get soon");
        homeProducts4.setProduct_sale_price("1200");
        homeProducts4.setProduct_warranty("1 year post service");
        homeProducts4.setProduct_specification("Easily mobile at just 6 pounds, " +
                "the Toshiba Satellite A135-S4527 makes it easy to get your work");
        homeProducts4.setProduct_details("this is details4, this is details4 , this is details4, this is details4");

        homeProductses = new ArrayList<>();
        homeProductses.add(homeProducts1);
        homeProductses.add(homeProducts2);
        homeProductses.add(homeProducts3);
        homeProductses.add(homeProducts4);

        HomeItems homeItems1 = new HomeItems();
        homeItems1.setId("1");
        homeItems1.setTitle("Hot Deals");
        homeItems1.setHomeProductses(homeProductses);

        HomeItems homeItems2 = new HomeItems();
        homeItems2.setId("2");
        homeItems2.setTitle("Mobile & Tablet");
        homeItems2.setHomeProductses(homeProductses);

        homeItemses = new ArrayList<>();
        homeItemses.add(homeItems1);
        homeItemses.add(homeItems2);
        homeItemses.add(homeItems1);
        homeItemses.add(homeItems2);


        Adverts adverts1 = new Adverts();
        adverts1.setId("1");
        adverts1.setTitle("Microsoft Lumia Flash sale");
        adverts1.setInfo("get upto 31% discount");
        adverts1.setImage("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTLNW8zgJyY1Z9aRdKKLPlwOXrY3P0HkS-MbBKXX18GpQpLuj9r");

        Adverts adverts2 = new Adverts();
        adverts2.setId("2");
        adverts2.setTitle("Pre-Book Helio S2");
        adverts2.setInfo("get free helio gift box");
        adverts2.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_ae-pEwSnEDDzkKjFR4675wtXItyf-vKhHOiMrfgxf22gGvVFHQ");


        advertses = new ArrayList<>();
        advertses.add(adverts1);
        advertses.add(adverts2);

        reviews = new ArrayList<>();
        Review review1 = new Review();
        review1.setProduct_id("1");
        review1.setUser_name("Zahan");
        review1.setReview_text("This product is good");

        Review review2 = new Review();
        review2.setProduct_id("1");
        review2.setUser_name("Zahan");
        review2.setReview_text("This product is good");

        Review review3 = new Review();
        review3.setProduct_id("1");
        review3.setUser_name("Zahan");
        review3.setReview_text("This product is good");

        Review review4 = new Review();
        review4.setProduct_id("2");
        review4.setUser_name("Zahan");
        review4.setReview_text("This product is good");

        Review review5 = new Review();
        review5.setProduct_id("2");
        review5.setUser_name("Zahan");
        review5.setReview_text("This product is good");

        Review review6 = new Review();
        review6.setProduct_id("2");
        review6.setUser_name("Zahan");
        review6.setReview_text("This product is good");
        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);
        reviews.add(review6);


    }

    public static ArrayList<Category> fetchCategories() {
        return categories;
    }

    public static ArrayList<Banner> fetchBanners() {
        return banners;
    }

    public static ArrayList<HomeItems> fetchHomeItems() {
        return homeItemses;
    }

    public static ArrayList<Adverts> fetchAdverts() {
        return advertses;
    }

    public static ArrayList<HomeProducts> fetchHomeProductses() {
        return homeProductses;
    }

    public static HomeProducts fetchHomeProduct(String id) {
        for (int i = 0; i < homeProductses.size(); i++) {
            if (id.equals(homeProductses.get(i).getProduct_id())) {
                return homeProductses.get(i);
            }
        }
        return homeProductses.get(4);
    }
    public static ArrayList<Review> fetchProductReview(String id) {
        ArrayList<Review> filteredReview=new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            if (id.equals(reviews.get(i).getProduct_id())) {
                filteredReview.add(reviews.get(i));
            }
        }
       return filteredReview;
    }
}
