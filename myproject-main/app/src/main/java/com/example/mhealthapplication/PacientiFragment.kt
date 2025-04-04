package com.example.mhealthapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PacientiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_pacienti, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Sample data
        val examList: MutableList<Item> = ArrayList()
        examList.add(Item("Popescu Ionut"))
        examList.add(Item("Iota Cristina"))
        examList.add(Item("Adam Cristina"))
        examList.add(Item("Munteanu Andreea"))
        examList.add(Item("Mitroi Adina"))
        examList.add(Item("Vlad Dumitru"))
        examList.add(Item("Enache Cristian"))

        // Set LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Set Adapter
        val adapter = MyAdapter(examList)
        recyclerView.adapter = adapter
        return view
    }
}