package com.example.spinner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {

    private var selectedAge: String? = null
    private var selectedRole: String? = null

    private lateinit var secondNameET: EditText
    private lateinit var firstNameET: EditText
    private lateinit var roleSpinner: Spinner
    private lateinit var spinnerFilter: Spinner
    private lateinit var ageSpinner: Spinner
    private lateinit var saveBTN: Button
    private lateinit var personLV: ListView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowInsets(R.id.main)
        setupToolbar(R.id.toolbar, false)

        init()

        // Должности, возраст
        val roles: MutableList<String> = mutableListOf("none")
        roles.addAll(Roles.entries.map { it.rus })
        val age: MutableList<String> = mutableListOf("none")
        age.addAll((18..100).map { it.toString() })

        // Создание адаптера
        val rolesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val ageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, age)
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Установка адаптера
        roleSpinner.adapter = rolesAdapter
        spinnerFilter.adapter = rolesAdapter
        ageSpinner.adapter = ageAdapter

        val personList = arrayListOf(
            Person("Иванов", "Алексей", "18", Roles.IT_MANAGER.rus),
            Person("Петров", "Дмитрий", "19", Roles.DATA_SCIENTIST.rus),
            Person("Сидорова", "Диана", "20", Roles.FULLSTACK_DEVELOPER.rus)
        )

        applyPersonAdapter(personList)

        saveBTN.setOnClickListener {
            if (selectedAge != "none" && selectedRole != "none" &&
                secondNameET.text.toString().length > 1 && firstNameET.text.toString().length > 1
            ) {
                val newPerson = Person(
                    secondNameET.text.toString(),
                    firstNameET.text.toString(),
                    selectedAge,
                    selectedRole
                )

                personList.add(newPerson)
                applyPersonAdapter(personList)
                secondNameET.setText("")
                firstNameET.setText("")
                ageSpinner.setSelection(0)
                roleSpinner.setSelection(0)
            } else {
                Snackbar.make(it, "Введите все данные", Snackbar.LENGTH_LONG).show()
            }
        }

        ageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                selectedAge = age[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия при отсутствии выбранного элемента
            }
        }

        roleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                selectedRole = roles[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия при отсутствии выбранного элемента
            }
        }

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val filteredPersonList = if (roles[position] != "none") {
                    (personList.filter { it.role == roles[position] }).toMutableList()
                } else {
                    personList
                }
                applyPersonAdapter(filteredPersonList)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия при отсутствии выбранного элемента
            }
        }

    }

    private fun applyPersonAdapter(personList: MutableList<Person>) {
        personLV.adapter = PersonAdapter(this, personList)
    }

    private fun init() {
        secondNameET = findViewById(R.id.secondNameET)
        firstNameET = findViewById(R.id.firstNameET)
        roleSpinner = findViewById(R.id.roleSpinner)
        spinnerFilter = findViewById(R.id.spinnerFilter)
        ageSpinner = findViewById(R.id.ageSpinner)
        saveBTN = findViewById(R.id.saveBTN)
        personLV = findViewById(R.id.personLV)
    }
}