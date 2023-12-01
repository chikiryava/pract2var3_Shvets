package com.example.pract2var3

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    val buttonOptions: AppCompatButton = itemView.findViewById(R.id.buttonOptions)

    fun bind(todo: Todo) {
        // Установка текста и других данных в TextView
        textViewTitle.text = todo.title

        // Пример: Установка заднего фона в зависимости от значения переменной
        if (todo.isCompleted) {
            itemView.setBackgroundResource(R.drawable.completed_task)
        } else {
            itemView.setBackgroundResource(R.drawable.uncompleted_task)
        }

        // Дополнительные действия, связанные с ViewHolder

        // Например, обработка нажатия кнопки
        buttonOptions.setOnClickListener {
            // Обработка события при нажатии на кнопку
            // Можете добавить свой код здесь
        }
    }
}