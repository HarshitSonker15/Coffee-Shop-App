package com.example.coffeeshopapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshopapp.Domain.ItemsModel
import com.example.coffeeshopapp.Helper.ChangeNumberItemsListener
import com.example.coffeeshopapp.Helper.ManagmentCart
import com.example.coffeeshopapp.databinding.ViewholderCartBinding

class CartAdapter (

    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemListener: ChangeNumberItemsListener?= null

): RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder (val binding: ViewholderCartBinding):
    RecyclerView.ViewHolder(binding.root)

    private val managmentCart= ManagmentCart(context)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="$${item.price}"
        holder.binding.totalEachItem.text="$${item.numberInCart*item.price}"
        holder.binding.numberInCartTxt.text=item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        holder.binding.plusBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }
        holder.binding.minusBtn.setOnClickListener {
                managmentCart.minusItem(listItemSelected,position,object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemListener?.onChanged()
                    }
                })
            }
        holder.binding.removeItemBtn.setOnClickListener {
            managmentCart.removeItem(listItemSelected,position,object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }


    }

    override fun getItemCount(): Int = listItemSelected.size

}