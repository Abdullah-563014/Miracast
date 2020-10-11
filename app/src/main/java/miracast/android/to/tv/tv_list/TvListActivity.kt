package miracast.android.to.tv.tv_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityTvListBinding

class TvListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTvListBinding
    private lateinit var adapter: TvListRecyclerAdapter
    private lateinit var layoutManager : LinearLayoutManager
    private var list: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTvListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        loadTvListData()


    }

    private fun loadTvListData() {
        list.addAll(resources.getStringArray(R.array.tv_list_array))
        adapter.notifyDataSetChanged()
    }

    private fun setUpRecyclerView() {
        layoutManager= LinearLayoutManager(this)
        adapter= TvListRecyclerAdapter(this,binding,list)
        binding.tvListRecyclerView.layoutManager=layoutManager
        binding.tvListRecyclerView.adapter=adapter
        binding.tvListRecyclerView.setHasFixedSize(true)
    }

}