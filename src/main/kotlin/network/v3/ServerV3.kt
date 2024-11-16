package network.v3

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket

// thread 2개 생성. 2개 클라이언트 처리
fun main() {

    val serverSocket = ServerSocket(12345)

    println(serverSocket)
    val thread = Thread {
        handle(serverSocket)
    }
    val thread2 = Thread {
        handle(serverSocket)
    }
    thread.start()
    thread2.start()
}

private fun handle(serverSocket: ServerSocket) {
    val socket = serverSocket.accept()
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
}