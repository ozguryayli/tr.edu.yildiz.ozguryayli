package tr.edu.yildiz.ozguryayli;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    public int size;
    public int iperson;

    public RecyclerAdapter(int size, int iperson) {
        this.size = size;
        this.iperson = iperson;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textQ.setText(MainActivity.personList.get(iperson).questionList.get(i).ques);
        myViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
        myViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
        myViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
        myViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
        myViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
        if(MainActivity.personList.get(iperson).questionList.get(i).URI!=null){
            myViewHolder.textAt.setText(MainActivity.personList.get(iperson).questionList.get(i).URI.getPath());
        }

        myViewHolder.textAt.setVisibility(View.VISIBLE);
        int a=i;
        if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 1){
            myViewHolder.checkA.setChecked(true);
        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 2){
            myViewHolder.checkB.setChecked(true);
        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 3){
            myViewHolder.checkC.setChecked(true);
        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 4){
            myViewHolder.checkD.setChecked(true);
        }
        else{
            myViewHolder.checkE.setChecked(true);
        }

        myViewHolder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UpdateActivity.class);
                ((ListQuestionsActivity)v.getContext()).finish();
                v.getContext().startActivity(intent);
                intent.putExtra("iquestion",a);
                intent.putExtra("iperson",iperson);

                notifyDataSetChanged();

            }
        });

        myViewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to delete?");

                builder.setPositiveButton("| Yes, delete |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        MainActivity.personList.get(iperson).questionList.remove(MainActivity.personList.get(iperson).questionList.get(a));
                        ((ListQuestionsActivity)v.getContext()).finish();
                        Intent intent = new Intent(v.getContext(),ListQuestionsActivity.class);
                        intent.putExtra("iperson",iperson);
                        v.getContext().startActivity(intent);
                    }
                });

                builder.setNegativeButton("| No, cancel |", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();




            }
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textQ;
        TextView textA;
        TextView textB;
        TextView textC;
        TextView textD;
        TextView textE;
        TextView textAt;
        CheckBox checkA;
        CheckBox checkB;
        CheckBox checkC;
        CheckBox checkD;
        CheckBox checkE;
        Button buttonDelete;
        Button buttonUpdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textQ = itemView.findViewById(R.id.textViewQ);
            textA = itemView.findViewById(R.id.textViewA);
            textB = itemView.findViewById(R.id.textViewB);
            textC = itemView.findViewById(R.id.textViewC);
            textD = itemView.findViewById(R.id.textViewD);
            textE = itemView.findViewById(R.id.textViewE);
            textAt = itemView.findViewById(R.id.textAt);
            checkA = itemView.findViewById(R.id.checkBoxAA);
            checkB = itemView.findViewById(R.id.checkBoxBB);
            checkC = itemView.findViewById(R.id.checkBoxCC);
            checkD = itemView.findViewById(R.id.checkBoxDD);
            checkE = itemView.findViewById(R.id.checkBoxEE);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);

        }

    }

}
