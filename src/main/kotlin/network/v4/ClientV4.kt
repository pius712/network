package network.v4

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import java.util.*

fun main() {

    val PORT = 12345;

    // client 의 포트는 잘 할당하지 않음,
    val socket = Socket("localhost", PORT)

//    socket.getOutputStream()
//    socket.getInputStream()

    val dataInputStream = DataInputStream(socket.getInputStream())
    val dataOutputStream = DataOutputStream(socket.getOutputStream())

    val scanner = Scanner(System.`in`)
    while (true) {
        val sendMessage = scanner.nextLine()
        if (sendMessage == "exit") break
        dataOutputStream.writeUTF(sendMessage)
        println("client -> server: $sendMessage")

        val receivedMessage = dataInputStream.readUTF()
        println("server -> client: $receivedMessage")

    }

    dataInputStream.close()
    dataOutputStream.close()
    socket.close()
    println("closing the connection")
}