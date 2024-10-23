package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner

class MainActivity : BaseActivity() {

    private lateinit var secondNameET: EditText
    private lateinit var firstNameET: EditText
    private lateinit var roleSpinner: Spinner
    private lateinit var ageSpinner: Spinner
    private lateinit var saveBTN: Button
    private lateinit var personLV: ListView

    selectedAge

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowInsets(R.id.main)
        setupToolbar(R.id.toolbar, false)

        secondNameET = findViewById(R.id.secondNameET)
        firstNameET = findViewById(R.id.firstNameET)
        roleSpinner = findViewById(R.id.roleSpinner)
        ageSpinner = findViewById(R.id.ageSpinner)
        saveBTN = findViewById(R.id.saveBTN)
        personLV = findViewById(R.id.personLV)

        // Должности, возраст
        val roles = Roles.entries.map { it.rus }
        val age = (18..100).map { it }

        // Создание адаптера
        val rolesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val ageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, age)
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Установка адаптера
        roleSpinner.adapter = rolesAdapter
        ageSpinner.adapter = ageAdapter

        val personList = arrayListOf(
            Person("X", "Икс", 18, Roles.IT_MANAGER.rus),
            Person("Y", "Уай", 19, Roles.DATA_SCIENTIST.rus),
            Person("Z", "Зэт", 20, Roles.FULLSTACK_DEVELOPER.rus)
        )

        val adapter = PersonAdapter(this, personList)
        personLV.adapter = adapter

        saveBTN.setOnClickListener {
            newPerson = Person(secondNameET.text.toString(), firstNameET.text.toString(), ageSpinner. ,secondNameET.text.toString())
        }

        ageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedAge = age[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия при отсутствии выбранного элемента
            }
        }
    }
}