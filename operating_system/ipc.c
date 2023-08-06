#include <stdio.h>
#include <unistd.h>
#include <string.h>

#define BUFFER_SIZE 256

int main() {
    int pipefd[2];
    pid_t childpid;
    char buffer[BUFFER_SIZE];

    // Create the pipe
    if (pipe(pipefd) == -1) {
        perror("pipe");
        return 1;
    }

    // Fork a child process
    childpid = fork();
    if (childpid == -1) {
        perror("fork");
        return 1;
    }

    if (childpid == 0) {
        // Child process
        close(pipefd[1]);  // Close the write end of the pipe

        // Read from the pipe
        ssize_t numRead = read(pipefd[0], buffer, BUFFER_SIZE);
        if (numRead == -1) {
            perror("read");
            return 1;
        }

        printf("Child received message: %s", buffer);

        close(pipefd[0]);  // Close the read end of the pipe
    } else {
        // Parent process
        close(pipefd[0]);  // Close the read end of the pipe

        const char* message = "Hello from parent!";

        // Write to the pipe
        ssize_t numWritten = write(pipefd[1], message, strlen(message));
        if (numWritten == -1) {
            perror("write");
            return 1;
        }

        close(pipefd[1]);  // Close the write end of the pipe
    }

    return 0;
}
