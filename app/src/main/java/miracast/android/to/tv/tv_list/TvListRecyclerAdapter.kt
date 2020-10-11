package miracast.android.to.tv.tv_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import miracast.android.to.tv.MainActivity
import miracast.android.to.tv.R
import miracast.android.to.tv.connection_mode.ConnectionModeActivity
import miracast.android.to.tv.databinding.ActivityTvListBinding
import miracast.android.to.tv.databinding.TvListRecyclerItemViewModelBinding


class TvListRecyclerAdapter(
    var context: Context,
    val tvListActivityBinding: ActivityTvListBinding,
    var list: List<String>
) : RecyclerView.Adapter<TvListRecyclerAdapter.TvListViewHolder>(){

    private var lastPosition : Int=0;

    init {

        tvListActivityBinding.otherButton.setOnClickListener {
            val intent: Intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListViewHolder {
        var binding : TvListRecyclerItemViewModelBinding= TvListRecyclerItemViewModelBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return TvListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
        Picasso.get().load(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.binding.recyclerTvItemCastImageView)
        holder.binding.recyclerTvItemNameTextView.text=list.get(position)
        Picasso.get().load(R.drawable.arrow_right).error(R.drawable.arrow_right).into(holder.binding.recyclerTvItemArrowRightImageView)

        holder.binding.root.setOnClickListener {
            val intent: Intent = Intent(context, ConnectionModeActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onViewAttachedToWindow(holder: TvListViewHolder) {
        super.onViewAttachedToWindow(holder)
        setAnimation(holder.binding.root, 0)
    }

    override fun onViewDetachedFromWindow(holder: TvListViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.binding.root.clearAnimation()
    }


    class TvListViewHolder(binding: TvListRecyclerItemViewModelBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding : TvListRecyclerItemViewModelBinding=binding
    }


    private fun setAnimation(viewToAnimate: View, position: Int) {
        val animation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.slide_right_to_left)
        viewToAnimate.startAnimation(animation)
        lastPosition = position
    }




}