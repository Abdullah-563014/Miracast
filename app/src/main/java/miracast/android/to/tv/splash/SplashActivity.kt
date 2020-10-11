package miracast.android.to.tv.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import miracast.android.to.tv.MainActivity
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivitySplashBinding
import miracast.android.to.tv.tv_list.TvListActivity


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpUi();
        initAnimation();

    }


    private fun setUpUi() {
        Picasso.get().load(R.drawable.ic_launcher).into(binding.splashLogo)
        binding.splashLetsStartButton.setOnClickListener(this)
    }

    private fun initAnimation() {
        val blinkAnimation: Animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.shake_animation)
        binding.splashLetsStartButton.startAnimation(blinkAnimation)
    }

    private fun gotoNextActivity() {
        val intent = Intent(this@SplashActivity, TvListActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.splashLetsStartButton -> gotoNextActivity()
        }
    }


}