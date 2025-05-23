import java.util.Arrays; // Mengimpor kelas Arrays untuk manipulasi array
import java.util.Comparator; // Mengimpor kelas Comparator untuk perbandingan
import java.util.Scanner; // Mengimpor kelas Scanner untuk input dari pengguna

// Interface untuk mendefinisikan perilaku pencarian
interface Searchable<T> { 
    int compare(T value); // Metode abstrak untuk membandingkan nilai
}

public class Genericbinarysearch { 

    // Metode binary search generik
    public static <T> int binarySearch(T[] array, Searchable<T> searchable) { 
        int low = 0; // Batas bawah pencarian
        int high = array.length - 1; // Batas atas pencarian

        while (low <= high) { // Loop selama batas bawah <= batas atas
            int mid = low + (high - low) / 2; // Menghitung indeks tengah

            int comparison = searchable.compare(array[mid]); // Membandingkan nilai di indeks tengah

            if (comparison == 0) { 
                return mid; // Jika sama, kembalikan indeks ditemukan
            } else if (comparison < 0) { 
                high = mid - 1; // Jika lebih kecil, geser batas atas ke kiri
            } else { 
                low = mid + 1; // Jika lebih besar, geser batas bawah ke kanan
            }
        }
        return -1; // Jika tidak ditemukan, kembalikan -1
    }

    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input

        System.out.println("=== SISTEM PENCARIAN DATASET ==="); // Menampilkan judul program
        System.out.println("Pilih jenis data yang ingin dicari:"); // Menampilkan pilihan tipe data
        System.out.println("1. Integer"); 
        System.out.println("2. Double"); 
        System.out.println("3. String"); 
        System.out.print("Pilihan Anda (1-3): "); // Meminta input dari pengguna

        int pilihan = scanner.nextInt(); // Membaca input pilihan pengguna
        scanner.nextLine(); // Membersihkan buffer input

        switch (pilihan) { 
            case 1: // Jika pengguna memilih Integer
                Integer[] dataInteger = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // Dataset Integer

                System.out.println("\nData Integer: " + Arrays.toString(dataInteger)); // Menampilkan dataset
                System.out.print("Masukkan nilai integer yang dicari: "); 
                int targetInt = scanner.nextInt(); // Input target pencarian

                int indexInt = binarySearch(dataInteger, new Searchable<Integer>() { 
                    @Override
                    public int compare(Integer value) { 
                        return targetInt - value; // Perbandingan untuk pencarian
                    }
                });

                if (indexInt != -1) { 
                    System.out.println("Nilai " + targetInt + " ditemukan pada indeks " + indexInt); // Jika ditemukan
                } else { 
                    System.out.println("Nilai " + targetInt + " tidak ditemukan dalam dataset"); // Jika tidak ditemukan
                }
                break; 

            case 2: // Jika pengguna memilih Double
                Double[] dataDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9}; // Dataset Double

                System.out.println("\nData Double: " + Arrays.toString(dataDouble)); // Menampilkan dataset
                System.out.print("Masukkan nilai double yang dicari: "); 
                double targetDouble = scanner.nextDouble(); // Input target pencarian

                int indexDouble = binarySearch(dataDouble, new Searchable<Double>() { 
                    @Override
                    public int compare(Double value) { 
                        return Double.compare(targetDouble, value); // Perbandingan untuk pencarian
                    }
                });

                if (indexDouble != -1) { 
                    System.out.println("Nilai " + targetDouble + " ditemukan pada indeks " + indexDouble); // Jika ditemukan
                } else { 
                    System.out.println("Nilai " + targetDouble + " tidak ditemukan dalam dataset"); // Jika tidak ditemukan
                }
                break; 

            case 3: // Jika pengguna memilih String
                String[] dataString = {"alpha", "beta", "delta", "gamma", "omega", "sigma", "theta", "zeta"}; // Dataset String
                Arrays.sort(dataString); // Mengurutkan dataset

                System.out.println("\nData String: " + Arrays.toString(dataString)); // Menampilkan dataset
                System.out.print("Masukkan string yang dicari: "); 
                String targetString = scanner.nextLine(); // Input target pencarian

                int indexString = binarySearch(dataString, new Searchable<String>() { 
                    @Override
                    public int compare(String value) { 
                        return targetString.compareTo(value); // Perbandingan untuk pencarian
                    }
                });

                if (indexString != -1) { 
                    System.out.println("String \"" + targetString + "\" ditemukan pada indeks " + indexString); // Jika ditemukan
                } else { 
                    System.out.println("String \"" + targetString + "\" tidak ditemukan dalam dataset"); // Jika tidak ditemukan
                }
                break; 

            default: 
                System.out.println("Pilihan tidak valid!"); // Jika input tidak sesuai
        }
        scanner.close(); // Menutup Scanner
    }
}
