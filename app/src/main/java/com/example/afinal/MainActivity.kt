package com.example.afinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var check = false
        val goLogin = findViewById<Button>(R.id.btnLogIn)
        val btnCreateAccount = findViewById<Button>(R.id.sign_up_button)
        val fName = findViewById<EditText>(R.id.fname_editText)
        val lName = findViewById<EditText>(R.id.lname_editText)
        val email = findViewById<EditText>(R.id.email_editText)

        val username = findViewById<EditText>(R.id.user_id_editText)
        val phone = findViewById<EditText>(R.id.phone_editText)
        val password = findViewById<EditText>(R.id.user_password_editText)
        val emailRegex = Regex(".+@.+\\..+")
        val phoneRegex = Regex("\\d{10}")
        val strRegex = Regex("\\w+")
        val notNullRegex = Regex(".+")

        goLogin.setOnClickListener {
            val intentConstraint = Intent(this, LoginActivity::class.java)
            Timer().schedule(500){

            startActivity(intentConstraint)}
        }
        btnCreateAccount.setOnClickListener {
            val intentLinear = Intent(this, LoginActivity::class.java);
            var emailValue = email.text.toString()
            var phoneValue = phone.text.toString()
            var usernameValue = username.text.toString()
            var lNameValue = lName.text.toString()
            var passwordValue = password.text.toString()
            var fNameValue = fName.text.toString()
            //if(emailValue.contains('@'))
                    //check = true;
            if(emailRegex.matches(emailValue) && phoneRegex.matches(phoneValue) && strRegex.matches(usernameValue) &&
                strRegex.matches(fNameValue) && strRegex.matches(lNameValue) && notNullRegex.matches(passwordValue)) {
                check = true
            }
            if(check) {
                Toast.makeText(this, usernameValue + " Signed Up", Toast.LENGTH_SHORT).show()
                Timer().schedule(500) {
                    startActivity(intentLinear)
                }
            }
            else {
                Toast.makeText(this, "Error Signing Up", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //progressDialog = new ProgressDialog(this);
    //buttonRegister.setOnClickListener(this);
    fun registerUser() {
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

        //progressDialog.setMessage("Registering user...");
        //progressDialog.show();
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
        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest)
    }
    //progressDialog = new ProgressDialog(this);
    //buttonRegister.setOnClickListener(this);

}