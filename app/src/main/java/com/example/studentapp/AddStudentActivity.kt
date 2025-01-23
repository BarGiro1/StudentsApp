package com.example.studentapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    private var isEditMode = false
    private var originalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        originalId = intent.getStringExtra("student_id")
        isEditMode = originalId != null

        setupViews()

    }

    private fun setupViews() {
        val idInput = findViewById<EditText>(R.id.activity_add_student_student_id)
        val nameInput = findViewById<EditText>(R.id.activity_add_student_student_name)
        val phoneNumberInput = findViewById<EditText>(R.id.activity_add_student_student_phone_number)
        val addressInput = findViewById<EditText>(R.id.activity_add_student_student_address)
        val checkBox = findViewById<CheckBox>(R.id.activity_add_student_checked)
        val imageView = findViewById<ImageView>(R.id.student_image)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        imageView.setImageResource(R.drawable.ic_launcher_background)

        if (isEditMode) {
            val student = StudentRepository.getStudentById(originalId!!)
            student?.let {
                idInput.setText(it.id)
                nameInput.setText(it.name)
                phoneNumberInput.setText(it.phoneNumber)
                addressInput.setText(it.address)
                checkBox.isChecked = it.isChecked
                checkBox.text = if (checkBox.isChecked) "checked" else "unchecked"
                deleteButton.visibility = View.VISIBLE
            }
        }

        findViewById<Button>(R.id.save_button).setOnClickListener {
            if (idInput.text.isEmpty() || nameInput.text.isEmpty() || phoneNumberInput.text.isEmpty() || addressInput.text.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newStudent = Student(
                id = idInput.text.toString(),
                name = nameInput.text.toString(),
                phoneNumber = phoneNumberInput.text.toString(),
                address = addressInput.text.toString(),
                isChecked = checkBox.isChecked
            )

            if (isEditMode) {
                StudentRepository.updateStudent(originalId!!, newStudent)
            } else {
                StudentRepository.addStudent(newStudent)
            }
            finish()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
           checkBox.text = if (isChecked) "checked" else "unchecked"
        }

        deleteButton.setOnClickListener {
            originalId?.let { id ->
                StudentRepository.getStudentById(id)?.let { student ->
                    StudentRepository.deleteStudent(student)
                }
            }
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}