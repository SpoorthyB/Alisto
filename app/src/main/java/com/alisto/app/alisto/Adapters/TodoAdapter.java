package com.alisto.app.alisto.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alisto.app.alisto.Models.TodoModel;
import com.alisto.app.alisto.R;

import java.util.ArrayList;

/**
 * Created by Spoorthy on 9/22/2016.
 */
public class TodoAdapter extends ArrayAdapter<TodoModel> {

    public TodoAdapter(Context context, ArrayList<TodoModel> todos) {
        super(context, 0, todos);
    }

    private static class ViewHolder {
        TextView description;
        TextView priority;
        //TextView dueDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TodoModel item = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_todo, parent, false);
            viewHolder.description = (TextView) convertView.findViewById(R.id.TV_todo_desc);
            viewHolder.priority = (TextView) convertView.findViewById(R.id.TV_todo_priority);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.description.setText(item.description);
        viewHolder.priority.setText(item.priority);

        return convertView;
    }
}
