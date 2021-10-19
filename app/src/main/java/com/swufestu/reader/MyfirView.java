package com.swufestu.reader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyfirView extends LinearLayout {
    private TextView tx;
    private ImageView ima;
public MyfirView(Context context,IconText icontext){
    super(context);
this.setOrientation(HORIZONTAL);
ima=new ImageView(context);
ima.setImageDrawable(icontext.getIcon());
ima.setPadding(0,2,5,0);
addView(ima,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tx=new TextView(context);
tx.setText(icontext.getText());
addView(tx,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
}
    public void setIma(Drawable draw) {
        ima.setImageDrawable(draw);
    }

    public void setTx(String words) {
        tx.setText(words);
    }
}
