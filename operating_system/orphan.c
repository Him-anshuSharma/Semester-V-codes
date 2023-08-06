//parent terminates before child

#include<stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
	
	int pid = fork();

	if (pid > 0){

		printf("Hello from parent\n");
	}
	else if (pid == 0)
	{
		
		printf("hello from child");
	}
	return 0;
}
