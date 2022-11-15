open class NumberConverter {

    object ConvertToRussian {
        private const val none = ""

        fun convert(number: Int): String {
            var convertedValue = String()

            when(number.toString().length) {
                1 -> convertedValue = russianUnitsRepresent(number)
                2 -> convertedValue = defineTwoDigitNumber(number)
                3 -> convertedValue = "${russianHundredsRepresent(number.toString()[0].digitToInt())} ${defineTwoDigitNumber(number.toString().substring(1).toInt())}"
                4 -> convertedValue = defineFourDigitNumber(number)
                5 -> convertedValue = defineFiveDigitNumber(number)
                6 -> convertedValue = defineSixDigitNumber(number)
            }

            return convertedValue
        }

        private fun defineTwoDigitNumber(number: Int): String {
            if (number.toString().length == 1) return russianUnitsRepresent(number)
            var convertedPiece: String
            convertedPiece = russianDozensRepresent(number)
            if (convertedPiece == none)
                convertedPiece = "${russianDozensRepresent((number.toString()[0]+"0").toInt())} ${russianUnitsRepresent(number.toString()[1].digitToInt())}"
            return convertedPiece
        }

        private fun defineFourDigitNumber(number: Int): String {
            return "${russianUnitsThousandRepresent(number.toString()[0].digitToInt())}${russianThousandSyntax(number.toString()[0].digitToInt())}${russianHundredsRepresent(number.toString()[1].digitToInt())} ${defineTwoDigitNumber(number.toString().substring(2).toInt())}"
        }

        private fun defineFiveDigitNumber(number: Int): String {
            val syntaxPiece = if (number.toString().substring(0, 2).toInt() in 10..19) " тысяч "
            else russianThousandSyntax(number.toString()[1].digitToInt())

            return "${defineTwoDigitNumber(number.toString().substring(0, 2).toInt())}$syntaxPiece${russianHundredsRepresent(number.toString()[2].digitToInt())} ${defineTwoDigitNumber(number.toString().substring(3).toInt())}"
        }

        private fun defineSixDigitNumber(number: Int): String {
            val convertedStartPiece = russianHundredsRepresent(number.toString()[0].digitToInt())

            val syntaxPiece = if (number.toString().substring(1, 3).toInt() in 10..19 || number.toString().substring(1, 3).toInt() == 0) " тысяч "
            else russianThousandSyntax(number.toString()[2].digitToInt())

            var convertedEndPiece = none

            val secondPieceValue = number.toString().substring(1).toInt()

            when(secondPieceValue.toString().length) {
                1 -> convertedEndPiece = "$syntaxPiece${russianUnitsRepresent(secondPieceValue)}"
                2 -> convertedEndPiece =  "$syntaxPiece${defineTwoDigitNumber(secondPieceValue)}"
                3 -> convertedEndPiece = "$syntaxPiece${russianHundredsRepresent(secondPieceValue.toString()[0].digitToInt())} ${defineTwoDigitNumber(secondPieceValue.toString().substring(1).toInt())}"
                4 -> convertedEndPiece = " ${defineFourDigitNumber(secondPieceValue)}"
                5 -> convertedEndPiece = " ${defineTwoDigitNumber(number.toString().substring(1, 3).toInt())}$syntaxPiece${russianHundredsRepresent(number.toString()[3].digitToInt())} ${defineTwoDigitNumber(number.toString().substring(4).toInt())}"
            }

            return "$convertedStartPiece$convertedEndPiece"
        }

        private fun russianUnitsRepresent(number: Int): String {
            when(number) {
                1 -> return "один"
                2 -> return "два"
                3 -> return "три"
                4 -> return "четыре"
                5 -> return "пять"
                6 -> return "шесть"
                7 -> return "семь"
                8 -> return "восемь"
                9 -> return "девять"
            }
            return none
        }

        private fun russianUnitsThousandRepresent(number: Int): String {
            when(number) {
                1 -> return "одна"
                2 -> return "две"
            }
            return russianUnitsRepresent(number)
        }

        private fun russianThousandSyntax(number: Int): String {
            when(number) {
                0 -> return " тысяч "
                1 -> return " тысяча "
                2 -> return " тысячи "
                3 -> return " тысячи "
                4 -> return " тысячи "
                5 -> return " тысяч "
                6 -> return " тысяч "
                7 -> return " тысяч "
                8 -> return " тысяч "
                9 -> return " тысяч "
            }
            return none
        }

        private fun russianDozensRepresent(number: Int): String {
            when(number) {
                10 -> return "десять"
                11 -> return "одиннадцать"
                12 -> return "двенадцать"
                13 -> return "тринадцать"
                14 -> return "четырнадцать"
                15 -> return "пятнадцать"
                16 -> return "шестнадцать"
                17 -> return "семнаднать"
                18 -> return "восемнадцать"
                19 -> return "девятнадцать"
                20 -> return "двадцать"
                30 -> return "тридцать"
                40 -> return "сорок"
                50 -> return "пятьдесят"
                60 -> return "шестьдесят"
                70 -> return "семьдесят"
                80 -> return "восемьдесят"
                90 -> return "девяносто"
            }
            return none
        }

        private fun russianHundredsRepresent(number: Int): String {
            when (number) {
                1 -> return "сто"
                2 -> return "двести"
                3 -> return "триста"
                4 -> return "четыреста"
                5 -> return "пятьсот"
                6 -> return "шестьсот"
                7 -> return "семьсот"
                8 -> return "восемьсот"
                9 -> return "девятьсот"
            }
            return none
        }
    }
}