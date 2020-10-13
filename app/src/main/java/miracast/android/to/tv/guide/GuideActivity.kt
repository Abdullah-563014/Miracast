package miracast.android.to.tv.guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import miracast.android.to.tv.R
import miracast.android.to.tv.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}