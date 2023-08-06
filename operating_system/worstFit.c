#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int id;
    int size;
    int allocated;
} Block;

void* worstFit(Block* memory, int memorySize, int requestedSize) {
    int worstFitIndex = -1;
    int worstFitSize = -1;

    for (int i = 0; i < memorySize; i++) {
        if (!memory[i].allocated && memory[i].size >= requestedSize) {
            if (worstFitIndex == -1 || memory[i].size > worstFitSize) {
                worstFitIndex = i;
                worstFitSize = memory[i].size;
            }
        }
    }

    if (worstFitIndex == -1) {
        return NULL;
    }

    memory[worstFitIndex].allocated = 1;

    return &memory[worstFitIndex];
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

    int requestedSize = 250;
    Block* allocatedBlock = worstFit(memory, memorySize, requestedSize);

    if (allocatedBlock != NULL) {
        printf("Memory block %d allocated with size %d\n", allocatedBlock->id, allocatedBlock->size);
    } else {
        printf("No suitable memory block found.\n");
    }

    return 0;
}
