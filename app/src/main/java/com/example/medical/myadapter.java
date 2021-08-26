package com.example.medical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {
       holder.Hospital.setText(model.getHospital());
       holder.pincode.setText(model.getpincode());
        holder.ambulence.setText(model.getAmbulence());
       holder.NumofBed.setText(model.getNumofBed());
       Glide.with(holder.img.getContext()).load("https://lh3.googleusercontent.com/fHbZTzqN0hdQ2cb1oJMf1g1qhea8FusFwwzj0PqyC4nPoYNTX0_TFfygvI_VQ97_Iy71CfoCZbPKqmVg_aT5O9wM-cfa3tDH=s1200").into(holder.img);

                    holder.edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                    .setExpanded(true,1100)
                                    .create();

                            View myview=dialogPlus.getHolderView();
                            final EditText Hospital=myview.findViewById(R.id.uHospital);
                            final EditText pincode=myview.findViewById(R.id.upincode);
                            final EditText ambulence=myview.findViewById(R.id.uambulence);
                            final EditText NumofBed=myview.findViewById(R.id.uNumofBed);
                            Button submit=myview.findViewById(R.id.usubmit);


                            Hospital.setText(model.getHospital());
                            pincode.setText(model.getpincode());
                            NumofBed.setText(model.getNumofBed());
                            ambulence.setText(model.getAmbulence());

                            dialogPlus.show();

                                submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("Hospital",Hospital.getText().toString());
                                        map.put("NumofBed",NumofBed.getText().toString());
                                        map.put("pincode",pincode.getText().toString());
                                        map.put("ambulence",ambulence.getText().toString());

                                        FirebaseDatabase.getInstance().getReference().child("information")
                                                .child(getRef(position).getKey()).updateChildren(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        dialogPlus.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        dialogPlus.dismiss();
                                                    }
                                                });
                                    }
                                });


                        }
                    });







    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit;
        TextView Hospital,pincode,NumofBed,ambulence;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            Hospital=(TextView)itemView.findViewById(R.id.Hospitaltext);
            pincode=(TextView)itemView.findViewById(R.id.pincodetext);
            NumofBed=(TextView)itemView.findViewById(R.id.NumofBedtext);
            ambulence=(TextView)itemView.findViewById(R.id.ambulencetext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);

        }
    }
}
