package com.example.pract2var3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ITodoDao {

    @Insert
    suspend fun insert(todo: Todo)

    @Query("UPDATE todo_table SET isCompleted = :isCompleted WHERE id = :taskId")
    fun updateIsCompleted(taskId: Long, isCompleted: Boolean)

    @Delete
    suspend fun delete(todo:Todo)

    @Query("SELECT * from todo_table")
    suspend fun getAllTodos():List<Todo>
}