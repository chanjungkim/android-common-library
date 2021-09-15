package io.monolabs.common.utils

object KoreanUtil {
    @Throws(IllegalArgumentException::class)
    fun buildEunJosaFromKor(targetStr: String): String {
        var josa = ""
        val lastChar = targetStr[targetStr.length - 1]
        require(!(lastChar.toInt() < 0xAC00 || lastChar.toInt() > 0xD7A3)) { "It's not Korean Alphabet. Out of range." }
        josa = if ((lastChar.toInt() - 0xAC00) % 28 > 0) "은" else "는"
        return targetStr + josa
    }

    @Throws(IllegalArgumentException::class)
    fun buildEunJosaFromEng(targetStr: String): String {
        return when (targetStr[targetStr.length - 1].toUpperCase()) {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'O', 'P', 'Q', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                "${targetStr}는"
            }
            'L', 'M', 'R' -> {
                "${targetStr}은"
            }
            else -> {
                "$targetStr"
            }
        }
    }

    @Throws(IllegalArgumentException::class)
    fun buildEunJosaFromNum(targetStr: String): String {
        return when (targetStr[targetStr.length - 1]) {
            '2', '4', '5', '9' -> {
                "${targetStr}는"
            }
            '0', '1', '3', '6', '7', '8' -> {
                "${targetStr}은"
            }
            else -> {
                "$targetStr"
            }
        }
    }

    @Throws(IllegalArgumentException::class)
    fun buildAutoEunJosa(targetStr: String): String {
        var josa = ""
        val lastChar = targetStr[targetStr.length - 1]
        val builtWord = if (!(lastChar.toInt() < 0xAC00 || lastChar.toInt() > 0xD7A3)) {
            buildEunJosaFromKor(targetStr)
        }else if(lastChar.toInt() in 65..90){
            buildEunJosaFromEng(targetStr)
        }else if(lastChar.toInt() in 48..57){
            buildEunJosaFromNum(targetStr)
        }else{
            "${targetStr}는"
        }

        return builtWord
    }

    @Throws(IllegalArgumentException::class)
    fun buildGaJosa(targetStr: String): String {
        var josa = ""
        val lastChar = targetStr[targetStr.length - 1]
        require(!(lastChar.toInt() < 0xAC00 || lastChar.toInt() > 0xD7A3)) { "It's not Korean Alphabet. Out of range." }
        josa = if ((lastChar.toInt() - 0xAC00) % 28 > 0) "이가" else "가"
        return targetStr + josa
    }

    @Throws(IllegalArgumentException::class)
    fun buildEulJosa(targetStr: String): String {
        var josa = ""
        val lastChar = targetStr[targetStr.length - 1]
        require(!(lastChar.toInt() < 0xAC00 || lastChar.toInt() > 0xD7A3)) { "It's not Korean Alphabet. Out of range." }
        josa = if ((lastChar.toInt() - 0xAC00) % 28 > 0) "을" else "를"
        return targetStr + josa
    }

    @Throws(IllegalArgumentException::class)
    fun getEunJosaFromKor(targetStr: String): String {
        var josa = ""
        val lastChar = targetStr[targetStr.length - 1]
        require(!(lastChar.toInt() < 0xAC00 || lastChar.toInt() > 0xD7A3)) { "It's not Korean Alphabet. Out of range." }
        josa = if ((lastChar.toInt() - 0xAC00) % 28 > 0) "은" else "는"
        return josa
    }

    @Throws(IllegalArgumentException::class)
    fun getEunJosaFromEng(targetStr: String): String {
        return when (targetStr[targetStr.length - 1].toUpperCase()) {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'O', 'P', 'Q', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                "는"
            }
            'L', 'M', 'R' -> {
                "은"
            }
            else -> {
                " "
            }
        }
    }

    /**
     * one, two, three, ...
     */
    @Throws(IllegalArgumentException::class)
    fun getEunJosaFromNum(targetStr: String): String {
        return when (targetStr[targetStr.length - 1]) {
            '0', '2', '3', '4', '5', '6' -> {
                "는"
            }
            '1', '7', '8', '9' -> {
                "은"
            }
            else -> {
                " "
            }
        }
    }
}