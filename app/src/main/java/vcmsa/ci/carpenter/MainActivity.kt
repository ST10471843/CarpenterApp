package vcmsa.ci.carpenter

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var rBtn1: RadioButton
    private lateinit var rBtn2: RadioButton
    private lateinit var rBtn3: RadioButton
    private lateinit var cBox1: CheckBox
    private lateinit var cBox2: CheckBox
    private lateinit var cBox3: CheckBox
    private lateinit var etHours: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResults: TextView
    private lateinit var rGroup: RadioGroup



    private val prices = mapOf(
        R.id.cBox1 to 500,
        R.id.cBox2 to 100,
        R.id.cBox3 to 80

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rBtn1 = findViewById(R.id.rBtn1)
        rBtn2 = findViewById(R.id.rBtn2)
        rBtn3 = findViewById(R.id.rBtn3)
        cBox1 = findViewById(R.id.cBox1)
        cBox2 = findViewById(R.id.cBox2)
        cBox3 = findViewById(R.id.cBox3)
        etHours = findViewById(R.id.etHours)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResults = findViewById(R.id.tvResults)
        rGroup = findViewById(R.id.rGroup)

        btnCalculate.setOnClickListener {
            val selectedId = rGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val selectedOption = selectedRadioButton.text.toString()
                Toast.makeText(this, "Selected: $selectedOption", Toast.LENGTH_SHORT).show()

                // Get the price associated with the selected radio button
                var selectedPrice = 0

                if (cBox1.isChecked) {
                    selectedPrice += prices[R.id.cBox1] ?: 0
                }
                if (cBox2.isChecked) {
                  selectedPrice += prices[R.id.cBox2] ?: 0
                }
                if (cBox3.isChecked) {
                    selectedPrice+= prices[R.id.cBox3] ?: 0
                }

                // Get the hours input from the EditText (assuming it's an EditText)
                val etHoursValue = etHours.text.toString().toIntOrNull()

                // Validate hours and calculate total price
                validateHours(etHoursValue, selectedPrice)
            } else {
                Toast.makeText(this, "No option selected", Toast.LENGTH_SHORT).show()
            }
        }

        }
    private fun validateHours(etHours: Int?, selectedPrice: Int) {
        if (etHours == null || etHours < 0) {
            tvResults.text = getString(R.string.invalid_input_hours_cannot_be_null_or_negative)
        } else {
            // Calculate total price based on hours and selected price
            val totalPrice = etHours * selectedPrice
            tvResults.text = "Total Price: $$totalPrice"
            Toast.makeText(this, "Total Price: $$totalPrice", Toast.LENGTH_SHORT).show()
        }
    }


}



