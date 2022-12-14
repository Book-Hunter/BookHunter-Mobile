package com.zenlaeth.tpsup.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.*
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.activity.MonsterDetailsActivity
import com.zenlaeth.tpsup.bean.MonsterBean

class MonsterAdapter(
    val context: HomeActivity,
    private val monsterList: List<MonsterBean>,
    private val layoutId: Int
) : RecyclerView.Adapter<MonsterAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val monsterId:TextView? = view.findViewById(R.id.id_item)
        val monsterImage = view.findViewById<ImageView>(R.id.image_item)
        val monsterName:TextView? = view.findViewById(R.id.name_item)
        val monsterDescription: TextView? = view.findViewById(R.id.description_item)

        companion object {
            val monsterIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, MonsterDetailsActivity::class.java)
                // passer l'id à l'activité puis get unique monster
                intent.putExtra(monsterIdKey, monsterId?.text)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de l'article
        val currentMonster = monsterList[position]

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
//        Glide.with(context).load(Uri.parse(currentArticle.imageUrl)).into(holder.articleImage)
//        Picasso.get().load(currentMonster.imageUrl).into(holder.articleImage)

        // mettre à jour champs
        holder.monsterId?.text = currentMonster.id.toString()
        holder.monsterName?.text = currentMonster.name
        holder.monsterDescription?.text = currentMonster.description.take(20) + "..."

/*        // mettre à jour l'image (intent extra)
        holder.articleImage?.drawable */
    }

    override fun getItemCount(): Int = monsterList.size
}