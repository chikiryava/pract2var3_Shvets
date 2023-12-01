package com.example.pract2var3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pract2var3.databinding.FragmentAddTodoBinding
import com.example.pract2var3.databinding.FragmentTodoInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class TodoInfoFragment : Fragment() {
    private lateinit var binding: FragmentTodoInfoBinding
    private var id:Long = 0
    private var title = ""
    private var description = ""
    private var completed = false
    private lateinit var taskDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoInfoBinding.inflate(inflater, container, false)
        taskDatabase = AppDatabase.getInstance(requireContext())
        val bundle = arguments
        if (bundle != null) {
            id = bundle.getLong("id")
            title = bundle.getString("title").toString()
            description= bundle.getString("description").toString()
            completed = bundle.getBoolean("complete")
        }
        binding.todoDescriptionInfo.text = description
        binding.todoNameInfo.text = title
        binding.completedCheckboxInfo.isChecked = completed
        binding.completedCheckboxInfo.setOnClickListener{
            completed = binding.completedCheckboxInfo.isChecked;
            GlobalScope.launch(Dispatchers.IO) {
                taskDatabase.taskDao().updateIsCompleted(id,completed)
            }
        }
        return binding.root
    }



}