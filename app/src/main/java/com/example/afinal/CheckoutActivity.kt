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

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val goOrderSummary = findViewById<Button>(R.id.btnComfirmCheckout)
        val fName = findViewById<EditText>(R.id.fname_editText)
        val lName = findViewById<EditText>(R.id.lname_editText)
        val email = findViewById<EditText>(R.id.email_editText)
        val notNullRegex = Regex(".+")
        val emailRegex = Regex(".+@.+\\..+")
        val phoneRegex = Regex("\\d{10}")
        val strRegex = Regex("\\w+")
        val zipRegex = Regex(" \\d{5}")
        val ccRegex = Regex(" \\d{16}")
        var check = false
        val phone = findViewById<EditText>(R.id.user_id_editText)
        val address = findViewById<EditText>(R.id.phone_editText)
        val city = findViewById<EditText>(R.id.user_password_editText)
        val zip = findViewById<EditText>(R.id.etZip)
        val ccn = findViewById<EditText>(R.id.etCCNum)
        val exp = findViewById<EditText>(R.id.etExpDate)
        val cvv = findViewById<EditText>(R.id.etCVVNumber)

        goOrderSummary.setOnClickListener {
            val intentConstraint = Intent(this, OrderSummary::class.java);
            var emailValue = email.text.toString()
            var phoneValue = phone.text.toString()
            var addressValue = address.text.toString()
            var lNameValue = lName.text.toString()
            var cityValue = city.text.toString()
            var fNameValue = fName.text.toString()
            var zipValue = zip.text.toString()
            var ccnValue = ccn.text.toString()
            var expValue = exp.text.toString()
            var cvvValue = cvv.text.toString()
            if(emailRegex.matches(emailValue) && phoneRegex.matches(phoneValue) && notNullRegex.matches(addressValue) &&
                notNullRegex.matches(fNameValue) && notNullRegex.matches(lNameValue) && notNullRegex.matches(cityValue) &&
                notNullRegex.matches(zipValue) && notNullRegex.matches(ccnValue) && notNullRegex.matches(expValue) &&
                notNullRegex.matches(cvvValue)){
                Toast.makeText(this@CheckoutActivity, "Authenticating..", Toast.LENGTH_SHORT).show()

                check = true}
            if(check) {
                Toast.makeText(this@CheckoutActivity, "Authenticating..", Toast.LENGTH_SHORT).show()
                Timer().schedule(500){
                startActivity(intentConstraint);}
            }
            else{
                Toast.makeText(this@CheckoutActivity, "Please Check and retry", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun AuthenticateCheckout() {
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