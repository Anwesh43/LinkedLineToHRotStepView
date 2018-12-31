package com.anwesh.uiprojects.linkedlinetohrotstepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.linetohrotstepview.LineToHRotStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LineToHRotStepView.create(this)
    }
}
