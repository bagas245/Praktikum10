package com.alvinbagasoctaviandi_19102046.myapplication.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvinbagasoctaviandi_19102046.myapplication.QuoteAddUpdateActivity
import com.alvinbagasoctaviandi_19102046.myapplication.R
import com.alvinbagasoctaviandi_19102046.myapplication.data.Quote
import com.alvinbagasoctaviandi_19102046.myapplication.databinding.ItemQuoteBinding
import com.alvinbagasoctaviandi_19102046.myapplication.helper.EXTRA_POSITION
import com.alvinbagasoctaviandi_19102046.myapplication.helper.EXTRA_QUOTE
import com.alvinbagasoctaviandi_19102046.myapplication.helper.REQUEST_UPDATE
import com.alvinbagasoctaviandi_19102046.myapplication.helper.categoryList

class QuoteAdapter(private val activity: Activity):
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    var listQuotes = ArrayList<Quote>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            QuoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }
    override fun getItemCount(): Int = this.listQuotes.size
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(listQuotes[position],position)
    }
    inner class QuoteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemQuoteBinding.bind(itemView)
        fun bind(quote: Quote, position: Int) {
            binding.tvItemTitle.text = quote.title
            binding.tvItemCategory.text = categoryList[quote.category!!.toInt()]
            binding.tvItemDate.text = quote.date
            binding.tvItemDescription.text = quote.description
            binding.cvItemQuote.setOnClickListener{
                val intent = Intent(activity, QuoteAddUpdateActivity::class.java)
                intent.putExtra(EXTRA_POSITION, position)
                intent.putExtra(EXTRA_QUOTE, quote)
                activity.startActivityForResult(intent, REQUEST_UPDATE)
            }
        }
    }
    fun addItem(quote: Quote) {
        this.listQuotes.add(quote)
        notifyItemInserted(this.listQuotes.size - 1)
    }
    fun updateItem(position: Int, quote: Quote) {
        this.listQuotes[position] = quote
        notifyItemChanged(position, quote)
    }
    fun removeItem(position: Int) {
        this.listQuotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listQuotes.size)
    }

}