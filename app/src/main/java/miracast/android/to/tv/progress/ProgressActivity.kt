package miracast.android.to.tv.progress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.squareup.picasso.Picasso
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityProgressBinding
import miracast.android.to.tv.preparation.PreparationActivity
import java.lang.Exception

class ProgressActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityProgressBinding
    private var counter : Int=0
    private var progressThread : ProgressThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpUi()
        startProgressing()


    }


    private fun setUpUi() {
        binding.okButton.setOnClickListener(this)
    }

    private fun startProgressing() {
        if (progressThread!=null) {
            progressThread=null
        }
        progressThread=ProgressThread()
        progressThread!!.start()
    }

    private fun initAnimation() {
        val blinkAnimation: Animation = AnimationUtils.loadAnimation(this@ProgressActivity, R.anim.blink_animation)
        binding.okButton.startAnimation(blinkAnimation)
    }

    private fun gotoNextActivity() {
        val intent = Intent(this@ProgressActivity, PreparationActivity::class.java)
        startActivity(intent)
    }

    inner class ProgressThread : Thread() {
        override fun run() {
            counter=0
            while (counter<=99 && !isInterrupted) {
                try {
                    counter++
                    sleep(150)
                    runOnUiThread(kotlinx.coroutines.Runnable {
                        binding.ProgressBar.progress=counter
                        progressToCheckBoxOperation()
                    })
                } catch (e : Exception) {

                }
            }
        }
    }

    private fun progressToCheckBoxOperation() {
        if (counter in 21..28) {
            binding.checkBoxScanning.isChecked=true
        } else if (counter in 30..58) {
            binding.checkBoxDetecting.isChecked=true
        } else if (counter in 60..88) {
            binding.checkBoxInstalling.isChecked=true
        } else if (counter in 90..100) {
            binding.checkBoxConnecting.isChecked=true
            binding.okButton.visibility=View.VISIBLE
            initAnimation()
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.okButton -> gotoNextActivity()
        }
    }

    override fun onDestroy() {
        if (progressThread!=null) {
            progressThread!!.interrupt()
            progressThread=null
        }
        super.onDestroy()
    }


}