package com.swufestu.reader;

import android.graphics.drawable.Drawable;

public class IconText implements Comparable<IconText>{
    private String Text="";
    private Drawable Icon;
    private boolean Selectable = true;
    public IconText(String text, Drawable bullet) {
        Icon = bullet;
        Text = text;
    }

    public boolean isSelectable() {
        return Selectable;
    }

    public String getText() {
        return Text;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setText(String text) {
        Text = text;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }

    public void setSelectable(boolean selectable) {
        Selectable = selectable;
    }

    @Override
    public int compareTo(IconText iconText) {
        if(this.Text!=null){
            return  this.Text.compareTo(iconText.getText());
        }else {
            throw new IllegalArgumentException();
        }
    }
}
