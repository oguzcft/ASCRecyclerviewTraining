package com.ouz.ascrecyclerview02

import android.animation.LayoutTransition
import android.graphics.Color
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ouz.ascrecyclerview02.databinding.UsersGridBinding
import com.ouz.ascrecyclerview02.databinding.UsersLayoutBinding
import java.util.concurrent.Delayed

class GridAdap(private var userArrayList: ArrayList<DataType>, private val selectedU: MainActivity):RecyclerView.Adapter<GridAdap.UserGridDesign>(){

    var userTransfer:(DataType)->Unit={}

    class UserGridDesign(val binding: UsersGridBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UserGridDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val usersGridBinding = UsersGridBinding.inflate(layoutInflater, parent, false)
        return UserGridDesign(usersGridBinding)
    }

    override fun onBindViewHolder(holder:UserGridDesign, position: Int) {
        val user = userArrayList[position]


        holder.binding.textViewName.text = user.name
        holder.binding.textViewTelno.text = user.telNo
        holder.binding.circleImageView.setImageResource(user.image)

        holder.binding.cardView2.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        holder.binding.root.setOnLongClickListener {

            if (holder.binding.cardView2.isVisible) {

                TransitionManager.beginDelayedTransition(holder.binding.cardView2, AutoTransition())
                selectedU.selectedUser(null)
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#ff8a65"))
                holder.binding.cardView2.visibility = View.GONE
            }
            else {
                TransitionManager.beginDelayedTransition(holder.binding.cardView2, AutoTransition())

                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#ff7043"))
                selectedU.selectedUser(user)
                holder.binding.cardView2.visibility = View.VISIBLE
                holder.binding.cardView2.setOnClickListener {
                    userTransfer(user)
                }
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    interface SelectedUser{
        fun selectedUser(dataType: DataType?)
    }

    fun userListUpdate(userNewList: ArrayList<DataType>) {
        userArrayList = userNewList
        notifyDataSetChanged()
    }
}
