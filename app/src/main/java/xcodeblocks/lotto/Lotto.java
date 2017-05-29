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
    //[[1: 6개 랜덤 숫자 뽑기]]
        int index = 0;
        while ( index < NUMBER_SEL) {       //(그냥 무조건 다음 index로 넘어가면 안되므로 (for문 안씀))
            //[1: 일단 1개씩 생성]
            int rnd = getRandom(45);    //(-> 외부 메소드)
            boolean isDup = false;      //(중복여부 스위치 -- while문 돌때마다 이렇게 초기화)
            //[2: 이것을 (이미 뽑은) 다른 숫자와 중복되는 지 확인]
            for (int pickedNum: numbers) {          //(확장형 for문)
                if (rnd == pickedNum) {     //(이미 뽑은 숫자와 겹치면...)
                    isDup = true;   break;  //(다음 index로 넘어가지 못하게 방지.)
                }
            }
            //[3: 중복이 되지 안을 때만 -> 다음 숫자 뽑기]
            if(!isDup) {
                numbers[index] = rnd;       //(실제 대입)
                        index++;        //(다음 index)    //(flag는 매번 초기화되므로 여기서 건들 필요 X)
            }
        }
    //[[2: 오름차순 정렬]]
        selectionSort(numbers);     //(-> 외부 메소드)

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
