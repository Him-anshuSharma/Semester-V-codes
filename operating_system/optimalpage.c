#include <stdio.h>

#define MAX_FRAMES 100
#define MAX_PAGES 1000

int frames[MAX_FRAMES];
int pages[MAX_PAGES];
int frameCount;
int pageCount;

int pageFaults = 0;

void initializeFrames() {
    for (int i = 0; i < frameCount; i++) {
        frames[i] = -1; // -1 indicates an empty frame
    }
}

int findFarthestPage(int start, int end, int* futurePages) {
    int farthestPage = start;
    int farthestDistance = 0;
    for (int i = start; i < end; i++) {
        int page = pages[i];
        int distance = 0;
        for (int j = i + 1; j < pageCount; j++) {
            if (page == futurePages[j]) {
                break;
            }
            distance++;
        }
        if (distance > farthestDistance) {
            farthestPage = i;
            farthestDistance = distance;
        }
    }
    return farthestPage;
}

void printFrames() {
    printf("Frames: ");
    for (int i = 0; i < frameCount; i++) {
        if (frames[i] == -1) {
            printf("- ");
        } else {
            printf("%d ", frames[i]);
        }
    }
    printf("\n");
}

void simulateOptimal() {
    initializeFrames();

    int futurePages[MAX_PAGES];
    for (int i = 0; i < pageCount; i++) {
        futurePages[i] = -1; // -1 indicates the page does not appear in the future
        for (int j = i + 1; j < pageCount; j++) {
            if (pages[i] == pages[j]) {
                futurePages[i] = j;
                break;
            }
        }
    }

    for (int i = 0; i < pageCount; i++) {
        int page = pages[i];
        int pageFound = 0;

        for (int j = 0; j < frameCount; j++) {
            if (frames[j] == page) {
                pageFound = 1;
                break;
            }
        }

        if (!pageFound) {
            int replaceIndex = findFarthestPage(i + 1, pageCount, futurePages);
            frames[replaceIndex % frameCount] = page;
            pageFaults++;

            // Print the page fault and the current state of frames
            printf("Page Fault: Page %d at index %d\n", page, i);
            printFrames();
        } else {
            printf("Page Hit: Page %d at index %d\n", page, i);
            printFrames();
        }
    }
}

int main() {
    printf("Enter the number of frames: ");
    scanf("%d", &frameCount);

    printf("Enter the number of pages: ");
    scanf("%d", &pageCount);

    printf("Enter the page reference string:\n");
    for (int i = 0; i < pageCount; i++) {
        scanf("%d", &pages[i]);
    }

    simulateOptimal();

    printf("Number of page faults: %d\n", pageFaults);

    return 0;
}
