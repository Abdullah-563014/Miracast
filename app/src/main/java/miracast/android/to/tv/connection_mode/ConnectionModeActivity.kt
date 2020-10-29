package miracast.android.to.tv.connection_mode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.squareup.picasso.Picasso
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityConnectionModeBinding
import miracast.android.to.tv.privacy_policy.PrivacyPolicyActivity
import miracast.android.to.tv.progress.ProgressActivity
import miracast.android.to.tv.tv_list.TvListActivity

class ConnectionModeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityConnectionModeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConnectionModeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpUi()
        initAnimation()

    }

    private fun setUpUi() {
        Picasso.get().load(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(binding.appLogo)
        binding.nextButton.setOnClickListener(this)
        binding.connectionModeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.autoConnectionRadioButton -> {
                    binding.autoConnectionRadioButton.isChecked=true
                    binding.manualConnectionRadioButton.isChecked=false
                }

                R.id.manualConnectionRadioButton -> {
                    binding.autoConnectionRadioButton.isChecked=false
                    binding.manualConnectionRadioButton.isChecked=true
                }
            }
        }
    }

    private fun initAnimation() {
        val blinkAnimation: Animation = AnimationUtils.loadAnimation(this@ConnectionModeActivity, R.anim.blink_animation)
        binding.nextButton.startAnimation(blinkAnimation)
    }

    private fun gotoNextActivity() {
        val intent = Intent(this@ConnectionModeActivity, ProgressActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.nextButton -> gotoNextActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.toolbarPrivacyPolicyMenuId -> startActivity(
                Intent(
                    this@ConnectionModeActivity,
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