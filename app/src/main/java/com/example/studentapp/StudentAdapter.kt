package com.example.studentapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class StudentAdapter(
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit,
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.student_image)
        val nameText: TextView = view.findViewById(R.id.activity_list_item_student_name)
        val idText: TextView = view.findViewById(R.id.activity_list_item_student_id)
        val checkBox: CheckBox = view.findViewById(R.id.activity_list_item_student_checkbox)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        holder.nameText.text = student.name
        holder.idText.text = student.id
        holder.checkBox.isChecked = student.isChecked
        holder.itemView.setOnClickListener { onItemClick(student) }
        holder.checkBox.setOnClickListener { student.isChecked = holder.checkBox.isChecked }
    }
    override fun getItemCount() = students.size
    fun updateStudents(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}