package com.anwesh.uiprojects.linetohrotstepview

/**
 * Created by anweshmishra on 31/12/18.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val strokeFactor : Int = 90
val sizeFactor : Float = 2.7f
val scDiv : Double = 0.51
val scGap : Float = 0.05f
val backColor : Int = Color.parseColor("#212121")
val strokeColor : Int = Color.parseColor("#f44336")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse()).toFloat()
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateScale(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir

fun Canvas.drawLHRSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val size : Float = gap / sizeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.color = strokeColor
    save()
    translate(gap * (i + 1), h/2)
    rotate(90f * sc2)
    drawLine(0f, -size/2, 0f, size/2, paint)
    drawCircle(0f, 0f, size/7, paint)
    for(j in (0..(lines - 1))) {
        val sf : Float = 1f - 2 * j
        val sc : Float = sc1.divideScale(j, lines)
        save()
        translate(0f, size/2 * sf)
        rotate(90f * sc * sf)
        drawLine(0f, 0f, 0f, -size * sf, paint)
        restore()
    }
    restore()
}

