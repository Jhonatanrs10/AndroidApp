package com.example.jhonatanrsanimes

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.jhonatanrsanimes.database.AnimesDatabase
import com.example.jhonatanrsanimes.database.insertAnime
import com.example.jhonatanrsanimes.modulos.*
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths


class CadastroActivity : AppCompatActivity() {

    //criando variaveis

    private lateinit var editTextName: EditText
    private lateinit var editTextStatus: Spinner
    private lateinit var editTextRelease: Spinner
    private lateinit var editTextS1: EditText

    private lateinit var buttonSave: Button
    private lateinit var buttonImport: Button

    //extancia do banco de dados
    private lateinit var database: AnimesDatabase

    private var idAnime: Int? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //vinculando variaveis a itens do xml

        editTextName = findViewById(R.id.nameInput)
        editTextStatus = findViewById(R.id.statusSpinnerInput)
        editTextRelease = findViewById(R.id.releaseSpinnerInput)
        editTextS1 = findViewById(R.id.s1Input)


        // editTextStatusSpinner // statusSpinnerInput // lista + adcionando ao spinner
        editTextStatus.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, allStatus)
        //
        editTextRelease.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, allRelease)



        buttonSave = findViewById(R.id.animeSave)
        buttonImport = findViewById(R.id.animeImport)

        //nova extancia
        database = AnimesDatabase(this)

        //ação de importar
        buttonImport.setOnClickListener{
            importListAnime()
        }


        //ação de salvar
        buttonSave.setOnClickListener{
            saveAnime()
        }

        idAnime = intent.getIntExtra("ID_ANIME", -1)

    }

    private fun saveAnime(){

        val anime = Anime(
            name = editTextName.text.toString(),
            status = allStatus[editTextStatus.selectedItemPosition],
            release = allRelease[editTextRelease.selectedItemPosition],
            s1 = editTextS1.text.toString()
        )

        if (editTextName.text.toString() == "" ){
            mensagemTelaPopup(this, "Preencha todos os dados")
        }else{
            val idAnime = database.insertAnime(anime)

            if(idAnime == -1L) {
                Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Feito! (ID:$idAnime)", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun importListAnime(){

        val diretorioDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val arquivo = File(diretorioDownload,"dados.txt")
        val bufferedReader = arquivo.bufferedReader()
        val text = bufferedReader.readLines()[0]
        for(line in text){
            mensagemTelaPopup(this, "${text}")
            var arquivon = mutableListOf<String>()
            
        }

        var arquivoConvertido = mutableListOf<String>()

        //arquivoConvertido.add("TESTE1;Assistindo;Domingo;10")

        for (l in arquivoConvertido) {
            var listaLinha = l.split(";")

            val anime = Anime(
                name = listaLinha[0],
                status = listaLinha[1],
                release = listaLinha[2],
                s1 = listaLinha[3]
            )

            val idAnime = database.insertAnime(anime)

            if (idAnime == -1L) {
                Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Feito! (ID:$idAnime)", Toast.LENGTH_SHORT).show()
            }

        }
        mensagemTelaPopup(this, "FINAL DO COMANDO")
    }


}