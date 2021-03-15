package homework.chegg.com.chegghomework.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.viewmodel.CardViewModel

class CardActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private var toolbar: Toolbar? = null
    private var mRecyclerView: RecyclerView? = null
    private val cardViewModel: CardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        mRecyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView
    }

  private fun setObservers() {

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

    // TODO fetch data from all data sources, aggregate data and display in RecyclerView
    private fun onRefreshData() {
        Toast.makeText(this, "Fetch data aggregate and show on RecyclerView", Toast.LENGTH_SHORT).show()
    }
}