package network.v1

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket


fun main() {

    val PORT = 12345;

    // client 의 포트는 잘 할당하지 않음,
    val socket = Socket("localhost", PORT)

//    socket.getOutputStream()
//    socket.getInputStream()

    val dataInputStream = DataInputStream(socket.getInputStream())
    val dataOutputStream = DataOutputStream(socket.getOutputStream())

    val sendMessage = "hello"
    dataOutputStream.writeUTF(sendMessage)
    println("client -> server: $sendMessage")

    val receivedMessage = String(dataInputStream.readBytes(), Charsets.UTF_8)
    println("server -> client: $receivedMessage")

    println("closing the connection")
    dataInputStream.close()
    dataOutputStream.close()
    socket.close()
}