package homework.chegg.com.chegghomework.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.adapter.CardAdapter
import homework.chegg.com.chegghomework.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.activity_main.*


class CardActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private var toolbar: Toolbar? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var cardViewModel: CardViewModel
    private lateinit var loadingLav: LottieAnimationView
    private val cardAdapter: CardAdapter = CardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardViewModel = ViewModelProvider(this)[CardViewModel::class.java]
        buildUI()
        setObservers()
    }

    private fun setupToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun buildUI() {
        setContentView(R.layout.activity_main)
        setupToolbar()
        setLoadingLottieAv()
        setRecyclerView()
    }

    private fun setLoadingLottieAv() {

        loadingLav = loading_lav

        loadingLav.apply {
            loadingLav.playAnimation()
            loadingLav.repeatCount = LottieDrawable.INFINITE
        }

    }

    private fun setRecyclerView() {
        mRecyclerView = findViewById(R.id.my_recycler_view)

        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CardActivity)
            adapter = cardAdapter
        }

    }

    private fun setObservers() {

        cardViewModel.cardListLd.observe(this, {

            if (it.isEmpty()) {
                cardViewModel.refreshCardsList()
            } else {
                cardAdapter.cardMutableList = it.toMutableList()
                cardAdapter.notifyDataSetChanged()
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                onRefreshData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onRefreshData() {
        cardAdapter.apply {
            cardMutableList.clear()
            notifyDataSetChanged()
        }
        cardViewModel.refreshCardsList()
    }

}