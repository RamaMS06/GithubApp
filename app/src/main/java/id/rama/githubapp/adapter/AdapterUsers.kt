package id.rama.githubapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rama.githubapp.R
import id.rama.githubapp.model.ModelUsers
import kotlinx.android.synthetic.main.item_users_github.view.*

class AdapterUsers(var context: Context, var clickListener: UsersClickListener) :
    RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {
    private var listUsers = emptyList<ModelUsers>()

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.cv_container_users!!

        fun bindData(data: ModelUsers) {
            with(itemView) {
                txt_username_users.text = data.login
                Glide.with(context)
                    .load(data.avatar_url)
                    .into(img_users)
            }
        }

        fun init(item: ModelUsers, action: UsersClickListener) {
            with(itemView) {
                setOnClickListener {
                    action.onItemClickListener(item, absoluteAdapterPosition)
                }
            }
        }
    }

    interface UsersClickListener {
        fun onItemClickListener(item: ModelUsers, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_users_github, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.container.animation =
            AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
        holder.bindData(listUsers[position])
        holder.init(listUsers[position], clickListener)
    }

    override fun getItemCount(): Int = listUsers.size

    fun setData(newList: List<ModelUsers>) {
        listUsers = newList
        notifyDataSetChanged()

    }
}