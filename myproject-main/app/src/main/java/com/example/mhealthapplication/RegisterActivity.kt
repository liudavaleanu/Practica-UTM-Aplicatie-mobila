package com.example.mhealthapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegisterActivity : AppCompatActivity() {
    internal lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        setContentView(R.layout.activity_register)

//        var - variable
//        val - variabile finale
        auth = FirebaseAuth.getInstance()



        val title : TextView = findViewById(R.id.title_text_view)
        val emailEditText : EditText = findViewById(R.id.email_edit_text)
        val password1EditText : EditText = findViewById(R.id.password1)
        val password2EditText : EditText = findViewById(R.id.password2)
        val button : Button = findViewById(R.id.button_register)
        val loginTextView : TextView = findViewById(R.id.login)


        // Setarea listener-ului pentru butonul de înregistrare
        button.setOnClickListener {
            buttonWasPressed(emailEditText,password1EditText,password2EditText)
        }



        loginTextView.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onStart")
//    }
//    override fun onRestart() {
//        super.onRestart()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onRestart")
//    }
//    override fun onResume() {
//        super.onResume()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Toast.makeText(this,"Acesta este mesajul afisat",Toast.LENGTH_SHORT).show()
//        println("onDestroy")
//    }
    private fun buttonWasPressed(emailEditText : TextView,password1EditText : EditText, password2EditText : EditText) {
        val email = emailEditText.text.toString()
        val password1 = password1EditText.text.toString()
        val password2 = password2EditText.text.toString()

        if (password1 == password2) {
            auth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Înregistrarea a eșuat: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
        } else {
            Toast.makeText(this, "Parolele nu sunt identice", Toast.LENGTH_SHORT).show()
        }
    }
}

