package nextdot.com.nextecom.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.Collections;
import java.util.List;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.model.CartItemModel;

/**
 * Created by Admin on 4/15/2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<CartItemModel> data = Collections.emptyList();
    private Context context ;
    private OnItemClickListener mOnItemClickListener ;

    public interface OnItemClickListener{
        void OnItemClick(View view, int postion, CartItemModel obj) ;
    }

    public void setmOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener ;
    }

    public CartItemAdapter(Context context, List<CartItemModel> data){
        inflater = LayoutInflater.from(context);
        this.data = data ;
        this.context = context ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cart, parent, false) ;
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final CartItemModel current_data = data.get(position) ;
        holder.product_name.setText(current_data.getCart_product_name());
        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CartItemDailouge();
                //Toast.makeText(context, "ok !!", Toast.LENGTH_SHORT).show();
                if(mOnItemClickListener != null){
                    mOnItemClickListener.OnItemClick(v, position, current_data);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView product_name ;
        public MaterialRippleLayout lyt_parent ;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.cart_product_title);
            lyt_parent = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_parent);
        }
    }

}
