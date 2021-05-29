package id.rama.githubapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.rama.githubapp.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        moveActivity()
    }

    private fun moveActivity() {
        Handler().postDelayed({
            startActivity(Intent(this,MainMenu::class.java))
            finishAffinity()
        },5500)
    }
}