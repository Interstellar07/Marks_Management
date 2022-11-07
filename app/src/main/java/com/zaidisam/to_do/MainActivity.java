package com.zaidisam.to_do;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     public  RecyclerView rcv;
   public FloatingActionButton fab;
   public CheckBox chkbox;
    private DatabaseReference tododata;
   ArrayList<ToDoModel> arrtodo= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcylview);
        rcv.setLayoutManager(new LinearLayoutManager(this));
           fab = findViewById(R.id.fab);
           fab.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 // startActivity(new Intent(MainActivity.this,Scan_activity.class));
                  addnote();
              }
          });






    }

    private void addnote() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater =LayoutInflater.from(this);
        View myView =inflater.inflate(R.layout.exam_dets,null);
        myDialog.setView(myView);
        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);
        final EditText totalstudents = myView.findViewById(R.id.candidate);
        final EditText totalques = myView.findViewById(R.id.totalques);
         RadioGroup exmtype = myView.findViewById(R.id.SetOptions);
        final Button procees = myView.findViewById(R.id.procees);
        final Button cancel = myView.findViewById(R.id.cancel);


        procees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String totalstudnetstxt = totalstudents.getText().toString();
               int p = exmtype.getCheckedRadioButtonId();
                RadioButton exmtyp = myView.findViewById(p);
                Toast.makeText(MainActivity.this,exmtyp.getText().toString(),Toast.LENGTH_SHORT).show();
                String choice = exmtyp.getText().toString();
                String totalquesttxt = totalques.getText().toString();
                ToDoModel data = new ToDoModel(totalstudnetstxt,totalquesttxt,choice);
                arrtodo.add(data);
                Adapter adapter = new Adapter(MainActivity.this,arrtodo);
                rcv.setAdapter(adapter);
                dialog.dismiss();



            }
        });
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

}