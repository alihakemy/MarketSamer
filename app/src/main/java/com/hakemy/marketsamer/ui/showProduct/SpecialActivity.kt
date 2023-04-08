package com.hakemy.marketsamer.ui.showProduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.hakemy.marketsamer.base.BaseActivity
import com.hakemy.marketsamer.databinding.ActivitySpecialBinding
import com.hakemy.marketsamer.databinding.SpecialItem2Binding
import com.hakemy.marketsamer.ui.search.SearchActivity
import com.hakemy.marketsamer.ui.showProduct.adapter.SpecialAdapter
import com.hakemy.marketsamer.ui.showProduct.entities.ProductDetailsResponse
import com.hakemy.marketsamer.ui.showProduct.entities.Products
import com.hakemy.marketsamer.ui.showProduct.entities.Special
import com.hakemy.marketsamer.utils.ResultState


class SpecialActivity : BaseActivity() {

    companion object {
        fun startSpecialActivity(context: Context, id: String) {
            val intent = Intent(context, SpecialActivity::class.java)
            intent.putExtra("id", id.toString())
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: ShowProductViewModel


    lateinit var binding: ActivitySpecialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this)[ShowProductViewModel::class.java]

        viewModel.getProductDetails(intent.getStringExtra("id").toString())

        binding.imageView6.setOnClickListener {
            onBackPressed()
        }
        binding.imageView4.setOnClickListener {
            SearchActivity.startSearchActivity(this)
        }
        val adapt = SpecialAdapter()

        binding.recyclerView4.layoutManager = LinearLayoutManager(this)
        binding.recyclerView4.adapter = adapt

        viewModel.productDetails.observe(this, Observer {
            when (val result = it) {
                is ResultState.Error -> {
                    hideProgress()
                }
                ResultState.Loading -> {
                    showProgress()
                }
                is ResultState.Success -> {
                    hideProgress()
                    result.data?.data?.products?.special?.let { adapt.setSpecialList(it) }

                }
            }
        })
    }


}

class SpecialAdapter() : RecyclerView.Adapter<SpecialHolder>() {

    var lists = ArrayList<Special>()
    fun setSpecialList(list: ArrayList<Special>) {
        lists = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialHolder {

        return SpecialHolder(
            SpecialItem2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: SpecialHolder, position: Int) {
        holder.bind(lists.get(position))
    }
}

class SpecialHolder(val binding: SpecialItem2Binding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(get: Special) {

        binding.textView12.text = get.inputKey.toString()
        binding.textView13.text = get.inputValue.toString()

    }

}
