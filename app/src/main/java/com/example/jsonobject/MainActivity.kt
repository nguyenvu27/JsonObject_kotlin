package com.example.jsonobject

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv1: TextView = findViewById(R.id.tv1)
        var tv2: TextView = findViewById(R.id.tv2)
        var tv3: TextView = findViewById(R.id.tv3)
        var tv4: TextView = findViewById(R.id.tv4)
        var tv5: TextView = findViewById(R.id.tv5)


        val urlJSON = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json"
        ReadJson().execute(urlJSON)


    }

    inner class ReadJson : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            var content: StringBuilder = StringBuilder()
            val url: URL = URL(params[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader: InputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

            var line: String = ""
            try {
                do {
                    line = bufferedReader.readLine()
                    if (line != null) {
                        content.append(line)
                    }
                } while (line != null)
                bufferedReader.close()
            } catch (e: Exception) {
                Log.d("AAA", e.toString())
            }
            return content.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val objectInfor: JSONObject = JSONObject(result)

            val monhoc: String = objectInfor.getString("monhoc")
            val noihoc: String = objectInfor.getString("noihoc")
            val website: String = objectInfor.getString("website")
            val fanpage: String = objectInfor.getString("fanpage")
            val logo: String = objectInfor.getString("logo")

            Toast.makeText(
                this@MainActivity, monhoc + "\n" + noihoc +
                        "\n" + website + "\n" + fanpage + "\n" + logo, Toast.LENGTH_LONG
            ).show()
            tv1.setText(monhoc)
            tv2.setText(noihoc)
            tv3.setText(website)
            tv4.setText(fanpage)
            tv5.setText(logo)
        }
    }
}
