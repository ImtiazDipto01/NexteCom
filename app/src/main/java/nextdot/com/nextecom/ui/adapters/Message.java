package nextdot.com.nextecom.ui.adapters;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sakib on 3/21/2017.
 */

public class Message {

    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
