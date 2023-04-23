import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        int[] s = dataInput();
        int n = s.length;
        long begin = Calendar.getInstance().getTimeInMillis();
        // DuyetDayCon(s, n);
        //chiaDeTri(s, n);
        quyHoachDong(s);
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Executed Time: " + (end - begin) + " ms");

    }

    public static void DuyetDayCon(int[] s, int n) {
        int max_sum = 0;
        int start_index = -1;
        int end_index = -1;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += s[j];
                if (sum > max_sum) {
                    max_sum = sum;
                    start_index = i;
                    end_index = j;
                }
            }
        }
        // In kết quả
        System.out.println("Tong lon nhat: " + max_sum);
        System.out.print("Day con tuong ung: ");
        for (int i = start_index; i <= end_index; i++) {
            System.out.print(s[i] + " ");
        }
    }
    public static void quyHoachDong(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // Tạo mảng dp để lưu trữ dãy con liên tiếp có tổng lớn nhất tại vị trí i
    
        // Khởi tạo giá trị đầu tiên
        dp[0] = nums[0];
        int maxSum = dp[0];
        int end_index = 0;
        // Tính toán dãy con liên tiếp có tổng lớn nhất tại vị trí i bằng cách so sánh
        // giá trị của số đang xét và tổng của số đó với dãy con liên tiếp có tổng lớn nhất tại vị trí i-1
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (maxSum < dp[i]) {
                maxSum = dp[i];
                end_index = i;
            }
        }
        System.out.println("MaxSum = "+maxSum);
        System.out.println("end_index: "+end_index);
        
    }
// Chia để trị
    public static void chiaDeTri(int[] s, int n) {
        int start_index = -1;
        int end_index = -1;
        int max_sum = timConMax(s, 0, n - 1);
        // In kết quả
        System.out.println("Tổng lớn nhất: " + max_sum);
    }

    public static int timConMax(int[] a, int from, int to) {
        if (from == to) {
            return a[from];
        }
        int mid = (from + to) / 2;
        int left_sum = timConMax(a, from, mid);
        int right_sum = timConMax(a, mid + 1, to);
        int crossing_sum = findMaxCrossingSubarraySum(a, from, mid, to);
        return Math.max(Math.max(right_sum, left_sum), crossing_sum);

    }

    public static int findMaxCrossingSubarraySum(int[] a, int from, int mid, int to) {
        int left_sum = -999;
        int sum = 0;
        for (int i = mid; i >= from; i--) {
            sum = sum + a[i];
            if (sum > left_sum)
                left_sum = sum;
        }
        int right_sum = -999;
        sum = 0;
        for (int i = mid + 1; i <= to; i++) {
            sum = sum + a[i];
            if (sum > right_sum)
                right_sum = sum;
        }
        return left_sum + right_sum;
    }

    public static int[] dataInput() {

        String fileName = "F:/HocTap/UDThuatToan/ontap/timDayConMax/DuyetDayCon/mydata.txt";
        int[] array = new int[1];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                if (row == 0) {
                    int n = Integer.parseInt(values[0]);
                    array = new int[n];
                    row++;
                }
                for (int col = 0; col < values.length; col++) {
                    array[col] = Integer.parseInt(values[col]);
                }
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
            return array;
        }
    }
}
