import java.io.Console;
import java.io.DataInputStream;
import java.io.IOException;

public class Main {
    public static void main(String... args) throws Exception {
        {
            DataInputStream in = new DataInputStream(System.in);


            try {
                System.out.println(calc(in.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String calc(String input) throws Exception {

        int result = 0;
        String[] split = input.split(" ");
        int numberValueFirst;
        int numberValueSecond;

        if (split.length > 3) {
            throw new Exception("throws Exception //т.к. ввод более двух операндов");
        }

        if (RomeNum.NONE.isRomeNumber(split[0]) & !RomeNum.NONE.isRomeNumber(split[2])) {
            throw new Exception("throws Exception //т.к. ввод одновременно и римских и арабских чисел  ");
        }
        if (!RomeNum.NONE.isRomeNumber(split[0]) & RomeNum.NONE.isRomeNumber(split[2])) {
            throw new Exception("throws Exception //т.к. ввод одновременно и римских и арабских чисел  ");
        }

        if (RomeNum.NONE.isRomeNumber(split[0]) & RomeNum.NONE.isRomeNumber(split[2])) {
            numberValueFirst = RomeNum.NONE.getArabicNumber(split[0]);
            numberValueSecond = RomeNum.NONE.getArabicNumber(split[2]);

            if (numberValueFirst < 1 | numberValueFirst > 10) {
                throw new Exception("throws Exception //т.к. допустимые числа ввода от 1 до 10 включительно");
            }

            if (numberValueSecond < 1 | numberValueSecond > 10) {
                throw new Exception("throws Exception //т.к. допустимые числа ввода от 1 до 10 включительно");
            }
            if (!ArifmeticAction.PLUS.isArifmeticsAction(split[1])) {
                throw  new Exception("throws Exception //т.к. введен недопустимый операнд");
            }

            if (split[1].equals("+")) {
                result = numberValueFirst + numberValueSecond;
            } else if (split[1].equals("-")) {
                result = numberValueFirst - numberValueSecond;
            } else if (split[1].equals("*")) {
                result = numberValueFirst * numberValueSecond;
            } else if (split[1].equals("/")) {
                result = numberValueFirst / numberValueSecond;
            }

            String countResult = countRomeResult(result);

            if (countResult.isEmpty()){
                throw new Exception("throws Exception //т.к. римские цифры не могут быть отрицательными");
            }

            return countResult;
        } else if (!RomeNum.NONE.isRomeNumber(split[0]) & !RomeNum.NONE.isRomeNumber(split[2])) {


            try {
                numberValueFirst = Integer.parseInt(split[0]);
                numberValueSecond = Integer.parseInt(split[2]);
            }catch (NumberFormatException e){

                numberValueFirst = -1;
                numberValueSecond = -1;
            }

            if (numberValueFirst < 1 | numberValueFirst > 10) {
                throw new Exception("throws Exception //т.к. допустимые числа ввода от 1 до 10 включительно");
            }

            if (numberValueSecond < 1 | numberValueSecond > 10) {
                throw new Exception("throws Exception //т.к. допустимые числа ввода от 1 до 10 включительно");
            }
            if (!ArifmeticAction.PLUS.isArifmeticsAction(split[1])) {
                throw  new Exception("throws Exception //т.к. введен недопустимый операнд");
            }

            if (split[1].equals("+")) {
                result = numberValueFirst + numberValueSecond;
            } else if (split[1].equals("-")) {
                result = numberValueFirst - numberValueSecond;
            } else if (split[1].equals("*")) {

                result = numberValueFirst * numberValueSecond;
            } else if (split[1].equals("/")) {
                result = numberValueFirst / numberValueSecond;
            }

        }


        return String.valueOf(result);

    }

    public static String countRomeResult(int result) {

        String finalResult = "";


        if (result <= 10) {
            finalResult = RomeNum.NONE.getRomeNumber(result);
        } else if (result < 50) {
            int countTen = result / 10;
            for (int q = 0; q < countTen; q++) {
                finalResult += "X";
            }
            int secondNumber = result - (countTen * 10);
            finalResult = finalResult + "" + RomeNum.NONE.getRomeNumber(secondNumber);
            return finalResult;
        } else if (result == 50) {
            finalResult = "L";
        } else if (result < 100) {
            int countFive = result - 50;
            int countTen = countFive / 10;
            finalResult = "L";
            for (int q = 0; q < countTen; q++) {
                finalResult += "X";
            }

            int secondNumber = result - (50+countTen*10);
            finalResult = finalResult + "" + RomeNum.NONE.getRomeNumber(secondNumber);
        } else if (result == 100) {
            finalResult = "C";
        }

        return finalResult;
    }

    enum RomeNum {
        NONE("0"),
        I("1"),
        II("2"),
        III("3"),
        IV("4"),
        V("5"),
        VI("6"),
        VII("7"),
        VIII("8"),
        IX("9"),
        X("10");

        private String value;

        RomeNum(String v) {
            value = v;
        }

        public boolean isRomeNumber(String input) {
            for (RomeNum num : values()) {
                if (input.equals(num.name())) {
                    return true;
                }
            }
            return false;
        }

        int getArabicNumber(String romeNumber) {
            for (RomeNum num : values()) {
                if (romeNumber.equals(num.name())) {
                    return Integer.valueOf(num.value);
                }
            }
            return -1;
        }

        String getRomeNumber(int romeNumber) {
            for (RomeNum num : values()) {

                if (romeNumber != 0 && Integer.valueOf((num.value)) == romeNumber) {
                    return num.name();
                }
            }
            return "";
        }
    }

    enum ArifmeticAction {
        PLUS("+"),
        MINUS("-"),
        MULTY("*"),
        DOUBLE("/");

        private String value;

        ArifmeticAction(String v) {
            value = v;
        }

        public boolean isArifmeticsAction(String input) {
            for (ArifmeticAction num : values()) {
                if (input.equals(num.value)) {
                    return true;
                }
            }
            return false;
        }

    }
}

