package com.trantan.lab07_tranvantan_quanlytruongdaihoc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Quản lý Trường ĐH - CĐ");
    }

    public void onClickButtonQLTruongDaiHoc(View view) {
        Intent intent = new Intent(this, QLTruongDaiHocActivity.class);
        startActivity(intent);
    }

    public void onClickButtonQLKhoa(View view) {
        Intent intent = new Intent(this, QLKhoaActivity.class);
        startActivity(intent);
    }
}
