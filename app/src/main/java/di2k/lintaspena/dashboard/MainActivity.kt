package di2k.lintaspena.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import di2k.lintaspena.instagramlayout.R

class MainActivity : AppCompatActivity() {
    //varible declaration
    private lateinit var mycard: CardView
    private lateinit var i: Intent
    private lateinit var ll: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll = findViewById(R.id.ll)
        mycard = findViewById(R.id.bankcardId)
        i = Intent(this, AEActivity::class.java)
        mycard.setOnClickListener(View.OnClickListener {
            @Override//Created by 디딬 Didik M. Hadiningrat on 22 July 2019
            fun onClick(view: View){
                startActivity(i)
            }
        })
    }
}
