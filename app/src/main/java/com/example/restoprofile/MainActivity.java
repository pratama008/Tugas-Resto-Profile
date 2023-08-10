package com.example.restoprofile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView icEmail = findViewById(R.id.icEmail);
        ImageView icPhone = findViewById(R.id.icPhone);
        ImageView icLocation = findViewById(R.id.icLocation);

        icEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        icPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber();
            }
        });

        icLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        // Data daftar makanan (ganti dengan daftar makanan yang sesuai)
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add("Nasi Goreng Seafood");
        menuList.add("Fuyunghai");
        menuList.add("Sapo Tahu");
        menuList.add("Ayam Koloke");
        menuList.add("Cap Cay");

        // Inisialisasi ListView
        ListView listViewMenu = findViewById(R.id.listViewMenu);

        // Buat ArrayAdapter untuk menampilkan daftar makanan dalam ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuList);

        // Set ArrayAdapter ke ListView
        listViewMenu.setAdapter(adapter);

        // Hitung total tinggi item dalam ListView
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listViewMenu);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        // Set tinggi ListView sesuai total tinggi item
        listViewMenu.getLayoutParams().height = totalHeight + (listViewMenu.getDividerHeight() * (adapter.getCount() - 1));
        listViewMenu.requestLayout();

        // Data daftar jam dan hari pelayanan (ganti dengan data yang sesuai)
        ArrayList<String> jamBukaList = new ArrayList<>();
        jamBukaList.add("Senin - Jumat: 10.00 - 22.00");
        jamBukaList.add("Sabtu: 11.00 - 23.00");
        jamBukaList.add("Minggu: Tutup");

        // Inisialisasi ListView untuk jam dan hari pelayanan
        ListView listViewJamBuka = findViewById(R.id.listViewJamBuka);

        // Buat ArrayAdapter untuk menampilkan daftar jam dan hari pelayanan dalam ListView
        ArrayAdapter<String> jamBukaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jamBukaList);

        // Set ArrayAdapter ke ListView jam dan hari pelayanan
        listViewJamBuka.setAdapter(jamBukaAdapter);

        // Hitung total tinggi item dalam ListView
        for (int i = 0; i < jamBukaAdapter.getCount(); i++) {
            View listItem = jamBukaAdapter.getView(i, null, listViewJamBuka);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        // Set tinggi ListView sesuai total tinggi item
        listViewJamBuka.getLayoutParams().height = totalHeight + (listViewJamBuka.getDividerHeight() * (jamBukaAdapter.getCount() - 1));
        listViewJamBuka.requestLayout();
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:email@example.com")); // Ganti dengan alamat email yang diinginkan
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tanya Seputar Resto");
        startActivity(intent);
    }

    private void dialPhoneNumber() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:082135331084"));
        startActivity(intent);
    }

    private void openMap() {
        Uri gmmIntentUri = Uri.parse("geo:-6.9826264,110.4066259?q=UDINUS"); // Ganti dengan koordinat yang diinginkan
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
