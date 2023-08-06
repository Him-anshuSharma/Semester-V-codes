#include <stdio.h>
#include <stdlib.h>


typedef struct {
    int id;
    int size;
    int allocated;
} Block;

void* bestFit(Block* memory, int memorySize, int requestedSize) {
    int bestFitIndex = -1;
    int bestFitSize = -1;

    for (int i = 0; i < memorySize; i++) {
        if (!memory[i].allocated && memory[i].size >= requestedSize) {
            if (bestFitIndex == -1 || memory[i].size < bestFitSize) {
                bestFitIndex = i;
                bestFitSize = memory[i].size;
            }
        }
    }

    if (bestFitIndex == -1) {
        return NULL;
    }

    memory[bestFitIndex].allocated = 1;

    return &memory[bestFitIndex];
}

int main() {
    Block memory[] = {
        {1, 100, 0},
        {2, 500, 0},
        {3, 200, 0},
        {4, 300, 0},
        {5, 600, 0}
    };

    int memorySize = sizeof(memory) / sizeof(memory[0]);

    printf("Available memory blocks:\n");
    for (int i = 0; i < memorySize; i++) {
        printf("Block %d, Size: %d\n", memory[i].id, memory[i].size);
    }

    int numRequests;
    printf("\nEnter the number of memory requests: ");
    scanf("%d", &numRequests);

    int requests[numRequests];

    for (int i = 0; i < numRequests; i++) {
        int requestedSize;
        printf("Enter the requested memory size for request %d: ", i + 1);
        scanf("%d", &requests[i]);
    }
    printf("\n");
    for (int i = 0; i < numRequests; i++) {
        Block* allocatedBlock = bestFit(memory, memorySize, requests[i]);

        if (allocatedBlock != NULL) {
            printf("Memory block %d allocated with size %d\n", allocatedBlock->id, allocatedBlock->size);
        } else {
            printf("No suitable memory block found for request %d.\n", i + 1);
        }
    }

    return 0;
}
