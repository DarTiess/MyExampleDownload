package com.example.myexample.ui.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myexample.DataBaseDownLoad;
import com.example.myexample.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    ListView downLoadList;
    DataBaseDownLoad dbDownLoad;
    SQLiteDatabase db;
    Cursor downLoadCursor;
    SimpleCursorAdapter downLoadAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
       // galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
           // @Override
          //  public void onChanged(@Nullable String s) {
              //  textView.setText(s);
          // }
       // });

        downLoadList=root.findViewById(R.id.list_files);

        dbDownLoad=new DataBaseDownLoad(downLoadList.getContext());

        return root;
    }
    public void onResume() {
        super.onResume();
        db = dbDownLoad.getReadableDatabase();

       downLoadCursor =  db.rawQuery("select * from "+ DataBaseDownLoad.TABLE, null);

        String[] headers = new String[] {DataBaseDownLoad.COLUMN_NAME,DataBaseDownLoad.COLUMN_SIZE, DataBaseDownLoad.COLUMN_PATH};

        downLoadAdapter = new SimpleCursorAdapter(downLoadList.getContext(), android.R.layout.two_line_list_item,
                downLoadCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);

       downLoadList.setAdapter(downLoadAdapter);
    }
}
