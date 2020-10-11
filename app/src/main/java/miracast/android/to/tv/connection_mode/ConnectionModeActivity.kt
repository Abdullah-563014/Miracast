package miracast.android.to.tv.connection_mode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityConnectionModeBinding

class ConnectionModeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConnectionModeBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConnectionModeBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}