package id.rama.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.rama.githubapp.R
import id.rama.githubapp.factory.FactoryUsers
import id.rama.githubapp.repository.RepoUsers
import id.rama.githubapp.utils.Utilities
import id.rama.githubapp.viewmodel.ViewModelUsers
import kotlinx.android.synthetic.main.activity_detail_users.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailUsers : AppCompatActivity() {
    private lateinit var viewModelUsers : ViewModelUsers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_users)

        getExtraUser()
        backButton()
    }

    private fun backButton() {
        btn_back_detail_user.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getExtraUser() {
        val username = intent.getStringExtra(Utilities.USERNAME_GITHUB)
        Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show()
        val repo = RepoUsers()
        val factoryUsers = FactoryUsers(repo)
        viewModelUsers = ViewModelProvider(this, factoryUsers).get(ViewModelUsers::class.java)
        viewModelUsers.getDetailUsers(username)
        viewModelUsers.detailRespomseUsers.observe(this,{response ->
            if (response.isSuccessful){
                val user = response.body()
                Glide.with(this)
                    .load(user?.avatar_url)
                    .into(img_detail_user)

                txt_detail_name_user.text = user?.name
                txt_detail_email.text = user?.email
                txt_detail_location.text = user?.location
                txt_detail_followers.text = user?.followers
            }
        })
    }
}