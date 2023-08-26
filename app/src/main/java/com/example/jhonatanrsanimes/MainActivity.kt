package com.example.jhonatanrsanimes

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jhonatanrsanimes.database.AnimesDatabase
import com.example.jhonatanrsanimes.database.selectAnime
import com.example.jhonatanrsanimes.databinding.ActivityMainBinding
import com.example.jhonatanrsanimes.modulos.mensagemTelaPopup
import java.io.File
import java.nio.file.Files
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val animeAdapter = AnimeAdapter()

    private lateinit var database: AnimesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = animeAdapter

        database = AnimesDatabase(this)



        //icone image view com ação de click
        val btnMenu = binding.icCadastrar

        btnMenu.setOnClickListener{


        }


        //cria variavel view com id do botao/fab
        val fab: View = findViewById(R.id.fabAdd)

        //adciona click longo ao btnmenu para o contextmenu
        registerForContextMenu(btnMenu)

        //faz determinada ação ao ser clicado
        fab.setOnClickListener { view ->
            //acessa outra activity/tela var CadastroActivity
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        //seleciona todos os animes
        val animes = database.selectAnime().map {
            it.copy(onClick = ::openDetails)
        }

        //atualiza o adapter com as informações do objeto/lista de objetos animes
        animeAdapter.updateItems(animes)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?){
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_exportar -> {
                exportData()
                true
            } else -> super.onContextItemSelected(item)
        }

    }

    private fun openDetails(idAnime: Int?){
        //acessa outra activity/tela var CadastroActivity
        val intent = Intent(this, CadastroActivity::class.java)
        intent.putExtra("ID_ANIME", idAnime)
        startActivity(intent)
    }
    private fun exportData(){
        val data = database.selectAnime()
        val diretorioDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val arquivo = File(diretorioDownload,"dados.txt")
        arquivo.writeText("${data}")
        mensagemTelaPopup(this, "Exportado!")
    }

}

