package network.v1

import java.io.DataInputStream
import java.net.ServerSocket

fun main() {

    val serverSocket = ServerSocket(12345)

    val socket = serverSocket.accept()
    println("connection established $socket")

    val inputStream = DataInputStream(socket.getInputStream())
    //    val outputStream = DataOutputStream(socket.getOutputStream())
    val outputStream = socket.getOutputStream()

    val received = inputStream.readUTF()
    println("client -> server: $received")

    val send = received + "world"
    outputStream.write(send.toByteArray())
    println("server -> client: $send")

    println("closing the connection")
    inputStream.close()
    outputStream.close()
    socket.close()
}