package com.anastasia.develop.learneasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ModulesAdapter : RecyclerView.Adapter<ModulesAdapter.StartFragmentHolder>() {

    var onShowClickListener: ((Module) -> Unit)? = null
    private val module = mutableListOf<Module>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartFragmentHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_modules, parent, false)
        return StartFragmentHolder(v)
    }

    override fun onBindViewHolder(holder: StartFragmentHolder, position: Int) {
        val m: Module = module[position]
        holder.bind(m)
    }

    override fun getItemCount(): Int = module.size

    fun setData(list: List<Module>) {
        module.clear()
        module.addAll(list)
    }

    inner class StartFragmentHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val txtNewModule = itemView.findViewById<TextView>(R.id.txt_new_module)
        private val txtModulesCount = itemView.findViewById<TextView>(R.id.txt_modules_count)
        private var module: Module? = null

        fun bind(module: Module) {
            this.module = module
            txtNewModule.text = module.name
            txtModulesCount.text = module.words.count().toString()
        }

        init {
            itemView.setOnClickListener {
                module?.let { onShowClickListener?.invoke(it) }
            }
        }
    }
}


