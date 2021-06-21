package br.com.modulo1.ui.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.modulo1.R;
import br.com.modulo1.model.User;

public class UserListAdapter extends BaseAdapter {

    private final List<User> users = new ArrayList<>();
    private final Context context;

    public UserListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        ((TextView) view.findViewById(R.id.name_user_item)).setText(users.get(position).getName());
        ((TextView) view.findViewById(R.id.email_user_item)).setText(users.get(position).getEmail());
        return view;
    }

    public void remove(User user) {
        users.remove(user);
        notifyDataSetChanged();
    }

    public void refreshAdapter(List<User> list) {
        users.clear();
        users.addAll(list);
        notifyDataSetChanged();
    }
}
