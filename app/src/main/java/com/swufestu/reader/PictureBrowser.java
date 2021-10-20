package com.swufestu.reader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

import com.swufestu.reader.pramater.Constrant;

import java.io.File;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.List;

public class PictureBrowser extends Activity {
    private ImageSwitcher Swicther;
    private static final String TAG="PictureBrowser";
    private List<String> ThumbIds = new ArrayList<String>();
    AdapterView.OnItemSelectedListener lis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_browser);
        Swicther=findViewById(R.id.switcher);
        Log.i(TAG,"swithcher is"+Swicther);
        Swicther.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        Swicther.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        addImageToList(Constrant.FILE_PATH);
        Gallery g=findViewById(R.id.gallery);
        Swicther.setBackgroundDrawable(Drawable.createFromPath(Constrant.FILE_PATH));
        g.setAdapter(new ImageAdapter(this));
        lis=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String path=ThumbIds.get(i);
                Log.i(TAG,"Item:"+path);
                Swicther.setBackgroundDrawable(Drawable.createFromPath(path));
                System.gc();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        g.setOnItemSelectedListener(lis);
    }

    private void addImageToList(String filePath) {
        File f=new File(filePath);
        if(f.isFile()){
            f=f.getParentFile();
        }
        Log.i(TAG,"get list file:\n");
        File[] fs=f.listFiles();
        int length = fs.length;
        String[] imageEnds = getResources().getStringArray(R.array.imageEnds);
        for(int i=0;i<length;i++){
            String path=fs[i].getAbsolutePath();
            Log.i(TAG,"file path is:"+path);
            if(checkEnds(path,imageEnds)){
                ThumbIds.add(path);
            }
        }
        f=null;
        fs=null;
        System.gc();
    }

    private boolean checkEnds(String path, String[] imageEnds) {
        for(String end:imageEnds){
            if(path.endsWith(end)){
                return true;
            }
        }
        return false;
    }


    private class ImageAdapter extends BaseAdapter {
        private Context Context1;
        public ImageAdapter(Context context) {
            Context1=context;
        }

        @Override
        public int getCount() {
            return ThumbIds.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imai=new ImageView(Context1);
            imai.setImageDrawable(Drawable.createFromPath(ThumbIds.get(i)));
            imai.setAdjustViewBounds(true);
            imai.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return imai;
        }
    }
}