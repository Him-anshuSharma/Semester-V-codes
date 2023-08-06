
#include <stdio.h>

typedef struct
{
    int id;
    int arrival_time;
    int burst_time;
    int priority;
    int turn_around_time;
    int completing_time;
    int waiting_time;
} process;

process queue[100];
int queue_size = 0;
int queue_start_index = 0;
int current_time = 0;
int total_items = 5;

process processes[] = {
    {1, 5, 5, 0, 0, 0, 0},
    {2, 4, 2, 7, 0, 0, 0},
    {3, 3, 7, 5, 0, 0, 0},
    {4, 0, 4, 10, 0, 0, 0},
    {5, 3, 5, 5, 0, 0, 0}};

int completed_processes = 0;

void get_total_items()
{
    printf("Enter the total number of items: ");
    scanf("%d", &total_items);
}

void get_process_details()
{
    for (int i = 0; i < total_items; i++)
    {
        printf("Enter the arrival time of process %d: ", i + 1);
        scanf("%d", &processes[i].arrival_time);

        printf("Enter the burst time of process %d: ", i + 1);
        scanf("%d", &processes[i].burst_time);

        printf("Enter the priority of process %d: ", i + 1);
        scanf("%d", &processes[i].priority);
    }
}

void selection_sort()
{

    for (int i = 0; i < total_items - 1; i++)
    {
        int min_idx = i;
        for (int j = i + 1; j < total_items; j++)
        {
            if (processes[j].arrival_time <= processes[min_idx].arrival_time)
            {
                min_idx = j;
            }
        }

        if (min_idx != i)
        {
            process temp = processes[i];
            processes[i] = processes[min_idx];
            processes[min_idx] = temp;
        }
    }
}

void print_processes()
{

    printf("| Process | Arrival Time | Completion Time | Burst Time | Turnaround Time | Waiting Time |\n");
    printf("------------------------------------------------------------------------------------\n");

    for (int i = 0; i < total_items; i++)
    {
        printf("|   P%-2d  |      %-2d       |        %-2d         |     %-2d      |        %-2d         |      %-2d       |\n",
               queue[i].id, queue[i].arrival_time, queue[i].completing_time, queue[i].burst_time, queue[i].turn_around_time, queue[i].waiting_time);
    }

    printf("------------------------------------------------------------------------------------\n");
}

void store_processes_before_current_time()
{

    for (int i = queue_size; i < total_items; i++)
    {
        if (processes[i].arrival_time <= current_time)
        {
            queue[queue_size++] = processes[i];
        }
    }
}

void sort_queue_on_priority()
{

    for (int i = queue_start_index; i < queue_size; i++)
    {

        for (int j = i + 1; j < queue_size; j++)
        {
            if (queue[i].priority < queue[j].priority)
            {

                process temp = queue[i];
                queue[i] = queue[j];
                queue[j] = temp;
            }
        }
    }
}

void execute(process *process)
{

    int index = completed_processes;

    current_time = current_time + process->burst_time;

    process->turn_around_time = current_time - process->arrival_time;

    process->completing_time = current_time;

    process->waiting_time = process->completing_time - process->arrival_time - process->burst_time;

    queue_start_index += 1;

    while (processes[index].id != process->id)
    {
        index++;
    }

    processes[index].completing_time = process->completing_time;
    processes[index].turn_around_time = process->turn_around_time;
    processes[index].waiting_time = process->waiting_time;
    completed_processes += 1;
}

int main()
{
    selection_sort();
    while (completed_processes != total_items)
    {
        if (queue_size != total_items)
        {
            store_processes_before_current_time();
            sort_queue_on_priority();
            execute(&queue[queue_start_index]);
        }
        else
        {
            execute(&queue[queue_start_index]);
        }
    }
    print_processes();
    return 0;
}
