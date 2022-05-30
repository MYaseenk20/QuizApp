package com.example.myapplication.adaptor

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activities.QuestionActivity
import com.example.myapplication.models.Quiz
import com.example.myapplication.utils.getColor
import com.example.myapplication.utils.getIcon
import kotlinx.android.synthetic.main.quiz_item.view.*

class QuizAdaptor(val context: Context,val quizzes : List<Quiz>) : RecyclerView.Adapter<quizViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): quizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item,parent,false)
        return quizViewHolder(view)
    }

    override fun onBindViewHolder(holder: quizViewHolder, position: Int) {
        val currentItem = quizzes[position]
        holder.title.text = currentItem.title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(getColor.getColor()))
        holder.iconView.setImageResource(getIcon.getIcon())
        holder.itemView.setOnClickListener {
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",currentItem.title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }


}
class quizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.quizTitle
    val iconView = itemView.quizIcon
    val cardContainer = itemView.cardContainer
}

