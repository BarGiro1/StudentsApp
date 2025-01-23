package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        val recyclerView =  findViewById<RecyclerView>(R.id.students_recycler_view)
        adapter = StudentAdapter(
            StudentRepository.getAllStudents(),
            onItemClick = { student ->
                Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra("student_id", student.id)
                    startActivity(this)
                }
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupAddButton() {
        findViewById<FloatingActionButton>(R.id.fab_add_student).setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        adapter.updateStudents(StudentRepository.getAllStudents())
        super.onResume()
    }
}