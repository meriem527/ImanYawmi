package com.example.imanyawmi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imanyawmi.R;
import com.example.imanyawmi.util.IslamicCalendarHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IslamicCalendarAdapter extends RecyclerView.Adapter<IslamicCalendarAdapter.CalendarViewHolder> {

    private Context context;
    private List<IslamicCalendarHelper.CalendarDay> days;
    private OnDayClickListener onDayClickListener;
    private int selectedPosition = -1;
    private int currentDay = -1;

    public interface OnDayClickListener {
        void onDayClick(IslamicCalendarHelper.CalendarDay day, int position);
    }

    public IslamicCalendarAdapter(Context context, List<IslamicCalendarHelper.CalendarDay> days, OnDayClickListener listener) {
        this.context = context;
        this.days = days;
        this.onDayClickListener = listener;

        // Find today's position
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.getDefault());
        currentDay = Integer.parseInt(sdf.format(today.getTime()));
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar_day, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        IslamicCalendarHelper.CalendarDay day = days.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("d", Locale.getDefault());
        String dayNumber = sdf.format(day.getGregorianDate());

        holder.tvGregorianDay.setText(dayNumber);
        holder.tvHijriDay.setText(day.getHijriDate());

        // Style based on important day or selected day
        if (day.isImportantDay()) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.important_day_background));
            holder.tvGregorianDay.setTextColor(Color.WHITE);
            holder.tvHijriDay.setTextColor(Color.WHITE);
        } else if (position == selectedPosition) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.selected_day_background));
            holder.tvGregorianDay.setTextColor(Color.WHITE);
            holder.tvHijriDay.setTextColor(Color.WHITE);
        } else {
            // Handle current day highlighting
            Calendar cal = Calendar.getInstance();
            cal.setTime(day.getGregorianDate());
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            if (dayOfMonth == currentDay) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.current_day_background));
                holder.tvGregorianDay.setTextColor(Color.WHITE);
                holder.tvHijriDay.setTextColor(Color.WHITE);
            } else {
                holder.cardView.setCardBackgroundColor(Color.WHITE);
                holder.tvGregorianDay.setTextColor(Color.BLACK);
                holder.tvHijriDay.setTextColor(context.getResources().getColor(R.color.hijri_day_text));
            }
        }

        // Setup click listener
        holder.itemView.setOnClickListener(v -> {
            int oldSelectedPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            // Update the previously selected and newly selected items
            if (oldSelectedPosition != -1) {
                notifyItemChanged(oldSelectedPosition);
            }
            notifyItemChanged(selectedPosition);

            if (onDayClickListener != null) {
                onDayClickListener.onDayClick(day, selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void updateData(List<IslamicCalendarHelper.CalendarDay> newDays) {
        this.days = newDays;
        notifyDataSetChanged();
    }

    static class CalendarViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvGregorianDay;
        TextView tvHijriDay;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardDay);
            tvGregorianDay = itemView.findViewById(R.id.tvGregorianDay);
            tvHijriDay = itemView.findViewById(R.id.tvHijriDay);
        }
    }
}