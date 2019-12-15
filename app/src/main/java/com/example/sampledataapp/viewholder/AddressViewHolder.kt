package com.example.sampledataapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledataapp.R
import com.example.sampledataapp.data.model.Address
import com.example.sampledataapp.util.getFullName

class AddressViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.address_item, parent, false)) {

    private var address: TextView? = null
    private var name: TextView? = null
    private var mobile: TextView? = null

    init {
        address = itemView.findViewById(R.id.address)
        name = itemView.findViewById(R.id.name)
        mobile = itemView.findViewById(R.id.mobile)
    }

    fun bind(address: Address) {
        this.address?.text = address.address
        name?.text = getFullName(address.firstName, address.lastName)
        mobile?.text = address.mobile.toString()
    }

}