package com.trantan.lab07_tranvantan_quanlytruongdaihoc.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.R;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.adapter.TruongHocAdapter;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.data.DBManager;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.TruongDaiHoc;

import java.util.List;

public class QLTruongDaiHocActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private EditText edtMaTruong;
    private EditText edtTenTruong;
    private Button btnThem;
    private Button btnHuy;
    private ListView lvTruongHoc;

    private DBManager dbManager;

    private TruongHocAdapter adapter;
    private List<TruongDaiHoc> listTruongDaiHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltruong_dai_hoc);
        getSupportActionBar().setTitle("Quản Lý Trường Đại Học");
        init();
        initView();
        eventsClick();
        setAdapter();
    }

    private void init() {
        dbManager = new DBManager(this);
        listTruongDaiHoc = dbManager.getAllTruongDaiHoc();
    }


    private void initView() {
        edtMaTruong = findViewById(R.id.edtMaTruong);
        edtTenTruong = findViewById(R.id.edtTenTruong);
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
        lvTruongHoc = findViewById(R.id.lvTruongHoc);
    }


    private void eventsClick() {
        btnThem.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        lvTruongHoc.setOnItemClickListener(this);
        lvTruongHoc.setOnItemLongClickListener(this);
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new TruongHocAdapter(this, R.layout.item_truong_hoc, listTruongDaiHoc);
            lvTruongHoc.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            lvTruongHoc.setSelection(adapter.getCount() - 1);
        }
    }

    private TruongDaiHoc createTruongHoc() {
        String maTruong = edtMaTruong.getText().toString();
        String tenTruong = edtTenTruong.getText().toString();

        TruongDaiHoc truongDaiHoc = new TruongDaiHoc(maTruong, tenTruong);

        return truongDaiHoc;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThem:{
                if (edtMaTruong.getText().toString().isEmpty() || edtTenTruong.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập đẩy đủ thông tin", Toast.LENGTH_LONG).show();
                }else {
                    TruongDaiHoc truongDaiHoc = createTruongHoc();
                    if (truongDaiHoc != null) {
                        dbManager.addTruongDaiHoc(truongDaiHoc);
                        xoaDuLieuNhap();
                    }
                    lamMoiListTruongHoc();
                    setAdapter();
                }
                break;
            }

            case R.id.btnHuy:{
                xoaDuLieuNhap();
                break;
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        TruongDaiHoc truongDaiHoc = listTruongDaiHoc.get(index);

        edtMaTruong.setText(truongDaiHoc.getMaTruong());
        edtTenTruong.setText(truongDaiHoc.getTenTruong());
    }

    int index = -1;

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        builder.setTitle("Delete!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Bạn có muốn xoá " + listTruongDaiHoc.get(index).getTenTruong() + " không!!!")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TruongDaiHoc truongDaiHoc = listTruongDaiHoc.get(index);
                        int result = dbManager.deleteTruongHoc(truongDaiHoc);
                    if (result > 0) {
                            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_LONG).show();
                            lamMoiListTruongHoc();
                        } else {
                            Toast.makeText(getApplicationContext(), "Xoá không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

        builder.show();

        return false;
    }

    private void lamMoiListTruongHoc() {
        listTruongDaiHoc.clear();
        listTruongDaiHoc.addAll(dbManager.getAllTruongDaiHoc());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void xoaDuLieuNhap() {
        edtMaTruong.setText("");
        edtTenTruong.setText("");
    }
}
