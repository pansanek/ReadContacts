package ru.potemkin.readcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestContacts()
    }

    private fun requestContacts() {
        thread {
            val cursor = contentResolver.query(
                Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            while (cursor?.moveToNext() == true){
                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                        ContactsContract.Contacts._ID
                    )
                )
                val name = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        ContactsContract.Contacts.DISPLAY_NAME
                    )
                )
                val contact = Contact(id,name)
                Log.d("MainActivity",contact.toString())
            }
            cursor?.close()
        }
    }
}