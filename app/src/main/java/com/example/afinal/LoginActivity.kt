package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity() {
    val notNullRegex = Regex(".+")
    var check = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val goSignup = findViewById<Button>(R.id.btnSignup)
        val goProduct = findViewById<Button>(R.id.btnAuthenticate)
        val username = findViewById<EditText>(R.id.etUsername)

        val password = findViewById<EditText>(R.id.etPassword)

        goSignup.setOnClickListener {
            val intentConstraint = Intent(this, MainActivity::class.java);

            Timer().schedule(500) {
                startActivity(intentConstraint);
            }
        }
        goProduct.setOnClickListener {
            var usernameValue = username.text.toString()

            var passwordValue = password.text.toString()
            val intentLinear = Intent(this, ProductActivity::class.java);
            if(notNullRegex.matches(usernameValue) && notNullRegex.matches(passwordValue) && usernameValue == "salme011") {
                check = true
                Toast.makeText(this@LoginActivity, "Authenticating..", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@LoginActivity, usernameValue + " Logged In", Toast.LENGTH_SHORT).show()
            }
            if(check){
            Timer().schedule(500) {
                //Toast.makeText(this@LoginActivity, "Authenticating...", Toast.LENGTH_LONG).show()
                //
                startActivity(intentLinear);
            }}
            else{
                Toast.makeText(this@LoginActivity, "Username or password is incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun AuthenticateUser() {
        val etFname = findViewById<EditText>(R.id.fname_editText)
        val etLname = findViewById<EditText>(R.id.lname_editText)
        val etEmail = findViewById<EditText>(R.id.email_editText)
        val etUsername = findViewById<EditText>(R.id.user_id_editText)
        val etPhone = findViewById<EditText>(R.id.phone_editText)
        val etPassword = findViewById<EditText>(R.id.user_password_editText)
        val fname: String = etFname.getText().toString().trim({ it <= ' ' })
        val lname: String = etLname.getText().toString().trim({ it <= ' ' })
        val email: String = etEmail.getText().toString().trim({ it <= ' ' })
        val username: String = etUsername.getText().toString().trim({ it <= ' ' })
        val phone: String = etPhone.getText().toString().trim({ it <= ' ' })
        val password: String = etPassword.getText().toString().trim({ it <= ' ' })
        val stringRequest: StringRequest =
            object : StringRequest(
                Method.POST,
                Constants.ROOT_URL,
                Response.Listener { response -> //progressDialog.hide();
                    var jsonObject: JSONObject? = null
                    try {
                        jsonObject = JSONObject(response)
                        Toast.makeText(
                            applicationContext,
                            jsonObject.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error -> //progressDialog.hide();
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG)
                        .show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> =
                        HashMap()
                    params["FNAME"] = fname
                    params["LNAME"] = lname
                    params["EMAIL"] = email
                    params["ID_NUMBER"] = username
                    params["PHONE"] = phone
                    params["PASSWORD"] = password
                    return params
                }
            }
    }
}