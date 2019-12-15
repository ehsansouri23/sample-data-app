package com.example.sampledataapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledataapp.data.model.Address
import com.example.sampledataapp.viewholder.AddressViewHolder

class AddressAdapter : RecyclerView.Adapter<AddressViewHolder>() {
    var addresses: List<Address> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AddressViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(addresses[position])
    }

    override fun getItemCount(): Int = addresses.size
}