package com.codingblocks.mynotesusingdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    ArrayList<Note> arrayList;
    NotesDataBaseHelper notesDataBaseHelper;

    public NotesAdapter(ArrayList<Note> notes, NotesDataBaseHelper notesDataBaseHelper) {
        this.arrayList = notes;
        this.notesDataBaseHelper=notesDataBaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_notes,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Note note=arrayList.get(i);
        viewHolder.title.setText(note.getTitle());
        viewHolder.description.setText(note.getDescription());
        viewHolder.timestamp.setText(note.getTimeStamp());
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note delnote=arrayList.get(i);
                notesDataBaseHelper.deleteNote(delnote);
                arrayList.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,timestamp;
        Button btndelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tvtitle);
            description=itemView.findViewById(R.id.tvdes);
            timestamp=itemView.findViewById(R.id.tvtimestamp);
            btndelete=itemView.findViewById(R.id.btndelete);
        }
    }
}
