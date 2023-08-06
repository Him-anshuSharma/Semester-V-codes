#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    const char* filename = "example.txt";
    int fd = open(filename, O_RDWR | O_CREAT);
    if (fd == -1) {
        perror("Failed to open file");
        exit(EXIT_FAILURE);
    }


    struct flock fl;
    fl.l_type = F_WRLCK;        

    if (fcntl(fd, F_SETLK, &fl) == -1) {
        perror("Failed to acquire lock");
        close(fd);
        exit(EXIT_FAILURE);
    }

    printf("Lock acquired. Press Enter to release the lock.\n");
    getchar();


    fl.l_type = F_UNLCK;
    if (fcntl(fd, F_SETLK, &fl) == -1) {
        perror("Failed to release lock");
        close(fd);
        exit(EXIT_FAILURE);
    }

    printf("Lock released.\n");

    close(fd);
    return 0;
}
