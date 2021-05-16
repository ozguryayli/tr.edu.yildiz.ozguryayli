package tr.edu.yildiz.ozguryayli;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecyclerAdapterExam extends RecyclerView.Adapter<RecyclerAdapterExam.ExamViewHolder> {
    public List<Integer> numbers = new ArrayList<Integer>();
    public int size;
    public int iperson;
    public int difficulty;
    public static List<Question> examQuestions = new ArrayList<Question>();

    public RecyclerAdapterExam(int size, int iperson, int difficulty) {
        this.size = size;
        this.iperson = iperson;
        this.difficulty = difficulty;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_exam, viewGroup,false);

        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder examViewHolder, int i) {

        examViewHolder.textQ.setText(MainActivity.personList.get(iperson).questionList.get(i).ques);
      /*  examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
        examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
        examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
        examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
        examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);*/
        examViewHolder.textA.setText("");
        examViewHolder.textB.setText("");
        examViewHolder.textC.setText("");
        examViewHolder.textD.setText("");
        examViewHolder.textE.setText("");
        if(MainActivity.personList.get(iperson).questionList.get(i).URI!=null){
            examViewHolder.textAtt.setText(MainActivity.personList.get(iperson).questionList.get(i).URI.getLastPathSegment());
        }


        int a=i;
        for(int b = 2; b < 6; b++) {
            numbers.add(b);
        }


        if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 1){
            examViewHolder.checkA.setChecked(true);
            examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
            for(int b=0;b<difficulty-1;b++){
                Collections.shuffle(numbers,new Random());

                if(numbers.get(0).equals(2)){
                    examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(3)){
                    examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(4)){
                    examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
                    numbers.remove(0);
                }
                else{
                    examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
                    numbers.remove(0);
                }
            }
        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 2){
            examViewHolder.checkB.setChecked(true);
            examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
            for(int b=0;b<difficulty-1;b++){
                Collections.shuffle(numbers,new Random());

                if(numbers.get(0).equals(2)){
                    examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(3)){
                    examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(4)){
                    examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
                    numbers.remove(0);
                }
                else{
                    examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
                    numbers.remove(0);
                }
            }

        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 3){
            examViewHolder.checkC.setChecked(true);
            examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
            for(int b=0;b<difficulty-1;b++){
                Collections.shuffle(numbers,new Random());

                if(numbers.get(0).equals(2)){
                    examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(3)){
                    examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(4)){
                    examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
                    numbers.remove(0);
                }
                else{
                    examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
                    numbers.remove(0);
                }
            }

        }
        else if(MainActivity.personList.get(iperson).questionList.get(i).ansRight == 4){
            examViewHolder.checkD.setChecked(true);
            examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
            for(int b=0;b<difficulty-1;b++){
                Collections.shuffle(numbers,new Random());

                if(numbers.get(0).equals(2)){
                    examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(3)){
                    examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
                    numbers.remove(0);
                }
                else if(numbers.get(0).equals(4)){
                    examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
                    numbers.remove(0);
                }
                else{
                    examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
                    numbers.remove(0);
                }
            }
        }
        else{
            examViewHolder.checkE.setChecked(true);
            examViewHolder.textE.setText(MainActivity.personList.get(iperson).questionList.get(i).ansE);
            for(int b=0;b<difficulty-1;b++){
                Collections.shuffle(numbers,new Random());

                if(numbers.get(0).equals(2)){
                    examViewHolder.textA.setText(MainActivity.personList.get(iperson).questionList.get(i).ansA);
                    numbers.remove(Integer.valueOf(2));
                }
                else if(numbers.get(0).equals(3)){
                    examViewHolder.textB.setText(MainActivity.personList.get(iperson).questionList.get(i).ansB);
                    numbers.remove(Integer.valueOf(3));
                }
                else if(numbers.get(0).equals(4)){
                    examViewHolder.textC.setText(MainActivity.personList.get(iperson).questionList.get(i).ansC);
                    numbers.remove(Integer.valueOf(4));
                }
                else{
                    examViewHolder.textD.setText(MainActivity.personList.get(iperson).questionList.get(i).ansD);
                    numbers.remove(Integer.valueOf(5));
                }
            }

        }

        examViewHolder.checkSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(examViewHolder.checkSelect.isChecked()){
                    examQuestions.add(MainActivity.personList.get(iperson).questionList.get(a));
                    if(MainActivity.personList.get(iperson).questionList.get(a).URI!=null){
                        examQuestions.get(examQuestions.size()-1).setURI(MainActivity.personList.get(iperson).questionList.get(a).URI);
                    }
                    if(examViewHolder.textA.getText().length()==0){
                        examQuestions.get(examQuestions.size()-1).setAnsA("");
                    }
                    if(examViewHolder.textB.getText().length()==0){
                        examQuestions.get(examQuestions.size()-1).setAnsB("");
                    }
                    if(examViewHolder.textC.getText().length()==0){
                        examQuestions.get(examQuestions.size()-1).setAnsC("");
                    }
                    if(examViewHolder.textD.getText().length()==0){
                        examQuestions.get(examQuestions.size()-1).setAnsD("");
                    }
                    if(examViewHolder.textE.getText().length()==0){
                        examQuestions.get(examQuestions.size()-1).setAnsE("");
                    }

                }
                else{
                    examQuestions.remove(MainActivity.personList.get(iperson).questionList.get(a));
                }

            }
        });




    }

    @Override
    public int getItemCount() {
        return size;
    }


    static class ExamViewHolder extends RecyclerView.ViewHolder{

        TextView textQ;
        TextView textA;
        TextView textB;
        TextView textC;
        TextView textD;
        TextView textE;
        TextView textAtt;
        CheckBox checkA;
        CheckBox checkB;
        CheckBox checkC;
        CheckBox checkD;
        CheckBox checkE;
        CheckBox checkSelect;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);

            textQ = itemView.findViewById(R.id.textView18);
            textA = itemView.findViewById(R.id.textView5);
            textB = itemView.findViewById(R.id.textView7);
            textC = itemView.findViewById(R.id.textView8);
            textD = itemView.findViewById(R.id.textView12);
            textE = itemView.findViewById(R.id.textView13);
            textAtt= itemView.findViewById(R.id.textViewAtt);
            checkA = itemView.findViewById(R.id.checkBox);
            checkB = itemView.findViewById(R.id.checkBox2);
            checkC = itemView.findViewById(R.id.checkBox3);
            checkD = itemView.findViewById(R.id.checkBox4);
            checkE = itemView.findViewById(R.id.checkBox5);
            checkSelect = itemView.findViewById(R.id.checkBoxSelect);
        }
    }
}
