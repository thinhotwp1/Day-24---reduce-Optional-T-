import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 1. identity = 0 (giá trị khởi tạo của tổng)
        // 2. accumulator = (subtotal, element) -> subtotal + element
        int sum = numbers.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);

        System.out.println("Tổng: " + sum); // Kết quả: 15

        // Cách viết ngắn hơn dùng Method Reference
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Tổng 2: " + sum2); // Kết quả: 15

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // 1. identity = "" (chuỗi rỗng)
        // 2. accumulator = (longest, current) ->
        String longestName = names.stream().reduce(names.getFirst(),(longName, currentName) -> {
            if(currentName.length() > names.get(0).length()) {
                return currentName;
            }
            return longName;
        });

        System.out.println("Tên dài nhất: " + longestName); // Kết quả: Charlie

        // KHÔNG có giá trị khởi tạo
        Optional<Integer> optionalSum = numbers.stream()
                .reduce(Integer::sum);

        // Chúng ta phải xử lý trường hợp rỗng
        sum = optionalSum.orElse(0); // Nếu rỗng thì trả về 0
        System.out.println("Tổng (Optional): " + sum); // Kết quả: 15

        List<Integer> emptyList = Arrays.asList();

        Optional<Integer> emptyOptional = emptyList.stream()
                .reduce(Integer::sum);

        System.out.println("Stream có rỗng không: " + emptyOptional.isEmpty()); // Kết quả: true
        int sumFromEmpty = emptyOptional.orElse(0);
        System.out.println("Tổng từ Stream rỗng: " + sumFromEmpty); // Kết quả: 0

        Optional<Integer> findMin = numbers.stream().min(Integer::compareTo);
        System.out.println("Min: " + findMin.get());
    }
}