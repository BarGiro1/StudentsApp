package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var studentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("student_id") ?: return
        setupViews()
    }

    private fun setupViews() {
        val checkBox = findViewById<CheckBox>(R.id.activity_student_details_student_checked)
        val student = StudentRepository.getStudentById(studentId) ?: return
        val editButton = findViewById<Button>(R.id.activity_student_details_edit_button)
        findViewById<ImageView>(R.id.student_image).setImageResource(R.drawable.ic_launcher_background)
        findViewById<TextView>(R.id.activity_student_details_student_name).text = "Name: ${student.name}"
        findViewById<TextView>(R.id.activity_student_details_student_id).text = "ID: ${student.id}"
        findViewById<TextView>(R.id.activity_student_details_student_phone_number).text = "Phone: ${student.phoneNumber}"
        findViewById<TextView>(R.id.activity_student_details_student_address).text = "Address: ${student.address}"
        checkBox.isChecked = student.isChecked
        checkBox.text = if (student.isChecked) "checked" else "unchecked"

        editButton.setOnClickListener {
            Intent(this, AddStudentActivity::class.java).apply {
                putExtra("student_id", student.id)
                startActivity(this)
                finish()
            }
        }

    }

    override fun onResume() {
        setupViews()
        super.onResume()
    }
}