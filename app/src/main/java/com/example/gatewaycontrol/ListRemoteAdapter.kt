package com.example.gatewaycontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.gatewaycontrol.room.Remote

class ListRemoteAdapter(private val listRemote: ArrayList<Remote>, private val listener: onClickListener) :
    RecyclerView.Adapter<ListRemoteAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn: Button = itemView.findViewById(R.id.btnRow)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRemoteAdapter.ListViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListRemoteAdapter.ListViewHolder, position: Int) {
        val remote = listRemote[position]


        holder.btn.setText(remote.remoteName)
        holder.btn.setOnClickListener {
            listener.onClick(remote)
        }
    }

    override fun getItemCount(): Int {
        return listRemote.size
    }

    fun setData(list: List<Remote>){
        listRemote.clear()
        listRemote.addAll(list)
        notifyDataSetChanged()
    }

    interface onClickListener{
        fun onClick(remote: Remote)
    }

}