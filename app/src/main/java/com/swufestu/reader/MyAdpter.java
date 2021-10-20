package com.swufestu.reader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdpter extends BaseAdapter {
private Context context;
private List<IconText> Items=new ArrayList<IconText>();
public MyAdpter(Context context){
    this.context=context;
}
public void addItem(IconText it){
    Items.add(it);
}
    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int i) {
        return Items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
MyfirView fv;
if(view==null){
    fv=new MyfirView(context,Items.get(i));
}else {
    fv=(MyfirView) view;
    fv.setTx(Items.get(i).getText());
    fv.setIma(Items.get(i).getIcon());
}
        return fv;
    }

    public void setItems(List<IconText> items) {
        Items = items;
    }

    @Override
    public boolean isEnabled(int position) {
        return Items.get(position);
    }
}
