package miracast.android.to.tv.guide

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityGuideBinding
import miracast.android.to.tv.privacy_policy.PrivacyPolicyActivity

class GuideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.toolbarPrivacyPolicyMenuId -> startActivity(
                Intent(
                    this@GuideActivity,
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