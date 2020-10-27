package com.example.archivos2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String status= Environment.getExternalStorageState();
        TextView tv=findViewById(R.id.text);
        if (!status.equals(Environment.MEDIA_MOUNTED)){
            tv.setText("No hay memoria SDcard");
        }
        File dir=Environment.getExternalStorageDirectory();
        File pt=new File(dir.getAbsolutePath()+File.separator+"productos.txt");
        try {
            BufferedReader lector=new BufferedReader(new FileReader(pt));
            String res="";
            String linea;
            while ((linea=lector.readLine())!=null){
                res=res+linea+"\n";
            }
            lector.close();
            tv.setText(res);
        }catch (IOException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    public void limpiar(View view){
        EditText Cod = findViewById(R.id.cod);
        EditText Tip = findViewById(R.id.tipo);
        EditText Des = findViewById(R.id.des);
        EditText Uni = findViewById(R.id.und);
        EditText Uxe = findViewById(R.id.uxe);
        EditText Exi = findViewById(R.id.exis);

        Cod.setText("");
        Tip.setText("");
        Des.setText("");
        Uni.setText("");
        Uxe.setText("");
        Exi.setText("");

    }
    public void buscar(View view){
        EditText Cod = findViewById(R.id.cod);
        EditText Tip = findViewById(R.id.tipo);
        EditText Des = findViewById(R.id.des);
        EditText Uni = findViewById(R.id.und);
        EditText Uxe = findViewById(R.id.uxe);
        EditText Exi = findViewById(R.id.exis);

        String CodBus=Cod.getText().toString();
        String line="";
        String status= Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this,"error getting card",Toast.LENGTH_LONG).show();
        }
        File dir=Environment.getExternalStorageDirectory();
        File pt=new File(dir.getAbsolutePath()+File.separator+"productos.txt");
        try {
            BufferedReader lector=new BufferedReader(new FileReader(pt));
            String res="";
            String linea;
            while ((linea=lector.readLine())!=null){
                if(linea.indexOf(";")!=-1){
                    if (linea.split(";")[0].equals(CodBus)){
                        res=res+linea;
                    }
                }
            }
            lector.close();
            line=line+res;
        }catch (IOException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        if (line.equals("")){
            Toast.makeText(this,"NO EXISTE PRODUCTO",Toast.LENGTH_LONG).show();
        }else{
            String VP[]=line.split(";");

            Tip.setText(VP[1]);
            Des.setText(VP[2]);
            Uni.setText(VP[3]);
            Uxe.setText(VP[4]);
            Exi.setText(VP[5]);
        }

    }
}