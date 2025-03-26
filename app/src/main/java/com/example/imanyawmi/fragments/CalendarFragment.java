// CalendarFragment.java - Create this as a new file
package com.example.imanyawmi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imanyawmi.R;
import com.example.imanyawmi.adapter.IslamicCalendarAdapter;
import com.example.imanyawmi.util.IslamicCalendarHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private IslamicCalendarHelper calendarHelper;
    private RecyclerView rvCalendar;
    private IslamicCalendarAdapter calendarAdapter;
    private TextView tvCurrentMonth;
    private ImageView btnPreviousMonth;
    private ImageView btnNextMonth;
    private Calendar currentCalendar;
    private LinearLayout calendarContainer;
    private ImageView btnToggleCalendar;
    private boolean isCalendarExpanded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Initialize views
        rvCalendar = view.findViewById(R.id.rvCalendar);
        tvCurrentMonth = view.findViewById(R.id.tvCurrentMonth);
        btnPreviousMonth = view.findViewById(R.id.btnPreviousMonth);
        btnNextMonth = view.findViewById(R.id.btnNextMonth);
        calendarContainer = view.findViewById(R.id.calendarContainer);
        btnToggleCalendar = view.findViewById(R.id.btnToggleCalendar);

        // Initialize calendar helper
        calendarHelper = new IslamicCalendarHelper();

        // Set up current calendar to track the month being displayed
        currentCalendar = Calendar.getInstance();

        // Set up RecyclerView with grid layout (7 columns for days of week)
        rvCalendar.setLayoutManager(new GridLayoutManager(getContext(), 7));

        // Load current month's data
        loadMonthData(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH));

        // Set up click listeners for navigation buttons
        btnPreviousMonth.setOnClickListener(v -> navigateToPreviousMonth());
        btnNextMonth.setOnClickListener(v -> navigateToNextMonth());

        // Set up click listener for toggle button - THIS FIXES YOUR ISSUE
        btnToggleCalendar.setOnClickListener(v -> toggleCalendarVisibility());

        // Initially collapse the calendar
        calendarContainer.setVisibility(View.GONE);

        return view;
    }

    private void loadMonthData(int year, int month) {
        // Get days for the month
        List<IslamicCalendarHelper.CalendarDay> days = calendarHelper.getDaysInMonth(year, month);

        // Update month title
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        currentCalendar.set(Calendar.YEAR, year);
        currentCalendar.set(Calendar.MONTH, month);
        currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
        tvCurrentMonth.setText(monthFormat.format(currentCalendar.getTime()));

        // Initialize adapter if needed
        if (calendarAdapter == null) {
            calendarAdapter = new IslamicCalendarAdapter(getContext(), days,
                    (day, position) -> onDaySelected(day));
            rvCalendar.setAdapter(calendarAdapter);
        } else {
            // Update existing adapter data
            calendarAdapter.updateData(days);
        }
    }

    private void navigateToPreviousMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        loadMonthData(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH));
    }

    private void navigateToNextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        loadMonthData(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH));
    }

    private void onDaySelected(IslamicCalendarHelper.CalendarDay day) {
        // Handle day selection - can be implemented to communicate with the activity
        // For example, you might want to load tasks for this day
        if (getActivity() != null) {
            // Example: ((PlannerActivity) getActivity()).onDaySelected(day);
        }
    }

    // This method controls calendar expansion/collapse - fixes your issue
    private void toggleCalendarVisibility() {
        isCalendarExpanded = !isCalendarExpanded;

        if (isCalendarExpanded) {
            calendarContainer.setVisibility(View.VISIBLE);
            btnToggleCalendar.setRotation(180); // Rotate arrow to point up
        } else {
            calendarContainer.setVisibility(View.GONE);
            btnToggleCalendar.setRotation(0); // Rotate arrow to point down
        }
    }
}