package com.example.registeration.classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registeration.Activities.AllRecordact;
import com.example.registeration.Activities.Updateactivity;
import com.example.registeration.R;
import com.example.registeration.RoomDB.SignupRecord;
import com.example.registeration.RoomDB.ViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.viewholder> {
    public RecycleAdapter(Context context, ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    ViewModel viewModel;
    AllRecordact allRecordact;
    List<SignupRecord> itemlist = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sampleallrec, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.rec.setText(showlist(position));
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.DelRec(itemlist.get(position));
            }
        });
    }

    private String showlist(int position) {
        return (itemlist.get(position).getSignupid() + " : " + itemlist.get(position).getUsername() + " : " + itemlist.get(position).getUseremail() + " : " + itemlist.get(position).getUsermobile_nbr()
                + " : " + itemlist.get(position).getUserpassword());
    }


    //............................................................................................
    public void setRec(List<SignupRecord> listrec) {
        this.itemlist = listrec;
        notifyDataSetChanged();
    }

    //......................................................
    public SignupRecord getrecat(int position) {
        return itemlist.get(position);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {

        TextView rec;
        ImageButton btndel, btnupdate;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.txtview);
            btndel = itemView.findViewById(R.id.btndelete);
            btnupdate = itemView.findViewById(R.id.btnupdate);

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = clickedpos();
                    Intent intent = new Intent(context, Updateactivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("listitm", (Serializable) itemlist);
                    context.startActivity(intent);
                }
            });

        }

        int clickedpos() {
            return this.getAdapterPosition();
        }
    }
}
