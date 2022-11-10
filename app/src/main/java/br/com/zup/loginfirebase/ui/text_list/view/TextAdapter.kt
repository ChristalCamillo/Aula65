package br.com.zup.loginfirebase.ui.text_list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.loginfirebase.data.datasource.model.Text
import br.com.zup.loginfirebase.databinding.TextItemBinding

class TextAdapter(private var textList: MutableList<Text>,
) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    class ViewHolder(val binding: TextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showText(Text: Text) {
            binding.tvText.text = Text.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = textList[position]
        holder.showText(text)
        updateTextList(textList)
    }

    override fun getItemCount() = textList.size

    fun updateTextList(newList: MutableList<Text>) {
        textList = newList
        notifyDataSetChanged()
    }
}