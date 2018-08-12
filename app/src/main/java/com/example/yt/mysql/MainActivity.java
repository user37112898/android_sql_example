package com.example.yt.mysql;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb = new DataBaseHelper(this);
    EditText id,name,surname,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        surname=(EditText)findViewById(R.id.surname);
        marks=(EditText)findViewById(R.id.marks);
        ShowData();
        AddData();
        ViewData();
        UpdateData();
        DeleteData();

//        ArrayList<String> arrayList = new ArrayList<String>();
//        //TODO add items to list
//        StringAdapter adapter = new StringAdapter(this,arrayList);
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);
    }

    public void ShowData(){
        Cursor res = myDb.getAllData();

        ArrayList<DetailsClass> arrayList = new ArrayList<DetailsClass>();
        while (res.moveToNext()) {
            String id = res.getString(0);
            String name = res.getString(1);
            String surname = res.getString(2);
            String marks = res.getString(3);
            arrayList.add(new DetailsClass(id, name, surname, marks));
        }
        StringAdapter adapter = new StringAdapter(MainActivity.this,arrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void AddData(){
        Button AddData = (Button)findViewById(R.id.AddDAta);
        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.AddData(name.getText().toString(),
                        surname.getText().toString(),
                        marks.getText().toString());
                if(isInserted==true){
                    Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Not Inserted",Toast.LENGTH_SHORT).show();
                }
                ShowData();
            }
        });
    }

    public void ViewData(){
        Button ViewData = (Button)findViewById(R.id.ViewData);
        ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0){
                    showMessage("Error","No Data Found");
                    return;
                }else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id"+res.getString(0)+"\n");
                        buffer.append("Name"+res.getString(1)+"\n");
                        buffer.append("Surname"+res.getString(2)+"\n");
                        buffer.append("Marks"+res.getString(3)+"\n\n");
                    }
                    showMessage("Data",buffer.toString());
                }
            }
        });
    }

    public void UpdateData(){
        Button update = findViewById(R.id.Update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isUpdated = myDb.updateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isUpdated>0){
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Not Updated",Toast.LENGTH_SHORT).show();
                }
                ShowData();
            }
        });
    }

    public void DeleteData(){
        Button delete = findViewById(R.id.Delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isDeleted = myDb.delete(id.getText().toString());
                if(isDeleted>0){
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_SHORT).show();
                }
                ShowData();
            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: Debug it dude!!
                myDb.yolo();
                Toast.makeText(MainActivity.this,"All Data Deleted",Toast.LENGTH_LONG).show();
                ShowData();
                return true;
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}