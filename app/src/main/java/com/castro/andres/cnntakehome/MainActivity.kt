package com.castro.andres.cnntakehome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.castro.andres.cnntakehome.ui.MainFrag

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // start up Network fragment to do its thing

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById<View>(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            // Create a new Fragment to be placed in the activity layout
            val firstFragment = MainFrag()

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(intent.extras)

            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, firstFragment, "main_frag" ).commit()
        }
    }
}
