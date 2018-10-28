package com.nanotrick.ordershipper.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nanotrick.ordershipper.R;

/**
 * Created by milymozz on 2018. 4. 9..
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView txtOrderId, txtOrderDate, txtOrderStatus, txtOrderPhone, txtOrderAddress;

    public Button btnShipping;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderDate = (TextView) itemView.findViewById(R.id.order_date);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);

        btnShipping = (Button) itemView.findViewById(R.id.btnShipping);
    }

}
