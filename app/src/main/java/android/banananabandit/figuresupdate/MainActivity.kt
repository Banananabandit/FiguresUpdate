package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding // Keep this one in case we still need it
    lateinit var bottomNav: BottomNavigationView

    private val dailyUpdateFragment = DailyUpdateFragment()
    private val weeklyUpdateFragment = WeeklyUpdateFragment()
    private val fragmentManager = supportFragmentManager
    private val activeFragment: Fragment = dailyUpdateFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        loadFragment(DailyUpdateFragment())
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container_view, dailyUpdateFragment)
            add(R.id.fragment_container_view, weeklyUpdateFragment).hide(weeklyUpdateFragment)
        }.commit()

        bottomNav = binding.bottomNav
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dailyUpdate ->{
                    fragmentManager.beginTransaction().hide(weeklyUpdateFragment).show(dailyUpdateFragment).commit()
//                    loadFragment(DailyUpdateFragment())
                    true
                }
                R.id.weeklyUpdate -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(weeklyUpdateFragment).commit()
//                    loadFragment(WeeklyUpdateFragment())
                    true
                }
                else -> false

            }
        }
    }

//    private fun loadFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container_view, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

}
