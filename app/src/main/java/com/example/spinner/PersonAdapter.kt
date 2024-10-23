package com.example.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PersonAdapter(private val context: Context, private val data: ArrayList<Person>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.person_item, parent, false)
            holder = ViewHolder()
            holder.secondNameTV = view.findViewById(R.id.secondNameTV)
            holder.firstNameTV = view.findViewById(R.id.firstNameTV)
            holder.roleTV = view.findViewById(R.id.roleTV)
            holder.ageTV = view.findViewById(R.id.ageTV)

            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val listItem = getItem(position) as Person
        holder.secondNameTV.text = listItem.secondName
        holder.firstNameTV.text = listItem.firstName
        holder.roleTV.text = listItem.role
        holder.ageTV.text = listItem.age.toString()

        return view
    }

    private class ViewHolder {
        lateinit var secondNameTV: TextView
        lateinit var firstNameTV: TextView
        lateinit var roleTV: TextView
        lateinit var ageTV: TextView
    }
}