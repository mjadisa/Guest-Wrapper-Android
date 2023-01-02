package net.iris.android.apps.guestwrapper

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import net.iris.android.apps.guestwrapper.databinding.ActivityMainBinding
import java.lang.Long.parseLong

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var statusBarColor: Int? = null
        if (BuildConfig.StatusBarColor.isNotEmpty()) {
            try {
                statusBarColor = parseLong(BuildConfig.StatusBarColor, 16).toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        statusBarColor?.let { color ->
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }

        if (BuildConfig.LightStatusBar)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}