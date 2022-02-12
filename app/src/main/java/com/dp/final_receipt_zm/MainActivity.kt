package com.dp.final_receipt_zm.ui

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dp.final_receipt_zm.R

import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var et_pdf_name : EditText
    private lateinit var et_pdf_address : EditText
    private lateinit var et_pdf_contact : EditText
    private lateinit var btn_generate_pdf : Button
    private val STORAGE_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        et_pdf_name = findViewById(R.id.et_pdf_name)
        et_pdf_address = findViewById(R.id.et_pdf_address)
        et_pdf_contact = findViewById(R.id.et_pdf_contact)
        btn_generate_pdf = findViewById(R.id.btn_generate_pdf)
        btn_generate_pdf.setOnClickListener{
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED
                ){
                    val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, STORAGE_CODE)
                }else{
                    var iName = ArrayList<String>()
                    iName.add("Raider Tire")
                    iName.add("Raider Headlight")
                    iName.add("Motor Break")
                    PdfGenerator.generatePdf(this, iName)
                }
            }else{
                var iName = ArrayList<String>()
                iName.add("Raider Tire")
                iName.add("Raider Headlight")
                iName.add("Motor Break")
                PdfGenerator.generatePdf(this, iName)
            }
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            STORAGE_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    var iName = ArrayList<String>()
                    iName.add("Raider Tire")
                    iName.add("Raider Headlight")
                    iName.add("Motor Break")
                    PdfGenerator.generatePdf(this, iName)
                }else {
                    Toast.makeText(this,"permission denied", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

}