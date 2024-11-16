package network.v2

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket

fun main() {

    val serverSocket = ServerSocket(12345)

    println(serverSocket)
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