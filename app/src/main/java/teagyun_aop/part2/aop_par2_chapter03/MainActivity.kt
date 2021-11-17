package teagyun_aop.part2.aop_par2_chapter03

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.NumberPicker1)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.NumberPicker2)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.NumberPicker3)
            .apply{
                minValue = 0
                maxValue = 9
            }

    }

    private val openButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changepasswardButton: AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.changepasswardButton)
    }
    private var changePasswordMode = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {
            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경중입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            //유저로부터 온 입력값

            if (passwordferences.getString("password", "000").equals(passwordFromUser)) {
               startActivity(Intent(this, DiaryActivity::class.java))
                //패스워드가 성공 했을때

            } else {
                showErrorAlertDialog()
                //실패
            }
        }

        changepasswardButton.setOnClickListener {
            val passwordferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser ="${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (changePasswordMode) {


                passwordferences.edit(true){
                    putString("password", passwordFromUser)
                }
                changePasswordMode = false
                changepasswardButton.setBackgroundColor(Color.BLACK)
                Toast.makeText(this, "비밀번호 변경에 성공했습니다", Toast.LENGTH_SHORT).show()

            } else {
                //유저로부터 온 입력값

                if (passwordferences.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    changepasswardButton.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "변경할 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()


                } else {
                    showErrorAlertDialog()
                    //실패
                }
            }


        }



    }
    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못 되었습니다")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}