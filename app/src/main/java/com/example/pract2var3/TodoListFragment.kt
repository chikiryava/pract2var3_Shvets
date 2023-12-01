package com.example.pract2var3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pract2var3.databinding.FragmentTodoListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var taskDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)

        taskDatabase = AppDatabase.getInstance(requireContext())

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        todoAdapter = TodoAdapter(
            requireContext(),
            emptyList(),
            onDeleteClick = { todo -> onDeleteClick(todo) },
            onItemClick = { todo -> onItemClicked(todo) }
        )
        recyclerView.adapter = todoAdapter
        updateRecyclerView()

        return binding.root
    }
    private fun onItemClicked(todo: Todo) {
        val bundle = Bundle().apply {
            putLong("id",todo.id)
            putString("title", todo.title)
            putString("description",todo.description)
            putBoolean("complete",todo.isCompleted)
        }
        val fragmentB = TodoInfoFragment()
        fragmentB.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.container, fragmentB)

// Добавьте в стек возврата (опционально)
        fragmentTransaction.addToBackStack("back")

        fragmentTransaction.commit()
    }

    private fun onDeleteClick(todo: Todo) {
        GlobalScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao().delete(todo)
        }
        updateRecyclerView()
    }

    private fun updateRecyclerView() {
        GlobalScope.launch(Dispatchers.Main) {
            val todoList = withContext(Dispatchers.IO) {
                taskDatabase.taskDao().getAllTodos()
            }
            todoAdapter.updateList(todoList)
            binding.recyclerView.adapter = todoAdapter
        }
    }
}
