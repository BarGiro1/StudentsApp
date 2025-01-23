package com.example.studentapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getAllStudents(): List<Student> = students

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(originalId: String, updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == originalId }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }

}