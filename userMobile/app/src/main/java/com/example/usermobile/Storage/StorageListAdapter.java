package com.example.usermobile.Storage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usermobile.R;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class StorageListAdapter extends ArrayAdapter<Product> implements View.OnClickListener {
    Context mContext;
    private final ArrayList<Product> dataSet;
    private int lastPosition = -1;

    public StorageListAdapter(ArrayList<Product> data, int resource, Context context) {
        super(context, resource, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Product product = (Product) object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_list_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtPackage = (TextView) convertView.findViewById(R.id.packagesDialog);
            viewHolder.txtQuantity = (TextView) convertView.findViewById(R.id.quantity);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.expirationDate);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.txtName.setText(product.getName());

        if (!product.getUnitOfMeasure().isEmpty()) {
            viewHolder.txtQuantity.setText("Quantity: " + product.getQuantity() + " " + product.getUnitOfMeasure());
        } else {
            viewHolder.txtQuantity.setText("Quantity: " + product.getQuantity());
        }

        long dateDifference = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(product.getExpirationDate()));

        if (dateDifference < 0) {
            viewHolder.txtDate.setTextColor(Color.parseColor("#FF0000"));
            viewHolder.txtDate.setText("Expired " + Math.abs(dateDifference) + " days ago");
        } else {
            viewHolder.txtDate.setTextColor(Color.parseColor("#55FF00"));
            viewHolder.txtDate.setText("Expires in " + dateDifference + " days");
        }

        if (product.getPhotoLink() != null) {
            Picasso.get().load(product.getPhotoLink()).into(viewHolder.ivPhoto);
        } else {
            Picasso.get().load("https://www.facebook.com/dnwfood/photos/a.1680917368864156/1682203278735565/?type=3").into(viewHolder.ivPhoto);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private static class ViewHolder {
        TextView txtPackage;
        TextView txtName;
        TextView txtQuantity;
        TextView txtDate;
        ImageView ivPhoto;
    }
}