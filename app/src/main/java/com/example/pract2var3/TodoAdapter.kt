package com.example.pract2var3

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pract2var3.R
import com.example.pract2var3.Todo
import com.example.pract2var3.TodoViewHolder

class TodoAdapter(
    private val context: Context,
    private var todoList: List<Todo>,
    private val onDeleteClick: (Todo) -> Unit,
    private val onItemClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todoList[position]
        holder.bind(currentTodo)
        holder.buttonOptions.setOnClickListener {
            showDeleteConfirmationDialog(currentTodo)
        }
        holder.itemView.setOnClickListener {
            onItemClick(currentTodo)
        }
    }

    override fun getItemCount() = todoList.size

    fun updateList(newList: List<Todo>) {
        todoList = newList
        notifyDataSetChanged()
    }
    private fun showDeleteConfirmationDialog(todo: Todo) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Удаление")
            .setMessage("Вы уверены, что хотите удалить элемент?")
            .setPositiveButton("Да") { _, _ ->
                onDeleteClick(todo)
            }
            .setNegativeButton("Отмена") { _, _ ->
                // Отмена удаления, ничего не делаем
            }
            .create()
            .show()
    }
}