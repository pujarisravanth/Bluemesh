package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sravanth on 27-07-2017.
 */

public class DeviceChooseActivity extends Activity {

    public ArrayList<String> ch_dev_list;
    public ArrayList<String> dev_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);

        setResult(Activity.RESULT_CANCELED);

        Context context = this;
        ListView listview = (ListView)findViewById(R.id.listView);
        dev_list = getIntent().getStringArrayListExtra("choose_device");

        ArrayAdapter<String> deviceAdapter = new ArrayAdapter<String>(this, R.layout.list_item, dev_list);
        listview.setAdapter(deviceAdapter);
        listview.setItemsCanFocus(false);
        // we want multiple clicks
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setOnItemClickListener(new CheckBoxClick());

        Button submitButton = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("ch_device list",ch_dev_list);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public class CheckBoxClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            CheckedTextView ctv = (CheckedTextView)arg1;
            if(ctv.isChecked()){
                //Toast.makeText(DeviceChooseActivity.this, "now it is unchecked", Toast.LENGTH_SHORT).show();
                ch_dev_list.add(ctv.getText().toString());
            }else{
                //Toast.makeText(DeviceChooseActivity.this, "now it is checked", Toast.LENGTH_SHORT).show();
                ch_dev_list.remove(ctv.getText().toString());
            }
        }
    }
}
