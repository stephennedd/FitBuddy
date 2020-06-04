package com.san.fitbuddy.CompoundControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.san.fitbuddy.R;

public class ProfileView extends LinearLayout {

    private ImageView imageView;
    private Button button;


    public ProfileView(Context context) {
        super(context);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.profile_view, this);

        imageView = findViewById(R.id.clientImageId);
        button = findViewById(R.id.updatePhotoButton);
    }


}
