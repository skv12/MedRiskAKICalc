package com.example.diploma.ui.main

//import com.example.diploma.ResultWindowAdapter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.diploma.AppDatabase
import com.example.diploma.AppEntity
import com.example.diploma.R
import kotlinx.android.synthetic.main.window_main.*
import kotlinx.android.synthetic.main.window_main.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainFragment : Fragment() {
    private var listId: Int = 0
    private lateinit var db: AppDatabase
    private lateinit var mainViewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.window_main, container, false)
        val view = inflater.inflate(R.layout.resultwindow, container, false)
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "app_db"
        ).allowMainThreadQueries().build()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        root.resultButton.setOnClickListener {
            if(checkFill()){
                val data = AppEntity(
                    id = db.appDao().lastId()+1,
                    age = Age_text.text.toString().toInt(),
                    BMI = BMI_text.text.toString().toFloat(),
                    Atorvastatin = Atorvastatin_checkbox.isChecked,
                    LVEDP = LVEDPText.text.toString().toFloat(),
                    EF = EF_text.text.toString().toFloat(),
                    smoking = Smoking_checkbox.isChecked,
                    GFR = GFR_text.text.toString().toFloat(),
                    Creatinin = Creatinin_text.text.toString().toFloat(),
                    Proteinuria = Proteinuria_text.text.toString().toFloat(),
                    HbA1c = HbА1с_text.text.toString().toFloat(),
                    PlasmaGlucose = PlasmaGlucose_text.text.toString().toFloat(),
                    Hb = Hb_text.text.toString().toFloat(),
                    Ht = Ht_text.text.toString().toFloat(),
                    Cholesterol = Cholesterol_text.text.toString().toFloat(),
                    Triglycerides = Triglycerides_text.text.toString().toFloat(),
                    HDLcholesterol = HDLcholesterol_text.text.toString().toFloat(),
                    LDLcholesterol = LDLcholesterol_text.text.toString().toFloat(),
                    result = calculate(),
                    resultDate = formatted
                )
                val result = calculate()
                if(result<50) {
                    resultText.setTextColor(Color.GREEN)
                    resultText.text = "Низкий риск \n (" + result.toString() + ")"
                }
                else
                {
                    resultText.setTextColor(Color.RED)
                    resultText.text = "Высокий риск \n (" + result.toString() + ")"
                }
                db.appDao().insertAll(data)
            }
            else{
                Toast.makeText(activity, "Заполните поля", Toast.LENGTH_SHORT).show()
                db.clearAllTables()
            }
        }
        return root
    }

    /*override fun onResume() {
        super.onResume()
        if(listId != 0){
            val list : List<AppEntity> = db.appDao().selectItem(listId)
            Age_text.setText(list[1].toString())
        }
    }*/
    private fun calculate(): Float {
        val age = Age_text.text.toString().toFloat() * 0.7f
        val bmi = BMI_text.text.toString().toFloat() * 2.3f
        var astatin = 0f
        if(Atorvastatin_checkbox.isChecked){
            astatin = -16f
        }
        val lvedp = LVEDPText.text.toString().toFloat() * -18.5f
        val ef = EF_text.text.toString().toFloat() * 0.1f
        var smoking = 0f
        if(Smoking_checkbox.isChecked){
            smoking = 14f
        }
        val gfr = GFR_text.text.toString().toFloat() * 0.4f
        val creatinin = Creatinin_text.text.toString().toFloat() * 0.6f
        val proteinuria = Proteinuria_text.text.toString().toFloat() * 13f
        val HbA1c = HbА1с_text.text.toString().toFloat() * 3f
        val glucose = PlasmaGlucose_text.text.toString().toFloat() * 2.7f
        val hb = Hb_text.text.toString().toFloat() * 0.9f
        val ht = Ht_text.text.toString().toFloat() * -2.4f
        val chol = Cholesterol_text.text.toString().toFloat() * -2.5f
        val tg = Triglycerides_text.text.toString().toFloat() * -2f
        val hblc = HDLcholesterol_text.text.toString().toFloat() * -24f
        val lblc = LDLcholesterol_text.text.toString().toFloat() * 12.2f
        val const = -105.0f
        return age + bmi + astatin + lvedp + ef + smoking + gfr + creatinin + proteinuria + HbA1c + glucose + hb + ht + chol + tg + hblc + lblc + const
    }
    private fun checkFill(): Boolean{
        when {
            Age_text.text!!.isEmpty() -> {
                return false
            }
            BMI_text.text!!.isEmpty() -> {
                return false
            }
            GFR_text.text!!.isEmpty() -> {
                return false
            }
            Creatinin_text.text!!.isEmpty() -> {
                return false
            }
            Proteinuria_text.text!!.isEmpty() -> {
                return false
            }
            HbА1с_text.text!!.isEmpty() -> {
                return false
            }
            PlasmaGlucose_text.text!!.isEmpty() -> {
                return false
            }
            Hb_text.text!!.isEmpty() -> {
                return false
            }
            Ht_text.text!!.isEmpty() -> {
                return false
            }
            Cholesterol_text.text!!.isEmpty() -> {
                return false
            }
            Triglycerides_text.text!!.isEmpty() -> {
                return false
            }
            HDLcholesterol_text.text!!.isEmpty() -> {
                return false
            }
            LDLcholesterol_text.text!!.isEmpty() -> {
                return false
            }
            else -> {
                return true
            }
        }

    }
    fun fillBoxes(Id: Int) {
        listId = Id
    }

}