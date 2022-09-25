package com.example.testedaltonismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testedaltonismo.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2)

        //recebendo valor que veio na itent enviada pela tela1
        var opc_recebida = intent.extras
        var numero_recebido = opc_recebida?.getInt("opcao")

        //exibindo imagem de acordo com o numero recebido
        if(numero_recebido == 1){
            binding.imageView3.setImageResource(R.drawable.img29)
        }
        else{
            if(numero_recebido == 2){
                binding.imageView3.setImageResource(R.drawable.img74)
            }
            else{
                if(numero_recebido == 3){
                    binding.imageView3.setImageResource(R.drawable.img2)
                }
            }
        }

        //acao do botao
        binding.button5.setOnClickListener {
            var numero_informado = binding.editTextTextPersonName.text.toString()

            //criando itent
            var i2 = Intent()

            //colocando valores na itent
            i2.putExtra("opc",numero_recebido)
            i2.putExtra("escolhido",numero_informado)

            //enviando codigo de ok e a itente i2 para a tela1
            setResult(RESULT_OK,i2)
            onBackPressed()
        }
    }
}