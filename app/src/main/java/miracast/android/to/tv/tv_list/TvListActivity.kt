package miracast.android.to.tv.tv_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityTvListBinding
import miracast.android.to.tv.privacy_policy.PrivacyPolicyActivity

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.toolbarPrivacyPolicyMenuId -> startActivity(
                Intent(
                    this@TvListActivity,
                    PrivacyPolicyActivity::class.java
                )
            )
            R.id.toolbarRatingMenuId -> openAppInPlayStore()
        }
        return true
    }

    private fun openAppInPlayStore() {
        val i: Intent =  Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://play.google.com/store/apps/details?id="+applicationContext.packageName)
        startActivity(i)
    }

}