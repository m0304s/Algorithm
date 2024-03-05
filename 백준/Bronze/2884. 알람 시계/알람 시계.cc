#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int h, m;
	scanf("%d", &h);
	scanf("%d", &m);
	if (h > 0 && (m - 45) >= 0)
		printf("%d %d\n", h, m - 45);
	else if (h > 0 && (m - 45) < 0)
		printf("%d %d\n", h - 1, m + 15);
	else if (h == 0 && (m - 45) < 0)
		printf("%d %d\n", 23, m + 15);
	else if (h == 0 && (m - 45) >= 0)
		printf("%d %d\n", h, m - 45);

	return 0;
}