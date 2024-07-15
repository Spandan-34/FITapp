package com.example.fitapp



import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewChallenge: TextView
    private lateinit var buttonCompleteChallenge: Button
    private lateinit var spinnerChallengeCategory: Spinner
    private lateinit var listViewCompletedChallenges: ListView
    private lateinit var completedChallenges: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewChallenge = findViewById(R.id.textViewChallenge)
        buttonCompleteChallenge = findViewById(R.id.buttonCompleteChallenge)
        spinnerChallengeCategory = findViewById(R.id.spinnerChallengeCategory)
        listViewCompletedChallenges = findViewById(R.id.listViewCompletedChallenges)

        completedChallenges = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, completedChallenges)
        listViewCompletedChallenges.adapter = adapter

        buttonCompleteChallenge.setOnClickListener {
            val challenge = textViewChallenge.text.toString()
            if (challenge.isNotEmpty()) {
                completedChallenges.add(challenge)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Challenge Completed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No challenge to complete", Toast.LENGTH_SHORT).show()
            }
        }

        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.spinner_items, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerChallengeCategory.adapter = spinnerAdapter

        spinnerChallengeCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Display new challenge based on selected category
                // Implement your challenge display logic here
                val challenges = resources.getStringArray(R.array.spinner_items)
                textViewChallenge.text = "Today's challenge: ${challenges[position]}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}
