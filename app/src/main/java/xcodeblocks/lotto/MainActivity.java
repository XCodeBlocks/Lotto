//[test code]

//(Android project: Lotto random number generation)


/** [references]:
 *
 *  //[방향]: 일단 그때그때 필요한 변수들을 그 자리에서 부르고(정말 그렇게 하면 않되는 경우에만 다른 곳에 선언) 진행하고 나서
 *            어느정도 (코드가) 되었다 싶을때 선언들만 (메소드들) 밖으로 빼서 (클래스) 전역으로 선언. -- (처음부터 어렵다면)
 *
 * - (프리뷰(preview) 화면에서 한글 깨지는 현상 수정):
 *   http://hearit.tistory.com/23
 *   http://thdev.tech/androiddev/2016/09/21/Android-Studio-Layout-Preview-Not-Korean.html
 *
 *
 * - (logcat 사용 - 출력해서 확인하는 방법):
 * https://stackoverflow.com/questions/38289910/print-on-console-in-android-studio
 * ㄴ (실제로는 아래 코드처럼 사용)
 *
 * - (Intent를 이용하여 (특정 링크로) 브라우저 띄우기):
 *   http://bitsoul.tistory.com/36
 * - (스위치(switch)로 토글 이벤트 처리):
 *  http://cosmosjs.blog.me/220728864491
 *  https://stackoverflow.com/questions/11278507/android-widget-switch-on-off-event-listener
 *  http://gakari.tistory.com/entry/안드로이드-스위치Switch-만들어서-편리하게-토글하기
 *  http://abhiandroid.com/ui/switch
 *
 *
 * - (버튼 클릭 이벤트 -- 한 시청자의 제시법 <쓰지는 않을 예정: 이해를 못하겠음>):
 *  https://stackoverflow.com/questions/4153517/how-exactly-does-the-androidonclick-xml-attribute-differ-from-setonclicklistene
 *
 */


package xcodeblocks.lotto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
/*  static {
      System.loadLibrary("native-lib");
    }
*/
//[전역 변수 선언]
    int NUMBER_SEL = 6;             //FIXME: (Lotto.java(클래스)에서 전역으로 선언한 변수가 인식이 않됨!!) -- (-> 여기서 지역변수로 재선언)
    Lotto lottoTest = new Lotto();                          //(객체 생성 (+숫자 생성) )
    //[숫자들 출력하는 TextView 선언+초기화]
    TextView[] numbers_TextView = new TextView[NUMBER_SEL];               //TODO: [왜 전역변수인 NUMBER_SEL 인식이 않되는가 ?!]
    int[] numberArray = lottoTest.getNumbers();     //(getNumbers 메소드에 [] 잇는 대신에 저장할 별도의 숫자 배열 -- 시각상(지저분한 것 같아서))
    Button button_generate;
    Switch switch_freqToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("로또 번호 생성 앱");             //(제목(맨 윗줄) 내용 변경)

//[버튼 생성]
    //[각각의 TextView 객체 할당]     -- (선언은 위에서 미리...)
        numbers_TextView[0] = (TextView) findViewById(R.id.numbers0);
        numbers_TextView[1] = (TextView) findViewById(R.id.numbers1);
        numbers_TextView[2] = (TextView) findViewById(R.id.numbers2);
        numbers_TextView[3] = (TextView) findViewById(R.id.numbers3);
        numbers_TextView[4] = (TextView) findViewById(R.id.numbers4);
        numbers_TextView[5] = (TextView) findViewById(R.id.numbers5);
        button_generate = (Button) findViewById(R.id.button_generate);      //[버튼 객체 할당] -- (선언은 위에서)
        switch_freqToggle = (Switch) findViewById(R.id.switch_frequentNumbers);

//(확장형 for문: 빈칸으로 초기화)
        for (TextView each: numbers_TextView) {     //(여기서 '위의 명령'을 이런 식으로 반복할 수 없어서 위로 뺌)
            each.setText(" ");
        }

//[버튼 터치(클릭) 이벤트]
        button_generate.setOnTouchListener( new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lottoTest.numbersGenerate();        //(랜덤 숫자 생성 - 누를때마다 실행되므로 매번 달라지게 됨)

                String str = null;                              //(임시 저장 -- logcat으로 갈 문자열(DEBUG).)
                for( int x = 0 ; x < numberArray.length ; x++ )       //(숫자 배열(numberArray) 전체를 돌면서(length 필드로 길이 얻음)...)
                {
                    String eachString = Integer.toString( numberArray[x] );         //(숫자 (별로) -> 문자열 변환)
                    numbers_TextView[x].setText(eachString);                        //(해당 값들로 숫자 출력하게...)
                    str += ( eachString + " ");         //(DEBUG: ...로그 출력에 필요한 String 형태로 변환 + (숫자들 구별되게 띄어쓰기 추가))
                }
                Log.i("String", "디버그: " + str );                        //(logcat으로 출력)

                return false;       //(끝)
            }
        }
        );
//[스위치 터치(클릭) 이벤트]
        switch_freqToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //[스위치 동작 실제 부분]
                if (isChecked) {        //[on일때]
                    Snackbar.make(buttonView, "'자주 뽑히는 숫자'들이 뽑기에 반영됩니다!", Snackbar.LENGTH_SHORT).show();
                } else {                        //[off일때]
                    Snackbar.make(buttonView, "'자주 뽑히는 숫자'들을 뽑기에 반영하지 않습니다!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();      */
                Toast.makeText(getApplicationContext(), "로또와 관련된 사이트로 연결됩니다...", Toast.LENGTH_SHORT).show();

                Intent intent_browser = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.nlotto.co.kr/gameResult.do?method=statByNumber") );
                // intent.setPackage("com.android.chrome");     //(특정 앱으로 지정)
                startActivity(intent_browser);
            }
        });

/*      // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
*/   }

//[menu_main.xml 설정 읽어오기] -- (액션바 메뉴 구현 준비)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//[숫자 보여주기 초기화 - 메뉴 버튼 구현]
        if (id == R.id.action_reset) {
            //(확장형 for문: 6칸 모두 숫자 없애기(빈칸으로 다시 표시))
            for (TextView each: numbers_TextView) {
                each.setText(" ");
            }
            Toast.makeText( getApplicationContext(), "빈칸으로 초기화됐습니다!", Toast.LENGTH_SHORT).show();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText( getApplicationContext(), "For debugging purpose!\n(I manually added from the default code)", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();


}
