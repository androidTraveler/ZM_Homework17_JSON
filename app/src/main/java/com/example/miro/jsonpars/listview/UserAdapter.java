package com.example.miro.jsonpars.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.miro.jsonpars.R;
import com.example.miro.jsonpars.models.User;
import java.util.ArrayList;

public class UserAdapter extends BaseAdapter{
    private Context ctx;
    private LayoutInflater lInflater;
    ArrayList<User> objects;

    public UserAdapter(Context ctx, ArrayList<User> objects) {
        this.ctx = ctx;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        User user = getUser(position);
        ((TextView) view.findViewById(R.id.tvUser)).setText(user.getName());
        return view;
    }

    private User getUser(int position) {
        return ((User) getItem(position));
    }
}
