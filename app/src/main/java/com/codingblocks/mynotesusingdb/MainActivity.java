package com.codingblocks.mynotesusingdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     String title1;
     String des1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NotesDataBaseHelper notesDataBaseHelper=new NotesDataBaseHelper(this);
        final ArrayList<Note>notes=notesDataBaseHelper.getAllNotes();
        final ArrayList<Note>notes1=new ArrayList<>();
       if(notes.size()==0)
       {

       }
       else{

           RecyclerView recyclerView=findViewById(R.id.recyclerView);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           NotesAdapter notesAdapter=new NotesAdapter(notes,notesDataBaseHelper);
           recyclerView.setAdapter(notesAdapter);
       }



        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       final EditText title=findViewById(R.id.ettitle);
       final EditText des=findViewById(R.id.etdes);
       Button btnadd=findViewById(R.id.btnadd);

        final NotesAdapter notesAdapter=new NotesAdapter(notes,notesDataBaseHelper);
        recyclerView.setAdapter(notesAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title1 = title.getText().toString();

                des1 = des.getText().toString();
                notes1.add(new Note(title1,des1,""+System.currentTimeMillis(),0));

                for(int i=0;i<notes1.size();i++)
                {
                    notes.add(notes1.get(i));
                }
                notesAdapter.notifyDataSetChanged();
            }
        });

        notesDataBaseHelper.insert(new Note(title1,des1,""+System.currentTimeMillis(),0));



    }
}
