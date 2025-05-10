package com.example.cultivationdiary_test2.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultivationdiary_test2.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    public final TextView Month;
    public final TextView dayOfWeek;
    public final ConstraintLayout DayLayout;
    public final ImageView iconDiary;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.NumberDay);
        Month = itemView.findViewById(R.id.Month);
        dayOfWeek = itemView.findViewById(R.id.DayOfWeek);
        DayLayout = itemView.findViewById(R.id.DayLayout);
        iconDiary = itemView.findViewById(R.id.DiaryIcon);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view)
    {
        String fulldate = (String) view.getTag();
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText(), fulldate);
    }
}
