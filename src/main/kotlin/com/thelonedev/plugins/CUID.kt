package com.thelonedev.plugins

import java.net.InetAddress
import java.time.Instant


class CUID {
    companion object {
        private const val BASE = 36
        private val FINGERPRINT = getFingerPrint()
        private var counter: Int = 0

        fun generateCUID(): String {
            val timestamp: String = getTimestamp()
            val counter: String = getCounter()
            val fingerprint: String = FINGERPRINT
            val randomString1: String = getRandomString()
            val randomString2: String = getRandomString()

            return "c$timestamp$counter$fingerprint$randomString1$randomString2"
        }

        // currentTimeMillis in base36 format
        private fun getTimestamp(): String = Instant.now().toEpochMilli().toString(BASE)

        // Prevent same-machine collisions
        private fun getCounter(): String {
            counter = if (counter < Int.MAX_VALUE) counter+1 else 0
            return pad(counter.toString(BASE), 4)
        }

        // A few chars to generate distinct ids for different
        // clients (so different computers are far less
        // likely to generate the same id)
        private fun getFingerPrint(): String {
            val pid: String = ProcessHandle.current().pid().toString(BASE)
            val hostname: String = InetAddress.getLocalHost().hostName
            val acc = hostname.codePoints().reduce(0) { acc, code -> acc + code }.toString(BASE)
            return pad(pid, 2) + pad(acc,2)
        }

        // Grab some chars from Math.random()
        private fun getRandomString(): String {
            val randomInt = (Math.random() * 1000000000).toInt().toString(BASE)
            return pad(randomInt, 4)
        }

        // Fill in prefix '0' to match required string length
        // or in case input string is longer, extract only n last characters
        private fun pad(input: String, length: Int): String {
            return if (input.length < length) "0".repeat(length - input.length) + input else input.takeLast(length)
        }
    }
}
