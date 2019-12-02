package com.example.shoppingapplication.ButtonController;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.shoppingapplication.UpdateInventoryActivity;

public class UpdateInventoryButtonController implements View.OnClickListener {
    private Context appContext;

    public UpdateInventoryButtonController (Context context) {
        this.appContext = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.appContext, UpdateInventoryActivity.class);
        appContext.startActivity(intent);
    }
}
