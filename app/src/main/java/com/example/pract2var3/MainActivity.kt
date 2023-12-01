package com.example.pract2var3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_todo_list -> {
                    // Замените на реальный ID вашего элемента меню
                    replaceFragment(TodoListFragment()) // Замените YourFragment на ваш фрагмент
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_add_todo -> {
                    // Замените на реальный ID вашего элемента меню
                    replaceFragment(AddTodoFragment()) // Замените YourFragment на ваш фрагмент
                    return@setOnNavigationItemSelectedListener true
                }
                // Добавьте дополнительные ветви для других элементов меню, если необходимо
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        // При запуске приложения отображаем начальный фрагмент
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,TodoListFragment()) // Замените AddTodoFragment() на ваш фрагмент по умолчанию
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}