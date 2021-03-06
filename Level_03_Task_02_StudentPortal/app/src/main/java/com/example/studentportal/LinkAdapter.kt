package com.example.studentportal

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_link.view.*

class LinkAdapter (private val links:List<Link>): RecyclerView.Adapter<LinkAdapter.ViewHolder>(){

    //variables used for the url of the website to add
    val webProtocol: String = "https://"
    val subDomain: String = "www."

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(link: Link){

            itemView.tvTitle.text = link.linkTitle
            itemView.tvLink.text = webProtocol + subDomain + link.linkAddress

            //For testing the click event
            itemView.setOnClickListener{

                //Still need to add link for site
                Snackbar.make(itemView, "Link To Portal: " + link.linkAddress , Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(links[position])
    }
}