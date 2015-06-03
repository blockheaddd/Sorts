
import java.util.*;

public class sortTester {

    final static int THRESHOLD = 10;
    final static int SIZE = 20;

    public static void main(String[] args) {


        long t1, t2, qsTime, msTime, qsoTime, msoTime, radTime;

        int[] arr = {4,17,79,52,56,19,21,22,3,12,30,33,81,99,85,28,24,
                25,24,26,27,20,44,32,35,24,39,43,41,46,56,62,22,20,69,18};

        printArray(arr, arr.length);
        quickSort(arr, 0, arr.length - 1);
        printArray(arr, arr.length);



    }
    //Function for printing Array
    static void printArray(int arr[], int size)
    {
        for(int i=0; i < size; i++)
        {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }


    //Functions for Quick Sort

    static int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }

    static void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }

    //Functions for Optimized Quick Sort

    static void optimizedQuickSort(int arr[], int left, int right)
    {
        int index = partition(arr, left, right);
        if((right - left) < THRESHOLD)
        {
            insertionSort(arr, left, right);
        }
        else
        {
            if (left < index - 1)
                optimizedQuickSort(arr, left, index - 1);
            if (index < right)
                optimizedQuickSort(arr, index, right);
        }
    }

    static int optimizedPartition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = (right + left) / 2;
        //int pivot = findPivot(arr, left, right);

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };
        return i;
    }

    static int findPivot(int a[], int low, int high)
    {
        int middle = ( low + high ) / 2;
        int temp;
        if( a[middle] < a[low] )
        {
            temp = a[middle];
            a[middle] = a[low];
            a[low] = temp;
        }
        if( a[high]< a[low] )
        {
            temp = a[high];
            a[high] = a[low];
            a[low] = temp;
        }
        if( a[high] < a[middle])
        {
            temp = a[middle];
            a[middle] = a[high];
            a[high] = temp;
        }
        return middle;
    }


    public static void insertionSort(int[] arr, int left, int right)
    {
        int in, out;

        for (out = left + 1; out <= right; out++) {
            int temp = arr[out];
            in = out;

            while (in > left && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }

    }

    //Functions for Merge Sort

    public static void mergeSort(int [ ] a)
    {
        int[] tmp = new int[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }


    private static void mergeSort(int [ ] a, int [ ] tmp, int left, int right)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }


    private static void merge(int[ ] a, int[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left]<=a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }

    // Functions for Optimized Merge Sort
    static void mergesortOptimized(int[] A, int[] temp, int l, int r) {
        int i, j, k, mid = (l+r)/2; // Select the midpoint
        if (l == r) return; // List has one element
        if ((mid-l) >= THRESHOLD)
            mergesortOptimized(A, temp, l, mid);
        else
            insertionSort(A, l, mid-l+1);

        if ((r-mid) > THRESHOLD)
            mergesortOptimized(A, temp, mid+1, r);
        else
            insertionSort(A, mid+1, r-mid+1);

        // Do the merge operation. First, copy 2 halves to temp.
        for (i=l; i<=mid; i++) temp[i] = A[i];
        for (j=1; j<=r-mid; j++) temp[r-j+1] = A[j+mid];
        // Merge sublists back to array
        for (i=l,j=r,k=l; k<=r; k++)
            if (temp[i]< (temp[j]))
                A[k] = temp[i++];
            else
                A[k] = temp[j--];
    }

    //Function for Radix Sort
    static void radixSort(int[] input) {
        final int RADIX = 10;
        // declare and initialize bucket[]
        List<Integer>[] bucket = new ArrayList[RADIX];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) {
            maxLength = true;
            // split input between lists
            for (Integer i : input) {
                tmp = i / placement;
                bucket[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : bucket[b]) {
                    input[a++] = i;
                }
                bucket[b].clear();
            }
            // move to next digit
            placement *= RADIX;
        }
    }
}