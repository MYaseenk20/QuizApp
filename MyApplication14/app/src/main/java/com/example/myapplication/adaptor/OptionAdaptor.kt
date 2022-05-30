package com.example.myapplication.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Question
import kotlinx.android.synthetic.main.activity_question.view.*
import kotlinx.android.synthetic.main.option_item.view.*

class OptionAdaptor(val context: Context, val question: Question) : RecyclerView.Adapter<optionViewHolder>() {
    private val option : List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return optionViewHolder(view)
    }

    override fun onBindViewHolder(holder: optionViewHolder, position: Int) {
        holder.option.text = option[position]
//        holder.description.text = question.description
        holder.itemView.setOnClickListener {
            question.useranswer = option[position]
            notifyDataSetChanged()
        }
        if (question.useranswer == option[position]){
            holder.container.setBackgroundResource(R.drawable.red_border_btn)
        }else{
            holder.container.setBackgroundResource(R.drawable.simple_border_btn)

        }
    }

    override fun getItemCount(): Int {
        return option.size
    }
}

class optionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val option = itemView.quiz_option
    val description = itemView.description
    val container = itemView.option_container

}