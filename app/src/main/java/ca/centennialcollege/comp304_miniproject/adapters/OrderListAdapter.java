package ca.centennialcollege.comp304_miniproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.centennialcollege.comp304_miniproject.R;
import ca.centennialcollege.comp304_miniproject.models.Order;
import ca.centennialcollege.comp304_miniproject.models.OrderStatus;

public class OrderListAdapter extends ArrayAdapter<Order> {
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtClientName;
        TextView txtStatus;
        TextView txtReason;
    }

    public OrderListAdapter(List<Order> data, Context context) {
        super(context, R.layout.list_item_order, data);
        this.mContext=context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Order order = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_order, parent, false);
            viewHolder.txtClientName = convertView.findViewById(R.id.lst_row_txt_client_name);
            viewHolder.txtStatus = convertView.findViewById(R.id.lst_row_txt_status);
            viewHolder.txtReason = convertView.findViewById(R.id.lst_row_txt_reason);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_bottom : R.anim.down_top);
        result.startAnimation(animation);
        lastPosition = position;

        if (order != null) {
            viewHolder.txtClientName.setText(mContext.getString(R.string.lst_row_client_name,
                    order.getClient().getName()));
            viewHolder.txtStatus.setText(mContext.getString(R.string.lst_row_order_status,
                    order.getStatus().getDescription()));
            if (order.getStatus() == OrderStatus.NOT_DELIVERED) {
                viewHolder.txtReason.setText(mContext.getString(R.string.lst_row_status_reason,
                        order.getStatusReason()));
                viewHolder.txtReason.setVisibility(View.VISIBLE);
            } else {
                viewHolder.txtReason.setVisibility(View.GONE);
            }
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
