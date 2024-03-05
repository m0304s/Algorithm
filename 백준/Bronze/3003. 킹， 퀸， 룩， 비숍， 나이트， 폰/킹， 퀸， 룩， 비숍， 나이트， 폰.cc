#include <stdio.h>
int main(void)
{
    int king;
    int queen;
    int look;
    int visup;
    int night;
    int pawn;
    scanf("%d",&king); //킹 퀸 룩 비숍 나이트 폰 의 개수를 입력받는다.
    scanf("%d",&queen);
    scanf("%d",&look);
    scanf("%d",&visup);
    scanf("%d",&night);
    scanf("%d",&pawn);
    printf("%d %d %d %d %d %d\n",1-king,1-queen,2-look,2-visup,2-night,8-pawn);
    return 0;
}
