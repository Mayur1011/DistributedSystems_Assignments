#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

int main(int argc, char** argv) {
    int num_elements_per_proc;  // Number of elements per MPI process
    int *arr, *sub_arr;  // Array and subarray for each MPI process
    int sum = 0, total_sum = 0;  // Variables for local and global sum
    int i, n;

    MPI_Init(&argc, &argv);  // Initialize MPI
    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);  // Get the current MPI process rank
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);  // Get the total number of MPI processes

    if (world_rank == 0) {
        printf("Enter the number of elements (N): ");
        fflush(stdout);
        scanf("%d", &n);

        printf("Enter %d number : ", n);
        fflush(stdout);
        arr = (int*)malloc(n * sizeof(int));
        for (i = 0; i < n; i++) {
            int currnum;
            scanf("%d", &currnum);
            arr[i] = currnum;
        }

        // Print the array elements
        printf("Array: ");
        for (i = 0; i < n; i++) {
            printf("%d ", arr[i]);
        }
        printf("\n");
    }

    // Broadcast the number of elements to all processes
    MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);

    num_elements_per_proc = n / world_size;
    sub_arr = (int*)malloc(num_elements_per_proc * sizeof(int));

    // Scatter the array elements to different processes
    MPI_Scatter(arr, num_elements_per_proc, MPI_INT, sub_arr, num_elements_per_proc, MPI_INT, 0, MPI_COMM_WORLD);

    // Calculate the local sum
    for (i = 0; i < num_elements_per_proc; i++) {
        sum += sub_arr[i];
    }

    // Display intermediate sums calculated at different processors
    printf("Partial sum at process %d: %d\n", world_rank, sum);

    // Reduce all the partial sums to calculate the global sum
    MPI_Reduce(&sum, &total_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    if (world_rank == 0) {
        printf("Total sum: %d\n", total_sum);
    }

    MPI_Finalize();  // Finalize MPI

    return 0;
}
