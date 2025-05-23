import java.time.LocalDate; // Menggunakan kelas untuk menyimpan dan mengelola tanggal
import java.time.format.DateTimeFormatter; // Memformat tanggal menjadi bentuk yang lebih mudah dibaca
import java.util.Scanner; // Menggunakan Scanner untuk membaca input pengguna

// Kelas untuk menyimpan informasi acara
class Acara { 
    LocalDate tanggal; // Menyimpan tanggal acara
    String nama; // Nama acara
    String lokasi; // Lokasi acara
    String deskripsi; // Penjelasan tentang acara

    // Constructor untuk membuat objek Acara dengan data yang diberikan
    public Acara(LocalDate tanggal, String nama, String lokasi, String deskripsi) { 
        this.tanggal = tanggal; 
        this.nama = nama; 
        this.lokasi = lokasi; 
        this.deskripsi = deskripsi; 
    }

    // Metode untuk menampilkan informasi acara dengan format yang lebih rapi
    @Override
    public String toString() { 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format tanggal menjadi "10 Mei 2025"
        return String.format("Tanggal: %s\nNama Acara: %s\nLokasi: %s\nDeskripsi: %s",
                            tanggal.format(formatter), nama, lokasi, deskripsi); 
    }
}

// Kelas utama untuk sistem pencarian acara
public class PencarianAcara { 
    public static void main(String[] args) { 
        // Membuat daftar acara yang sudah diurutkan berdasarkan tanggal
        Acara[] jadwalAcara = { 
            new Acara(LocalDate.of(2025, 5, 10), "Workshop Java", "Ruang Pelatihan A", "Workshop dasar pemrograman Java"),
            new Acara(LocalDate.of(2025, 5, 15), "Seminar AI", "Aula Utama", "Seminar tentang perkembangan Artificial Intelligence"),
            new Acara(LocalDate.of(2025, 5, 20), "Kompetisi Coding", "Lab Komputer", "Kompetisi coding untuk mahasiswa"),
            new Acara(LocalDate.of(2025, 5, 25), "Tech Talk", "Auditorium", "Diskusi tentang teknologi terbaru"),
            new Acara(LocalDate.of(2025, 6, 1), "Career Fair", "Gedung Serbaguna", "Pameran karir bidang IT"),
            new Acara(LocalDate.of(2025, 6, 5), "Webinar Cloud Computing", "Online", "Webinar tentang teknologi cloud"),
            new Acara(LocalDate.of(2025, 6, 10), "Hackathon", "Co-Working Space", "Hackathon 24 jam"),
            new Acara(LocalDate.of(2025, 6, 15), "Workshop Database", "Ruang Pelatihan B", "Workshop database SQL dan NoSQL"),
            new Acara(LocalDate.of(2025, 6, 20), "Game Development Talk", "Ruang Multimedia", "Diskusi tentang pengembangan game")
        };

        Scanner scanner = new Scanner(System.in); // Membuat Scanner untuk membaca input pengguna

        // Tampilan awal program
        System.out.println("=== SISTEM PENCARIAN ACARA ===");
        System.out.println("Format tanggal: yyyy-MM-dd (contoh: 2025-05-20)");
        System.out.print("Masukkan tanggal yang ingin dicari: "); // Meminta pengguna memasukkan tanggal
        String tanggalInput = scanner.nextLine(); // Membaca input tanggal dari pengguna

        try { 
            // Konversi input ke format LocalDate
            LocalDate tanggalCari = LocalDate.parse(tanggalInput); 

            // Menjalankan pencarian acara dengan binary search
            int index = cariAcaraByTanggal(jadwalAcara, tanggalCari); 

            System.out.println("\nHASIL PENCARIAN:");
            if (index != -1) { 
                System.out.println("Acara ditemukan pada tanggal " + tanggalInput + "!");
                System.out.println(jadwalAcara[index]); // Menampilkan informasi acara
            } else { 
                System.out.println("Tidak ada acara yang terjadwal pada tanggal " + tanggalInput + ".");
            }
        } catch (Exception e) { 
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd."); 
        }

        scanner.close(); // Menutup Scanner agar tidak boros memori
    }

    // Metode pencarian acara berdasarkan tanggal menggunakan binary search
    public static int cariAcaraByTanggal(Acara[] jadwalAcara, LocalDate tanggal) { 
        int low = 0; // Batas bawah pencarian
        int high = jadwalAcara.length - 1; // Batas atas pencarian

        while (low <= high) { // Selama masih ada data yang bisa diperiksa
            int mid = low + (high - low) / 2; // Menentukan titik tengah dari daftar acara

            // Jika tanggal acara sama dengan tanggal yang dicari, kembalikan indeksnya
            if (jadwalAcara[mid].tanggal.isEqual(tanggal)) { 
                return mid; 
            }

            // Jika tanggal yang dicari lebih awal, cari di bagian kiri
            if (jadwalAcara[mid].tanggal.isAfter(tanggal)) { 
                high = mid - 1; 
            }
            // Jika tanggal yang dicari lebih akhir, cari di bagian kanan
            else { 
                low = mid + 1; 
            }
        }

        return -1; // Jika tidak ditemukan, kembalikan -1
    }
}
