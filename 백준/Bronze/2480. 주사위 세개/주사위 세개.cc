#include <stdio.h>
int main(void)
{
    int dice_a,dice_b,dice_c;
    int prize;
    int result;
    scanf("%d %d %d",&dice_a,&dice_b,&dice_c);
    int first_number;
    if(dice_a>dice_b&&dice_a>dice_c)
    first_number=dice_a;
    else if(dice_b>dice_a&&dice_b>dice_c)
    first_number=dice_b;
    else if(dice_c>dice_a&&dice_c>dice_a)
    first_number=dice_c;
    if(dice_a==dice_b&&dice_b==dice_c)
    {
        prize=10000+(dice_a)*1000;
    }
    else if(dice_a==dice_b||dice_b==dice_c||dice_c==dice_a)
    {
        if(dice_a==dice_b)
        {
            result=dice_a;
        }
        else if(dice_b==dice_c)
        {
            result=dice_b;
        }
       else if(dice_c==dice_a)
        {
            result=dice_c;
        }
        prize=1000+result*100;
    }
    else
    prize=first_number*100;
    
    printf("%d\n",prize);
}