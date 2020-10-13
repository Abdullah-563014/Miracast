package miracast.android.to.tv.preparation

import android.content.DialogInterface
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityPreparationBinding
import miracast.android.to.tv.guide.GuideActivity
import miracast.android.to.tv.home.MainActivity

class PreparationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPreparationBinding
    private lateinit var wifiManager: WifiManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPreparationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initAll()


    }


    private fun initAll() {
        wifiManager= applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        binding.preparationStartButton.setOnClickListener(this)
        binding.preparationGuideButton.setOnClickListener(this)
    }

    private fun showWifiOnAlertDialog() {
        val builder : AlertDialog.Builder=AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage(resources.getString(R.string.wifi_permission_dialog))
        builder.setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog : AlertDialog=builder.create()
        if (!isFinishing) {
            alertDialog.show()
        }
    }

    private fun gotoNextActivity(value : String) {
        val intent : Intent
        when(value) {
            "start" -> {
                if (!wifiManager.isWifiEnabled) {
                    if (Build.VERSION.SDK_INT>=29) {
                        showWifiOnAlertDialog()
                    } else {
                        wifiManager.setWifiEnabled(true)
                        intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                } else {
                    intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
            }
            "guide" -> {
                intent= Intent(this,GuideActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.preparationStartButton -> gotoNextActivity("start")
            R.id.preparationGuideButton -> gotoNextActivity("guide")
        }
    }


}