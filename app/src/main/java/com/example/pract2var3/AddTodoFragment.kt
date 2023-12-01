package com.example.pract2var3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pract2var3.databinding.FragmentAddTodoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTodoFragment : Fragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private lateinit var taskDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        taskDatabase = AppDatabase.getInstance(requireContext())
        binding.addTodoButton.setOnClickListener {
            if (binding.todoDescription.text.replace("\\s".toRegex(), "")
                    .isEmpty() || binding.todoName.text.replace("\\s".toRegex(), "")
                    .isEmpty()
            ) {
                Toast.makeText(requireContext(), "Вы не заполнили все поля", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener;
            }
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    taskDatabase.taskDao().insert(
                        Todo(
                            title = binding.todoName.text.toString(),
                            description = binding.todoDescription.text.toString()
                        )
                    )
                }
            }
            Toast.makeText(requireContext(), "Вы добавили задачу", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}