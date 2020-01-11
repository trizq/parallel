#include <random>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <iostream>

double countPI(int n)
{
	double PI;
	double x, y;
	int i, sum;

	sum = 0;
	srand(time(NULL));
	for (i = 1; i < n; i++)
	{
		x = (double)rand() / RAND_MAX;
		y = (double)rand() / RAND_MAX;
		if ((x*x + y * y) <= 1)
			sum++;
	}
	PI = 4.0*sum / n;
	return PI;
}

int main()
{
	int n;
	double PI;
	n = 1000000;
	PI = countPI(n);
	printf("PI=%f\n", PI);
	system("pause");
	return 0;
}
