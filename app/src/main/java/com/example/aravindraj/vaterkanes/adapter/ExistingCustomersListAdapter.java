package com.example.aravindraj.vaterkanes.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.aravindraj.vaterkanes.DataBaseHelperClass.DataBaseHelperClass;
import com.example.aravindraj.vaterkanes.R;
import com.example.aravindraj.vaterkanes.activities.ExistingCustomerActivity;
import com.example.aravindraj.vaterkanes.model.NewCustomerListData;

import java.util.ArrayList;

public class ExistingCustomersListAdapter extends RecyclerView.Adapter<ExistingCustomersListAdapter.ViewHolder>  {
    Context context;
    Activity activity;
    private ArrayList<NewCustomerListData> newCustomerListData;
    DataBaseHelperClass dataBaseHelperClass;
    private Dialog dialog;

    public ExistingCustomersListAdapter(ExistingCustomerActivity existingCustomerActivity,ArrayList<NewCustomerListData> newCustomerListData) {
        this.activity = existingCustomerActivity;
        this.newCustomerListData = newCustomerListData;
        dataBaseHelperClass = new DataBaseHelperClass(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.existing_customer_list_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.customer_name_adapter.setText(newCustomerListData.get(position).getCustomer_name());
        holder.customer_phoneno_adapter.setText(newCustomerListData.get(position).getCustomer_phoneno());
        holder.address_adapter.setText(newCustomerListData.get(position).getCustomer_address());


        holder.cardview_list.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onLongClick(View view) {
                // Start an alpha animation for clicked item
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(4000);
                view.setBackgroundColor(R.color.color1);
                view.startAnimation(animation1);
                // getting selected position in the list view
                openDialoge(newCustomerListData.get(position).getCustomer_id(),position);
                return true;
            }
        });



    }

    private void openDialoge(final int selected_position_table_id,final int position) {
        TextView edit_text;
        TextView delete_text;
         dialog = new Dialog(activity);
        /*dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);*/
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.customer_edit_delete_dialog);
        edit_text = dialog.findViewById(R.id.dialog_text_edit);
        delete_text = dialog.findViewById(R.id.dialog_text_delete);
        dialog.show();


        delete_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCustomerListData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,newCustomerListData.size());
                dialog.dismiss();
                deleteDataChanges(selected_position_table_id);


            }
        });

    }

    private void deleteDataChanges(int selected_position_table_id) {
        // send the selected id to the database helper class
        dataBaseHelperClass.deleteSpecificIld(selected_position_table_id);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Deleted Successfully");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
        });
        builder.show();





    }

    @Override
    public int getItemCount() {
        return newCustomerListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView customer_name_adapter;
        TextView customer_phoneno_adapter;
        TextView address_adapter;
        CardView cardview_list;



        public ViewHolder(View itemView) {
            super(itemView);
            cardview_list = (CardView) itemView.findViewById(R.id.cardview_list);
            customer_name_adapter = (TextView)itemView.findViewById(R.id.customer_name_adapter);
            customer_phoneno_adapter = (TextView)itemView.findViewById(R.id.customer_phoneno_adapter);
            address_adapter = (TextView)itemView.findViewById(R.id.address_adapter);


        }
    }


}
