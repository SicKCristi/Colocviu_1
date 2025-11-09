package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.util.Date
import java.util.Random
import kotlin.math.sqrt

class ProcessingThread(private val context: Context) : Thread() {
    private var isRunning = true
    private val random = Random()

    override fun run() {
        Log.d(
            "Thread_Process",
            "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid()
        )
        while (isRunning) {
            sendMessage()
            sleep()
        }
        Log.d("Thread_Process", "Thread has stopped!")
    }

    private fun sendMessage() {

        val n1=(0..100).random()
        val n2=(0..100).random()
        val n3=(0..100).random()
        val n4=(0..100).random()

        val intent = Intent()
        intent.setAction("ProcessingThread")
        intent.putExtra("n1",n1)
        intent.putExtra("n2",n2)
        intent.putExtra("n3",n3)
        intent.putExtra("n4",n4)
        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(1000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}