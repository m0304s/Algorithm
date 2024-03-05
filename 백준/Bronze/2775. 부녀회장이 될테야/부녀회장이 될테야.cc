#include <stdio.h>
int main(void){
    int T=0,K=0,N=0;
    int arr[15][15]={0};
    for(int i=0;i<15;i++){
        arr[0][i]=i;
    }
    for(int i=1;i<15;i++){
            for(int j=1;j<15;j++){
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        }
    scanf("%d",&T);
    for(int x=0;x<T;x++){
        scanf("%d %d",&K,&N);
        printf("%d\n",arr[K][N]);
    }
}