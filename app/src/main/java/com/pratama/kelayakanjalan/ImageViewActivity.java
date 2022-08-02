package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.view.BigImageView;

public class ImageViewActivity extends AppCompatActivity {

    PhotoView photoView;
    private static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        init();

    }

    private void init(){

        Intent intent = getIntent();

        url = intent.getStringExtra("url");

        photoView = findViewById(R.id.imageView);
        photoView.setImageBitmap(BitmapFactory.decodeFile(url));

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
