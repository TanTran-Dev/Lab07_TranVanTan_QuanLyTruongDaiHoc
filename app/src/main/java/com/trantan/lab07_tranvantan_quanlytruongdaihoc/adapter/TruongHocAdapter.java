package com.trantan.lab07_tranvantan_quanlytruongdaihoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.R;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.TruongDaiHoc;

import java.util.List;

public class TruongHocAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<TruongDaiHoc> listTruongHoc;

    public TruongHocAdapter(@NonNull Context context, int resource, List<TruongDaiHoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listTruongHoc = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truong_hoc, parent, false);
            holder = new ViewHolder();
            holder.txtStt = convertView.findViewById(R.id.txtStt);
            holder.txtMaTruong = convertView.findViewById(R.id.txtMaTruong);
            holder.txtTenTruong = convertView.findViewById(R.id.txtTenTruong);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TruongDaiHoc truongDaiHoc = listTruongHoc.get(position);
        holder.txtStt.setText(String.valueOf(position));
        holder.txtMaTruong.setText(truongDaiHoc.getMaTruong());
        holder.txtTenTruong.setText(truongDaiHoc.getTenTruong());

        return convertView;
    }

    public class ViewHolder {
        TextView txtStt;
        TextView txtMaTruong;
        TextView txtTenTruong;
    }
}
