package ca.centennialcollege.comp304_miniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.PersistableBundle;
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
        SharedPreferences preferences = getSharedPreferences("OrderStatusActivity", MODE_PRIVATE);

        Intent intent = getIntent();
        if (intent.hasExtra("Order")) {
            order = (Order) intent.getSerializableExtra("Order");

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("OrderId", order.getId());
            editor.commit();
        } else {
            int orderId = preferences.getInt("OrderId", -1);
            order = DataRepository.getOrder(orderId);
            if (order == null) {
                finish();
            }
        }

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

            StringBuilder sb = new StringBuilder();
            sb.append("Hello, ");
            sb.append(order.getClient().getName());
            sb.append(", \nYour order number ");
            sb.append(order.getNumber());
            sb.append(" was ");
            switch (order.getStatus()) {
                case ASSIGNED_TO_DELIVERER:
                    sb.append("assigned to a deliverer. We will notify when it is out for delivery.");
                    break;

                case IN_TRANSIT:
                    sb.append("out for delivery. It will be delivered today.");
                    break;

            }

            sendSMSUpdateStatus(order, sb.toString());
        } else {
            message = "This order was changed by other user!";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void btnViewMap_onClick(View v) {
        Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
        mapActivity.putExtra("order", order);
        startActivity(mapActivity);
    }

    private void sendSMSUpdateStatus(Order order, String message) {
        Uri uri = Uri.parse("smsto:" + order.getClient().getPhoneNumber());

        // Create an intent and open it
        Intent i = new Intent(android.content.Intent.ACTION_SENDTO,uri);
        i.putExtra("sms_body", message);
        startActivity(i);

    }
}
