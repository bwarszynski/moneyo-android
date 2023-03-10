package com.loevenhaupt.moneyo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.loevenhaupt.moneyo.databinding.ActivityAddTransactionBinding
import com.loevenhaupt.moneyo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        labelInput.addTextChangedListener {
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener {
            if(it!!.isNotEmpty())
              amountLayout.error = null
        }


        addTransactionBtn.setOnClickListener {
            val label = labelInput.text.toString()
            val description = descriptionInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            if(label.isEmpty()) {
                labelLayout.error = "Wprowadź poprawny tytuł"
            }

            else if(amount == null)
                amountLayout.error = "Wprowadź poprawną kwotę"

            else {
                val transaction = Transaction(0, label, amount, description)
                insert(transaction)
            }
        }
     closeBtn.setOnClickListener{
         finish()
     }
    }

    private fun insert(transaction: Transaction){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            finish()
        }
    }
}