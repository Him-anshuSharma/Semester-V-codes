//does not call wait to reap child process
#include<stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
int main()
{
	pid_t child_pid = fork();

	// Parent process
	if (child_pid > 0){
        printf("parent process started%d\n",getpid());
		sleep(10);
    }

	// Child process
	else{	
        printf("child process started%d\n",getpid());
        sleep(3);
        printf("child process finished%d\n",getpid());
		exit(0);
    }

	return 0;
}
