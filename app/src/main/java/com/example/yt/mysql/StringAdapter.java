package com.example.yt.mysql;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by yt on 14-Dec-17.
 */

public class StringAdapter extends ArrayAdapter<DetailsClass> {
    public StringAdapter(@NonNull Context context, ArrayList<DetailsClass> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.listitem ,parent,false);
        }
        DetailsClass currentItem = getItem(position);

        TextView id = (TextView)convertView.findViewById(R.id.id);
        id.setText(currentItem.getId());

        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(currentItem.getName());

        TextView surname = (TextView)convertView.findViewById(R.id.surname);
        surname.setText(currentItem.getSurname());

        TextView marks = (TextView)convertView.findViewById(R.id.marks);
        marks.setText(currentItem.getMarks());

        return convertView;
    }
}
