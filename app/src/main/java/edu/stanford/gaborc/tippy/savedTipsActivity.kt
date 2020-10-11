package edu.stanford.gaborc.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_saved_tips.*
import kotlinx.android.synthetic.main.row_view.view.*
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.nio.charset.Charset



class savedTipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myDataset = mutableListOf<String>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_tips)


        val file = File(this.filesDir, SAVED_TIPS_FILENAME)
        if (file.exists()) {
            file.bufferedReader().forEachLine {
                myDataset.add(it)
                Log.i("LINES", "TEXT $it")
            }
        }

        rvSavedTips.apply {
            layoutManager = LinearLayoutManager(this@savedTipsActivity)
            adapter = MyAdapter(myDataset)
        }
    }
}

class MyAdapter(private val myDataset: List<*>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvAmount: TextView = v.tvAmount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAmount.text = myDataset[position].toString()
    }

    override fun getItemCount() = myDataset.size

}

