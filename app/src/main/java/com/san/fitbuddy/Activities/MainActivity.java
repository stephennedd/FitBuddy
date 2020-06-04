package com.san.fitbuddy.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.san.fitbuddy.Model.ClientAdmin;
import com.san.fitbuddy.R;
import com.san.fitbuddy.Adapters.CustomListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ClientAdmin clientAdmin;
    private List clients;
    private CustomListAdapter adapter;
    static final int ADD_CLIENT_REQUEST = 0;
    static final int UPDATE_CLIENT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.client_list);
        FloatingActionButton addButton = findViewById(R.id.btn_floating);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whenAddButtonClicked();
            }
        });

        clientAdmin = new ClientAdmin();
        clients = ClientAdmin.clientList;

        if (clients.size() == 0) {
            clientAdmin.setClientList();
            clients = clientAdmin.getClientList();
        }
        Log.i("WHY", "onCreate:" + clients);

        adapter = new CustomListAdapter(this, clients);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                whenClientClicked(position);
            }
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                whenClientLongPressed(position);
                return true;
            }
        });

    }

    public void whenAddButtonClicked() {
        Intent intent = new Intent(this, AddClientActivity.class);
        startActivityForResult(intent, ADD_CLIENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CLIENT_REQUEST && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
//            filePath = saveImageToStorage(bitmap);
//            client.setImageFile(filePath+"/"+"avatar_"+(ClientAdmin.clientList.size()+1)+"jpg");
//        }
//    }


    public void whenClientLongPressed(int position) {
        Intent intent = new Intent(this, UpdateClientActivity.class);
        intent.putExtra("ListItemPosition", position);
        startActivityForResult(intent, UPDATE_CLIENT_REQUEST);
    }

    private void whenClientClicked(int position) {
        Intent intent = new Intent(this, ClientDetailsActivity.class);
        intent.putExtra("ListItemPosition", position);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}