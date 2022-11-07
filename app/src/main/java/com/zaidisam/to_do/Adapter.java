package com.zaidisam.to_do;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.viewholder>{
    private DatabaseReference tododata;
   Context context;
   ArrayList<ToDoModel> arrayList;
   public String id;

     Adapter(Context context , ArrayList<ToDoModel> arrayList)
     {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.todolayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
          holder.totalques.setText("Total Questions: "+arrayList.get(position).totalques);
          holder.totalstud.setText("Total Students: "+arrayList.get(position).totalstud);
          holder.examtype.setText("Exam Type: "+arrayList.get(position).exmtype);
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  context.startActivity(new Intent(context,Scan_activity.class));

              }
          });

//


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView totalstud,totalques,examtype;
        CheckBox chkbox;
        int status;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            totalstud=itemView.findViewById(R.id.totalstudents);
            totalques = itemView.findViewById(R.id.questotal);
            examtype = itemView.findViewById(R.id.exmtype);



        }
    }
}
