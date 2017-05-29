//[실제 기능 모음]

package xcodeblocks.lotto;

/**
 * Created by XCodeBlocks on 2017-05-29.
 *
 * --(reference):
 * -(숫자 랜덤 생성): http://www.java67.com/2015/01/how-to-get-random-number-between-0-and-1-java.html
 *
 */

public class Lotto {
//[변수(variable) 선언]
    private static final int NUMBER_SEL = 6;              //(상수 - 선택하는 숫자 개수)
    private int[] numbers = new int[NUMBER_SEL];          //(생성 번호 저장 - 상수 사용)  //(성분들 -> 기본 0으로 초기화)


//[생성자(constructor)]
    public Lotto() {
        int x = 0;
        while ( x < NUMBER_SEL) {       //(그냥 무조건 다음 index(x)로 넘어가면 안되므로 (for문 안씀))
            //[1: 일단 1개씩 생성]
            int rnd = getRandom(45);    //(-> 외부 메소드)
            boolean flag = false;       //(중복여부 스위치)
            //[2: 이것을 다른 (이미 뽑은) 숫자와 중복되는 지 확인]

            //[3: 중복이 되지 안을 때만 -> 다음 숫자 뽑기]
        }
    }

//[랜덤 생성 -- 숫자 1개]
    public static int getRandom(int max) {
        return (int) (Math.random() * max);       //(원래 double형 결과가 나오는 거라서)
    }

//[선택 정렬] - (오름차순)
    void selectionSort(int[] numbers)
    {
        for (int x = 0 ; x < (NUMBER_SEL - 1) ; x++ ) {
            for (int y = (x+1) ; y < NUMBER_SEL ;  y++ ) {
                if ( numbers[x] > numbers[y] ) {
                    swap(numbers[x], numbers[y]);       //(-> 별도 메소드로 분리)
                }
            }
        }
    }
//[숫자 교환]
    private void swap(int x, int y) {
        int temp = x;
               x = y;
               y = temp;
    }

//[getter]


//[setter]      -- (필요할 때만!)

}
