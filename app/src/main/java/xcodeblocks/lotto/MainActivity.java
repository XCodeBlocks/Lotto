//[test code]

//(Android project: Lotto random number generation)


/** [references]:       (only in English version): there may be some Korean pages, :P, but at least look at the headlines :P)
 *
 *  //[overall direction]: First, call the variables I would need at the spot (except those that shouldn't be -- declared elsewhere in that case) and keep going, then
 *            after code is done to some degree, only take out declaration outside of methods and make them (class) global. -- (if it's hard to do it from the start)
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
 *
 * - (스위치(switch)로 토글 이벤트 처리):
 *  http://cosmosjs.blog.me/220728864491
 *  https://stackoverflow.com/questions/11278507/android-widget-switch-on-off-event-listener
 *  http://gakari.tistory.com/entry/안드로이드-스위치Switch-만들어서-편리하게-토글하기
 *  http://abhiandroid.com/ui/switch
 *  - (스낵바(snack bar) - 스위치 이벤트에서):
 *  http://www.truiton.com/2015/06/android-snackbar-example/
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
//[global variable declaration]
    int NUMBER_SEL = 6;             //FIXME: (variable that is globally declared(static) in Lotto.java(class) is not recognized!!) -- (-> re-declared here as local(workaround :P ))
    Lotto lottoTest = new Lotto();                          //(object creation)
    //[declaring TextView to display numbers + initialization]
    TextView[] numbers_TextView = new TextView[NUMBER_SEL];     //(number boxes)          //TODO: [why NUMBER_SEL which should be global variable not recognized here?]
    int[] numberArray = lottoTest.getNumbers();     //(instead of attaching [] next to getNumbers (for array), making separate array variable to save it! -- for visual(may have looked messy))
    Button button_generate;             //(button to random generate numbers)
    Switch switch_freqToggle;           //(switch to toggle whether to reflect frequently picked number)
    boolean isSwitchChecked = false;    //(whether switch is checked -> affecting generation of random numbers!)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lotto Number Generation app");             //(changes the (content of the) title that would appear in upper row)

//[generating buttons]
    //[assign each 'TextView' objects]     -- (already declared above)
        numbers_TextView[0] = (TextView) findViewById(R.id.numbers0);
        numbers_TextView[1] = (TextView) findViewById(R.id.numbers1);
        numbers_TextView[2] = (TextView) findViewById(R.id.numbers2);
        numbers_TextView[3] = (TextView) findViewById(R.id.numbers3);
        numbers_TextView[4] = (TextView) findViewById(R.id.numbers4);
        numbers_TextView[5] = (TextView) findViewById(R.id.numbers5);
        button_generate = (Button) findViewById(R.id.button_generate);      //[assign button objects] -- (already declared above)
        switch_freqToggle = (Switch) findViewById(R.id.switch_frequentNumbers);

//('enhanced for loop': initialize to blanks)
        for (TextView each: numbers_TextView) {     //(assigning cannot be done here(make it repeatable here) (as above) -- so it's pulled above)
            each.setText(" ");
        }

//[button touch(click) event -- mostly interchangeable]
        button_generate.setOnTouchListener( new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lottoTest.numbersGenerate(isSwitchChecked);        //(generate random number - different results as it generates for each presses)     //([addition] slight change to internal mechanism according to whether switch is enabled!)

                String str = null;                              //(temporaryily save -- string to go to logcat(DEBUG).)
                for( int x = 0 ; x < numberArray.length ; x++ )       //(going around entirely on array of numbers(numberArray) (size acquired from 'length' field)...)
                {
                    String eachString = Integer.toString( numberArray[x] );         //(for each numbers -> change to String)
                    numbers_TextView[x].setText(eachString);                        //(...so those numbers can be shown.)
                    str += ( eachString + " ");         //(DEBUG: change to String format needed for outputting to log + (spaces between numbers to distinguish each))
                }
                Log.i("String", "DEBUG: " + str );                        //(output to 'logcat')

                return false;       //(END)
            }
        }
        );
//[switch touch(click) event]
        switch_freqToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //[actual part of switch operation]
                isSwitchChecked = isChecked;        //(synchronize the state for both)
                if (isChecked) {        //[if 'on']
                    Snackbar.make(buttonView, "'frequently picked numbers' will be reflected in generation!", Snackbar.LENGTH_SHORT).show();

                } else {                        //[if 'off']
                    Snackbar.make(buttonView, "'frequently picked numbers' will NOT be reflected in generation!'", Snackbar.LENGTH_SHORT).show();

                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();      */
                Toast.makeText(getApplicationContext(), "redirecting to site related to lotto...", Toast.LENGTH_SHORT).show();

                Intent intent_browser = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.nlotto.co.kr/gameResult.do?method=statByNumber") );       //([only for English version]: sorry I don't know if there is English site to link that has similar information :P imma just leave the page link as it was :P )
                // intent.setPackage("com.android.chrome");     //(uncomment this if you want the app to use specific app)
                startActivity(intent_browser);
            }
        });

/*      // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
*/   }

//[read configuration from menu_main.xml] -- (preparing to realize action bar menu)
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

//[resetting displaying numbers - realizing menu buttons]
        if (id == R.id.action_reset) {
            //('enhanced for loop': clear all 6 boxes of number(display to empty boxes again))
            for (TextView each: numbers_TextView) {
                each.setText(" ");
            }
            Toast.makeText( getApplicationContext(), "resetted to empty boxes!", Toast.LENGTH_SHORT).show();
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
