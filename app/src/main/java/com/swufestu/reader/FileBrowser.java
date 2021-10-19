package com.swufestu.reader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.swufestu.reader.domain.Book;
import com.swufestu.reader.pramater.Constrant;
import com.swufestu.reader.util.DB;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FileBrowser extends ListActivity {
    private static final String TAG="FileBrowser";
    private DB dbHelper=null;
    private static final int EXIT = Menu.FIRST;
    private static final int CIRC_SCREEN = Menu.FIRST + 1;
    private static final int DELETEFILE = 2;
    private enum DISPLAYMODE {
        ABSOLUTE, RELATIVE;
    }
    private final DISPLAYMODE DisplayMode = DISPLAYMODE.RELATIVE;
    private List<IconText> DirectoryList = new ArrayList<IconText>();
    private File CurrentDirectory = new File("F:\\大四上\\BOOK");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        browseToSdCard();
        this.setSelection(0);
        this.getListView().setOnItemLongClickListener(OnItemLongClickListener);
    }

    private void browseToSdCard() {
        browseToWhere(new File(getString(R.string.sdcard)));
    }

    private void browseToWhere(final File file) {
        if(this.DisplayMode==DISPLAYMODE.RELATIVE){
            this.setTitle(file.getAbsolutePath()+'-'+getString(R.string.app_name));
        }if(file.isDirectory()){
            this.CurrentDirectory=file;
            fill(file.listFiles());
        }else {
            DialogInterface.OnClickListener onClickListener=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            };
            DialogInterface.OnClickListener onClickListener1=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            };
        }
    }

    private void fill(File[] listFiles) {
        this.DirectoryList.clear();
        this.DirectoryList.add(new IconText(getString(R.string.current_dir),getResources().getDrawable(R.drawable.folder32)));
        this.DirectoryList.add(new IconText(getString(R.string.ad),getResources().getDrawable(R.drawable.folder32)))
        if(!this.CurrentDirectory.getParent().equals("/"))
            this.DirectoryList.add(new IconText(
                    getString(R.string.up_one_level),getResources().getDrawable(R.drawable.uponelevel)
            ));
            Drawable currentIcon=null;
            for(File cur:listFiles){
                if(cur.isDirectory()){
                    currentIcon=getResources().getDrawable(R.drawable.folder32);
                }else {
                    String filename=cur.getName();
                    if(checkEnd(filename,getResources().getStringArray(R.array.textEnds))){
                        currentIcon=getResources().getDrawable(R.drawable.text32);
                    }
                    if(checkEnd(filename,getResources().getStringArray(R.array.imageEnds))){
                        currentIcon=getResources().getDrawable(R.drawable.image32);
                        Log.i(TAG,"path:"+filename);
                    }if(checkEnd(filename,getResources().getStringArray(R.array.htmlEnds))){
                        currentIcon=getResources().getDrawable(R.drawable.webtext32);
                    }if(checkEnd(filename,getResources().getStringArray(R.array.umdEnds))){
                        currentIcon=getResources().getDrawable(R.drawable.umd32);
                    }
                }
                switch (this.DisplayMode){
                    case ABSOLUTE:
                        this.DirectoryList.add(new IconText(cur.getPath(),currentIcon));
                        break;
                    case RELATIVE:
                        int length = this.CurrentDirectory.getAbsolutePath().length();
                        this.DirectoryList.add(new IconText(cur.getAbsolutePath().substring(length),currentIcon));
                        break;
                }
            }
        Collections.sort(this.DirectoryList);
        MyAdpter adpter = new MyAdpter(this);
        adpter.setItems(this.DirectoryList);
        this.setListAdapter(adpter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int selectionid=(int) id;
        String selecttext = this.DirectoryList.get(selectionid).getText();
        if(selecttext.equals(getString(R.string.current_dir))){
            this.browseToWhere(this.CurrentDirectory);
        }else if(selecttext.equals(getString(R.string.up_one_level))){
            this.upOneLevel();

        }else {
            File file=null;
            switch (this.DisplayMode){
                case RELATIVE:
                    file=new File(this.CurrentDirectory.getAbsolutePath()
                            +this.DirectoryList.get(selectionid).getText());
                    String filepath=file.getAbsolutePath();
                    if(file.isFile()){
                        if(checkEnd(filepath,getResources().getStringArray(R.array.imageEnds)))
                            setProgressBarIndeterminateVisibility(false);
                        Intent i = new Intent(this, PictureBrowser.class);
                        i.putExtra("filePath",filepath);
                        startActivity(i);
                        setProgressBarIndeterminateVisibility(true);
                    }if(checkEnd(filepath,getResources().getStringArray(
                            R.array.htmlEnds
                ))){
                        setProgressBarIndeterminateVisibility(false);
                    Intent i = new Intent(this, HtmlBrowser.class);
                    i.putExtra("filePath",filepath);
                    startActivity(i);
                    setProgressBarIndeterminateVisibility(true);
                }if(checkEnd(filepath,getResources().getStringArray(
                        R.array.textEnds
                ))){
                    setProgressBarIndeterminateVisibility(false);
                    dbHelper = new DB(this);
                    Book book=new Book();
                    book.setBookPath(filepath);
                    Log.i(TAG,"path:"+book.getBookPath());
                    Constrant.BOOK_ID_IN_DATABASE=dbHelper.saveBook(book);
                    dbHelper.close();
                    Intent i = new Intent();
                    i.putExtra("filePath",filepath);
                    i.setClass(getApplicationContext(),)
                    startActivity(i);
                    setProgressBarIndeterminateVisibility(true);
                }
            }
        }
    }

    private void upOneLevel() {
    }

    private boolean checkEnd(String fileName, String[] fileEnd) {

    }
}