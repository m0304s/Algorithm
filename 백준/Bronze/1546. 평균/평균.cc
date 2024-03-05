#include <stdio.h>
int main(void) {
    int N;
    double score[1000] = {0};
    scanf("%d", &N);
    int MAX = 0;
    for (int i = 0; i < N; i++) {
        scanf("%lf", &score[i]);
    }
    for (int j = 0; j < N; j++) {
        if (MAX < score[j])
            MAX = score[j];
    }
    double new_score[1000] = {0};
    for (int i = 0; i < N; i++) {
        new_score[i] =score[i] / MAX * 100;
    }
    double sum = 0;
    for (int i = 0; i < N; i++) {
        sum += new_score[i];
    }
    printf("%lf\n", sum / N);
}
