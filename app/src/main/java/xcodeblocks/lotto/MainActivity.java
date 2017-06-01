//[test code]

//(Android project: Lotto random number generation)


/** [references]:
 *
 * - (logcat 사용 - 출력해서 확인하는 방법):
 * https://stackoverflow.com/questions/38289910/print-on-console-in-android-studio
 * ㄴ (실제로는 아래 코드처럼 사용)
 */


package xcodeblocks.lotto;

import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
/*
    static {
        System.loadLibrary("native-lib");
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //[확인(debugging)용]
        Lotto lottoTest = new Lotto();              //(객체 생성)
        int[] numberArray = lottoTest.getNumbers();     //(임시 저장 -- 숫자 배열 대신 저장)
        String str = null;                              //(임시 저장 -- logcat으로 갈 문자열)
        for(int x = 0; x < numberArray.length ; x ++)       //(숫자 배열(numberArray) 전체를 돌면서(length 필드로 길이 얻음)...)
            {str += Integer.toString( numberArray[x] );}    //(...로그 출력에 필요한 String 형태로 변환)
        Log.i("String", "디버그: " + str );                        //(logcat으로 출력)

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
/*
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
*/
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
