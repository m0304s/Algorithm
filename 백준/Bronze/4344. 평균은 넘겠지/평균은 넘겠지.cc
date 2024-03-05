#include <stdio.h>
int main(void) {
    int N;
    scanf("%d",&N);
    for(int i=0;i<N;i++){
        int count=0;
        double data[1000]={0};
        scanf("%d",&count);
        for(int j=0;j<count;j++){
            scanf("%lf",&data[j]);
        }
        double sum=0;
        for(int j=0;j<count;j++){
            sum+=data[j];
        }
        double average=sum/count;
        double number=0;
        for(int x=0;x<count;x++){
            if(data[x]>average)
                number++;
        }
        printf("%.3lf%%\n",(number/count)*100);
    }

}
