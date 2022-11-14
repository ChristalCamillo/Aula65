package br.com.zup.loginfirebase.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.loginfirebase.databinding.TextItemBinding

class TextAdapter(private var textList: MutableList<String>,
) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    class ViewHolder(val binding: TextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showText(text: String) {
            binding.tvText.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val savedText = textList[position]
        holder.showText(savedText)
    }

    override fun getItemCount() = textList.size

    fun updateTextList(newList: MutableList<String>) {
        if (textList.size == 0){
            textList = newList
        }else{
            textList = mutableListOf()
            textList.addAll(newList)
        }
        notifyDataSetChanged()
    }
}