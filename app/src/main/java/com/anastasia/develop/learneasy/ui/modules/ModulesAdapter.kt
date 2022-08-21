package com.anastasia.develop.learneasy.ui.modules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anastasia.develop.learneasy.R
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords

class ModulesAdapter : RecyclerView.Adapter<ModulesAdapter.StartFragmentHolder>() {

    var onShowClickListener: ((ModuleWithWords) -> Unit)? = null
    private val module = mutableListOf<ModuleWithWords>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartFragmentHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_modules, parent, false)
        return StartFragmentHolder(v)
    }

    override fun onBindViewHolder(holder: StartFragmentHolder, position: Int) {
        val m: ModuleWithWords = module[position]
        holder.bind(m)
    }

    override fun getItemCount(): Int = module.size

    fun setData(list: List<ModuleWithWords>) {
        module.clear()
        module.addAll(list)
    }

    inner class StartFragmentHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val txtNewModule = itemView.findViewById<TextView>(R.id.txt_new_module)
        private val txtModulesCount = itemView.findViewById<TextView>(R.id.txt_modules_count)
        private var module: ModuleWithWords? = null

        fun bind(moduleWithWords: ModuleWithWords) {
            this.module = moduleWithWords
            txtNewModule.text = moduleWithWords.module.name
            txtModulesCount.text = moduleWithWords.words.count().toString()
        }

        init {
            itemView.setOnClickListener {
                module?.let { onShowClickListener?.invoke(it) }
            }
        }
    }
}


