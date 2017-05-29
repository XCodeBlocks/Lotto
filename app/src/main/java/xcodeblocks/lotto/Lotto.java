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
    private int[] numbers = new int[NUMBER_SEL];          //(생성 번호 저장 - 상수 사용)


//[생성자(constructor)]
    public Lotto() {
        //[]

        //[]

        //[]
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
                    swap(numbers[x], numbers[y]);       //(별도 메소드로 분리)
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
