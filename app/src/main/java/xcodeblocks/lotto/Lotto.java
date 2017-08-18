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


//[생성자(constructor)]
    public Lotto() {

    }
//[실행될때마다 랜덤 생성]
    public void numbersGenerate(boolean flag) {     //(flag: 스위치 적용 여부 = '자주 뽑히는 숫자들' 반영 여부 -- <발표 자료> 참고)
    //[[1: 6개 랜덤 숫자 뽑기]]
        int index = 0;      //(전체 숫자 배열 인덱스)
        int rnd = 0;
        while ( index < NUMBER_SEL ) {       //(그냥 무조건 다음 index로 넘어가면 안되므로 (for문 안씀))
            //[1: 일단 1개씩 생성]
            if (flag) {             //[switch 'on'] -- (특정 숫자들에 가중 확률을 줘서 더 높은 확률로 뽑히게 하기)
                //(1: '우선순위 숫자들' 뽑힐 여부 판단) -- (비율은 (우선순위):(전체) = 75:25)
                int probability = getRandom(100);    //('우선순위 숫자'가 뽑힐 여부)
                if (probability < 75) {      //('우선순위 숫자') - (간접적으로 뽑기)       //(이미 만들어 놓은 메소드 사용)
                    int random_index = getRandom(frequentNumbers.length) - 1;   //(랜덤으로 ('우선순위 숫자' 중에서) 뽑을 성분의 인덱스(-전체 아님)부터 뽑고...)
                    rnd = frequentNumbers[random_index];                        //(...뽑은 인덱스에 해당하는 수를 결과로 내보냄.)
                } else {                    //(전체)
                    rnd = getRandom(45);    //(-> 외부 메소드)
                }

            }else {                //[switch 'off] -- (그냥 45개 숫자 중에서 무작위로 뽑기)
                rnd = getRandom(45);    //(-> 외부 메소드)
            }
            //[2: 이것을 (이미 뽑은) 다른 숫자와 중복되는 지 확인]
            boolean isDup = false;      //(중복여부 스위치 -- while문 돌때마다 이렇게 초기화)
            for (int pickedNum: numbers) {          //(확장형 for문)
                if (rnd == pickedNum) {     //(이미 뽑은 숫자와 겹치면...)
                    isDup = true;   break;  //(... 다음 index로 넘어가지 못하게 방지.)
                }
            }
            //[3: 중복이 되지 않을 때만 -> 다음 숫자 뽑기]
            if(! isDup) {
                numbers[index] = rnd;       //(실제 대입)
                index++;            //(다음 index)    //(flag는 매번 초기화되므로 여기서 건들 필요 X)
            }
        }
    //[[2: 오름차순 정렬]]
        Arrays.sort(numbers);       //(기존의 메소드를 이용한 오름차순 정렬)
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
