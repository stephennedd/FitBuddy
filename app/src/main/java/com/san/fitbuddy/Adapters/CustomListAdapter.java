package com.san.fitbuddy.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.san.fitbuddy.Model.Client;
import com.san.fitbuddy.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter {

    LayoutInflater layoutInflater;
    List<Client> clientList;
    private ImageView imageView;
    private TextView name;
    private TextView status;
    Button removeButton;


    public CustomListAdapter(Context context, List objects) {
        super(context, R.layout.client_list_item, objects);
        layoutInflater = LayoutInflater.from(context);
        clientList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Client client = clientList.get(position);

        if (convertView == null) {
            convertView=layoutInflater.inflate(R.layout.client_list_item, parent, false);
        }

        if (convertView != null) {
            name = convertView.findViewById(R.id.clientNameID);
            name.setText(client.getName());

            status = convertView.findViewById(R.id.clientStatusID);
            status.setText(client.getStatus());

            imageView = convertView.findViewById(R.id.detailProfileImage);

            InputStream inputStream = null;
            try {
                String imageFile = client.getImageFile();
                Log.i("Yoo", imageFile);

                inputStream = getContext().getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            removeButton = convertView.findViewById(R.id.removeButton);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clientList.remove(position);
                    notifyDataSetChanged();
                }
            });

        }

        return convertView;
    }
}
