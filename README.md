# TP6DPBO2025C1
Saya, Narendra Ridha Baihaqi dengan NIM 2308882, mengerjakan Tugas Praktikum 6 dalam mata kuliah DPBO dengan sebaik-baiknya demi keberkahan-Nya.
Saya berjanji tidak melakukan kecurangan sebagaimana yang telah dispesifikasikan. Aamiin.

---

# Flappy Bird Java – Tugas Pemrograman Berbasis Objek

Proyek ini adalah implementasi sederhana dari game **Flappy Bird** menggunakan bahasa pemrograman Java dan library **Swing**. Game ini dibuat untuk memenuhi tugas pada mata kuliah *Pemrograman Berbasis Objek (PBO)*. Game ini menampilkan burung yang harus dikendalikan agar tidak menabrak pipa dan tidak jatuh ke tanah. Pemain bisa menekan tombol **spasi** untuk membuat burung terbang dan harus terus menghindari rintangan sebanyak mungkin agar skor terus bertambah.

Game ini dibuat dalam bentuk desktop application berbasis GUI (Graphical User Interface) dan dibangun dengan pendekatan berbasis objek.

---

## Struktur File dan Penjelasan

### 1. `App.java`
File utama yang menjalankan program. Di sinilah **frame utama game dibuat** dan ditampilkan. Program akan menampilkan form awal (`Start.java`) sebelum masuk ke game `FlappyBird`.

### 2. `Start.java`
Form awal (tampilan pembuka) yang berisi tombol **"Mulai Game"**. Ketika tombol ini ditekan, form akan ditutup dan game Flappy Bird akan dimulai. Start ini juga menampilkan gambar background sebagai hiasan antarmuka.

### 3. `FlappyBird.java`
Ini adalah inti dari game. Di sini seluruh mekanisme game diatur, mulai dari:
- Menggambar elemen (background, burung, pipa, skor)
- Mengatur pergerakan burung dan pipa
- Deteksi tabrakan
- Sistem skor
- Logika game over dan restart
File ini merupakan turunan dari `JPanel` dan memiliki timer untuk animasi dan update game.

### 4. `RoundedBorder.java`
Kelas pembantu untuk membuat tombol dengan **sudut tumpul (rounded corners)**. Digunakan di tombol pada `Start.java` agar tampilan tombol lebih menarik dan modern.

### 5. `Pipe.java`
Kelas yang merepresentasikan objek **pipa** dalam game. Berisi properti seperti posisi, ukuran, gambar, dan kecepatan gerak pipa. Juga menyimpan status apakah pipa sudah dilewati oleh burung (untuk menambah skor).

### 6. `Player.java`
Kelas yang merepresentasikan **burung** yang dikendalikan pemain. Menyimpan data posisi, ukuran, kecepatan, serta gambar burung. Class ini juga mengatur bagaimana burung bergerak secara vertikal berdasarkan gravitasi dan input dari pemain.

---

## Cara Memulai Game
1. Jalankan `App.java`.
2. Tekan tombol **"Mulai Game"** pada tampilan awal.
3. Tekan tombol **spasi** untuk membuat burung terbang.
4. Hindari tabrakan dengan pipa dan jangan jatuh ke bawah.
5. Tekan **R** untuk me-restart game saat game over.

---

## Dokumentasi
![image](https://github.com/user-attachments/assets/75d4afe9-ba1a-45f5-9b1b-d615a965ef7e)

![image](https://github.com/user-attachments/assets/9a663c05-e4b0-43d1-9547-6faa97d64b42)

![Uploading image.png…]()

