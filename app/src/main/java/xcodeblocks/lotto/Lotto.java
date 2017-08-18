//[collection of actual functions]
//(test this code in normal Java environment if needed (online compilers can be used).)

package xcodeblocks.lotto;

import java.util.Arrays;

/**
 * Created by XCodeBlocks on 2017-05-29.            //(only in English version): there may be some Korean pages, :P, but at least look at the headlines :P)
 *
 * -(reference): (getRandom() method): if you don't add 1 (at 'return') range will be (0 ~ (max - 1)).
 *                               so add 1 to fix the range to (1 ~ max).
 *                              (http://mwultong.blogspot.com/2006/11/java-mathrandom-int.html)
 *                               (http://itmir.tistory.com/310 --> #9-3 entry)
 *
 * --(reference):
 * //(revert to specific version): (change 'branch' if needed) -> select specific commit(right click) -> select'Reset Current Branch to Here'
 *                         -> select suitable option out of 4(maybe 'keep' is ok if it's just to sync local branch to remote branch?)
 *                         -> HEAD will be also changed to that commit(revision)
 *                         -> (if (branch that has) latest version is needed, do this process(change branch then Reset) again.)
 *  ㄴ(source): (detailed explanation): https://git-scm.com/book/ko/v2/Git-도구-Reset-명확히-알고-가기
 *           (simple concept): https://backlogtool.com/git-guide/kr/stepup/stepup6_3.html
 *           (additional command (revert)): https://tuwlab.com/ece/22223
 *  |
 * //(seems like unsafe -- replaced by the method above:        // <Will leave untranslated :P >
 *      -(특정 버전으로 되돌리기): (안드로이드 스튜디오에서는... 해당 브랜치 우클릭->Checkout Revision 하면 됨.) http://opendive.blogspot.kr/2015/06/git.html
 *      (참고) - (한가지 해결 방법(오프라인으로 들은 advice)): 일단 HEAD 나두고... 프로젝트 전체를 다른 폴더에 clone 시켜서 내용 바꾸고 나서...
 *                                                     수정한 파일을 잘못된 곳에 덮어씌우고 거기서 commit을 시키면 됨.
 *                                                     (commit은 HEAD에서만 가능하다고 함.)
 *      -(오프라인에서 수정한 버전(revision)으로 branch 재지정하는 방법): https://stackoverflow.com/questions/5772192/how-can-i-reconcile-detached-head-with-master-origin
 *
 * -(generating random number): http://www.java67.com/2015/01/how-to-get-random-number-between-0-and-1-java.html
 *              ㄴ (additional info): (go to additional explanation at getRandom() method...)
 *  ㄴ(cf. pick random element from array - (not actually used):
 *  https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
 *  https://stackoverflow.com/questions/9055287/select-a-random-value-from-an-array
 *
 * -( Arrays.sort(ArrayName) ): http://emflant.tistory.com/210
 *
 */


public class Lotto {
//[variable declaration]
    public static final int NUMBER_SEL = 6;              //(constant - number of numbers chosen)
    private int[] numbers = new int[NUMBER_SEL];          //(saving generated numbers - using constant)  //(elements -> resetted to 0 by default)
    public final int[] frequentNumbers = {27, 1, 43, 20, 40, 17, 34, 37, 4, 13};     //(-- top 10 numbers in 2017/06/26)


//[constructor]
    public Lotto() {

    }
//[generate random numbers whenever executed(called)]
    public void numbersGenerate(boolean flag) {     //(flag: whether switch is on = whether 'frequently picked numbers' are reflected)
    //[[1: pick 6 random numbers]]
        int index = 0;      //(index of 6 number array) -- (to be used later!)
        int rnd = 0;
        while ( index < NUMBER_SEL ) {       //(index should not automatically move to next (so for loop is not used))
            //[1: create each 1]
            if (flag) {             //[switch 'on'] -- (give weight to 'specific numbers' to make them picked in higher probability)
                //(1: determine whether 'priority numbers' will be picked) -- (ratio here is (priority):(all) = 75:25)
                int probability = getRandom(100);    //(the probability of 'priority number' will be picked -- think this as number of % !)
                if (probability < 75) {      //(from 'priority numbers') - (pick indirectly)       //(using method that is made already)
                    int random_index = getRandom(frequentNumbers.length) - 1;   //(first, randomly pick index of 'priority numbers'(--not the all (45) numbers)...)
                    rnd = frequentNumbers[random_index];                        //(...then save the (priority) number corresponding to picked index as output.)
                } else {                    //(from all numbers)
                    rnd = getRandom(45);    //(-> external method)
                }

            }else {                //[switch 'off] -- (just (randomly) pick number from all 45)
                rnd = getRandom(45);    //(-> external method)
            }
            //[2: check this (number) to see if it's duplicate(same as already picked numbers)]
            boolean isDup = false;      //(switch whether if it's duplicate -- initialized  to this whenever (outer) while-looped)
            for (int pickedNum: numbers) {          //('enhanced for loop')
                if (rnd == pickedNum) {     //(If it matches the already picked numbers...)
                    isDup = true;   break;  //(... prevent 'index' from going next.)
                }
            }
            //[3: only if it's not duplicate -> pick(save) next number]
            if(! isDup) {
                numbers[index] = rnd;       //(actual assignment)
                index++;            //(next index)    //(don't need to change flag here -- resets ever loop)
            }
        }
    //[[2: ascending sort]]
        Arrays.sort(numbers);       //(ascending sort using existing method(from collection))
    }

//[랜덤 생성 -- 숫자 1개]
    public static int getRandom(int max) {
        return ( (int) (Math.random() * max) + 1 );       //(원래 double형 결과가 나오는 거라서 & (그냥은) 1 적은 숫자까지만 나옴.) -- (위 '참고'사항 참고)
    }
//[숫자 교환]
    private void swap(int x, int y) {
        int temp = x;
               x = y;
               y = temp;
    }

//[getter]
    public int[] getNumbers() {
        return numbers;
    }

//[setter]      -- (필요할 때만!)

}
