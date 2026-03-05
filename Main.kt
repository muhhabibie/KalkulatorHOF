// Data class tambahan untuk merapikan struktur pengujian
data class Contoh(val a: Int, val b: Int, val op: (Int, Int) -> Int, val simbol: String)

// A. Implementasi Inti (Wajib)
    // Higher-Order Function yang menerima fungsi sebagai parameter
fun applyOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main() {
    // 4 Operasi menggunakan lambda expression
    val add: (Int, Int) -> Int = { x, y -> x + y }
    val sub: (Int, Int) -> Int = { x, y -> x - y }
    val mul: (Int, Int) -> Int = { x, y -> x * y }

    // Penanganan division by zero: Melempar exception dengan pesan yang jelas
    val div: (Int, Int) -> Int = { x, y ->
        if (y == 0) throw ArithmeticException("ERROR (division by zero)") else x / y
    }

    // B. Program Demo (Wajib)
        // List berisi minimal 6 uji kasus, termasuk angka negatif dan pembagian nol
    val tests = listOf(
        Contoh(10, 5, add, "+"),      // Operasi normal
        Contoh(10, 5, sub, "-"),      // Operasi normal
        Contoh(10, 5, mul, "*"),      // Operasi normal
        Contoh(10, 5, div, "/"),      // Operasi normal
        Contoh(10, 0, div, "/"),      // Uji kasus: Pembagian dengan nol
        Contoh(-8, 2, div, "/")       // Uji kasus: Angka negatif
    )

    println("=== Demo Kalkulator Operasi ===")

    // Iterasi untuk mengeksekusi dan menampilkan output yang rapi
    for ((a, b, op, simbol) in tests) {
        try {
            val result = applyOperation(a, b, op)
            println("$a $simbol $b = $result")
        } catch (e: ArithmeticException) {
            println("$a $simbol $b = ${e.message}")
        }
    }
}
