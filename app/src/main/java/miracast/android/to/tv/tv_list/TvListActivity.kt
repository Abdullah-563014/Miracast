package miracast.android.to.tv.tv_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        temporaryData()


    }

    private fun temporaryData() {
        for (item in 0..50) {
            list.add("Item number "+item)
        }
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