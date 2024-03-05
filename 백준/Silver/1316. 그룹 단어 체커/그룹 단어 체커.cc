#include <stdio.h>
#include <string.h>
bool check_group_word(char word[]){
    int len=strlen(word);
    for(int i=0;i<len;i++){
        int count_of_index=0;
        for(int j=i+1;j<len;j++){
            if(word[i]==word[j])
                count_of_index++;
        }
        for(int j=i+1+count_of_index;j<len;j++){
            if(word[i]==word[j])
                return true;
        }
        i=i+count_of_index;
    }
    return false;
}
int main(void){
    int n;
    scanf("%d",&n);
    int count=0;
    for(int i=0;i<n;i++){
        char word[100];
        scanf("%s",word);
        if(!check_group_word(word))
            count++;
    }
    printf("%d\n",count);
}