package com.example.contacts;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.widget.Button;
import java.util.List;
import android.widget.Toast;

public class kontakAdapter extends ArrayAdapter<kontak> {
    // View lookup cache
    private static class ViewHolder {
        TextView nama;
        TextView nohp;
    }

    public kontakAdapter(Context context, int resource, List<kontak> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        kontak dtkontak = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewKontak; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewKontak = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_user, parent, false);
            viewKontak.nama = (TextView) convertView.findViewById(R.id.nm);
            viewKontak.nohp = (TextView) convertView.findViewById(R.id.hp);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewKontak);

        } else {
            viewKontak = (ViewHolder) convertView.getTag();
        }

        assert dtkontak != null;
        viewKontak.nama.setText(dtkontak.getNama());
        viewKontak.nohp.setText(dtkontak.getNoHp());

        // Get references to the edit and delete ImageViews
        ImageView edit = (ImageView) convertView.findViewById(R.id.edit);
        ImageView del = (ImageView) convertView.findViewById(R.id.del);

        // Set onClick listener for the edit ImageView
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the contact at the specified position
                final kontak contactToUpdate = getItem(position);

                // Create a dialog for editing contacts
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Contact");

                // Inflate the layout for the dialog
                View editContactView = LayoutInflater.from(getContext()).inflate(R.layout.add_kontak, null);
                builder.setView(editContactView);

                // Find views in the dialog layout
                final EditText editName = editContactView.findViewById(R.id.nm);
                final EditText editPhone = editContactView.findViewById(R.id.hp);

                // Set initial values for the EditText fields
                editName.setText(contactToUpdate.getNama());
                editPhone.setText(contactToUpdate.getNoHp());

                // Set up the buttons for the dialog
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the contact with new values
                        String newName = editName.getText().toString();
                        String newPhone = editPhone.getText().toString();

                        // Update the contact in the adapter
                        contactToUpdate.setNama(newName);
                        contactToUpdate.setNoHp(newPhone);

                        // Notify the adapter that the data set has changed
                        notifyDataSetChanged();

                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog without making any changes
                        dialog.dismiss();
                    }
                });

                // Show the dialog
                builder.show();
            }
        });


        // Set onClick listener for the delete ImageView
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a function in KontakActivity to delete the contact
                ((MainActivity) getContext()).deleteKontak(position);
            }
        });

        return convertView;
    }
}
