package com.example.imanyawmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imanyawmi.adapter.TodoAdapter;
import com.example.imanyawmi.fragments.CalendarFragment;
import com.example.imanyawmi.model.TodoItem;
import com.example.imanyawmi.util.IslamicCalendarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlannerActivity extends AppCompatActivity {

    private RecyclerView rvTodoList;
    private Button btnAddToList;
    private Button btnModifyList;
    private ImageButton btnHome;
    private TodoAdapter todoAdapter;
    private List<TodoItem> todoItems;
    private IslamicCalendarHelper.CalendarDay selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        // Initialize views
        rvTodoList = findViewById(R.id.rvTodoList);
        btnAddToList = findViewById(R.id.btnAddToList);
        btnModifyList = findViewById(R.id.btnModifyList);
        btnHome = findViewById(R.id.btnHome);

        // Add the calendar fragment
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.calendarFragmentContainer, new CalendarFragment());
            transaction.commit();
        }

        // Get selected day from intent (if passed)
        if (getIntent().hasExtra("selected_date")) {
            // This would need to be implemented to get the date from the calendar
            // For now we'll use today's date
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateStr = sdf.format(today);

            // In a real app, you'd retrieve stored tasks for this date
            todoItems = new ArrayList<>();
        } else {
            // Default to today if no date selected
            todoItems = new ArrayList<>();
        }

        // Initialize RecyclerView
        todoAdapter = new TodoAdapter(todoItems);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        rvTodoList.setAdapter(todoAdapter);

        // Set up click listeners
        todoAdapter.setOnTodoItemClickListener(new TodoAdapter.OnTodoItemClickListener() {
            @Override
            public void onItemClick(int position, TodoItem item) {
                showEditItemDialog(position, item);
            }

            @Override
            public void onCheckboxClick(int position, TodoItem item, boolean isChecked) {
                // Save changes to storage in a real app
                Toast.makeText(PlannerActivity.this,
                        item.isCompleted() ? "Task completed" : "Task marked incomplete",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToList.setOnClickListener(v -> showAddItemDialog());
        btnModifyList.setOnClickListener(v -> showModifyListDialog());
        btnHome.setOnClickListener(v -> finish());
    }

    // Method to be called when a day is selected in the calendar
    public void onDaySelected(IslamicCalendarHelper.CalendarDay day) {
        this.selectedDay = day;

        // In a real app, you would load tasks for the selected day
        // For demo, we'll just show a toast
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String dateStr = sdf.format(day.getGregorianDate());

        Toast.makeText(this, "Selected: " + dateStr, Toast.LENGTH_SHORT).show();

        // Clear the current list and load tasks for this day
        // This is a placeholder - in a real app, you'd load from storage
        todoItems.clear();

        // Add some sample tasks if it's an important day
        if (day.isImportantDay() && day.getEvent() != null) {
            todoItems.add(new TodoItem("Remember: " + day.getEvent()));
        }

        todoAdapter.notifyDataSetChanged();
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Task");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String taskText = input.getText().toString().trim();
            if (!taskText.isEmpty()) {
                TodoItem newItem = new TodoItem(taskText);
                todoAdapter.addItem(newItem);
                // Save to storage in a real app
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showEditItemDialog(int position, TodoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(item.getText());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String taskText = input.getText().toString().trim();
            if (!taskText.isEmpty()) {
                item.setText(taskText);
                todoAdapter.notifyItemChanged(position);
                // Save to storage in a real app
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.setNeutralButton("Delete", (dialog, which) -> {
            todoAdapter.removeItem(position);
            // Remove from storage in a real app
        });

        builder.show();
    }

    private void showModifyListDialog() {
        String[] options = {"Clear Completed Tasks", "Clear All Tasks"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modify List");
        builder.setItems(options, (dialog, which) -> {
            switch (which) {
                case 0:
                    // Clear completed tasks
                    List<TodoItem> activeTasks = new ArrayList<>();
                    for (TodoItem item : todoItems) {
                        if (!item.isCompleted()) {
                            activeTasks.add(item);
                        }
                    }
                    todoAdapter.updateData(activeTasks);
                    todoItems = activeTasks;
                    break;
                case 1:
                    // Clear all tasks
                    todoItems.clear();
                    todoAdapter.notifyDataSetChanged();
                    break;
            }
            // Save changes to storage in a real app
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}