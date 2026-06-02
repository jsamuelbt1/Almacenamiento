package com.example.almacenamiento
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.Reader

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val path: String = filesDir.absolutePath

        binding.tvwRuta.text = path
        val filename = "datos_usuario.txt"
        val fileContents = "Puntaje: 1500\nNivel: 5"
        openFileOutput(filename,
            Context.MODE_PRIVATE).use { output ->
            output.write(fileContents.toByteArray())
            }

        openFileInput("datos_usuario.txt").bufferedReader().use { reader ->
            val text = reader.readText()
            binding.tvwContentFile.text = text
        }
        val arrayArchivos: Array<String> = fileList()

        arrayArchivos.forEach { nombre ->
            Log.i("Stored". nombre)
        }
        val cacheFile = File.createTempFile("session_tmp", ".dat",
                cacheDir)
        cacheFile.writeText("ID_Sesion: 0x99283")

    }
}