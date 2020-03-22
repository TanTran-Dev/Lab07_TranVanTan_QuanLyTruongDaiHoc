package com.trantan.lab07_tranvantan_quanlytruongdaihoc.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.R;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.adapter.KhoaAdapter;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.data.DBManager;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.Khoa;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.TruongDaiHoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QLKhoaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private EditText edtTenKhoa;
    private EditText edtToaNha;
    private EditText edtSoDienThoai;

    private Button btnThem;
    private Button btnHuy;
    private ListView lvKhoa;
    private Spinner spnTruongHoc;

    private DBManager dbManager;
    private List<Khoa> listKhoa;
    private List<TruongDaiHoc> listTruongHoc;
    private List<String> listTenTruongHoc;
    private KhoaAdapter khoaAdapter;
    private ArrayAdapter<String> stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlkhoa);

        getSupportActionBar().setTitle("Quản Lý Khoa");

        init();
        initView();
        eventsClick();
        setAdapter();
    }


    private void init() {
        dbManager = new DBManager(this);
        listKhoa = dbManager.getAllKhoa();
        listTruongHoc = dbManager.getAllTruongDaiHoc();
        listTenTruongHoc = new ArrayList<>();

        for (TruongDaiHoc truongDaiHoc : listTruongHoc) {
            listTenTruongHoc.add(truongDaiHoc.getTenTruong());
        }

        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(listTenTruongHoc);

        listTenTruongHoc.clear();
        listTenTruongHoc.addAll(hashSet);

        Collections.sort(listTenTruongHoc);

        stringAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, listTenTruongHoc);
    }

    private void initView() {
        edtTenKhoa = findViewById(R.id.edtTenKhoa);
        edtToaNha = findViewById(R.id.edtToaNha);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        lvKhoa = findViewById(R.id.lvKhoa);
        spnTruongHoc = findViewById(R.id.spnTruongHoc);
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
    }

    private void eventsClick() {
        btnThem.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        lvKhoa.setOnItemLongClickListener(this);
        lvKhoa.setOnItemClickListener(this);

    }

    private Khoa taoMoiKhoa() {
        String tenKhoa = edtTenKhoa.getText().toString();
        String toaNha = edtToaNha.getText().toString();
        int phoneNumber = Integer.parseInt(edtSoDienThoai.getText().toString());

        String tenTruongHoc = (String) spnTruongHoc.getSelectedItem();

        Khoa khoa = new Khoa(tenTruongHoc, toaNha, tenKhoa, phoneNumber);

        return khoa;
    }

    private void setAdapter() {
        if (khoaAdapter == null) {
            khoaAdapter = new KhoaAdapter(this, R.layout.item_khoa, listKhoa);
            lvKhoa.setAdapter(khoaAdapter);
        } else {
            khoaAdapter.notifyDataSetChanged();
            lvKhoa.setSelection(khoaAdapter.getCount() - 1);
        }

        spnTruongHoc.setAdapter(stringAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThem:{
                if (edtTenKhoa.getText().toString().isEmpty()
                        || edtToaNha.getText().toString().isEmpty()
                        || edtSoDienThoai.getText().toString().isEmpty()){

                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Khoa khoa = taoMoiKhoa();
                    if (khoa != null) {

                        dbManager.addKhoa(khoa);

                        xoaDuLieuNhap();
                    }
                    lamMoiListKhoa();
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

    private void xoaDuLieuNhap() {
        edtSoDienThoai.setText("");
        edtToaNha.setText("");
        edtTenKhoa.setText("");
    }

    private void lamMoiListKhoa() {
        listKhoa.clear();
        listKhoa.addAll(dbManager.getAllKhoa());
        if (khoaAdapter != null) {
            khoaAdapter.notifyDataSetChanged();
        }
    }

    int index = -1;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        Khoa khoa = listKhoa.get(index);

        edtTenKhoa.setText(khoa.getTenKhoa());
        edtToaNha.setText(khoa.getToaNha());
        edtSoDienThoai.setText(String.valueOf(khoa.getSoDienThoai()));

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { index = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        builder.setTitle("Delete!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Bạn có muốn xoá " + listKhoa.get(index).getTenKhoa() + " không!!!")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Khoa khoa = listKhoa.get(index);
                        int result = dbManager.deleteKhoa(khoa);

                        if (result > 0) {
                            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_LONG).show();
                            lamMoiListKhoa();
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
}
