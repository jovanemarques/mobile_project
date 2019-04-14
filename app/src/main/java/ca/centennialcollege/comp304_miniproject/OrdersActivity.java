package ca.centennialcollege.comp304_miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ca.centennialcollege.comp304_miniproject.adapters.OrderListAdapter;
import ca.centennialcollege.comp304_miniproject.models.DataRepository;
import ca.centennialcollege.comp304_miniproject.models.Order;
import ca.centennialcollege.comp304_miniproject.models.OrderStatus;

public class OrdersActivity extends AppCompatActivity {

    Menu menu;
    List<OrderStatus> filter;
    List<Order> listOrders;
    ListView lvwOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        lvwOrders = findViewById(R.id.lstOrders);
        filter = new ArrayList<>();
        filter.add(OrderStatus.RECEIVED);
        filter.add(OrderStatus.ASSIGNED_TO_DELIVERER);
        filter.add(OrderStatus.IN_TRANSIT);
        filter.add(OrderStatus.NOT_DELIVERED);
        loadOrders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.orders_filter, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        OrderStatus itemStatus = null;

        item.setChecked(!item.isChecked());

        switch (item.getItemId()) {
            case R.id.order_filter_received:
                itemStatus = OrderStatus.RECEIVED;
                break;

            case R.id.order_filter_assigned:
                itemStatus = OrderStatus.ASSIGNED_TO_DELIVERER;
                break;

            case R.id.order_filter_transit:
                itemStatus = OrderStatus.IN_TRANSIT;
                break;

            case R.id.order_filter_delivered:
                itemStatus = OrderStatus.DELIVERED;
                break;

            case R.id.order_filter_not_delivered:
                itemStatus = OrderStatus.NOT_DELIVERED;
                break;
        }

        if (item.isChecked()) {
            filter.add(itemStatus);
        } else {
            filter.remove(itemStatus);
        }

        loadOrders();

        return true;
    }

    private void loadOrders() {
        lvwOrders = findViewById(R.id.lstOrders);
        listOrders = new ArrayList<>();

        Enumeration<Order> orders = DataRepository.getOrders();

        while (orders.hasMoreElements()) {
            Order order = orders.nextElement();
            if (filter.contains(order.getStatus())) {
                listOrders.add(order);
            }
        }

        OrderListAdapter listAdapter = new OrderListAdapter(listOrders, getApplicationContext());

        lvwOrders.setAdapter(listAdapter);
        lvwOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNextActivity(listOrders.get(position));
            }
        });
    }

    private void openNextActivity(Order order) {
        Intent intent = new Intent(this, OrderStatusActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        loadOrders();
        super.onResume();
    }

}
