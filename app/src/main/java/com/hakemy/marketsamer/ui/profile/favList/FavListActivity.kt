package com.hakemy.marketsamer.ui.profile.favList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivityFavListBinding
import com.hakemy.marketsamer.ui.addAddress.AddAddress
import com.hakemy.marketsamer.ui.register.RegisterActivity
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.utils.SharePreferenceManager
import com.hakemy.marketsamer.utils.services.RetrofitService
import kotlinx.coroutines.launch

class FavListActivity : BaseActivity() {
    companion object{

        fun startFavListActivity(context: Context){
            if(SharePreferenceManager.getIsVerified().not()){
                RegisterActivity.startRegisterActivity(context)

                return
            }
            val intent= Intent(context, FavListActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
    lateinit var binding:ActivityFavListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFavListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            try {
                showProgress()


                val result=RetrofitService.servicesApi().favouritesPage()

                val adapter=FavRecyclerViewAdapter()
                result.data?.productItem?.let { adapter.setItems(it) }
                val manager = GridLayoutManager(this@FavListActivity, 2)
                binding.recyclerView2.layoutManager = manager
                binding.recyclerView2.adapter=adapter

                hideProgress()

            }catch (e:java.lang.Exception){
                hideProgress()
            }

        }


        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }

    }
}