#include <stdio.h>
int main(void)
{
    int hour,minute,time;
    scanf("%d %d",&hour,&minute);
    scanf("%d",&time);
    int time_h,time_m;
    time_h=time/60;
    time_m=time%60;
    int final_hour;
    int final_minute;
    final_hour=hour+time_h;
    if((minute+time_m)>=60)
    {
        final_hour=final_hour+(minute+time_m)/60;
    }
    final_minute=(minute+time_m)%60;
    if(final_hour>=24)
    {
        final_hour=final_hour%24;
    }
    printf("%d %d\n",final_hour,final_minute);
}