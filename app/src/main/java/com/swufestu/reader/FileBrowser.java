package com.swufestu.reader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import com.swufestu.reader.util.DB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileBrowser extends ListActivity {
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
            this.getCurrentFocus();
        }
    }
}