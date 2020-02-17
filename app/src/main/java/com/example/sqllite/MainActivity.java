package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText e1,e2,e3,e4,e5;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);

        e1=findViewById(R.id.name);
        e2=findViewById(R.id.address);
        e3=findViewById(R.id.number);
        e4=findViewById(R.id.idText);
        b1=findViewById(R.id.addButton);
        b2=findViewById(R.id.showButton);
        b3=findViewById(R.id.updateButton);
        b4=findViewById(R.id.deleteButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(e1.getText().toString().isEmpty() && e2.getText().toString().isEmpty() && e3.getText().toString().isEmpty() && !e4.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please fill only name, address and number details",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    boolean isinsert=databaseHelper.insertData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                    if(isinsert==true)
                    {
                        Toast.makeText(getApplicationContext(),"insert Sucessefull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"insert unSucessefull",Toast.LENGTH_SHORT).show();
                    }
                }
                }


        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor=databaseHelper.showAllData();

                if(cursor.getCount()==0)
                {
                    showData("Error","Nothing Found");
                    return;

                }

                StringBuffer stringBuffer=new StringBuffer();

                while(cursor.moveToNext())
                {
                    stringBuffer.append("ID: "+cursor.getString(0)+"\n");
                    stringBuffer.append("NAME: "+cursor.getString(1)+"\n");
                    stringBuffer.append("ADDRESS: "+cursor.getString(2)+"\n");
                    stringBuffer.append("NUMBER: "+cursor.getString(3)+"\n"+"\n");
                }
                showData("Data",stringBuffer.toString());

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e4.getText().toString().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    boolean b=databaseHelper.updateData(e4.getText().toString(),e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                    if(b == true)
                    {
                        Toast.makeText(getApplicationContext(),"Update Sucessefull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Update unSucessefull",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(e1.getText().toString()==null && e1.getText().toString()==null && e1.getText().toString()==null && e1.getText().toString()==null || e1.getText().toString()!=null )
                {
                    Integer integer=databaseHelper.dataDelete(e4.getText().toString());

                    if(integer > 0)
                    {
                        Toast.makeText(getApplicationContext(),"Delete Sucessefull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Delete unSucessefull",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill only id",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void showData(String title,String Data)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Data);
        builder.show();
    }
}
