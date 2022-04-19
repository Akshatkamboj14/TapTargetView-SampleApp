package com.example.myapplication67

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener{
            startActivity(Intent(this,bt1::class.java))

        }
        findViewById<Button>(R.id.button2).setOnClickListener{
            startActivity(Intent(this,bt2::class.java))

        }
        showTargetSequence(
            createTarget(
                findViewById(R.id.button),
                "Click here to Go to Bt1 activity!!",
                "This is the description of bt1 button"
            ),
            createTarget(
                findViewById(R.id.button2),
                "Click here to Go to Bt2 activity!!",
                "This is the description of bt2 button"
            ), onFinish = {
                    Log.d("Tag","onCreate: Finish")
            })
    }

    private fun createTarget(view: View, title: String, description: String, id: Int = -1) =
        TapTarget.forView(view,title,description)
        .tintTarget(false)
        .drawShadow(true)
        .id(id)

    private fun showTargetSequence(
        vararg tapTarget: TapTarget,
        onFinish : () -> Unit = {},
        onCancel : () -> Unit = {},
    ){
        TapTargetSequence(this)
            .targets(tapTarget.toList())
            .listener(object : TapTargetSequence.Listener {
                override fun onSequenceFinish() {
                    onFinish()
                }

                override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                    Log.d("Tag","onSequenceStep: ${lastTarget?.id()}")
                }

                override fun onSequenceCanceled(lastTarget: TapTarget?) {
                    onCancel()
                }


            })
            .start()
    }
}