package com.example.actividadnueva2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private String mCurrent;
    private Context context;
    private MyDB myDBA;
    public WordListAdapter(Context context, LinkedList<String> wordList, MyDB myDBA) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.myDBA=myDBA;
        this.context=context;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){ // Inflate an item view.
                View mItemView = mInflater.inflate(R.layout.row, parent, false); return new
                WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
         mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }



    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView wordItemView;
        private WordListAdapter mAdapter;
        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showFoodOrder();
        }
    }

    public void dialogerase(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Borrar una constante?");
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             mWordList.remove(mCurrent);
             myDBA.deleteItem(mCurrent);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void showFoodOrder() {
        Intent intent = new Intent(context,OrderActivity.class);
        intent.putExtra("nombre",name);
        intent.putExtra("numero",numero);

        context.startActivity(intent);
    }


}