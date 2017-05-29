//[실제 기능 모음]

package xcodeblocks.lotto;

/**
 * Created by XCodeBlocks on 2017-05-29.
 *
 * -(참고): [생성자]-[[1]]-[2]: (0 체크가 필요없는 이유):
 *                            처음에 클래스 안에서 배열 필드(field) 선언할때는 자동으로 모든 성분들이 0으로 초기화 되는데
 *                            저장하는 방식이... 저장해도 될때만 저장하는 방식이라서(미리 저장해서 바꾸지 않으므로)
 *                            마지막 숫자를 확인할때 아직 배열(여기서는 numbers)의 마지막 성분이 0으로 그대로 있으므로
 *                            이것과 비교하면 0이 나왔는지가 검출이 된다.
 *
 * --(reference):
 * -(특정 버전으로 되돌리기): (안드로이드 스튜디오에서는... 해당 브랜치 우클릭->Checkout Revision 하면 됨.) http://opendive.blogspot.kr/2015/06/git.html
 *
 * -(숫자 랜덤 생성): http://www.java67.com/2015/01/how-to-get-random-number-between-0-and-1-java.html
 *
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
            //[2: 이것을 (이미 뽑은) 다른 숫자와 중복되는 지 확인]     -- (!: 0 나왔는지 체크는 필요없는 이유: 위의 /** */ 부분 참고)
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
