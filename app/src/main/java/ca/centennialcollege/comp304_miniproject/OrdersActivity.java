package ca.centennialcollege.comp304_miniproject;

import android.content.Intent;
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

public class OrdersActivity extends AppCompatActivity {

    List<Order> listOrders;
    ListView lvwOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        lvwOrders = findViewById(R.id.lstOrders);
        loadOrders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.orders_filter, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void loadOrders() {
        lvwOrders = findViewById(R.id.lstOrders);
        listOrders = new ArrayList<>();

        Enumeration<Order> orders = DataRepository.getOrders();

        while (orders.hasMoreElements()) {
            listOrders.add(orders.nextElement());
        }

        OrderListAdapter listAdapter = new OrderListAdapter(listOrders, getApplicationContext());

        lvwOrders.setAdapter(listAdapter);
        lvwOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) parent.getItemAtPosition(position);
                Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                mapActivity.putExtra("order", order);
                startActivity(mapActivity);
            }
        });
    }
}
