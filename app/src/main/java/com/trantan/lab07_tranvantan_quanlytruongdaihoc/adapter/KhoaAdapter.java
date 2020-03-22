package com.trantan.lab07_tranvantan_quanlytruongdaihoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.R;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.Khoa;

import java.util.List;

public class KhoaAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private List<Khoa> listKhoa;

    public KhoaAdapter(@NonNull Context context, int resource, List<Khoa> objects) {
        this.context = context;
        this.resource = resource;
        this.listKhoa = objects;
    }


    @Override
    public int getCount() {
        if (listKhoa == null) {
            return 0;
        }
        return listKhoa.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoa, parent, false);
            holder = new ViewHolder();

            holder.txtTenKhoa = convertView.findViewById(R.id.txtTenKhoa);
            holder.txtToaNha = convertView.findViewById(R.id.txtToaNha);
            holder.txtSoDienThoai = convertView.findViewById(R.id.txtSoDienThoai);
            holder.txtMaTruong = convertView.findViewById(R.id.txtMaTruong);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Khoa khoa = listKhoa.get(position);

        holder.txtTenKhoa.setText(khoa.getTenKhoa());
        holder.txtToaNha.setText(khoa.getToaNha());
        holder.txtSoDienThoai.setText(String.valueOf(khoa.getSoDienThoai()));
        holder.txtMaTruong.setText(khoa.getTenTruongHoc());

        return convertView;
    }

    public class ViewHolder {
        TextView txtTenKhoa;
        TextView txtToaNha;
        TextView txtSoDienThoai;
        TextView txtMaTruong;
    }
}
