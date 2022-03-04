package com.haya.android_no_mori.ui.sample.firestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haya.android_no_mori.R
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser

class FireStoreSampleUserListAdapter(var userList: List<SampleUser>) :
    RecyclerView.Adapter<FireStoreSampleUserListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.user_name)
        val userFavoriteNum: TextView = view.findViewById(R.id.user_favorite_num)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.sample_user_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = userList[position]
        viewHolder.name.text = "ユーザー名：${user.name}"
        viewHolder.userFavoriteNum.text = "好きな数字：${user.favoriteNum}"
    }

    override fun getItemCount() = userList.size
}