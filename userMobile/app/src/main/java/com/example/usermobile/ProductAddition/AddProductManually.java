package com.example.usermobile.ProductAddition;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usermobile.R;
import com.example.usermobile.Storage.Product;

public class AddProductManually extends AppCompatActivity {
    /*
        TASKS:
            Activitate care citeste: Nume, Quantity, expirationDate, ambalaje cu dropdown, categorii cu dropdown
            O clasa Product cu atributele (Id, Nume, Quantity, expirationDate, ambalaje cu dropdown, categorii cu dropdown)
            StorageDatabase - clasa care se ocupa cu store User's Storage in firebase database:
                functions: storeProduct(idUser, Product), deleteProduct(idUser, idProduct)


         */
    //private TextView tvNume, tvQuantity, tvExpirationDate, tvPackage, tvCategory;
    String[] itemsPackage = {"Paper", "Glass", "Plastic", "Carton", "Metal", "Unknown"};
    //String[] itemsCategory = {"Fruits", "Vegetables", "Cereals", "Unknown"};
    String[] itemsCategory = {
            "Plant-based foods and beverages",
            "Snacks",
            "Beverages",
            "Dairies",
            "Cereals and potatoes",
            "Meats",
            "Fruits and vegetables",
            "Cheeses",
            "Frozen foods",
            "Desserts",
            "Seafood",
            "Condiments",
            "Fishes",
            "Wines",
            "Pastas"};
    String productName, productQuantity, productExpirationDate, productPackage, productCategory;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    AutoCompleteTextView autoCompleteTextView2;
    ArrayAdapter<String> arrayAdapter2;

    private EditText etNume, etQuantity;
    private DatePicker dpExpirationDate;
    private ListView lvPackage, lvCategory;
    private Button bAdd, bCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_manually);


        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_b, itemsPackage);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                productPackage = item;
            }
        });

        autoCompleteTextView2 = findViewById(R.id.auto_complete_txt2);
        arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.list_item_b, itemsCategory);
        autoCompleteTextView2.setAdapter(arrayAdapter2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                productCategory = item;
            }
        });


        etNume = findViewById(R.id.etProductName);
        etQuantity = findViewById(R.id.etQuantity);
        dpExpirationDate = findViewById(R.id.datePicker2);

        bAdd = findViewById(R.id.addBtn2);
        bCancel = findViewById(R.id.cancelBtn2);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDatabase();
                Toast.makeText(getApplicationContext(), "ProductAdded", Toast.LENGTH_SHORT).show();
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    void sendToDatabase() {
        productName = etNume.getText().toString();
        int intProductQuantity = Integer.parseInt(etQuantity.getText().toString());
        productExpirationDate = "Selected date: " + dpExpirationDate.getDayOfMonth() + "/" + (dpExpirationDate.getMonth() + 1) + "/" + dpExpirationDate.getYear();

        Product product = new Product(productName, intProductQuantity, productExpirationDate, productCategory, productPackage, "-");

        // add product to database
    }

}