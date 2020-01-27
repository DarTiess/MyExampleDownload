package com.example.myexample.ui.home;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myexample.DataBaseDownLoad;
import com.example.myexample.R;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button loadBtn;
    public  TextView pathText;
    String path;
    EditText fileName;

    DataBaseDownLoad sqlDownloadHelper;
    SQLiteDatabase db;
    Cursor downLoadCursor;
    long Id=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        pathText=root.findViewById(R.id.path_text);
        loadBtn=root.findViewById(R.id.button_load);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnFile("https://www.ssyoutube.com/watch?v=k9dStNYqNXg&list=PLIU76b8Cjem54jkj0XtCAp8JqZooY_eBI&index=8");

             /*   try {
                    clickOnFile("http://ssyoutube.com/watch?v=wEWF2xh5E8s");
                } catch (JaxenException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });
        setContentView(loadBtn, new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        fileName=root.findViewById(R.id.name_file);
        sqlDownloadHelper=new DataBaseDownLoad(fileName.getContext());
        db=sqlDownloadHelper.getWritableDatabase();

        return root;
    }

    private void setContentView(Button loadBtn, LayoutParams layoutParams) {

    }
     private void clickOnFile(String url) {


           ChromeDriver driver = new ChromeDriver();
          driver.get(url);

       String element_submit = driver.findElement(By.className("link-download")).getAttribute("href");
         //WebElement elem=driver.findElement(By.tagName("a"));

           //  elem.click();
           //  String curentURL = driver.getCurrentUrl();
             pathText.setText(element_submit);


             Toast.makeText(this.loadBtn.getContext(), "fgjfgjfgvjtghjug озит 2", Toast.LENGTH_SHORT).show();


            // String curentURL = driver.getCurrentUrl();
          //   pathText.setText(curentURL);
         // downloadFile(curentURL);

    }
    private void downloadFile(String url) {



        new AsyncTask<String, Integer, File>() {

            private Exception m_error = null;


            @SuppressLint("WrongThread")
            @Override
            protected File doInBackground(String... strings) {
                URL url;
                HttpURLConnection urlConnection;
                InputStream inputStream;
                int totalSize;
                int downloadSize;
                byte[] buffer;
                int bufferLenght;

                File file = null;
                FileOutputStream fos = null;

                try {
                    url = new URL(strings[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    file = File.createTempFile("Dars", "download");
                    fos = new FileOutputStream(file);
                    inputStream = urlConnection.getInputStream();
                    totalSize = urlConnection.getContentLength();
                    downloadSize = 0;
                    path=file.getAbsolutePath();


                    buffer = new byte[1024];
                    bufferLenght = 0;

                    while ((bufferLenght = inputStream.read(buffer)) > 0) {

                        fos.write(buffer, 0, bufferLenght);
                        downloadSize += bufferLenght;
                        publishProgress(downloadSize, totalSize);
                    }



                    fos.close();
                    inputStream.close();

                    return file;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    m_error = e;
                } catch (IOException e) {
                    e.printStackTrace();
                    m_error = e;
                }

                return null;

            }

            protected void onPostExcecute(File file) {
                if (m_error != null) {
                    m_error.printStackTrace();
                    return;
                }

                file.delete();
            }
        }.execute(url);
        Toast.makeText(this.loadBtn.getContext(), "Введите денежную сумму перечисленную на депозит 2", Toast.LENGTH_SHORT).show();
        pathText.setText(path);
       /* ContentValues cv = new ContentValues();
        cv.put(DataBaseDownLoad.COLUMN_NAME, fileName.getText().toString());
        cv.put(DataBaseDownLoad.COLUMN_SIZE, String.valueOf(2442));
        cv.put(DataBaseDownLoad.COLUMN_PATH, path);
        //  if (Id > 0) {
        //    db.update(DataBaseDownLoad.TABLE, cv, DataBaseDownLoad.COLUMN_ID + "=" + String.valueOf(Id), null);
        //  } else {
        db.insert(DataBaseDownLoad.TABLE, null, cv);
        //  }

*/
    }
    private void goHome(){

        db.close();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* view.findViewById(R.id.button_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToHomeSecondFragment action =
                        HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment
                                ("From HomeFragment");
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(action);
            }
        });*/
    }
}
