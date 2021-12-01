package projectStart;

public class TestingTemporaryCode 
{
	
	/*
	 * 
	 * PARENT.i /
1 return bi=2c
LEFT.i /
1 return 2i
RIGHT.i /
1 return 2i C 1
	 * 
	 * 
	 * MAX-HEAPIFY.A; i /
1 l = LEFT.i /
2 r = RIGHT.i /
3 if l <= A.heap-size and A[l]  > A[i]
4 largest = l
5 else largest = i
6 if r <= A.heap-size and A[r] > A[largest]
7 largest = r
8 if largest != i
9 exchange A[i]  with A[largest]
10 MAX-HEAPIFY(A, largest)
	 */
	// Java program for implementation of Heap Sort
		public void sort(int arr[])
		{
			int n = arr.length;

			// Build heap (rearrange array)
			for (int i = n / 2 - 1; i >= 0; i--)
				heapify(arr, n, i);

			// One by one extract an element from heap
			for (int i = n - 1; i > 0; i--) {
				// Move current root to end
				int temp = arr[0];
				arr[0] = arr[i];
				arr[i] = temp;

				// call max heapify on the reduced heap
				heapify(arr, i, 0);
			}
		}

		// To heapify a subtree rooted with node i which is
		// an index in arr[]. n is size of heap
		void heapify(int arr[], int n, int i)
		{
			int largest = i; // Initialize largest as root
			int l = 2 * i + 1; // left = 2*i + 1
			int r = 2 * i + 2; // right = 2*i + 2

			// If left child is larger than root
			if (l < n && arr[l] > arr[largest])
				largest = l;

			// If right child is larger than largest so far
			if (r < n && arr[r] > arr[largest])
				largest = r;

			// If largest is not root
			if (largest != i) {
				int swap = arr[i];
				arr[i] = arr[largest];
				arr[largest] = swap;

				// Recursively heapify the affected sub-tree
				System.out.println("Current ARR: ");
				printArray(arr);
				heapify(arr, n, largest);
			}
		}

		/* A utility function to print array of size n */
		static void printArray(int arr[])
		{
			int n = arr.length;
			for (int i = 0; i < n; ++i)
				System.out.print(arr[i] + " ");
			System.out.println();
		}

		// Driver code
		public static void main(String args[])
		{
			int arr[] = {17, 27,  3, 16, 13,  10,  11,  5,  7,  12,  4,  8,  9,  10};
			int n = arr.length;

			TestingTemporaryCode ob = new TestingTemporaryCode();
			ob.sort(arr);

			System.out.println("Sorted array is");
			printArray(arr);
		}

}
