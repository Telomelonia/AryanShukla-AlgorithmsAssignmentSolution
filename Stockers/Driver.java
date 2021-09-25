package Stockers;

import java.util.Scanner;
import Stockers.Company;
public class Driver 
{
    //Merge sort
        static void sort(double arr[], int l, int r)
        {
            if (l < r)
            {
                int m = (l+r)/2;
                sort(arr, l, m);
                sort(arr , m+1, r);
                merge(arr, l, m, r);
            }
        }
        static void merge(double arr[], int l, int m, int r)
        {
            int n1 = m - l + 1;
            int n2 = r - m;
            double L[] = new double [n1];
            double R[] = new double [n2];
            for (int i=0; i<n1; ++i)
            {
                L[i] = arr[l + i];
            }
            for (int j=0; j<n2; ++j)
            {
                R[j] = arr[m + 1+ j];
            }
            int i = 0, j = 0;
            int k = l;
            while (i < n1 && j < n2)
            {
                if (L[i] <= R[j])
                {
                    arr[k] = L[i];
                    i++;
                }
                else
                {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }
            while (i < n1)
            {
                arr[k] = L[i];
                i++;
                k++;
            }
            while (j < n2)
            {
                arr[k] = R[j];
                j++;
                k++;
            }
        }
    static void printArray(double arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    static void AscendingOrder(double[] arr)
    {
        sort(arr,0,arr.length-1);
        printArray(arr);
    }
    static void DescendingOrder(double[] arr)
    {
        double descendingArr[] = new double[arr.length]; 
        for (int i = 0; i < arr.length; i++) 
        {
            descendingArr[i] = arr[arr.length - 1-i];    
        }
        printArray(descendingArr);
    }
    static int countTrue(Company[] arr){
        int count =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].stockIncrease == true)
                count++;
        }
        return count;
    }
    static int binarySearch(double arr[], int l, int r, double x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }

    
    
    
    
    
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.println("enter the no of companies");
        /*1.get values in obj array of class Company
        2.switch
        3.make temp array from data and sort that temp array
        4.reverse that sorted array
        5.count true and false
        6.search method on temp arr.
        */
        int n = sc.nextInt();
        Company[] arr = new Company[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Company();
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Enter current stock price of the company "+i+1);
            double share = sc.nextDouble();
            System.out.println("Whether company's stock price rose today compare to yesterday?");
            boolean a =sc.nextBoolean();
            arr[i].setStockIncrease(a);
            arr[i].setStockPrice(share);
        }
        double temp_arr[]  = new double[arr.length];
        //temp_arr is the temporary array which contains share price(double)
        for (int index = 0; index < arr.length; index++) 
        {
                temp_arr[index] = arr[index].stockPrice;
        }
        int key = -1;
        int true_ = countTrue(arr);//count those whose entry are true
        int false_ = arr.length - true_;//count those whose entry are false
        while(key!=0)
        {
            System.out.println("");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Enter the operation that you want to perform");
            System.out.println("1. Display the companies stock prices in ascending order");
            System.out.println("2. Display the companies stock prices in descending order");
            System.out.println("3. Display the total no of companies for which stock prices rose today");
            System.out.println("4. Display the total no of companies for which stock prices declined today");
            System.out.println("5. Search a specific stock price");
            System.out.println("6. press 0 to exit");
            System.out.println("-------------------------------------------------------------");
            System.out.println("");
            key = sc.nextInt();
            if(key == 0)
            {
                System.out.println("Exited successfully");    
                break;
            }
            switch (key) 
            {
                case 1:
                    System.out.println("Stock prices in ascending order are :");
                    AscendingOrder(temp_arr);
                    //temp_arr is sorted ascending
                    break;  
                case 2:      
                    System.out.println("Stock prices in descending order are :");
                    //temp_arr is still ascending
                    DescendingOrder(temp_arr);
                    break;
                case 3:
                    System.out.print("Total no of companies whose stock price rose today : ");
                    System.out.print(true_);
                    break;
                case 4:
                    System.out.print("Total no of companies whose stock price declined today : ");
                    System.out.print(false_);
                    break;
                case 5:
                    System.out.println("enter the key value");
                    double x = sc.nextDouble();
                    int res = binarySearch(temp_arr,0,temp_arr.length-1,x);
                    if(res == -1){
                        System.out.println("Stock of value "+x+" is not present ");
                    }
                    else{
                        System.out.println("Stock of value "+x+" is present ");
                    }
                    break;
                default:
                    System.out.println("invalid output");
                    key = 0;
                    break;
            }
        }
    }
}
