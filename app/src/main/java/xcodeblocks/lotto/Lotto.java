//[실제 기능 모음]

package xcodeblocks.lotto;

/**
 * Created by XCodeBlocks on 2017-05-29.
 */

public class Lotto {
//[변수(variable) 선언]
    private static final int NUMBER_SEL = 6;              //(상수 - 선택하는 숫자 개수)
    private int[] numbers = new int[NUMBER_SEL];          //(생성 번호 저장 - 상수 사용)


//[생성자(constructor)]
    public Lotto() {

    }


//[랜덤 생성]


//[선택 정렬] - (오름차순)
    void selectionSort(int[] numbers)
    {
        for (int x = 0 ; x < (NUMBER_SEL - 1) ; x++ ) {
            for (int y = (x+1) ; y < NUMBER_SEL ;  y++ ) {
                if ( numbers[x] > numbers[y] ) {
                    swap(numbers[x], numbers[y]);
                }
            }
        }
    }
//[숫자 교환]
    private void swap(int x, int y) {

    }

//[getter]


//[setter]      -- (필요할 때만!)

}
