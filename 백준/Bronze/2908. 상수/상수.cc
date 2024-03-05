#include <stdio.h>
#include <string.h>
int main(void){
    int num1;
    int num2;
    scanf("%d",&num1);
    scanf("%d",&num2);
    int hundred,ten,one;
    hundred=num1/100;
    ten=(num1-hundred*100)/10;
    one=num1%10;
    num1=one*100+ten*10+hundred;
    hundred=num2/100;
    ten=(num2-hundred*100)/10;
    one=num2%10;
    num2=one*100+ten*10+hundred;
    if(num1>num2)
        printf("%d",num1);
    else
        printf("%d",num2);
}