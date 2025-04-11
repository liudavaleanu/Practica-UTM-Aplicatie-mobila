//declarare pachet
package com.example.mhealthapplication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
// definire clasa care reprezinta o activitate (fereastra a apliicatie)
// comment Liudaa

class MainActivity : AppCompatActivity() {
    //Declară o variabilă privată de tip BottomNavigationView pe care o vei inițializa ulterior (prin lateinit).
    //lateinit semnifică faptul că variabila va fi inițializată în metoda onCreate, înainte de a fi utilizată.
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    // punctul de intrare al activității
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        frameLayout = findViewById(R.id.frame_layout)


//        bottomNavigationView.setSelectedItemId(R.id.fragment_home);

        bottomNavigationView.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.action_home -> {
                        val fragment = HomeFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                            .commit()
                }
                R.id.action_pacienti -> {
                    val fragment = PacientiFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                }
                R.id.action_teste -> {
                    val fragment = TesteFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                }
                R.id.action_calendar -> {
                    val fragment = CalendarFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                }
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var bottomNavigationView : BottomNavigationView
    }

}