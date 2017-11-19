package nextdot.com.nextecom.ui.widgtes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import nextdot.com.nextecom.R;

/**
 * Created by Zahan on 9/3/2016.
 */
public class SearchEditText extends RelativeLayout {

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.search_edit_text, this);
    }
}
