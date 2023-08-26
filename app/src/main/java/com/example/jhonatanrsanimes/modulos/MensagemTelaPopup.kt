package com.example.jhonatanrsanimes.modulos

import android.content.Context
import android.widget.Toast

fun mensagemTelaPopup(context: Context, text: String?){
        Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()
}