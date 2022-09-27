package com.example.testedaltonismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.testedaltonismo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val activityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            if (result.data != null) {
                Toast.makeText(baseContext, result.data!!.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    lateinit var binding: ActivityMainBinding
    var resultado = Resultado("?","?","?","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.resultado = resultado

        //recebendo e analizando valores enviados pela tela2
        val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK->{
                    val param = it.data?.extras

                    var n_opcao = param?.getInt("opc")
                    var n_escolhido:Int? = param?.getString("escolhido")?.toIntOrNull()

                    if(n_escolhido == null){
                        Toast.makeText(this, "foi digitado um valor invalido", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if(n_opcao == 1){
                            binding.apply {
                                resultado!!.r1 = n_escolhido.toString()
                                invalidateAll()
                            }
                        }
                        else{
                            if(n_opcao == 2){
                                binding.apply {
                                    resultado!!.r2 = n_escolhido.toString()
                                    invalidateAll()
                                }
                            }
                            else{
                                if(n_opcao == 3){
                                    binding.apply {
                                        resultado!!.r3 = n_escolhido.toString()
                                        invalidateAll()
                                    }
                                }
                            }
                        }
                        Toast.makeText(this, "resultado inserido", Toast.LENGTH_SHORT).show()
                        if(! resultado.r4.equals("")){
                            binding.apply {
                                resultado!!.r4 = ""
                                invalidateAll()
                            }
                        }
                    }
                }
                RESULT_CANCELED->{
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //botoes para os testes
        binding.button.setOnClickListener {
            var i = Intent(this,MainActivity2::class.java)
            i.putExtra("opcao",1)
            activityForResult.launch(i)
        }
        binding.button2.setOnClickListener {
            var i = Intent(this,MainActivity2::class.java)
            i.putExtra("opcao",2)
            activityForResult.launch(i)
        }
        binding.button3.setOnClickListener {
            var i = Intent(this,MainActivity2::class.java)
            i.putExtra("opcao",3)
            activityForResult.launch(i)
        }

        //botao para analizar o resultado
        binding.button4.setOnClickListener {
            if(resultado.r1.equals("?") || resultado.r2.equals("?") || resultado.r3.equals("?")){
                Toast.makeText(this, "preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
            else{
                if(resultado.r1.equals("29") && resultado.r2.equals("74") && resultado.r3.equals("2")){
                    binding.apply {
                        resultado!!.r4 = "você não tem daltonismo"
                        invalidateAll()
                    }
                }
                else{
                    binding.apply {
                        resultado!!.r4 = "você pode ter daltonismo, procure um medico"
                        invalidateAll()
                    }
                }
            }
        }
    }
}