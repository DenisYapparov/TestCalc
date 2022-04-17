import java.io.Console;
import java.io.DataInputStream;
import java.io.IOException;

public class Main {
    public static void main (String... args){
        {
            DataInputStream in = new DataInputStream(System.in);


            try {
                System.out.println(calc(in.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String calc(String input){

        int result = 0;
        String[] split = input.split(" ");
        int numberValueFirst;
        int numberValueSecond;
        if (RomeNum.NONE.isRomeNumber(split[0])&RomeNum.NONE.isRomeNumber(split[2])){
            numberValueFirst = RomeNum.NONE.getArabicNumber(split[0]);
            numberValueSecond = RomeNum.NONE.getArabicNumber(split[2]);
            if(split[1].equals("+")){
                result = numberValueFirst+numberValueSecond;
            }
            else if (split[1].equals("-")){
                result = numberValueFirst-numberValueSecond;
            }
            else if (split[1].equals("*")){
                result = numberValueFirst*numberValueSecond;
            }
            else if (split[1].equals("/")){
                result = numberValueFirst/numberValueSecond;
            }
            return countRomeResult(result);
        }
        else if (!RomeNum.NONE.isRomeNumber(split[0])&!RomeNum.NONE.isRomeNumber(split[2])){
            numberValueFirst = Integer.valueOf(split[0]);
            numberValueSecond = Integer.valueOf(split[2]);

            if(split[1].equals("+")){
                result = numberValueFirst+numberValueSecond;
            }
            else if (split[1].equals("-")){
                result = numberValueFirst-numberValueSecond;
            }
            else if (split[1].equals("*")){

                result = numberValueFirst*numberValueSecond;
            }
            else if (split[1].equals("/")){
                result = numberValueFirst/numberValueSecond;
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
        }
        else if (result==50){
            finalResult = "L";
        }
        else if (result < 100) {
            int countTen = result / 10;
            finalResult = "L";
            for (int q = 0; q < countTen; q++) {
                finalResult += "X";

            }

            int secondNumber = result - (countTen * 10);
            finalResult = finalResult + "" + secondNumber;
        }
        else if (result==100){
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
                return 0;
            }
        String getRomeNumber(int romeNumber) {
            for (RomeNum num : values()) {

                if (romeNumber!=0&&Integer.valueOf((num.value))==romeNumber) {
                    return num.name();
                }
            }
            return "";
        }
    }
//    enum ArifmeticAction {
//        PLUS("+"),
//        MULTIPLY("*"),
//        MINUS("-"),
//        DIVIDE("/");
//
//        private String value;
//
//        ArifmeticAction(String a) {
//            value = a;
//        }
//        String getArifmeticAction (String input ) {
//            for (ArifmeticAction action : values()) {
//                if (input.equals(action.value)){
//                    return action.value;
//                }
//            }
//            return "";
//        }
//    }
}
