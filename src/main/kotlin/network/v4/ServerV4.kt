package network.v4

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket

// 연결될때마다, 소켓별 스레드 생성
fun main() {

    val serverSocket = ServerSocket(12345)

    while (true) {
        val socket = serverSocket.accept()
        Thread {
            println("connection established $socket")

            val inputStream = DataInputStream(socket.getInputStream())
            val outputStream = DataOutputStream(socket.getOutputStream())

            while (true) {
                val received = inputStream.readUTF()
                println("client -> server: $received")

                val send = received + "world"
                outputStream.writeUTF(send)
                println("server -> client: $send")
            }

            inputStream.close()
            outputStream.close()
            socket.close()
            println("closing the connection")
        }.start()
    }

}
