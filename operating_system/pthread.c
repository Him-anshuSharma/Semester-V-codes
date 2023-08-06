#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int var = 0;

pthread_mutex_t lock;
void *thread_function(void *arg) {
  int num = *((int *)arg);

  // Acquire the lock
  pthread_mutex_lock(&lock);

  // Critical section
  printf("Thread %d: I have the lock\n", num);
  var ++;
  sleep(1);
  printf("var value %d\n",var);
  printf("Thread %d: Releasing the lock\n", num);

  // Release the lock
  pthread_mutex_unlock(&lock);

  return NULL;
}

int main() {
  int i;
  pthread_t threads[10];

  // Initialize the lock
  pthread_mutex_init(&lock, NULL);

  // Create 10 threads
  for (i = 0; i < 10; i++) {
    int *num = malloc(sizeof(int));
    *num = i;
    pthread_create(&threads[i], NULL, thread_function, num);
  }

  // Join all threads
  for (i = 0; i < 10; i++) {
    pthread_join(threads[i], NULL);
  }

  // Destroy the lock
  pthread_mutex_destroy(&lock);

  return 0;
}
