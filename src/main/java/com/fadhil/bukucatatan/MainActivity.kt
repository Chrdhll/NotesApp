package com.fadhil.bukucatatan

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.bukucatatan.adapter.noteAdapter
import com.fadhil.bukucatatan.database.NoteDatabaseHelper
import com.fadhil.bukucatatan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        displayNotes()


        binding.addButton.setOnClickListener(){
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume(){
        super.onResume()
        displayNotes()
    }


    private fun displayNotes() {
       val notes = db.getAllNotes()
        binding.noteRecyclerView.adapter = noteAdapter(notes)
        binding.noteRecyclerView.layoutManager=GridLayoutManager(this,2)
    }


}