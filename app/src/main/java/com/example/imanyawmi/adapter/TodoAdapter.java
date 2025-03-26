package com.example.imanyawmi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imanyawmi.R;
import com.example.imanyawmi.model.TodoItem;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<TodoItem> items;
    private OnTodoItemClickListener listener;

    public interface OnTodoItemClickListener {
        void onItemClick(int position, TodoItem item);
        void onCheckboxClick(int position, TodoItem item, boolean isChecked);
    }

    public TodoAdapter(List<TodoItem> items) {
        this.items = items;
    }

    public void setOnTodoItemClickListener(OnTodoItemClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<TodoItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public void addItem(TodoItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void removeItem(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoItem item = items.get(position);

        holder.tvTodoText.setText(item.getText());
        holder.cbTodoComplete.setChecked(item.isCompleted());

        // Apply different style for completed items
        if (item.isCompleted()) {
            holder.tvTodoText.setAlpha(0.5f);
        } else {
            holder.tvTodoText.setAlpha(1.0f);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbTodoComplete;
        TextView tvTodoText;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTodoComplete = itemView.findViewById(R.id.cbTodoComplete);
            tvTodoText = itemView.findViewById(R.id.tvTodoText);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position, items.get(position));
                }
            });

            cbTodoComplete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    boolean isChecked = cbTodoComplete.isChecked();
                    TodoItem item = items.get(position);
                    item.toggleCompletion();
                    listener.onCheckboxClick(position, item, isChecked);
                    notifyItemChanged(position);
                }
            });
        }
    }
}