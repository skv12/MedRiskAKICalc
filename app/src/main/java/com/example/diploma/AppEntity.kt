package com.example.diploma

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "result_db")
data class AppEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Age") var age: Int,
    @ColumnInfo(name = "BMI") var BMI: Float,
    @ColumnInfo(name = "Atorvastatin") var Atorvastatin: Boolean,
    @ColumnInfo(name = "LVEDP") var LVEDP: Float,
    @ColumnInfo(name = "EF") var EF: Float,
    @ColumnInfo(name = "Smoking") var smoking: Boolean,
    @ColumnInfo(name = "GFR") var GFR: Float,
    @ColumnInfo(name = "Creatinin") var Creatinin: Float,
    @ColumnInfo(name = "Proteinuria") var Proteinuria: Float,
    @ColumnInfo(name = "НbА1с") var HbA1c: Float,
    @ColumnInfo(name = "PlasmaGlucose") var PlasmaGlucose: Float,
    @ColumnInfo(name = "Hb") var Hb: Float,
    @ColumnInfo(name = "Ht") var Ht: Float,
    @ColumnInfo(name = "Сholesterol") var Cholesterol: Float,
    @ColumnInfo(name = "Triglycerides") var Triglycerides: Float,
    @ColumnInfo(name = "HDLcholesterol") var HDLcholesterol: Float,
    @ColumnInfo(name = "LDLcholesterol") var LDLcholesterol: Float,
    @ColumnInfo(name = "result") var result: Float,
    @ColumnInfo(name = "resultDate") var resultDate: String
)