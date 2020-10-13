package miracast.android.to.tv.home

import android.app.Activity
import android.app.AlertDialog.THEME_HOLO_LIGHT
import android.content.*
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import miracast.android.to.tv.DeviceSpecs
import miracast.android.to.tv.R
import miracast.android.to.tv.Utils
import miracast.android.to.tv.databinding.ActivityMainBinding
import miracast.android.to.tv.privacy_policy.PrivacyPolicyActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var wifiManager: WifiManager
    private val ACTION_WIFI_DISPLAY_SETTINGS : String = "android.settings.WIFI_DISPLAY_SETTINGS"
    private var btn: ImageView? = null
    private var device: DeviceSpecs? = null
    private val wifi: WifiManager? = null
    private var activity: Activity? = null
    private var mContext: Context? = null
    var doubleBackToExitPressedOnce = false
    private val counterKey : String="CounterKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initAll()



    }



    private fun initAll() {
        wifiManager= applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        mContext = this
        activity = this
        rateMyApp()
        device = DeviceSpecs()
        binding.modelName.text = resources.getString(R.string.step)
        binding.productName.text = resources.getString(R.string.tv_and_mobile_same_wifi)
        binding.osVersionName.text = resources.getString(R.string.miracast_display_enabled)
        binding.versionSDKVersion.text = resources.getString(R.string.click_start_and_wifi_display)

        binding.widiBtn.setOnClickListener(this)

    }

    private fun wifidisplay() {
        try {
            startActivity(Intent(ACTION_WIFI_DISPLAY_SETTINGS))
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            try {
                startActivity(packageManager.getLaunchIntentForPackage("com.samsung.wfd.LAUNCH_WFD_PICKER_DLG"))
            } catch (e2: Exception) {
                try {
                    startActivity(Intent("android.settings.CAST_SETTINGS"))
                } catch (e3: Exception) {
                    Toast.makeText(applicationContext, "Device not supported", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.click_back_again), Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun rateMyApp() {
        val counter : Int = Utils.getIntFromStorage(applicationContext,counterKey,0)
        if (counter != 0 && counter % 5 == 0) {
            val alert: AlertDialog.Builder = AlertDialog.Builder(this,R.style.MyDialogTheme)
            alert.setTitle(resources.getString(R.string.please_rate))
            alert.setMessage(resources.getString(R.string.a_moment_to_rate))
            alert.setPositiveButton(resources.getString(R.string.cancel)
            ) { dialog, whichButton -> dialog.dismiss() }
            alert.setNegativeButton(resources.getString(R.string.rate_it)
            ) { dialog, which ->
                val appName = applicationContext.packageName
                try {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("market://details?id=$appName")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("http://play.google.com/store/apps/details?id=$appName")
                        )
                    )
                }
            }
            if (!isFinishing) {
                alert.show()
            }
        }
        Utils.saveIntToStorage(applicationContext,counterKey,counter+1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.toolbarPrivacyPolicyMenuId -> startActivity(
                Intent(
                    this@MainActivity,
                    PrivacyPolicyActivity::class.java
                )
            )
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.widiBtn -> if (wifiManager.isWifiEnabled) {
                wifidisplay()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.sorry_no_wifi_connection),
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(
                this@MainActivity,
                resources.getString(R.string.sorry_no_wifi_connection),
                Toast.LENGTH_LONG
            ).show()
            finishAffinity()
        }
    }


}