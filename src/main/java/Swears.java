public class Swears {
    public String swear(String mes) {
        //int sub6 = mes.toLowerCase().indexOf("fuck");
        int sub8 = mes.toLowerCase().indexOf("pussy");
        int sub12 = mes.toLowerCase().indexOf("cum");
        int sub13 = mes.toLowerCase().indexOf("penis");
        int sub14 = mes.toLowerCase().indexOf("vagina");
        int sub15 = mes.toLowerCase().indexOf("niga");
        int sub16 = mes.toLowerCase().indexOf("nigga");
        int sub17 = mes.toLowerCase().indexOf("gay");
        int sub18 = mes.toLowerCase().indexOf("lesbian");

        if (sub8 != -1) {
            return "No Swearing!";
        }

        else if (sub17 != -1) {
            return "Use the word 'homosexual' instead.";
        }

        else if (sub18 != -1) {
            return "Use the word 'homosexual' instead.";
        }

        /*else if (sub6 != -1) {
            return "No Swearing!";
        }*/

        else if (sub12 != -1) {
            if (sub12 == 0) {
                return "No Swearing!";
            }

            if (mes.substring(sub12 - 1, sub12).equals(" ")) {
                return "No Swearing!";
            }
        }

        else if (sub13 != -1) {
            return "No Swearing!";
        }

        else if (sub14 != -1) {
            return "No Swearing!";
        }

        else if (sub15 != -1) {
            return "No Swearing!";
        }

        else if (sub16 != -1) {
            return "No Swearing!";
        }

        return "";
    }
}
