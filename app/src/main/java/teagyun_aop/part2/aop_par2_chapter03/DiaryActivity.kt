package teagyun_aop.part2.aop_par2_chapter03

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.AbsSavedState
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity:AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    //핸들러


    private val DiaryEditText: EditText by lazy {
        findViewById<EditText>(R.id.DiaryEditText)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        val diaryPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        //diaryPreferences에 getSharedPreferences 함수로 diary라는 이름으로 text를 저장한다.

        DiaryEditText.setText(diaryPreferences.getString("diary",""))
        //저장값 가져오는 함수 (기본값 = 빈값을 가져옴)

        val runnable = Runnable{
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("diary",DiaryEditText.text.toString())
            }
        }

        DiaryEditText.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 3000)

            }
        }


    }


