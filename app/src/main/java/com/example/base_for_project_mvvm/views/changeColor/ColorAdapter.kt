package com.example.base_for_project_mvvm.views.changeColor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_for_project_mvvm.ColorClassListItem
import com.example.base_for_project_mvvm.databinding.ItemLayoutBinding
import com.example.base_for_project_mvvm.model.ColorClass

class ColorAdapter(private val listener: Listener): RecyclerView.Adapter<ColorAdapter.ColorsViewHolder>() {

    var items : List<ColorClassListItem> = emptyList()
        set(value){
        field =value
        notifyDataSetChanged()
    }

    class ColorsViewHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =ItemLayoutBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener{
            dushka(binding.root)
        }
        return ColorsViewHolder(binding)
    }
    fun dushka(v:View){
        Log.d("HEYYYYYYYY","YO")
        var item =v.tag as ColorClass
        listener.onColorChosen(item)
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {

        val colorClass =items[position].colorName
        val selected =items[position].selected
        with(holder.binding){
            root.tag =colorClass
            nameColor.text =colorClass.name
            squareInItem.setBackgroundColor(colorClass.value)
            if(selected){
                selectedIndicatorImageView
            }
            selectedIndicatorImageView.visibility= if(selected) View.VISIBLE else View.GONE

        }






        val item =items[position]
        holder.binding.root.tag =item.colorName
        holder.binding.squareInItem.setBackgroundColor(item.colorName.value)
        holder.binding.nameColor.text = item.colorName.name

    }


    override fun getItemCount(): Int  =items.size

    interface Listener {
        /**
         * Called when user chooses the specified color
         * @param namedColor color chosen by the user
         */
        fun onColorChosen(namedColor: ColorClass)
    }



}
