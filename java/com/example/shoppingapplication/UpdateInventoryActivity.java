package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.ItemNotFoundException;
import com.b07.exceptions.NullParameterException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;

import java.math.BigDecimal;
import java.util.List;

public class UpdateInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);

        try {
            int apple = DatabaseInsertHelper.insertItem("Apple", new BigDecimal("0.43"), this);
            int orange = DatabaseInsertHelper.insertItem("Orange", new BigDecimal("0.49"), this);
            DatabaseInsertHelper.insertInventory(apple, 9, this);
            DatabaseInsertHelper.insertInventory(orange, 5, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout inventoryView = findViewById(R.id.inventory_view);
        Inventory inventory = null;
        try {
            inventory = DatabaseSelectHelper.getInventory(this);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        List<Item> itemList = inventory.getItemList();
        for (Item item: itemList){
            String itemName = item.getName();
            BigDecimal itemPrice = item.getPrice();
            int itemQuantity = inventory.getItemMap().get(item);
            String itemSummary = itemName + ";  $" + itemPrice.toString() + ";  " +
                    itemQuantity + " in stock";
            TextView itemDisplay = new TextView(this);
            itemDisplay.setTextSize(20);
            itemDisplay.setText(itemSummary);
            inventoryView.addView(itemDisplay);
        }
    }
}
