package ca.centennialcollege.comp304_miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import ca.centennialcollege.comp304_miniproject.models.DataRepository;
import ca.centennialcollege.comp304_miniproject.models.Deliverer;
import ca.centennialcollege.comp304_miniproject.models.Order;
import ca.centennialcollege.comp304_miniproject.models.OrderStatus;

public class OrderStatusActivity extends AppCompatActivity {

    Order order;
    OrderStatus nextStatus;
    Spinner spnDeliverer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("Order");

        TextView tvwClient = findViewById(R.id.tvwClientName);
        tvwClient.setText(order.getClient().getName());

        TextView tvwStatus = findViewById(R.id.tvwStatus);
        tvwStatus.setText(order.getStatus().getDescription());

        TextView tvwAddress = findViewById(R.id.tvwAddress);
        tvwAddress.setText(order.getDeliveryAddress().toString());

        TextView lblDeliverer = findViewById(R.id.lblDeliverer);
        spnDeliverer = findViewById(R.id.spnDeliverer);
        Button btnUpdateOrder = findViewById(R.id.btnUpdateOrder);

        if ((order.getStatus() == OrderStatus.RECEIVED)
                || (order.getStatus() == OrderStatus.NOT_DELIVERED)) {
            lblDeliverer.setVisibility(View.VISIBLE);
            spnDeliverer.setVisibility(View.VISIBLE);
            btnUpdateOrder.setVisibility(View.VISIBLE);
            btnUpdateOrder.setText(R.string.order_status_btn_assign);
            nextStatus = OrderStatus.ASSIGNED_TO_DELIVERER;

            // Load the spinner with the deliverers
            ArrayAdapter<Deliverer> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, Collections.list(DataRepository.getDeliverers()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnDeliverer.setAdapter(adapter);

        } else if (order.getStatus() == OrderStatus.ASSIGNED_TO_DELIVERER) {
            lblDeliverer.setVisibility(View.GONE);
            spnDeliverer.setVisibility(View.GONE);
            btnUpdateOrder.setVisibility(View.VISIBLE);
            btnUpdateOrder.setText(R.string.order_status_btn_transit);
            nextStatus = OrderStatus.IN_TRANSIT;
        } else {
            lblDeliverer.setVisibility(View.GONE);
            spnDeliverer.setVisibility(View.GONE);
            btnUpdateOrder.setVisibility(View.GONE);
        }
    }

    public void btnUpdateOrder_onClick(View v) {
        Order order = DataRepository.getOrder(this.order.getId());
        String message;

        if (this.order.getStatus() == order.getStatus()) {
            order.setStatus(nextStatus);
            if (nextStatus == OrderStatus.ASSIGNED_TO_DELIVERER) {
                Deliverer develirer = (Deliverer) spnDeliverer.getSelectedItem();
                DataRepository.UpdateDeliverer(develirer.getId(), order);
            }
            message = "Order status successfully updated!";
        } else {
            message = "This order was changed by other user!";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

}
