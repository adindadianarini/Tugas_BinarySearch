import java.util.Scanner; // Mengimpor kelas Scanner untuk membaca input dari pengguna

// Kelas untuk menyimpan informasi buku
class Buku { 
    String isbn; // Nomor ISBN buku
    String judul; // Judul buku
    String penulis; // Nama penulis buku
    int tahunTerbit; // Tahun terbit buku

    // Konstruktor untuk mengisi data buku
    public Buku(String isbn, String judul, String penulis, int tahunTerbit) { 
        this.isbn = isbn; 
        this.judul = judul; 
        this.penulis = penulis; 
        this.tahunTerbit = tahunTerbit; 
    }

    // Metode untuk menampilkan data buku dalam format yang rapi
    @Override
    public String toString() { 
        return String.format("ISBN: %s\nJudul: %s\nPenulis: %s\nTahun Terbit: %d",
                            isbn, judul, penulis, tahunTerbit); 
    }
}

// Kelas utama untuk pencarian buku
public class PencarianBuku { 
    public static void main(String[] args) { 
        // Daftar buku yang sudah diurutkan berdasarkan ISBN
        Buku[] daftarBuku = { 
            new Buku("9780071606301", "Java: The Complete Reference", "Herbert Schildt", 2007),
            new Buku("9780132222204", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780132778046", "Head First Java", "Kathy Sierra & Bert Bates", 2005),
            new Buku("9780134685991", "Effective Python", "Brett Slatkin", 2019),
            new Buku("9780135957059", "Clean Code", "Robert C. Martin", 2008),
            new Buku("9780137081073", "The Clean Coder", "Robert C. Martin", 2011),
            new Buku("9780262033848", "Introduction to Algorithms", "Cormen, Leiserson, Rivest & Stein", 2009),
            new Buku("9780321356680", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780596009205", "Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2004)
        };

        Scanner scanner = new Scanner(System.in); // Membuat Scanner untuk membaca input pengguna

        System.out.println("=== SISTEM PENCARIAN BUKU PERPUSTAKAAN ===");
        System.out.print("Masukkan nomor ISBN buku yang dicari: "); // Meminta input ISBN buku
        String isbnCari = scanner.nextLine(); // Membaca input pengguna

        // Melakukan pencarian dengan binary search
        int index = cariBukuByISBN(daftarBuku, isbnCari); 

        System.out.println("\nHASIL PENCARIAN:");
        if (index != -1) { 
            System.out.println("Buku ditemukan!"); 
            System.out.println(daftarBuku[index]); // Menampilkan informasi buku yang ditemukan
        } else { 
            System.out.println("Buku dengan ISBN " + isbnCari + " tidak ditemukan."); 
        }

        scanner.close(); // Menutup Scanner agar tidak boros memori
    }

    // Metode untuk mencari buku berdasarkan ISBN dengan binary search
    public static int cariBukuByISBN(Buku[] daftarBuku, String isbn) { 
        int low = 0; // Batas bawah pencarian
        int high = daftarBuku.length - 1; // Batas atas pencarian

        while (low <= high) { // Selama masih ada data yang bisa diperiksa
            int mid = low + (high - low) / 2; // Menentukan titik tengah dari daftar buku

            // Membandingkan ISBN buku di indeks tengah dengan ISBN yang dicari
            int comparison = daftarBuku[mid].isbn.compareTo(isbn); 

            // Jika ISBN sama, kembalikan indeksnya
            if (comparison == 0) { 
                return mid; 
            }

            // Jika ISBN yang dicari lebih kecil, cari di bagian kiri daftar
            if (comparison > 0) { 
                high = mid - 1; 
            }
            // Jika ISBN yang dicari lebih besar, cari di bagian kanan daftar
            else { 
                low = mid + 1; 
            }
        }

        return -1; // Jika buku tidak ditemukan, kembalikan -1
    }
}
