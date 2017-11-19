package nextdot.com.nextecom.entities;

import java.util.ArrayList;

/**
 * Created by Zahan on 9/4/2016.
 */
public class HomeItems {

    String id,title;
    ArrayList<HomeProducts> homeProductses=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HomeProducts> getHomeProductses() {
        return homeProductses;
    }

    public void setHomeProductses(ArrayList<HomeProducts> homeProductses) {
        this.homeProductses = homeProductses;
    }
}
