import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Interface untuk mendefinisikan perilaku pencarian
interface Searchable<T> {
    int compare(T value);
}

public class Genericbinarysearch {

    // Metode binary search generik
    public static <T> int binarySearch(T[] array, Searchable<T> searchable) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int comparison = searchable.compare(array[mid]);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEM PENCARIAN DATASET ===");
        System.out.println("Pilih jenis data yang ingin dicari:");
        System.out.println("1. Integer");
        System.out.println("2. Double");
        System.out.println("3. String");
        System.out.print("Pilihan Anda (1-3): ");

        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        switch (pilihan) {
            case 1:
                // Pencarian nilai Integer
                Integer[] dataInteger = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

                System.out.println("\nData Integer: " + Arrays.toString(dataInteger));
                System.out.print("Masukkan nilai integer yang dicari: ");
                int targetInt = scanner.nextInt();

                int indexInt = binarySearch(dataInteger, new Searchable<Integer>() {
                    @Override
                    public int compare(Integer value) {
                        return targetInt - value; // Negatif jika target < value, 0 jika sama, positif jika target > value
                    }
                });

                if (indexInt != -1) {
                    System.out.println("Nilai " + targetInt + " ditemukan pada indeks " + indexInt);
                } else {
                    System.out.println("Nilai " + targetInt + " tidak ditemukan dalam dataset");
                }
                break;

            case 2:
                // Pencarian nilai Double
                Double[] dataDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9};

                System.out.println("\nData Double: " + Arrays.toString(dataDouble));
                System.out.print("Masukkan nilai double yang dicari: ");
                double targetDouble = scanner.nextDouble();

                int indexDouble = binarySearch(dataDouble, new Searchable<Double>() {
                    @Override
                    public int compare(Double value) {
                        return Double.compare(targetDouble, value);
                    }
                });

                if (indexDouble != -1) {
                    System.out.println("Nilai " + targetDouble + " ditemukan pada indeks " + indexDouble);
                } else {
                    System.out.println("Nilai " + targetDouble + " tidak ditemukan dalam dataset");
                }
                break;

            case 3:
                // Pencarian nilai String
                String[] dataString = {"alpha", "beta", "delta", "gamma", "omega", "sigma", "theta", "zeta"};
                Arrays.sort(dataString); // Pastikan array terurut

                System.out.println("\nData String: " + Arrays.toString(dataString));
                System.out.print("Masukkan string yang dicari: ");
                String targetString = scanner.nextLine();

                int indexString = binarySearch(dataString, new Searchable<String>() {
                    @Override
                    public int compare(String value) {
                        return targetString.compareTo(value);
                    }
                });

                if (indexString != -1) {
                    System.out.println("String \"" + targetString + "\" ditemukan pada indeks " + indexString);
                } else {
                    System.out.println("String \"" + targetString + "\" tidak ditemukan dalam dataset");
                }
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }

        scanner.close();
    }
}
